
package acme.features.auditor.auditRecord;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.records.AuditRecord;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class AuditorAuditRecordCreateService implements AbstractCreateService<Auditor, AuditRecord> {

	@Autowired
	AuditorAuditRecordRepository repository;


	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "creationMoment");
	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "status", "body", "finalMode");
		model.setAttribute("jobId", entity.getJob().getId());
	}

	@Override
	public AuditRecord instantiate(final Request<AuditRecord> request) {
		assert request != null;
		AuditRecord res = new AuditRecord();
		Job j = this.repository.findJobById(request.getModel().getInteger("jobId"));
		Auditor a = this.repository.findAuditorById(request.getPrincipal().getActiveRoleId());
		res.setAuditor(a);
		res.setJob(j);
		res.setCreationMoment(new Date(System.currentTimeMillis() - 1));
		return res;
	}

	@Override
	public void validate(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		errors.state(request, this.checkStatus(entity), "status", "auditor.audit-record.error.status");
	}

	private boolean checkStatus(final AuditRecord ar) {
		if (ar.getStatus().equals("PUBLISHED")) {
			return ar.getFinalMode();
		} else {
			return !ar.getFinalMode();
		}
	}

	@Override
	public void create(final Request<AuditRecord> request, final AuditRecord entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}

}
