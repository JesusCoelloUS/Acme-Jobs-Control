
package acme.features.authenticated.auditRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.records.AuditRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAuditRecordRepository extends AbstractRepository {

	@Query("select i from AuditRecord i where i.id=?1")
	AuditRecord findOneById(int id);

	@Query("select a from AuditRecord a where a.job.id=?1 and a.finalMode = true")
	Collection<AuditRecord> findManyByJob(int id);

}
