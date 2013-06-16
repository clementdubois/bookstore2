package controller.frontOffice;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import ejb.CategoryService;
import entities.Category;

import beans.MessageBean;

@Named
@SessionScoped
public class BookFrontController implements Serializable {
	@Inject
	private Logger log;
	@Inject
	private Category category;
	@EJB
	private BookService bookService;
	
	public List getBooks(Category c){
		if(c != null){
			category = c;
		}
	}
}