package viikko2.bookstore.Bookstoreprojekti.web;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import viikko2.bookstore.Bookstoreprojekti.domain.Book;
import viikko2.bookstore.Bookstoreprojekti.domain.BookRepository;
import viikko2.bookstore.Bookstoreprojekti.domain.CategoryRepository;


@Controller
public class BookController {
	
	   // Spring-alusta luo sovelluksen käynnistyessä BookRepository-rajapintaa toteuttavan luokan olion 
	   // ja kytkee olion BookController-luokasta luodun olion attribuuttiolioksi
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	private static final Logger log = LoggerFactory.getLogger(BookController.class);
	
	@RequestMapping(value="/login")
    public String login() {	
        return "login";
    }

	@RequestMapping(value = "/index" , method = RequestMethod.GET)
	public String getindex(Model model) {
	return "index";

}
	 // Home page of REST services
    @RequestMapping(value="/homepagerest", method = RequestMethod.GET)
    public String getRestHome() {	
    	return "homepagerest"; // resthomepage.html
    }
	
	// tyhjän kirjalomakkeen muodostaminen
	@RequestMapping(value = "/addbook" , method = RequestMethod.GET)
		public String getNewBookForm(Model model) {
		model.addAttribute("book", new Book());	// "tyhjä" book-olio
		model.addAttribute("categories", categoryRepository.findAll());
			return "addbook";
	
	}
	

	// lomakeella syötettyjen tietojen vastaanotto
		//@RequestMapping(value = "/newbook", method = RequestMethod.POST)
		//public String getNewBookForm(@ModelAttribute Book book, Model model) {
			//model.addAttribute("book", book);
			//return "booklist";
		//}
	
	//Lomakkeen tietojen vastaanotto ja tallennus kantaan
	@RequestMapping(value = "/addbook",method = RequestMethod.POST)
	public String add(Book book) {
		log.debug(book.toString());
		bookRepository.save(book);
		log.debug(book.toString());
		return "redirect:booklist";
	}
	
		
	// listaus
		@RequestMapping(value = "/booklist", method = RequestMethod.GET)
		public String getBooks(Model model) {
				List<Book> books =  (List<Book>) bookRepository.findAll(); // haeta tietokannasta kirjat
				model.addAttribute("books", books); // välitetään lista templatelle model-olion avulla
				return "booklist"; // DispatherServlet saa tämän template-nimen ja kutsuu seuraavaksi booklist.html-templatea,
									// joka prosessoidaan palvelimella
}
		//kirjan poisto
		@PreAuthorize("hasAuthority('ADMIN')")
		@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
		public String deleteBook(@PathVariable("id") Long bookId, Model model) {
			bookRepository.deleteById(bookId);
			return "redirect:../booklist";
		}
		
		// now you will add current book object to model. You also have to send current
		//book id from the list page to controller (like you did in delete link)
		
		// /edit
		//haetaan annettu id kuten delete toiminnossa getillä
		@PreAuthorize("hasAuthority('ADMIN')")
		@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
		public String editBook(@PathVariable("id") Long bookId, Model model) {
			//etsitään id:n avulla muokattavan kirjan tiedot
			//Optional vaihtoehdolla voi tehdä niin, että jos tietoa ei löydykkään niin sovellus ei kaadu
			Optional<Book> foundedBook = bookRepository.findById(bookId);
			model.addAttribute("foundedbook", foundedBook); //// välitetään lista templatelle model-olion avulla
			return "edit";
		}
		//Päivitys, haetaan kannasta tiedot ja takaisin update komennolla ei inserttinä ette itee uutta
		//Lomakkeen tietojen vastaanotto ja tallennus kantaan
		@RequestMapping(value = "/editbook",method = RequestMethod.POST)
		public String update(Book book) {
			if
			(bookRepository.existsById(book.getId())) {
					bookRepository.save(book);
			}
						return "redirect:../booklist";
}
}
