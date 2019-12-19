
package acme.features.authenticated.threadMessages;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.threadMessages.ThreadMessage;
import acme.entities.threads.Thread;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedThreadMessageRepository extends AbstractRepository {

	@Query("select tm from ThreadMessage tm where tm.thread.id=?1")
	Collection<ThreadMessage> findManyByThread(int id);

	@Query("select t from Thread t where t.id=?1")
	Thread findOneThreadById(int id);

	@Query("select tm from ThreadMessage tm where tm.id=?1")
	ThreadMessage findOneThreadMessageById(int id);

	@Query("select a from Authenticated a where a.id=?1")
	Authenticated findOneAuthenticatedById(int id);

}
