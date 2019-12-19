package acme.features.administrator.investorRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.records.InvestorRecord;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/investor-record/")
public class AdministratorInvestorRecordController extends AbstractController<Administrator, InvestorRecord> {

	@Autowired
	private AdministratorInvestorRecordCreateService	createService;
	@Autowired
	private AdministratorInvestorRecordUpdateService	updateService;
	@Autowired
	private AdministratorInvestorRecordDeleteService	deleteService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}