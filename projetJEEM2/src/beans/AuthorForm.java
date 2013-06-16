package beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import entities.Book;

@Named
@RequestScoped
public class AuthorForm {
	private String firstName;
	private String lastName;
  
	@NotNull
	@Length(min = 2, max = 25)
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@NotNull
	@Length(min = 2, max = 25)
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}