package controller.frontOffice;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import beans.SearchForm;
import ejb.AuthorService;
import ejb.BookService;
import ejb.CategoryService;

@SessionScoped
@ManagedBean
public class SearchController implements Serializable {
	@Inject
	private SearchForm searchForm;
	@EJB
	private BookService bookService;
	@EJB
	private CategoryService categoryService;
	@EJB
	private AuthorService authorService;
	private List results;

	/*@Produces
	@Named
	public String doSearch(){
		results = bookService.search(searchForm.getSearchText());
		return "/frontOffice/books/index";
	}*/
	
	public String beanType(){
		return searchForm.getOption();
	}
	
	@Produces
	@Named
	public List getResults(){
		return results;
	}
	
	public void setResults(List list){
		results = list;
	}
}