package controller.frontOffice;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import ejb.OrderService;
import entities.Order;

@ManagedBean
@RequestScoped
public class OrderFrontController implements Serializable {
	
	@Inject
	private Logger log;
	@EJB
	private OrderService orderService;

	@Inject
	private ClientController clientController;
	
	public List<Order> getOrders(){
		List<Order> or = clientController.getCurrentClient().getCommandes();
		for(Order order : or){
			System.out.println(order.getDate());
		}
		return or;
	}

	public ClientController getClientController() {
		return clientController;
	}

	public void setClientController(ClientController clientController) {
		this.clientController = clientController;
	}
	
	

	
	
	
	
}