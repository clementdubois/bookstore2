package controller.frontOffice;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ejb.ClientService;
import entities.Client;
import entities.Order;
import exceptions.inscription.LoginUsedException;
import exceptions.inscription.UnconfirmedPasswordException;
import exceptions.login.ClientNotFoundException;

import annotations.LoggedIn;
import beans.InscriptionForm;
import beans.LoginForm;
import beans.MessageBean;

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
	private MessageBean messageBean;
	@EJB
	private ClientService clientService;

	private Client currentClient;
	private Order currentOrder;

	@Produces
	@Named
	@LoggedIn
	public Client getCurrentClient() {
		return currentClient;
	}
	
	public void setCurrentClient(Client currentClient) {
		this.currentClient = currentClient;
	}



	public String doLogin() {
		try {
			currentClient = clientService.login(loginForm.getLogin(),
					loginForm.getPassword());
		} catch (ClientNotFoundException e) {
			messageBean.addMessage("clientNotFound");
		}

		return "/frontOffice/login";
	}
	
	public boolean isLoggedIn() {
		return currentClient != null;
	}

	public String doLogout() {
		currentClient = null;
		return "/frontOffice/login";
	}
	
	public String doInscription(){
		try {
			clientService.inscription(inscriptionForm.getLogin(), inscriptionForm.getPassword(), inscriptionForm.getPasswordConfirmation());
			return "/frontOffice/login";
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
}