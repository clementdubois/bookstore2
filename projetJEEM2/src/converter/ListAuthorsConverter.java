package converter;
 
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ejb.AuthorService;
import entities.Author;
 
@ManagedBean(name = "listAuthorsConverterBean") 
@FacesConverter(value = "listAuthorsConverter")
public class ListAuthorsConverter implements Converter {
    @EJB
    private AuthorService authorService;  
 
    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
        return null; 
    }
 
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
    	List<Author> authors = (List<Author>) o;
    	String result = "";
    	for(Author a : authors){
    		result += a.getFirstName() + " " + a.getLastName() + " | ";
    	}
        return result; 
    }
}