package exceptions.inscription;
public class InscriptionException extends Exception{	
	public InscriptionException(){
		super("Erreur inscription");
	}
	public InscriptionException(String message){
		super("Erreur Inscription : ".concat(message));
	}
}
