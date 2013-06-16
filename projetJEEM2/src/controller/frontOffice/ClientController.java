package controller.frontOffice;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ejb.ClientService;
import entities.Client;

import annotations.LoggedIn;
import beans.LoginForm;
import beans.MessageBean;

@Named
@SessionScoped
public class ClientController implements Serializable {
	@Inject
	private Logger log; //= LoggerFactory.getLogger(ClientController.class);
	@Inject
	private LoginForm loginForm;
	@Inject
	private MessageBean messageBean;
	@EJB
	private ClientService clientService;

	private Client currentClient;

	@Produces
	@Named
	@LoggedIn
	public Client getCurrentClient() {
		return currentClient;
	}
	
	public String doLogin() {
		currentClient = clientService.login(loginForm.getLogin(),
				loginForm.getPassword());
		return "login";
	}
	
	public boolean isLoggedIn() {
		return currentClient != null;
	}

	public String doLogout() {
		currentClient = null;
		return "login";
	}
}