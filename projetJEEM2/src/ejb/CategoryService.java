package ejb;

import java.util.List;

import entities.Category;

public interface CategoryService extends GenericCRUDService<Category>{
	public List search(String searchText);
}