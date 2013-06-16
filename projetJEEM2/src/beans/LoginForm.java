package beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIInput;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Named
@RequestScoped
public class LoginForm {
  private String login;
  private String password;
  
  private UIInput UiLogin;
 

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
	public UIInput getUiLogin() {
		return UiLogin;
	}
	public void setUiLogin(UIInput uiLogin) {
		UiLogin = uiLogin;
	}
  
  
}