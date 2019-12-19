
package acme.features.provider.request;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requests.Request;
import acme.entities.roles.Provider;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.services.AbstractCreateService;

@Service
public class ProviderRequestCreateService implements AbstractCreateService<Provider, Request> {

	@Autowired
	ProviderRequestRepository repository;


	@Override
	public boolean authorise(final acme.framework.components.Request<Request> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final acme.framework.components.Request<Request> request, final Request entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "checked", "creationMoment");
	}

	@Override
	public void unbind(final acme.framework.components.Request<Request> request, final Request entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "ticker", "title", "deadline", "text", "reward");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("checked", "false");
		} else {
			request.transfer(model, "checked");
		}
	}

	@Override
	public Request instantiate(final acme.framework.components.Request<Request> request) {
		assert request != null;
		Request res = new Request();
		return res;
	}

	@Override
	public void validate(final acme.framework.components.Request<Request> request, final Request entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Boolean isChecked = request.getModel().getBoolean("checked");
		errors.state(request, isChecked, "checked", "provider.request.error.must-check");
	}

	@Override
	public void create(final acme.framework.components.Request<Request> request, final Request entity) {
		assert request != null;
		assert entity != null;
		Date now = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(now);
		this.repository.save(entity);
	}

}
