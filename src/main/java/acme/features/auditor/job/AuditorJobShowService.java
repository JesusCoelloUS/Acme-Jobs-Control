
package acme.features.auditor.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuditorJobShowService implements AbstractShowService<Auditor, Job> {

	@Autowired
	AuditorJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		Principal principal = request.getPrincipal();
		Auditor Auditor = this.repository.findOneAuditorById(request.getPrincipal().getActiveRoleId());
		assert principal.getActiveRoleId() == Auditor.getId();

		return true;
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "id", "reference", "title", "status", "deadline", "moreInfo", "salary", "description");
	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;
		return this.repository.findOneJobById(request.getModel().getInteger("id"));
	}

}
