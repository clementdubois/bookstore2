package ejb;

import java.util.ResourceBundle;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;

import beans.InscriptionForm;
import beans.PasswordForm;
import entities.Client;
import exceptions.inscription.InvalidCheckPasswordException;
import exceptions.inscription.LoginUsedException;
import exceptions.inscription.UnconfirmedPasswordException;
import exceptions.login.ClientNotFoundException;
@Stateless
@Local(ClientService.class)
public class ClientServiceEJB extends GenericCRUDServiceEJB<Client> implements ClientService{
	public Client login(String login, String password) throws ClientNotFoundException {
		Client client = null;
		try {
			client = (Client) em.createQuery("select c from Client c where c.login=:login and c.password=:password")
					.setParameter("login", login).setParameter("password", password).getSingleResult();
		} catch (NoResultException e) {
			throw new ClientNotFoundException();
		}
		return client;
	}

	public Boolean inscription(String login, String password, String passwordConfirmation) throws LoginUsedException, UnconfirmedPasswordException{
		long length =  (Long)em.createQuery("select count(c.login) from Client c where c.login=:login")
				.setParameter("login", login).getSingleResult();
		//Si le client existe déjà, on génère une erreur
		if(length > 0){
			throw new LoginUsedException();
		}else{
			//Vérification du mot de passe
			if(!password.equals(passwordConfirmation)){
				throw new UnconfirmedPasswordException();
			}else{
				//On créer le nouveau client
				Client client = new Client();
				client.setLogin(login);
				client.setPassword(password);
				this.create(client);
				return true;
			}
		}
	}

	public Client modifier(InscriptionForm inscriptionForm, Long clientId){
		Client c = (Client)this.find(clientId);
		c.setFirstName(inscriptionForm.getFirstName());
		c.setLastName(inscriptionForm.getLastName());

		return this.update(c);
	}


	public Client changerPassword(PasswordForm passwordForm, Long id)throws UnconfirmedPasswordException, InvalidCheckPasswordException {
		Client c = (Client)this.find(id);
		if(!passwordForm.getPassword().equals(passwordForm.getPasswordConfirmation())){
			throw new UnconfirmedPasswordException();
		}else if(!passwordForm.getPasswordCheck().equals(c.getPassword())){
			throw new InvalidCheckPasswordException();
		}
		c.setPassword(passwordForm.getPassword());
		return this.update(c);
	}

}