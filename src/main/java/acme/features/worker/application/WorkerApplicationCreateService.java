
package acme.features.worker.application;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class WorkerApplicationCreateService implements AbstractCreateService<Worker, Application> {

	@Autowired
	WorkerApplicationRepository repository;


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
		if (entity.getJob().getBisit() != null) {
			model.setAttribute("hasBisit", true);
			if (request.isMethod(HttpMethod.GET)) {
				model.setAttribute("tracer", "");
				model.setAttribute("passwordProtected", false);
				model.setAttribute("password", "");
			} else {
				request.transfer(model, "tracer", "passwordProtected", "password");
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
		errors.state(request, this.checkReference(entity), "reference", "worker.application.error.reference");
		if (entity.getJob().getBisit() != null) {
			request.getModel().setAttribute("hasBisit", true);
			String tracer = request.getModel().getString("tracer");
			Boolean passwordProtected = request.getModel().getBoolean("passwordProtected");
			String password = request.getModel().getString("password");
			if (!tracer.equals("") || passwordProtected || !password.equals("")) {
				errors.state(request, !tracer.equals(""), "tracer", "worker.application.error.tracer");
				if (passwordProtected) {
					errors.state(request, this.checkPassword(password), "password", "worker.application.error.password");
				}
				if (!password.equals("")) {
					errors.state(request, passwordProtected, "passwordProtected", "worker.application.error.passwordProtected");
					errors.state(request, this.checkPassword(password), "password", "worker.application.error.password");
				}
			}
		}
	}

	private boolean checkReference(final Application app) {
		Collection<Application> all = this.repository.findAllApplications();
		for (Application a : all) {
			if (app.getReference().equals(a.getReference())) {
				return false;
			}
		}
		return true;
	}

	private boolean checkPassword(final String password) {
		if (password.length() < 10) {
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
		return letters >= 1 && digits >= 1 && symbols >= 1;
	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;
		if (entity.getJob().getBisit() != null) {
			String tracer = request.getModel().getString("tracer");
			Boolean passwordProtected = request.getModel().getBoolean("passwordProtected");
			String password = request.getModel().getString("password");
			if (!tracer.equals("") || passwordProtected || !password.equals("")) {
				entity.setTracer(tracer);
				entity.setPasswordProtected(passwordProtected);
				if (passwordProtected) {
					entity.setPassword(password);
				} else {
					entity.setPassword(null);
				}
			} else {
				entity.setTracer(null);
				entity.setPasswordProtected(null);
				entity.setPassword(null);
			}
		}
		this.repository.save(entity);
	}

}
