
package acme.entities.answers;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Answer extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				text;

	private Boolean				passwordProtected;

	@Length(min = 8)
	//	@Pattern(
	//		regexp = "^[a-zA-zñÑçÇ]{3,}[0-9]{3,}[^\\wñÑçÇ]{2,}|[a-zA-zñÑçÇ]{3,}[^\\wñÑçÇ]{2,}[0-9]{3,}|[0-9]{3,}[a-zA-zñÑçÇ]{3,}[^\\wñÑçÇ]{2,}|[0-9]{3,}[^\\wñÑçÇ]{2,}[a-zA-zñÑçÇ]{3,}|[^\\wñÑçÇ]{2,}[a-zA-zñÑçÇ]{3,}[0-9]{3,}|[^\\wñÑçÇ]{2,}[0-9]{3,}[a-zA-zñÑçÇ]{3,}$")
	private String				password;
}
