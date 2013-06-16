package beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Named
@RequestScoped
public class CategoryForm {
  private String title;
  
  @NotNull
  @Length(min = 3, max = 25)
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
}