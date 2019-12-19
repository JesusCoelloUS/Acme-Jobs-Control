
package acme.features.worker.duty;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class WorkerDutyShowService implements AbstractShowService<Worker, Duty> {

	@Autowired
	WorkerDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;
		Principal principal = request.getPrincipal();
		Worker Worker = this.repository.findOneWorkerById(request.getPrincipal().getActiveRoleId());
		assert principal.getActiveRoleId() == Worker.getId();
		return true;
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "description", "percentage");
	}

	@Override
	public Duty findOne(final Request<Duty> request) {
		assert request != null;
		Duty res = this.repository.findOneDutyById(request.getModel().getInteger("id"));
		Collection<Job> activeJobs = this.repository.findActiveJobs();
		assert activeJobs.contains(res.getJob());
		return res;
	}

}
