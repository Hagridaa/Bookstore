package viikko2.bookstore.Bookstoreprojekti.web;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import viikko2.bookstore.Bookstoreprojekti.domain.Book;
import viikko2.bookstore.Bookstoreprojekti.domain.BookRepository;
import viikko2.bookstore.Bookstoreprojekti.domain.CategoryRepository;

@RestController
public class BookRestController {

	// Spring-alusta luo sovelluksen käynnistyessä BookRepository-rajapintaa toteuttavan luokan olion 
	   // ja kytkee olion BookController-luokasta luodun olion attribuuttiolioksi
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	private static final Logger log = LoggerFactory.getLogger(BookController.class);
	
	//Create REST service that return all books (JSON)
	//Create a method to controller
	//Ignore one-to-many link from JSON
	@RequestMapping(value="/books",method = RequestMethod.GET)
	public @ResponseBody List<Book>booklistRest() {
		return(List<Book>) bookRepository.findAll();
	}
	
	//b.) Create REST service that return one book by id (JSON)
	//Create a method to controller
	//Use path variable to get book id
	@RequestMapping(value="/book/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
	return bookRepository.findById(bookId);
	}
	
	
	
	
}
