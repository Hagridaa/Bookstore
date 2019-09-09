package viikko2.bookstore.Bookstoreprojekti.domain;

import org.springframework.data.repository.CrudRepository;


	// tietokantakäsittelyn rajapinta
	public interface BookRepository extends CrudRepository<Book, Long> {
		

	}

