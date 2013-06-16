package exceptions.login;

public class LoginException extends Exception{	
	public LoginException(){
		super("Erreur Login");
	}
	public LoginException(String message){
		super("Erreur Login : ".concat(message));
	}
}
