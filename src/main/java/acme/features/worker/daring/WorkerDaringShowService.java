
package acme.features.worker.daring;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.darings.Daring;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.features.worker.job.WorkerJobRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class WorkerDaringShowService implements AbstractShowService<Worker, Daring> {

	@Autowired
	WorkerDaringRepository	repository;
	@Autowired
	WorkerJobRepository		jobRepository;


	@Override
	public boolean authorise(final Request<Daring> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Daring> request, final Daring entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "text", "moreInfo");
	}

	@Override
	public Daring findOne(final Request<Daring> request) {
		assert request != null;
		Daring res = this.repository.findOneDaringById(request.getModel().getInteger("id"));
		Collection<Job> activeJobs = this.jobRepository.findActiveJobs();
		boolean available = false;
		for (Job j : activeJobs) {
			if (j.getDaring() != null && j.getDaring().equals(res)) {
				available = true;
				break;
			}
		}
		assert available;
		return res;
	}

}
