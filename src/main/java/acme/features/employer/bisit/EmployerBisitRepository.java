
package acme.features.employer.bisit;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bisits.Bisit;
import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerBisitRepository extends AbstractRepository {

	@Query("select d from Bisit d where d.id=?1")
	Bisit findOneBisitById(int id);

	@Query("select j from Job j where j.id=?1")
	Job findOneJobById(int id);

	@Query("select j from Job j where j.bisit.id=?1")
	Job findOneJobByBisitId(int id);

}
