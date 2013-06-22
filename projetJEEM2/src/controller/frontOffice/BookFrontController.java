package controller.frontOffice;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import ejb.BookService;
import ejb.CategoryService;
import entities.Book;
import entities.Category;

@ManagedBean
@SessionScoped
public class BookFrontController implements Serializable {
	
	@Inject
	private Logger log;
	@EJB
	private BookService bookService;
	@EJB
	private CategoryService categoryService;
	@Inject
	@Named
	private List<Book> books;
	@Named
	private Book book;
	
	public String index(Long c){
		if(c != null){
			Category category = categoryService.find(c);
			books = category.getBooks();
		}else{
			books = (List<Book>) bookService.findAll();
		}
		return "/frontOffice/books/index";
	}
	
	public String showBook(long id){
		Book b = bookService.find(id);
		this.setBook(b);
		return "/frontOffice/books/show";
	}
	
	public List<Book> bestSells(){
		List<Book> books = (List<Book>)bookService.findWithNamedQuery("Book.bestSells");
		for(Book b : books){
			System.out.println(b.getId());
		}
		return books;
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