package ejb;

import java.util.ResourceBundle;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;

import entities.Admin;
@Stateless
@Local(AdminService.class)
public class AdminServiceEJB extends GenericCRUDServiceEJB<Admin> implements AdminService{
 
  public Admin login(String login, String password) {
    Admin admin = null;
    try {
      admin = (Admin) em.createQuery("select c from Admin c where c.login=:login and c.password=:password")
          .setParameter("login", login).setParameter("password", password).getSingleResult();
    } catch (NoResultException e) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ResourceBundle bundle = (ResourceBundle) facesContext.getApplication()
            .getResourceBundle(facesContext, "messages");
        String summary = bundle.getString("adminNotFound");
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, summary);
        facesContext.addMessage(null, msg);
        return null;
    }
    return admin;
  }
}