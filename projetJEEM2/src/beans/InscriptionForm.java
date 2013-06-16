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
  
  
  
  
}