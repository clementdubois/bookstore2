package beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class SearchForm {
	private String searchText;
	private String option = "book";
  
	public String getSearchText() {
	    return this.searchText;
	  }

	  public void setSearchText(String searchText) {
	    this.searchText = searchText;
	  }

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}
}