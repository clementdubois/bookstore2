/**
 * 
 */
package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * @author dga
 *
 */
@Entity()
@NamedQueries({
@NamedQuery(name = "Client.findAll", query = "Select c From Client c"),
@NamedQuery(name = "Client.findLikeOnLogin", query = "Select c From Client c where c.login like :like"),
@NamedQuery(name = "Client.findByLoginAndPassword", query = "Select c From Client c where c.login = :login and c.password = :password")
})
public class Client extends Persistent {
  private String login;
  private String password;

  private String firstName;
  private String lastName;
  private String avatar;
  
  private List<Order> commandes = new ArrayList<Order>();
  
  public boolean equals(Object other){
    if(other != null && other instanceof Client)
        return getLogin().equals(((Client)other).getLogin());
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

  @OneToMany(mappedBy="client",cascade ={CascadeType.PERSIST}) //CascadeType.REMOVE
  public List<Order> getCommandes() {
    return commandes;
  }
  
  public void setCommandes(List<Order> commandes) {
    this.commandes = commandes;
  }
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getAvatar() {
	return avatar;
}
public void setAvatar(String avatar) {
	this.avatar = avatar;
}



}
