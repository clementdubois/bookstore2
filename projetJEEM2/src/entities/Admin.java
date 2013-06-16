/**
 * 
 */
package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author dga
 *
 */
@Entity()
@NamedQueries({
@NamedQuery(name = "Admin.findAll", query = "Select c From Client c"),
@NamedQuery(name = "Admin.findLikeOnLogin", query = "Select c From Client c where c.login like :like")
})
public class Admin extends Persistent {
  private String login;
  private String password;
  
  public boolean equals(Object other){
    if(other != null && other instanceof Admin)
        return getLogin().equals(((Admin)other).getLogin());
    return false;
  }
  public int hashCode(){
    return getLogin().hashCode();
  }
  
  @Column(nullable = false, unique=true)
  public String getLogin() {
    return login;
  }
  public void setLogin(String login) {
    this.login = login;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
}
