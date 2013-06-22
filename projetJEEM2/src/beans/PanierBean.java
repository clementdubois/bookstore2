package beans;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.DragDropEvent;

import controller.frontOffice.ClientController;

import ejb.OrderService;
import entities.Book;
import entities.Order;
import entities.OrderItem;

@Named
@SessionScoped
public class PanierBean implements Serializable{
	private Order panier;
	@EJB
	private OrderService panierService;
	@Inject
	private ClientController clientController;
	
	public PanierBean(){
		panier = new Order();
	};
	
	public void addBook(Book b){
		System.out.println("add boooooookkkkkk");
		panier.addOne(b);
	}
	
	public void enleverBook(Book b){
		System.out.println("entrer");
		panier.removeOne(b);
	}
	
	public void validerPanier(){
		panier.setDate(new Date());
		panier.setClient(clientController.getCurrentClient());
		panierService.create(panier);
		panier = new Order();
	}
	
	public void dropBook(DragDropEvent ddEvent){
		System.out.println(ddEvent.getData().toString());
		Book book = (Book) ddEvent.getData();
		System.out.println(book.getTitle());
		System.out.println(book.getId());
		addBook(book);
	}

	public Order getPanier() {
		return panier;
	}

	public void setPanier(Order panier) {
		this.panier = panier;
	}
	
	
	
}