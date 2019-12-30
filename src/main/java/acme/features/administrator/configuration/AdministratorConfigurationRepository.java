
package acme.features.administrator.configuration;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configurations.Configuration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorConfigurationRepository extends AbstractRepository {

	@Query("select c from Configuration c")
	Collection<Configuration> findMany();

	@Query("select c from Configuration c where c.id=?1")
	Configuration findOne(int id);

	@Query("select (select count(j1) from Job j1 where j1.daring !=NULL) / (count(j)*1.0) from Job j")
	Double findRatioOfJobsThatHaveADaring();

	@Query("select (select count(a1) from Application a1 where a1.answer !=NULL) / (count(a)*1.0) from Application a")
	Double findRationOfApplicationsThatHaveAnAnswer();

	@Query("select (select count(a1) from Application a1 where a1.answer !=NULL and a1.answer.passwordProtected = true) / (count(a)*1.0) from Application a")
	Double findRationOfApplicationsThatHaveAPasswordProtectedAnswer();

}
