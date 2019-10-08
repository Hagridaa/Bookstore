package viikko2.bookstore.Bookstoreprojekti;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import viikko2.bookstore.Bookstoreprojekti.domain.Book;
import viikko2.bookstore.Bookstoreprojekti.domain.BookRepository;
import viikko2.bookstore.Bookstoreprojekti.domain.Category;

//Add JPA repository tests (for all repositories)
//i. Test create, delete and search functionalities
@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

	@Autowired
	private BookRepository repository;
	
	@Test
	public void createNewBook() {
		Book book = new Book("Cooking4", "Milla Magia", 1985, "12345x", 12.90,new Category("Play"));
		repository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	
	@Test
	public void deleteBook() {
		Book book = new Book("Cooking6", "Milla Magia", 1985, "12345x", 12.90,new Category("Play"));
		repository.save(book);
		assertThat(book.getId()).isNotNull();
		repository.delete(book);
		assertThat(book.getAuthor().isEmpty());
	
}
	@Test
	public void searchBook() {
		List<Book> books = (List<Book>) repository.findAll();
		assertThat(books).isNotEmpty();
	}
}
