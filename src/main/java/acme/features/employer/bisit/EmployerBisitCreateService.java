
package acme.features.employer.bisit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bisits.Bisit;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerBisitCreateService implements AbstractCreateService<Employer, Bisit> {

	@Autowired
	EmployerBisitRepository repository;


	@Override
	public boolean authorise(final Request<Bisit> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Bisit> request, final Bisit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Bisit> request, final Bisit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "text", "tracer");
		model.setAttribute("id", request.getModel().getInteger("id"));
	}

	@Override
	public Bisit instantiate(final Request<Bisit> request) {
		assert request != null;
		Bisit res = new Bisit();
		return res;
	}

	@Override
	public void validate(final Request<Bisit> request, final Bisit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<Bisit> request, final Bisit entity) {
		assert request != null;
		assert entity != null;
		if (entity.getTracer().equals("")) {
			entity.setTracer(null);
		}
		Bisit saved = this.repository.save(entity);
		Job j = this.repository.findOneJobById(request.getModel().getInteger("id"));
		j.setBisit(saved);
	}

}
