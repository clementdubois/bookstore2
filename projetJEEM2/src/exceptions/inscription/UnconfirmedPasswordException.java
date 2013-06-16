package exceptions.inscription;

public class UnconfirmedPasswordException extends InscriptionException {

	public UnconfirmedPasswordException(){
		super("Le mot de passe et la confirmation de sont pas identiques");
	}
	public UnconfirmedPasswordException(String message){
		super("Le mot de passe et la confirmation de sont pas identiques : ".concat(message));
	}

}
