
package acme.features.employer.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerApplicationUpdateService implements AbstractUpdateService<Employer, Application> {

	@Autowired
	EmployerApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "reference", "status", "rejectDecision", "statement", "creationMoment", "skills", "qualifications");
	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;
		return this.repository.findOneApplicationById(request.getModel().getInteger("id"));
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		errors.state(request, this.checkRejectDecision(entity), "rejectDecision", "employer.application.error.rejectDecision");
		errors.state(request, this.checkOtherDecision(entity), "rejectDecision", "employer.application.error.otherDecision");
	}

	private boolean checkRejectDecision(final Application a) {
		if (a.getStatus().equals("REJECTED")) {
			return !a.getRejectDecision().equals("");
		}
		return true;
	}

	private boolean checkOtherDecision(final Application a) {
		if (!a.getStatus().equals("REJECTED")) {
			return a.getRejectDecision().equals("");
		}
		return true;
	}

	@Override
	public void update(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}

}
