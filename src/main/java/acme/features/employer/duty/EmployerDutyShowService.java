
package acme.features.employer.duty;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerDutyShowService implements AbstractShowService<Employer, Duty> {

	@Autowired
	EmployerDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;
		Principal principal = request.getPrincipal();
		Employer employer = this.repository.findOneEmployerById(request.getPrincipal().getActiveRoleId());
		assert principal.getActiveRoleId() == employer.getId();
		return true;
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "description", "percentage");
		model.setAttribute("canBeUpdated", !entity.getJob().getFinalMode());
	}

	@Override
	public Duty findOne(final Request<Duty> request) {
		assert request != null;
		Duty res = this.repository.findOneDutyById(request.getModel().getInteger("id"));
		Collection<Job> myJobs = this.repository.findMyJobs(request.getPrincipal().getActiveRoleId());
		assert myJobs.contains(res.getJob());
		return res;
	}

}
