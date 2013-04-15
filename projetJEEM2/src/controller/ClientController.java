package controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ejb.ClientService;
import entities.Client;

import annotations.LoggedIn;
import beans.LoginForm;

@Named
@SessionScoped
public class ClientController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(ClientController.class);
	@Inject
	private LoginForm loginForm;
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