
package acme.features.employer.daring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.darings.Daring;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class EmployerDaringDeleteService implements AbstractDeleteService<Employer, Daring> {

	@Autowired
	EmployerDaringRepository repository;


	@Override
	public boolean authorise(final Request<Daring> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Daring> request, final Daring entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Daring> request, final Daring entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "text", "moreInfo");
	}

	@Override
	public Daring findOne(final Request<Daring> request) {
		assert request != null;
		Daring res = this.repository.findOneDaringById(request.getModel().getInteger("id"));
		return res;
	}

	@Override
	public void validate(final Request<Daring> request, final Daring entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Daring> request, final Daring entity) {
		assert request != null;
		assert entity != null;
		Job j = this.repository.findOneJobByDaringId(entity.getId());
		j.setDaring(null);
		this.repository.delete(entity);
	}

}
