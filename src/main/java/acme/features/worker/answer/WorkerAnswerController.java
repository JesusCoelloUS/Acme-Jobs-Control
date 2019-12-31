
package acme.features.worker.answer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.answers.Answer;
import acme.entities.roles.Worker;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/worker/answer/")
public class WorkerAnswerController extends AbstractController<Worker, Answer> {

	@Autowired
	private WorkerAnswerShowService showService;


	@PostConstruct
	private void initialize() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
