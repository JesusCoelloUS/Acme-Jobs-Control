
package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select (select count(j1) from Job j1 where j1.bisit !=NULL) / (count(j)*1.0) from Job j")
	Double findRatioOfJobsThatHaveABisit();

	@Query("select (select count(b1) from Bisit b1 where b1.tracer !=NULL) / (count(b)*1.0) from Bisit b")
	Double findRatioOfBisitsThatHaveATracer();

	@Query("select (select count(a1) from Application a1 where a1.passwordProtected = true) / (count(a)*1.0) from Application a")
	Double findRatioOfApplicationsThatHaveAPasswordProtectedTracer();

}
