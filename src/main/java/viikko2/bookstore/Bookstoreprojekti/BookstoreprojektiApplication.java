package viikko2.bookstore.Bookstoreprojekti;

import viikko2.bookstore.Bookstoreprojekti.domain.Book;
import viikko2.bookstore.Bookstoreprojekti.domain.BookRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;




@SpringBootApplication
public class BookstoreprojektiApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreprojektiApplication.class);  //uusi loggeriattribuutti

	public static void main(String[] args) {
		SpringApplication.run(BookstoreprojektiApplication.class, args);
	}

	//  testidatan luonti H2-testitietokantaan aina sovelluksen käynnistyessä
	@Bean
	public CommandLineRunner Demo(BookRepository bookRepository) { 
		return (args) -> {
			log.info("save a couple of books");
			//(Long id, String title, String author, Integer year, String isbn, double price)
			bookRepository.save(new Book("Cooking", "Milla Magia", 1985, "12345x", 12.90));
			bookRepository.save(new Book("Cooking2", "Milla Magia", 1985, "12346x", 14.90));	
			
			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
};
	}
	
}
