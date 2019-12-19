
package acme.features.worker.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class WorkerJobShowService implements AbstractShowService<Worker, Job> {

	@Autowired
	WorkerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		Principal principal = request.getPrincipal();
		Worker w = this.repository.findOneWorkerById(request.getPrincipal().getActiveRoleId());
		assert principal.getActiveRoleId() == w.getId();

		return true;
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "id", "reference", "title", "status", "deadline", "moreInfo", "salary", "description");
		model.setAttribute("isActive", entity.isActive());
	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;
		Job res = this.repository.findOneJobById(request.getModel().getInteger("id"));
		Collection<Job> activeJobs = this.repository.findActiveJobs();
		assert activeJobs.contains(res);
		return res;
	}

}
