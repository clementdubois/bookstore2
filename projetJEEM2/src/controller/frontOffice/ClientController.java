package controller.frontOffice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.FileUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ejb.ClientService;
import entities.Client;
import entities.Order;
import exceptions.inscription.InvalidCheckPasswordException;
import exceptions.inscription.LoginUsedException;
import exceptions.inscription.UnconfirmedPasswordException;
import exceptions.login.ClientNotFoundException;

import annotations.LoggedIn;
import beans.InscriptionForm;
import beans.LoginForm;
import beans.MessageBean;
import beans.PasswordForm;

@SessionScoped
@ManagedBean
public class ClientController implements Serializable {
	@Inject
	private Logger log; //= LoggerFactory.getLogger(ClientController.class);
	@Inject
	private LoginForm loginForm;
	@Inject
	private InscriptionForm inscriptionForm;
	@Inject
	private PasswordForm passwordForm;
	@Inject
	private MessageBean messageBean;
	@EJB
	private ClientService clientService;
	
	private UploadedFile file;

	private Client currentClient;
	private Order currentOrder;
	private String UPLOAD_PATH = "/Users/clement/git/bookstore_2/projetJEEM2/WebContent/resources/images";

	@Produces
	@Named
	@LoggedIn
	public Client getCurrentClient() {
		return currentClient;
	}
	
	public void setCurrentClient(Client currentClient) {
		this.currentClient = currentClient;
	}

	public void doLogin() {
		try {
			currentClient = clientService.login(loginForm.getLogin(),
					loginForm.getPassword());
		} catch (ClientNotFoundException e) {
			messageBean.addMessage("clientNotFound");
		}

	}
	
	public boolean isLoggedIn() {
		return currentClient != null;
	}

	public String doLogout() {
		currentClient = null;
		return "/frontOffice/home";
	}
	
	public String doInscription(){
		try {
			clientService.inscription(inscriptionForm.getLogin(), inscriptionForm.getPassword(), inscriptionForm.getPasswordConfirmation());
			return "/frontOffice/home";
		} catch (LoginUsedException e) {
			messageBean.addMessage("clientAlreadyExists");
			return "/frontOffice/inscription";
			
		} catch (UnconfirmedPasswordException e) {
			messageBean.addMessage("passwordsDoesntMatch");
			return "/frontOffice/inscription";
		}

	}
	
	public String detailCommande(Order order){
		setCurrentOrder(order);
		return "/frontOffice/client/detailCommande";
	}

	@Produces
	@Named
	@LoggedIn
	public Order getCurrentOrder() {
		return currentOrder;
	}

	public void setCurrentOrder(Order currentOrder) {
		this.currentOrder = currentOrder;
	}
	
	@Produces
	@Named
	public String update(){
		currentClient = clientService.modifier(inscriptionForm, currentClient.getId());
		messageBean.addMessage("profilModifie");

		return "/frontOffice/client/client";
	}
	
	@Produces
	@Named
	public String changePassword(){
		try {
			currentClient = clientService.changerPassword(passwordForm, currentClient.getId());
			messageBean.addMessage("passwordModifie");
		} catch (UnconfirmedPasswordException e) {
			messageBean.addMessage("passwordsDoesntMatch");
		} catch (InvalidCheckPasswordException e){
			messageBean.addMessage("wrongCheckPassword");
		}
		return "/frontOffice/client/client";
	}
	
	   
	  
	    public UploadedFile getFile() {  
	        return file;  
	    }  
	  
	    public void setFile(UploadedFile file) {  
	        this.file = file;  
	    }  
	  
	    
	    public void handleFileUpload(FileUploadEvent event) {
	    	try {
	    		//Enregistrement du fichier sur le disque
	    		File targetFolder = new File(this.UPLOAD_PATH + "/avatars");
	    		InputStream inputStream = event.getFile().getInputstream();
	    		OutputStream out = new FileOutputStream(new File(targetFolder,"avatar_"+currentClient.getId()));
	    		int read = 0;
	    		byte[] bytes = new byte[1024];	    		 
	    		while ((read = inputStream.read(bytes)) != -1) {
	    			out.write(bytes, 0, read);
	    		}
	    		inputStream.close();
	    		out.flush();
	    		out.close();
	    		
	    		//Enregistrement en base de l'adresse de l'avatar	
	    		currentClient.setAvatar("avatars/avatar_" + currentClient.getId());
	    		currentClient = clientService.update(currentClient);
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	}
	    }
}