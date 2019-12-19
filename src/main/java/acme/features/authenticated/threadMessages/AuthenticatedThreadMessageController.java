
package acme.features.authenticated.threadMessages;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.threadMessages.ThreadMessage;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/thread-message/")
public class AuthenticatedThreadMessageController extends AbstractController<Authenticated, ThreadMessage> {

	@Autowired
	private AuthenticatedThreadMessageListService	listService;
	@Autowired
	private AuthenticatedThreadMessageShowService	showService;
	@Autowired
	private AuthenticatedThreadMessageCreateService	createService;


	@PostConstruct
	private void initialize() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
