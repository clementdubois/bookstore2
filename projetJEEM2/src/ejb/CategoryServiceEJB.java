package ejb;

import java.util.ResourceBundle;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;

import entities.Category;
@Stateless
@Local(CategoryService.class)
public class CategoryServiceEJB extends GenericCRUDServiceEJB<Category> implements CategoryService{
}