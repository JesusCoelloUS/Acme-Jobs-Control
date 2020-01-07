
package acme.features.worker.bisit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bisits.Bisit;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.features.worker.job.WorkerJobRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class WorkerBisitShowService implements AbstractShowService<Worker, Bisit> {

	@Autowired
	WorkerBisitRepository	repository;
	@Autowired
	WorkerJobRepository		jobRepository;


	@Override
	public boolean authorise(final Request<Bisit> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Bisit> request, final Bisit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "text", "tracer");
	}

	@Override
	public Bisit findOne(final Request<Bisit> request) {
		assert request != null;
		Bisit res = this.repository.findOneBisitById(request.getModel().getInteger("id"));
		Collection<Job> activeJobs = this.jobRepository.findActiveJobs();
		boolean available = false;
		for (Job j : activeJobs) {
			if (j.getBisit() != null && j.getBisit().equals(res)) {
				available = true;
				break;
			}
		}
		assert available;
		return res;
	}

}
