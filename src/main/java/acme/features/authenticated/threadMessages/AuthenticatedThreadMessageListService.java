
package acme.features.authenticated.threadMessages;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.threadMessages.ThreadMessage;
import acme.entities.threads.Thread;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedThreadMessageListService implements AbstractListService<Authenticated, ThreadMessage> {

	@Autowired
	AuthenticatedThreadMessageRepository repository;


	@Override
	public boolean authorise(final Request<ThreadMessage> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<ThreadMessage> request, final ThreadMessage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "creationMoment");
	}

	@Override
	public Collection<ThreadMessage> findMany(final Request<ThreadMessage> request) {
		assert request != null;
		Thread thread = this.repository.findOneThreadById(request.getModel().getInteger("id"));
		return this.repository.findManyByThread(thread.getId());
	}

}
