
package acme.features.authenticated.threadMessages;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.threadMessages.ThreadMessage;
import acme.entities.threads.Thread;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedThreadMessageCreateService implements AbstractCreateService<Authenticated, ThreadMessage> {

	@Autowired
	AuthenticatedThreadMessageRepository repository;


	@Override
	public boolean authorise(final Request<ThreadMessage> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<ThreadMessage> request, final ThreadMessage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "creationMoment");
	}

	@Override
	public void unbind(final Request<ThreadMessage> request, final ThreadMessage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "tags", "body");
		model.setAttribute("threadId", entity.getThread().getId());
	}

	@Override
	public ThreadMessage instantiate(final Request<ThreadMessage> request) {
		assert request != null;
		ThreadMessage res = new ThreadMessage();
		Thread t = this.repository.findOneThreadById(request.getModel().getInteger("threadId"));
		res.setThread(t);
		res.setCreationMoment(new Date(System.currentTimeMillis() - 1));
		return res;
	}

	@Override
	public void validate(final Request<ThreadMessage> request, final ThreadMessage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<ThreadMessage> request, final ThreadMessage entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}

}
