package exceptions.login;

public class ClientNotFoundException extends LoginException{	
	public ClientNotFoundException(){
		super("Client inconnu");
	}
	public ClientNotFoundException(String message){
		super("Client inconnu : ".concat(message));
	}
}
