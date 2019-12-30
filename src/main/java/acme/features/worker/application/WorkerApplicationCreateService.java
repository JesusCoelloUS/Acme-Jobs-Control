
package acme.features.worker.application;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.answers.Answer;
import acme.entities.applications.Application;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.features.worker.answer.WorkerAnswerRepository;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class WorkerApplicationCreateService implements AbstractCreateService<Worker, Application> {

	@Autowired
	WorkerApplicationRepository	repository;
	@Autowired
	WorkerAnswerRepository		answerRepository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "status", "creationMoment", "rejectDecision");
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "reference", "statement", "skills", "qualifications");
		model.setAttribute("id", entity.getJob().getId());
		if (entity.getJob().getDaring() != null) {
			model.setAttribute("hasDaring", true);
			if (request.isMethod(HttpMethod.GET)) {
				model.setAttribute("answerText", "");
				model.setAttribute("answerPasswordProtected", false);
				model.setAttribute("answerPassword", "");
			} else {
				request.transfer(model, "answerText", "answerPasswordProtected", "answerPassword");
			}
		}
	}

	@Override
	public Application instantiate(final Request<Application> request) {
		assert request != null;
		Application res = new Application();
		Worker w = this.repository.findOneWorkerById(request.getPrincipal().getActiveRoleId());
		Job j = this.repository.findOneJobById(request.getModel().getInteger("jobId"));
		res.setWorker(w);
		res.setJob(j);
		res.setStatus("PENDING");
		res.setCreationMoment(new Date(System.currentTimeMillis() - 1));
		res.setRejectDecision("");
		return res;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if (entity.getJob().getDaring() != null) {
			request.getModel().setAttribute("hasDaring", true);
			String text = request.getModel().getString("answerText");
			errors.state(request, !text.equals(""), "answerText", "worker.application.error.text");
			Boolean passwordProtected = request.getModel().getBoolean("answerPasswordProtected");
			if (passwordProtected) {
				String password = request.getModel().getString("answerPassword");
				errors.state(request, this.checkPassword(password), "answerPassword", "worker.application.error.password");
			}
		}
	}

	private boolean checkPassword(final String password) {
		if (password.length() < 8) {
			return false;
		}
		int letters = 0;
		int digits = 0;
		int symbols = 0;
		for (int i = 0; i < password.length(); i++) {
			if (Character.isLetter(password.charAt(i))) {
				letters++;
			} else if (Character.isDigit(password.charAt(i))) {
				digits++;
			} else {
				symbols++;
			}
		}
		return letters >= 3 && digits >= 3 && symbols >= 2;
	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;
		if (entity.getJob().getDaring() != null) {
			String answerText = request.getModel().getString("answerText");
			Boolean answerPasswordProtected = request.getModel().getBoolean("answerPasswordProtected");
			String answerPassword = request.getModel().getString("answerPassword");
			Answer answer = new Answer();
			answer.setText(answerText);
			answer.setPasswordProtected(answerPasswordProtected);
			answer.setPassword(answerPassword);
			Answer saved = this.answerRepository.save(answer);
			entity.setAnswer(saved);
		}
		this.repository.save(entity);
	}

}
