
package acme.entities.records;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CompanyRecord extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				company;

	@NotBlank
	private String				sector;

	@NotBlank
	private String				CEO;

	@NotBlank
	private String				description;

	@NotBlank
	@URL
	private String				website;

	@NotBlank
	@Pattern(regexp = "^[+][1-9]{1,3}[ ][(]([0-9]|(0|[1-9]{1,4}))[)][ ][0-9]{6,10}|[+][1-9]{1,3}[ ][0-9]{6,10}|[(]([0-9]|(0|[1-9]{1,4}))[)][ ][0-9]{6,10}|[0-9]{6,10}$")
	private String				phone;

	@NotBlank
	@Email
	private String				email;

	@Range(min = 0, max = 5)
	private Integer				stars;


	//-----------derivated atributes-------------

	@Transient
	public Boolean isIncorporated() {
		return this.company.contains("Inc");
	}

}
