
package acme.features.employer.bisit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bisits.Bisit;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.features.employer.job.EmployerJobRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerBisitShowService implements AbstractShowService<Employer, Bisit> {

	@Autowired
	EmployerBisitRepository	repository;
	@Autowired
	EmployerJobRepository	jobRepository;


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
		Job j = this.repository.findOneJobByBisitId(entity.getId());
		model.setAttribute("canBeUpdatedOrDeleted", !j.getFinalMode());
	}

	@Override
	public Bisit findOne(final Request<Bisit> request) {
		assert request != null;
		Bisit res = this.repository.findOneBisitById(request.getModel().getInteger("id"));
		Collection<Job> myJobs = this.jobRepository.findMyJobs(request.getPrincipal().getActiveRoleId());
		boolean isMine = false;
		for (Job j : myJobs) {
			if (j.getBisit() != null && j.getBisit().equals(res)) {
				isMine = true;
				break;
			}
		}
		assert isMine;
		return res;
	}

}
