
package acme.features.worker.application;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.components.Errors;
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
	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}

}
