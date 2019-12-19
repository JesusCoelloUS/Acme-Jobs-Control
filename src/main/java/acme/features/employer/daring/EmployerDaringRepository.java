
package acme.features.employer.daring;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.darings.Daring;
import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerDaringRepository extends AbstractRepository {

	@Query("select d from Daring d where d.id=?1")
	Daring findOneDaringById(int id);

	@Query("select j from Job j where j.id=?1")
	Job findOneJobById(int id);

	@Query("select j from Job j where j.daring.id=?1")
	Job findOneJobByDaringId(int id);

}
