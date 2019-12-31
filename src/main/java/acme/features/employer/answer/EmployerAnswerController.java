
package acme.features.employer.answer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.answers.Answer;
import acme.entities.roles.Employer;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/employer/answer/")
public class EmployerAnswerController extends AbstractController<Employer, Answer> {

	@Autowired
	private EmployerAnswerShowService showService;


	@PostConstruct
	private void initialize() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
