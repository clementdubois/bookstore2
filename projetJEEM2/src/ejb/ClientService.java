package ejb;

import beans.InscriptionForm;
import beans.PasswordForm;
import entities.Client;
import exceptions.inscription.InvalidCheckPasswordException;
import exceptions.inscription.LoginUsedException;
import exceptions.inscription.UnconfirmedPasswordException;
import exceptions.login.ClientNotFoundException;

public interface ClientService extends GenericCRUDService<Client>{
  public Client login(String login, String password) throws ClientNotFoundException;
  public Boolean inscription(String login, String password, String passwordConfirmation) throws LoginUsedException, UnconfirmedPasswordException;
  public Client modifier(InscriptionForm inscriptionForm, Long clientId);
  public Client changerPassword(PasswordForm passwordForm, Long id) throws UnconfirmedPasswordException, InvalidCheckPasswordException;
}