
package acme.features.authenticated.bisit;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bisits.Bisit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedBisitRepository extends AbstractRepository {

	@Query("select d from Bisit d where d.id=?1")
	Bisit findOneBisitById(int id);

}
