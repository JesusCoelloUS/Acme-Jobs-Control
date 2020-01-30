
package acme.entities.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	private static final long	serialVersionUID	= 1L;

	Double						ratioOfJobsThatHaveABisit;
	Double						ratioOfBisitsThatHaveATracer;
	Double						ratioOfApplicationsThatHaveAPasswordProtectedTracer;

}
