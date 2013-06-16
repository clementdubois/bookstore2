package beans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import entities.Author;
import entities.Book;
import entities.Category;

@Named
@RequestScoped
public class BookForm {
	private String title = "";
	private Category category;
	private java.math.BigDecimal price = new BigDecimal(0);;
	private java.util.Date date = new Date();
	private List<Author> authors;
	private byte[] photo;
  
	public String getTitle() {
	    return this.title;
	  }

	  public void setTitle(String title) {
	    this.title = title;
	  }

	  @ManyToOne()
	  public Category getCategory() {
	    return this.category;
	  }

	  public void setCategory(Category category) {
	    this.category = category;
	  }

	  public java.math.BigDecimal getPrice() {
	    return this.price;
	  }

	  public void setPrice(java.math.BigDecimal price) {
	    this.price = price;
	  }

	  @Temporal(TemporalType.DATE)
	  public java.util.Date getDate() {
	    return this.date;
	  }

	  public void setDate(java.util.Date date) {
	    this.date = date;
	  }

	  @ManyToMany
	  // @JoinTable(name = "BookAuthor", joinColumns = { @JoinColumn(name =
	  // "bookId") }, inverseJoinColumns = { @JoinColumn(name = "authorId") })
	  public List<Author> getAuthors() {
	    return this.authors;
	  }

	  public void setAuthors(List<Author> authors) {
	    this.authors = authors;
	  }

	  @Lob
	  @Column(length = 100000)
	  // La taille de la colonne est nécessaire pour Derby
	  public byte[] getPhoto() {
	    return this.photo;
	  }

	  public void setPhoto(byte[] photo) {
	    this.photo = photo;
	  }
}