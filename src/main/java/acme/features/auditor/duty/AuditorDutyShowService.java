
package acme.features.auditor.duty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuditorDutyShowService implements AbstractShowService<Auditor, Duty> {

	@Autowired
	AuditorDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;
		Principal principal = request.getPrincipal();
		Auditor Auditor = this.repository.findOneAuditorById(request.getPrincipal().getActiveRoleId());
		assert principal.getActiveRoleId() == Auditor.getId();
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
		return this.repository.findOneDutyById(request.getModel().getInteger("id"));
	}

}
