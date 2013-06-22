package beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class SearchForm {
	private String searchText;
  
	public String getSearchText() {
	    return this.searchText;
	  }

	  public void setSearchText(String searchText) {
	    this.searchText = searchText;
	  }
}