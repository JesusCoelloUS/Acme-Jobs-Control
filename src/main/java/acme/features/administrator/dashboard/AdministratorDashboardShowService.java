
package acme.features.administrator.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	@Autowired
	AdministratorDashboardRepository repository;


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "ratioOfJobsThatHaveABisit", "ratioOfBisitsThatHaveATracer", "ratioOfApplicationsThatHaveAPasswordProtectedTracer");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;
		Dashboard res = new Dashboard();
		res.setRatioOfJobsThatHaveABisit(this.repository.findRatioOfJobsThatHaveABisit());
		res.setRatioOfBisitsThatHaveATracer(this.repository.findRatioOfBisitsThatHaveATracer());
		res.setRatioOfApplicationsThatHaveAPasswordProtectedTracer(this.repository.findRatioOfApplicationsThatHaveAPasswordProtectedTracer());
		return res;
	}

}
