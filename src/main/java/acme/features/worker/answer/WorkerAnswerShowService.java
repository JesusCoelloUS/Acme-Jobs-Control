
package acme.features.worker.answer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.answers.Answer;
import acme.entities.applications.Application;
import acme.entities.roles.Worker;
import acme.features.worker.application.WorkerApplicationRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class WorkerAnswerShowService implements AbstractShowService<Worker, Answer> {

	@Autowired
	WorkerAnswerRepository		repository;
	@Autowired
	WorkerApplicationRepository	appRepository;


	@Override
	public boolean authorise(final Request<Answer> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Answer> request, final Answer entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "text", "passwordProtected", "password");
	}

	@Override
	public Answer findOne(final Request<Answer> request) {
		assert request != null;
		Answer res = this.repository.findOneAnswerById(request.getModel().getInteger("id"));
		Collection<Application> myApps = this.appRepository.findManyByWorkerId(request.getPrincipal().getActiveRoleId());
		boolean isMine = false;
		for (Application a : myApps) {
			if (a.getAnswer() != null && a.getAnswer().equals(res)) {
				isMine = true;
				break;
			}
		}
		assert isMine;
		return res;
	}

}
