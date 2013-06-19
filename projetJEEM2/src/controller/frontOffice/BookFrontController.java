package controller.frontOffice;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import ejb.BookService;
import entities.Book;
import entities.Category;

@Named
@RequestScoped
public class BookFrontController implements Serializable {
	
	@Inject
	private Logger log;
	@EJB
	private BookService bookService;
	@Inject
	@Named
	private List<Book> books;
	@Named
	private Book book;
	
	public String index(Category c){
		if(c != null){
			books = c.getBooks();
		}else{
			books = (List<Book>) bookService.findAll();
		}
		return "/frontOffice/books/index";
	}
	
	public String showBook(Book b){
		this.setBook(b);
		return "/frontOffice/books/show";
	}
	
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
}