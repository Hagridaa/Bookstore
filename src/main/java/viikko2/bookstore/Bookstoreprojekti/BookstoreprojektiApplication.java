package viikko2.bookstore.Bookstoreprojekti;


import viikko2.bookstore.Bookstoreprojekti.domain.Book;
import viikko2.bookstore.Bookstoreprojekti.domain.BookRepository;
import viikko2.bookstore.Bookstoreprojekti.domain.Category;
import viikko2.bookstore.Bookstoreprojekti.domain.CategoryRepository;
import viikko2.bookstore.Bookstoreprojekti.domain.User;
import viikko2.bookstore.Bookstoreprojekti.domain.UserRepository;

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
	public CommandLineRunner Demo(BookRepository bookRepository, CategoryRepository categoryRepository, UserRepository urepository) { 
		return (args) -> {
			log.info("save a couple of books");
			//(Long id, String title, String author, Integer year, String isbn, double price)
			
			categoryRepository.save(new Category("Food"));
			categoryRepository.save(new Category("Scifi"));
			categoryRepository.save(new Category("Fantasy"));
			categoryRepository.save(new Category("Romance"));
			
			//drepository.findByName("Business").get(0)));
			bookRepository.save(new Book("Cooking", "Milla Magia", 1985, "12345x", 12.90, categoryRepository.findByName("Food").get(0)));
			bookRepository.save(new Book("Harry Potter", "Rowling", 1999, "12346x", 14.90,categoryRepository.findByName("Scifi").get(0)));	
			
			// Create users: admin/admin user/user
			//(String username, String passwordHass, String email, String role)
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "malli@o.fi","USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C","malli@o.fi", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
};
	}
	
}
