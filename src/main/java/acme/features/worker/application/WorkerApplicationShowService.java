
package acme.features.worker.application;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.roles.Worker;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class WorkerApplicationShowService implements AbstractShowService<Worker, Application> {

	@Autowired
	WorkerApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean result;
		Worker worker;
		Principal principal;
		worker = this.repository.findOneWorkerById(request.getPrincipal().getActiveRoleId());
		principal = request.getPrincipal();
		result = worker.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "reference", "status", "statement", "creationMoment", "skills", "qualifications", "rejectDecision", "job.id");
		model.setAttribute("jobId", entity.getJob().getId());
	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");
		Application result = this.repository.findOneApplicationById(id);
		Collection<Application> myApps = this.repository.findManyByWorkerId(request.getPrincipal().getActiveRoleId());
		assert myApps.contains(result);
		return result;
	}

}
