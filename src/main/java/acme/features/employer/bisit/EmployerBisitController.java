
package acme.features.employer.bisit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.bisits.Bisit;
import acme.entities.roles.Employer;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/employer/bisit/")
public class EmployerBisitController extends AbstractController<Employer, Bisit> {

	@Autowired
	private EmployerBisitShowService	showService;
	@Autowired
	private EmployerBisitCreateService	createService;
	@Autowired
	private EmployerBisitUpdateService	updateService;
	@Autowired
	private EmployerBisitDeleteService	deleteService;


	@PostConstruct
	private void initialize() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}
