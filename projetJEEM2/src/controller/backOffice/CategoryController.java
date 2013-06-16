package controller.backOffice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
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
@SessionScoped
public class CategoryController implements Serializable {
	@Inject
	private Logger log; //= LoggerFactory.getLogger(CategoryController.class);
	@Inject
	private CategoryForm categoryForm;
	@Inject
	private MessageBean messageBean;
	@EJB
	private CategoryService categoryService;
	
	private Category category;
	
	@Produces
	@Named
	@Categories
	public List getCategories(){
		return (List<Category>) categoryService.findAll();
	}
	
	public String addCategory(){
		Category c = new Category();
		c.setTitle(categoryForm.getTitle());
		categoryService.create(c);
		return "/backOffice/category/index";
	}
	
	public String deleteCategory(Long categoryId){
		categoryService.delete(categoryId);
		return "/backOffice/category/index";
	}
	
	public String editCategory(Category c){
		categoryForm.setTitle(c.getTitle());
		category = c;
		return "/backOffice/category/edit";
	}
	//*
	public String updateCategory(Long categoryId){
		category.setTitle(categoryForm.getTitle());
		categoryService.update(category);
		category = null;
		return "/backOffice/category/index";
	}//*/
}