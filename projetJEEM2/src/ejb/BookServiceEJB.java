package ejb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;

import entities.Book;
@Stateless
@Local(BookService.class)
public class BookServiceEJB extends GenericCRUDServiceEJB<Book> implements BookService{
	  public List search(String searchText) {
		  HashMap<String,Object> map = new HashMap<String, Object>();
		  map.put("like",searchText);
		  return findWithNamedQuery("Book.findLikeOnTitle", map);
	  }
}