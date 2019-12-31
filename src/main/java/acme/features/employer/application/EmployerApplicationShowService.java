
package acme.features.employer.application;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerApplicationShowService implements AbstractShowService<Employer, Application> {

	@Autowired
	EmployerApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean result;
		Employer employer;
		Principal principal;
		employer = this.repository.findOneEmployerById(request.getPrincipal().getActiveRoleId());
		principal = request.getPrincipal();
		result = employer.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "reference", "status", "rejectDecision", "statement", "creationMoment", "skills", "qualifications");
		model.setAttribute("jobId", entity.getJob().getId());
		if (entity.getAnswer() != null) {
			model.setAttribute("hasAnswer", true);
			model.setAttribute("answerId", entity.getAnswer().getId());
		}
	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;

		Application result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneApplicationById(id);
		Collection<Application> apps = this.repository.findApplicationsMadeToMyJobs(request.getPrincipal().getActiveRoleId());
		assert apps.contains(result);
		return result;
	}

}
