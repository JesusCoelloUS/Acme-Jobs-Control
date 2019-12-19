
package acme.features.administrator.companyRecord;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.records.CompanyRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorCompanyRecordRepository extends AbstractRepository {

	@Query("select a from CompanyRecord a where a.id=?1")
	CompanyRecord findOneById(int id);

}
