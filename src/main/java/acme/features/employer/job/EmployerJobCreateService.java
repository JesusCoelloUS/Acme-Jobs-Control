
package acme.features.employer.job;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configurations.Configuration;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.features.administrator.configuration.AdministratorConfigurationRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerJobCreateService implements AbstractCreateService<Employer, Job> {

	@Autowired
	EmployerJobRepository					repository;
	@Autowired
	AdministratorConfigurationRepository	configurationRepository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "reference", "status", "title", "deadline", "salary", "moreInfo", "description");
	}

	@Override
	public Job instantiate(final Request<Job> request) {
		assert request != null;
		Job res = new Job();
		Employer e = this.repository.findOneEmployerById(request.getPrincipal().getActiveRoleId());
		res.setEmployer(e);
		res.setFinalMode(false);
		res.setStatus("DRAFT");
		return res;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		errors.state(request, !this.isSpam(entity), "description", "employer.job.error.spam");
		errors.state(request, this.checkReference(entity), "reference", "employer.job.error.reference");
		errors.state(request, this.checkDeadline(entity), "deadline", "employer.job.error.deadline");
	}

	private boolean isSpam(final Job j) {
		if (j.getTitle().equals("") && j.getDescription().equals("")) {
			return false;
		}
		Collection<Configuration> cs = this.configurationRepository.findMany();
		Configuration c = null;
		for (Configuration co : cs) {
			c = co;
			break;
		}
		Integer sum = 0;

		if (!j.getTitle().equals("")) {
			String[] title = j.getTitle().split(",");
			for (String s : title) {
				if (c.getSpamWords().contains(s)) {
					sum++;
				}
			}
		}

		if (!j.getDescription().equals("")) {
			String[] description = j.getDescription().split(",");
			for (String s : description) {
				if (c.getSpamWords().contains(s)) {
					sum++;
				}
			}
		}
		return sum >= c.getThreshold();
	}

	private boolean checkReference(final Job job) {
		Collection<Job> all = this.repository.findAllJobs();
		for (Job j : all) {
			if (job.getReference().equals(j.getReference())) {
				return false;
			}
		}
		return true;
	}

	private boolean checkDeadline(final Job job) {
		if (job.getDeadline() == null) {
			return true;
		}
		Date today = new Date();
		int diff = (int) ((job.getDeadline().getTime() - today.getTime()) / (1000 * 60 * 60 * 24));
		return diff >= 7;
	}

	@Override
	public void create(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}

}
