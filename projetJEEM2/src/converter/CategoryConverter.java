package converter;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import ejb.CategoryService;
import entities.Category;

public class CategoryConverter implements Converter{

	@EJB
	private CategoryService categoryService;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Category cat = null;
		try{
			cat = (Category) categoryService.find(arg2);
		}catch(Throwable e){
			
		}
		return cat;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		String val = null;
		try{
			Category cat = (Category) arg2;
			val = Long.toString(cat.getId());
		}catch(Throwable e){
			
		}
				return val;
	}

}
