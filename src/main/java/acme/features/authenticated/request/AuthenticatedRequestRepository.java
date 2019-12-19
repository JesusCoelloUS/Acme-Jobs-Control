
package acme.features.authenticated.request;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.requests.Request;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedRequestRepository extends AbstractRepository {

	@Query("select r from Request r where r.id=?1")
	Request findOneRequestById(int id);

	@Query("select r from Request r where r.creationMoment < current_date and r.deadline > current_date")
	Collection<Request> findManyRequest();

}
