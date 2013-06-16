package ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;

import entities.Author;
@Stateless
@Local(AuthorService.class)
public class AuthorServiceEJB extends GenericCRUDServiceEJB<Author> implements AuthorService{
}