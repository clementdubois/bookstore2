package controller.frontOffice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ejb.CategoryService;
import entities.Category;

import annotations.Categories;
import annotations.LoggedIn;
import beans.CategoryForm;
import beans.LoginForm;
import beans.MessageBean;

@Named
@RequestScoped
public class CategoryFrontController implements Serializable {
	@Inject
	private Logger log;
	@EJB
	private CategoryService categoryService;
	
	private Category category;
	
	@Produces
	@Named
	public List getCategoriesBook(){
		return (List<Category>) categoryService.findAll();
	}

}