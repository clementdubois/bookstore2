package beans;  
  
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.application.FacesMessage;  
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;  

import org.primefaces.component.menuitem.MenuItem;  
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;  
import org.primefaces.model.MenuModel;  

import controller.frontOffice.BookFrontController;
import controller.frontOffice.ClientController;


import ejb.CategoryService;
import entities.Category;
  
@ManagedBean
@RequestScoped
public class MenuBean {
	@EJB
	private CategoryService categoryService;
    private MenuModel model;
    
    @ManagedProperty(value="#{clientController}")
	private ClientController clientController;
 
    public MenuBean() {
    } 
    
    @PostConstruct
    public void init(){
    	FacesContext facesCtx = FacesContext.getCurrentInstance();
    	ELContext elCtx = facesCtx.getELContext();
    	ExpressionFactory expFact = facesCtx.getApplication().getExpressionFactory();
    	
        model = new DefaultMenuModel();
        //Accueil
        MenuItem accueil = new MenuItem();
        accueil.setValue("Accueil");
        accueil.setUrl("/frontOffice/login.jsf");
        model.addMenuItem(accueil);
        
        //Catégories
        Submenu categoriesMenu = new Submenu();
    	List<Category> categories = (List<Category>)categoryService.findAll();
        categoriesMenu.setLabel("Livres");
        for(Category cat : categories){
        	MenuItem item = new MenuItem();
            item.setValue(cat.getTitle());
            item.setAjax(false);
            MethodExpression aEx = expFact.createMethodExpression(elCtx, "#{bookFrontController.index("+cat.getId()+")}", String.class, new Class[]{Long.class});
            item.setActionExpression(aEx);
            categoriesMenu.getChildren().add(item);
        }
        model.addSubmenu(categoriesMenu);
        
        //Si le client est connecté
        if(clientController.getCurrentClient() != null){
        	//Mes commandes
        	MenuItem commandes = new MenuItem();
        	commandes.setValue("Commandes");
        	commandes.setUrl("/frontOffice/client/detailCommande.jsf");
        	model.addMenuItem(commandes);
        	//Mon profil
        	MenuItem profil = new MenuItem();
        	profil.setValue("Mon Profil");
        	profil.setUrl("/frontOffice/client/client.jsf");
        	model.addMenuItem(profil);
        }
    }
  
    public MenuModel getModel() {  
        return model;  
    }     
      
    public void save() {  
        addMessage("Data saved");  
    }  
      
    public void update() {  
        addMessage("Data updated");  
    }  
      
    public void delete() {  
        addMessage("Data deleted");  
    }  
      
    public void addMessage(String summary) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }

	public ClientController getClientController() {
		return clientController;
	}

	public void setClientController(ClientController clientController) {
		this.clientController = clientController;
	}
}  