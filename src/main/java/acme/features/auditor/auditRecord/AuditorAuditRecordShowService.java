
package acme.features.auditor.auditRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.records.AuditRecord;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class AuditorAuditRecordShowService implements AbstractShowService<Auditor, AuditRecord> {

	@Autowired
	AuditorAuditRecordRepository repository;


	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "status", "creationMoment", "body", "finalMode");
		if (!entity.getFinalMode()) {
			model.setAttribute("canBeUpdated", true);
		}

	}

	@Override
	public AuditRecord findOne(final Request<AuditRecord> request) {
		assert request != null;
		int id = request.getModel().getInteger("id");
		AuditRecord res = this.repository.findOneById(id);
		return res;
	}

}
