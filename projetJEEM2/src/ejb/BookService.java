package ejb;

import java.util.List;

import entities.Book;

public interface BookService extends GenericCRUDService<Book>{
	public List search(String searchText);
}