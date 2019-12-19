
package acme.features.worker.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface WorkerJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id=?1")
	Job findOneJobById(int id);

	@Query("select e from Worker e where e.id=?1")
	Worker findOneWorkerById(int id);

	@Query("select j from Job j where j.status = 'PUBLISHED' and j.deadline > CURRENT_DATE")
	Collection<Job> findActiveJobs();

}
