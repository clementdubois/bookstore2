package beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIInput;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Named
@RequestScoped
public class InscriptionForm {
  private String login;
  private String password;
  private String passwordConfirmation;
  private String passwordCheck;
  private String firstName;
  private String lastName;
  private boolean changePassword;

  @NotNull
  @Length(min = 3, max = 25)
  public String getLogin() {
    return login;
  }
  public void setLogin(String login) {
    this.login = login;
  }
  public String getPassword() {
    return password;
  }
  
  @NotNull
  @Length(min = 3, max = 25)
  public void setPassword(String password) {
    this.password = password;
  }
  
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	public String getLastName() {
		return lastName;
	}
	
	@NotNull
	@Length(min = 3)
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	
	@NotNull
	@Length(min = 3)
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public boolean isChangePassword() {
		return changePassword;
	}
	public void setChangePassword(boolean changePassword) {
		this.changePassword = changePassword;
	}
	public String getPasswordCheck() {
		return passwordCheck;
	}
	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}
  
  
  
  
}