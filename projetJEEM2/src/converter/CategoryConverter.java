package converter;
 
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
 
import ejb.CategoryService;
import entities.Category;
 
 
 
@ManagedBean(name = "categoryConverterBean") 
@FacesConverter(value = "categoryConverter")
public class CategoryConverter implements Converter {
 
    @EJB
    private CategoryService categoryService;  
 
    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
        return categoryService.find(Long.valueOf(value)); 
    }
 
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return ((Category) o).getId().toString(); 
    }
}