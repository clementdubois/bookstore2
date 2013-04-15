package ejb;

import java.util.ResourceBundle;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;

import entities.Client;
@Stateless
@Local(ClientService.class)
public class ClientServiceEJB extends GenericCRUDServiceEJB<Client> implements ClientService{
 
  public Client login(String login, String password) {
    Client client = null;
    try {
      client = (Client) em.createQuery("select c from Client c where c.login=:login and c.password=:password")
          .setParameter("login", login).setParameter("password", password).getSingleResult();
    } catch (NoResultException e) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ResourceBundle bundle = (ResourceBundle) facesContext.getApplication()
            .getResourceBundle(facesContext, "messages");
        String summary = bundle.getString("clientNotFound");
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, summary);
        facesContext.addMessage(null, msg);
        return null;
    }
    return client;
  }
}