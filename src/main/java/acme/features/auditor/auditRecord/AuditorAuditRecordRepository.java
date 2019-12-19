
package acme.features.auditor.auditRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Job;
import acme.entities.records.AuditRecord;
import acme.entities.roles.Auditor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorAuditRecordRepository extends AbstractRepository {

	@Query("select i from AuditRecord i where i.id=?1")
	AuditRecord findOneById(int id);

	@Query("select ar from AuditRecord ar where ar.auditor.id=?1")
	Collection<AuditRecord> findManyByAuditor(int id);

	@Query("select j from Job j where j.id=?1")
	Job findJobById(int id);

	@Query("select a from Auditor a where a.id=?1")
	Auditor findAuditorById(int id);

}
