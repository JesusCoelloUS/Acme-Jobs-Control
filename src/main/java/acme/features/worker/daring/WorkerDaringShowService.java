
package acme.features.worker.daring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.darings.Daring;
import acme.entities.roles.Worker;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class WorkerDaringShowService implements AbstractShowService<Worker, Daring> {

	@Autowired
	WorkerDaringRepository repository;


	@Override
	public boolean authorise(final Request<Daring> request) {
		assert request != null;
		return true;
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

}