package ejb;

import entities.Client;
import exceptions.inscription.LoginUsedException;
import exceptions.inscription.UnconfirmedPasswordException;
import exceptions.login.ClientNotFoundException;

public interface ClientService extends GenericCRUDService<Client>{
  public Client login(String login, String password) throws ClientNotFoundException;
  public Boolean inscription(String login, String password, String passwordConfirmation) throws LoginUsedException, UnconfirmedPasswordException;
}