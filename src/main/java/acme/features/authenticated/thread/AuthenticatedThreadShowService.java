
package acme.features.authenticated.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.threads.Thread;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedThreadShowService implements AbstractShowService<Authenticated, Thread> {

	@Autowired
	AuthenticatedThreadRepository repository;


	@Override
	public boolean authorise(final Request<Thread> request) {
		assert request != null;
		Principal principal = request.getPrincipal();
		Authenticated authenticated = this.repository.findOneAuthenticatedById(request.getPrincipal().getActiveRoleId());
		assert principal.getActiveRoleId() == authenticated.getId();
		return true;
	}

	@Override
	public void unbind(final Request<Thread> request, final Thread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "moment", "id");
	}

	@Override
	public Thread findOne(final Request<Thread> request) {
		assert request != null;
		return this.repository.findOneById(request.getModel().getInteger("id"));
	}

}
