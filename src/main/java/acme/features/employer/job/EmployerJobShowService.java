
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerJobShowService implements AbstractShowService<Employer, Job> {

	@Autowired
	EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		Principal principal = request.getPrincipal();
		Employer employer = this.repository.findOneEmployerById(request.getPrincipal().getActiveRoleId());
		assert principal.getActiveRoleId() == employer.getId();

		return true;
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "id", "reference", "title", "status", "deadline", "moreInfo", "salary", "description", "finalMode");
		Collection<Application> apps = this.repository.findApplicationsFromJob(entity.getId());
		if (apps.isEmpty()) {
			model.setAttribute("canBeDeleted", true);
		}
		if (!entity.getFinalMode()) {
			model.setAttribute("canBeUpdated", true);
		}
		if (entity.getBisit() != null) {
			model.setAttribute("hasABisit", true);
			model.setAttribute("bisitId", entity.getBisit().getId());
		}
	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;
		Job res = this.repository.findOneJobById(request.getModel().getInteger("id"));
		Collection<Job> myJobs = this.repository.findMyJobs(request.getPrincipal().getActiveRoleId());
		assert myJobs.contains(res);
		return res;
	}

}
