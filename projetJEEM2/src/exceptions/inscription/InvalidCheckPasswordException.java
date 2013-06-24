package exceptions.inscription;

public class InvalidCheckPasswordException extends InscriptionException {

	public InvalidCheckPasswordException(){
		super("L'ancien mot de passe et le nouveau sont pas identiques");
	}
	public InvalidCheckPasswordException(String message){
		super("L'ancien mot de passe et le nouveau sont pas identiques : ".concat(message));
	}

}
