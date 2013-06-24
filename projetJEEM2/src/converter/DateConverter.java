package converter;
 
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ejb.AuthorService;
import entities.Author;
 
@ManagedBean(name = "dateConverterBean") 
@FacesConverter(value = "dateConverter")
public class DateConverter implements Converter { 
 
    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
        return null; 
    }
 
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
    	Date d = (Date) o;
    	return d.toString();

    }
}