
package acme.features.authenticated.thread;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.threads.Thread;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedThreadRepository extends AbstractRepository {

	@Query("select t from Thread t where (select a from Authenticated a where a.id=?1) member of t.users")
	Collection<Thread> findThreadsWhereIAmInvolved(int id);

	@Query("select t from Thread t where t.id=?1")
	Thread findOneById(int id);

	@Query("select a from Authenticated a where a.id=?1")
	Authenticated findOneAuthenticatedById(int id);
}
