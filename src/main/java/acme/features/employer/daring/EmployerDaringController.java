
package acme.features.employer.daring;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.darings.Daring;
import acme.entities.roles.Employer;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/employer/daring/")
public class EmployerDaringController extends AbstractController<Employer, Daring> {

	@Autowired
	private EmployerDaringShowService	showService;
	@Autowired
	private EmployerDaringCreateService	createService;
	@Autowired
	private EmployerDaringUpdateService	updateService;
	@Autowired
	private EmployerDaringDeleteService	deleteService;


	@PostConstruct
	private void initialize() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}
