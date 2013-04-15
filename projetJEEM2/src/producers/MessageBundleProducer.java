package producers;
import java.util.ResourceBundle;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import annotations.Messages;



public class MessageBundleProducer {
  @Inject
  private FacesContext facesContext;
  @Produces @Messages 
  public ResourceBundle getBundle() { 
    return (ResourceBundle)facesContext.getApplication().getResourceBundle(facesContext, "messages");
  }
}