package ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;

import entities.Book;
@Stateless
@Local(BookService.class)
public class BookServiceEJB extends GenericCRUDServiceEJB<Book> implements BookService{
}