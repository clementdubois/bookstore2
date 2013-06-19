package controller.backOffice;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import annotations.Books;
import beans.BookForm;
import beans.MessageBean;
import ejb.AuthorService;
import ejb.BookService;
import ejb.CategoryService;
import entities.Book;
import entities.Category;

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
	@EJB
	private CategoryService categoryService;
	@EJB
	private AuthorService authorService;
	
	private Book book;
	
	public Category getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(Category selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	private Long selectedId;
	private Category selectedCategory;
	
	@Produces
	@Named
	@Books
	public List getBooks(){
		return (List<Book>) bookService.findAll();
	}
	
	public String addBook(){
		Book book = new Book();
		book.setTitle(bookForm.getTitle());
		book.setCategory(bookForm.getCategory());
		book.setAuthors(bookForm.getAuthors());
		book.setPrice(bookForm.getPrice());
		bookService.create(book);
		return "/backOffice/book/index";
	}
	
	public String deleteBook(Long bookId){
		bookService.delete(bookId);
		return "/backOffice/book/index";
	}
	
	public String editBook(Book b){
		bookForm.setTitle(b.getTitle());
		bookForm.setCategory(b.getCategory());
		bookForm.setPrice(b.getPrice());
		selectedCategory = b.getCategory();
		selectedId = selectedCategory.getId();
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
	
	public Long getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(Long selectedId) {
		this.selectedId = selectedId;
	}
	
	public void selectIdChanged(ValueChangeEvent e){
		selectedId = (Long) e.getNewValue();
		if(selectedId!=null)
			selectedCategory = (Category) categoryService.find(selectedId);
		else
			selectedCategory = null;
	}
	
}