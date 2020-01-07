
package acme.features.authenticated.bisit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bisits.Bisit;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedBisitShowService implements AbstractShowService<Authenticated, Bisit> {

	@Autowired
	AuthenticatedBisitRepository repository;


	@Override
	public boolean authorise(final Request<Bisit> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Bisit> request, final Bisit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "text", "tracer");
	}

	@Override
	public Bisit findOne(final Request<Bisit> request) {
		assert request != null;
		Bisit res = this.repository.findOneBisitById(request.getModel().getInteger("id"));
		return res;
	}

}
