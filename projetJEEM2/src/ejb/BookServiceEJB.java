package ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import entities.Book;
@Stateless
@Local(BookService.class)
public class BookServiceEJB extends GenericCRUDServiceEJB<Book> implements BookService{
	  public List search(String searchText) {
	    return findWithNamedQuery("select t from Book t where title = " + searchText);
	  }
}