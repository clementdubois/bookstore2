package controller.backOffice;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import annotations.Authors;
import annotations.Categories;
import beans.AuthorForm;
import beans.MessageBean;
import ejb.AuthorService;
import ejb.GenericCRUDService;
import entities.Author;

@Named
@SessionScoped
public class AuthorController implements Serializable {
	@Inject
	private Logger log; //= LoggerFactory.getLogger(AuthorController.class);
	@Inject
	private AuthorForm authorForm;
	@Inject
	private MessageBean messageBean;
	@EJB
	private AuthorService authorService;
	
	private Author author;
	
	@Produces
	@Named
	@Authors
	public List getAuthors(){
		return (List<Author>) authorService.findAll();
	}
	
	public String addAuthor(){
		Author a = new Author();
		a.setFirstName(authorForm.getFirstName());
		a.setLastName(authorForm.getLastName());
		authorService.create(a);
		return "/backOffice/author/index";
	}
	
	public String deleteAuthor(Long authorId){
		authorService.delete(authorId);
		return "/backOffice/author/index";
	}
	
	public String editAuthor(Author a){
		authorForm.setFirstName(a.getFirstName());
		authorForm.setLastName(a.getLastName());
		author = a;
		return "/backOffice/author/edit";
	}
	//*
	public String updateAuthor(){
		author.setFirstName(authorForm.getFirstName());
		author.setLastName(authorForm.getLastName());
		authorService.update(author);
		author = null;
		return "/backOffice/author/index";
	}//*/
}