
package acme.features.employer.daring;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.darings.Daring;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.features.employer.job.EmployerJobRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerDaringShowService implements AbstractShowService<Employer, Daring> {

	@Autowired
	EmployerDaringRepository	repository;
	@Autowired
	EmployerJobRepository		jobRepository;


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
		Job j = this.repository.findOneJobByDaringId(entity.getId());
		model.setAttribute("canBeUpdatedOrDeleted", !j.getFinalMode());
	}

	@Override
	public Daring findOne(final Request<Daring> request) {
		assert request != null;
		Daring res = this.repository.findOneDaringById(request.getModel().getInteger("id"));
		Collection<Job> myJobs = this.jobRepository.findMyJobs(request.getPrincipal().getActiveRoleId());
		boolean isMine = false;
		for (Job j : myJobs) {
			if (j.getDaring() != null && j.getDaring().equals(res)) {
				isMine = true;
				break;
			}
		}
		assert isMine;
		return res;
	}

}
