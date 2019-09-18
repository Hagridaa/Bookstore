package viikko2.bookstore.Bookstoreprojekti.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface CategoryRepository extends CrudRepository<Category, Long> {

	// tietokantakäsittelyn rajapinta
	
	List<Category> findByName(String name);
	
	
}
