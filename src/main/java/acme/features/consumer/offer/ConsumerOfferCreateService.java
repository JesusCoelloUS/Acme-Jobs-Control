package acme.features.consumer.offer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offers.Offer;
import acme.entities.roles.Consumer;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ConsumerOfferCreateService implements AbstractCreateService<Consumer, Offer> {

	@Autowired
	ConsumerOfferRepository repository;


	@Override
	public boolean authorise(final Request<Offer> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "creationMoment");
	}

	@Override
	public void unbind(final Request<Offer> request, final Offer entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "ticker", "title", "deadline", "text", "minMoney", "maxMoney");
		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("checked", "false");
		} else {
			request.transfer(model, "checked");
		}
	}

	@Override
	public Offer instantiate(final Request<Offer> request) {
		assert request != null;
		return new Offer();
	}

	@Override
	public void validate(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Boolean isChecked = request.getModel().getBoolean("checked");
		errors.state(request, isChecked, "checked", "consumer.offer.error.must-check");
	}

	@Override
	public void create(final Request<Offer> request, final Offer entity) {
		assert request != null;
		assert entity != null;
		entity.setCreationMoment(new Date(System.currentTimeMillis() - 1));
		this.repository.save(entity);
	}

}
