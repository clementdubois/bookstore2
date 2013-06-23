package beans;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.DragDropEvent;

import controller.frontOffice.ClientController;

import ejb.BookService;
import ejb.ClientService;
import ejb.OrderService;
import entities.Book;
import entities.Client;
import entities.Order;
import entities.OrderItem;

@Named
@SessionScoped
public class PanierBean implements Serializable{
	private Order panier;
	@EJB
	private OrderService panierService;
	@EJB
	private BookService bookService;
	@EJB
	private ClientService clientService;
	
	public PanierBean(){
		panier = new Order();
	};
	
	public void addBook(Long id){
		Book b = bookService.find(id);
		panier.addOne(b);
	}
	
	public void enleverBook(Long id){
		System.out.println("entrer");
		Book b = bookService.find(id);
		panier.removeOne(b);
	}
	
	public void validerPanier(Long clientId){
		panier.setDate(new Date());
		if(clientId != null){
			panier.setClient(clientService.find(clientId));
		}
		panierService.create(panier);
		panier = new Order();
	}
	
	public void dropBook(DragDropEvent ddEvent){
		Book book = (Book) ddEvent.getData();
		addBook(book.getId());
	}

	public Order getPanier() {
		return panier;
	}

	public void setPanier(Order panier) {
		this.panier = panier;
	}
	
}