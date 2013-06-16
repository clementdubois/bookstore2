package controller.backOffice;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ejb.AdminService;
import entities.Admin;

import annotations.LoggedIn;
import beans.LoginForm;
import beans.MessageBean;

@Named
@SessionScoped
public class AdminController implements Serializable {
	@Inject
	private Logger log; //= LoggerFactory.getLogger(AdminController.class);
	@Inject
	private LoginForm loginForm;
	@Inject
	private MessageBean messageBean;
	@EJB
	private AdminService adminService;

	private Admin currentAdmin;

	@Produces
	@Named
	@LoggedIn
	public Admin getCurrentAdmin() {
		return currentAdmin;
	}
	
	public String doLogin() {
		currentAdmin = adminService.login(loginForm.getLogin(),
				loginForm.getPassword());
		return "login";
	}
	
	public boolean isLoggedIn() {
		return currentAdmin != null;
	}

	public String doLogout() {
		currentAdmin = null;
		return "login";
	}
}