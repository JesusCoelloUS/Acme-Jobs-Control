
package acme.entities.configurations;

import javax.persistence.Entity;
import javax.validation.constraints.Min;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Configuration extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	private String				spamWords;

	@Min(0)
	private Integer				threshold;
}
