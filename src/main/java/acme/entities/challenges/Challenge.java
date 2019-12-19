
package acme.entities.challenges;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Challenge extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				title;

	@Future
	@Temporal(TemporalType.TIMESTAMP)
	private Date				deadline;

	@NotBlank
	private String				description;

	@Valid
	private Integer				goldReward;

	@Valid
	private Integer				silverReward;

	@Valid
	private Integer				bronzeReward;

	@NotBlank
	private String				goldGoal;

	@NotBlank
	private String				silverGoal;

	@NotBlank
	private String				bronzeGoal;
}
