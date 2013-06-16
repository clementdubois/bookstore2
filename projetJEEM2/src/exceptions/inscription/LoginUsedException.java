package exceptions.inscription;

public class LoginUsedException extends InscriptionException {

	public LoginUsedException(){
		super("Login déjà utilisé");
	}
	public LoginUsedException(String message){
		super("Login déjà utilisé : ".concat(message));
	}

}
