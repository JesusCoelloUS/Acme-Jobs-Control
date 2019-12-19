
package acme.features.authenticated.threadMessages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.threadMessages.ThreadMessage;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedThreadMessageShowService implements AbstractShowService<Authenticated, ThreadMessage> {

	@Autowired
	AuthenticatedThreadMessageRepository repository;


	@Override
	public boolean authorise(final Request<ThreadMessage> request) {
		assert request != null;
		Principal principal = request.getPrincipal();
		Authenticated authenticated = this.repository.findOneAuthenticatedById(request.getPrincipal().getActiveRoleId());
		assert principal.getActiveRoleId() == authenticated.getId();
		return true;
	}

	@Override
	public void unbind(final Request<ThreadMessage> request, final ThreadMessage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "creationMoment", "tags", "body");
	}

	@Override
	public ThreadMessage findOne(final Request<ThreadMessage> request) {
		assert request != null;
		return this.repository.findOneThreadMessageById(request.getModel().getInteger("id"));
	}

}
