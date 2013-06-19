package converter;
 
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ejb.AuthorService;
import entities.Author;
 
 
 
@ManagedBean(name = "authorConverterBean") 
@FacesConverter(value = "authorConverter")
public class AuthorConverter implements Converter {
 
    @EJB
    private AuthorService authorService;  
 
    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
        return authorService.find(Long.valueOf(value)); 
    }
 
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return ((Author) o).getId().toString(); 
    }
}