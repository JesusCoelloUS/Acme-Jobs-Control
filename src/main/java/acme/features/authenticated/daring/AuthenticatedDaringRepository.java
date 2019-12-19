
package acme.features.authenticated.daring;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.darings.Daring;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedDaringRepository extends AbstractRepository {

	@Query("select d from Daring d where d.id=?1")
	Daring findOneDaringById(int id);

}
