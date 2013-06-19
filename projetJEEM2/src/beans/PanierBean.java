package beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entities.Book;
import entities.Order;

@Named
@SessionScoped
public class PanierBean implements Serializable{
	private Order panier;
	
	public PanierBean(){
		panier = new Order();
	};
	
	public void addBook(Book b){
		panier.addOne(b);
	}

	public Order getPanier() {
		return panier;
	}

	public void setPanier(Order panier) {
		this.panier = panier;
	}
	
	
	
}