
package acme.entities.records;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class InvestorRecord extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				investor;

	@NotBlank
	private String				sector;

	@NotBlank
	private String				statement;

	@Range(min = 0, max = 5)
	private Integer				stars;
}
