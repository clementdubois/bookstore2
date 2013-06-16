package controller.backOffice;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import annotations.Books;
import beans.BookForm;
import beans.MessageBean;
import ejb.BookService;
import entities.Book;

@Named
@SessionScoped
public class BookController implements Serializable {
	@Inject
	private Logger log; //= LoggerFactory.getLogger(BookController.class);
	@Inject
	private BookForm bookForm;
	@Inject
	private MessageBean messageBean;
	@EJB
	private BookService bookService;
	
	private Book book;
	
	@Produces
	@Named
	@Books
	public List getBooks(){
		return (List<Book>) bookService.findAll();
	}
	
	public String addBook(){
		Book b = new Book();
		b.setTitle(bookForm.getTitle());
		b.setCategory(bookForm.getCategory());
		book.setPrice(bookForm.getPrice());
		bookService.create(b);
		return "/backOffice/book/index";
	}
	
	public String deleteBook(Long bookId){
		bookService.delete(bookId);
		return "/backOffice/book/index";
	}
	
	public String editBook(Book b){
		bookForm.setTitle(b.getTitle());
		bookForm.setCategory(b.getCategory());
		book.setPrice(bookForm.getPrice());
		book = b;
		return "/backOffice/book/edit";
	}
	//*
	public String updateBook(Long bookId){
		book.setTitle(bookForm.getTitle());
		book.setCategory(bookForm.getCategory());
		book.setPrice(bookForm.getPrice());/*
		book.setDate(bookForm.getDate());
		book.setAuthors(bookForm.getAuthors());
		book.setPhoto(bookForm.getPhoto());//*/
		bookService.update(book);
		book = null;
		return "/backOffice/book/index";
	}//*/
}