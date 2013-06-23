package ejb;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import entities.Author;
@Stateless
@Local(AuthorService.class)
public class AuthorServiceEJB extends GenericCRUDServiceEJB<Author> implements AuthorService{
	public List search(String searchText) {
		  HashMap<String,Object> map = new HashMap<String, Object>();
		  map.put("like",searchText);
		  map.put("like2",searchText);
		  return findWithNamedQuery("Author.findLikeOnName", map);
	  }
}