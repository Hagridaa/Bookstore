package viikko2.bookstore.Bookstoreprojekti.web;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import viikko2.bookstore.Bookstoreprojekti.domain.Book;
import viikko2.bookstore.Bookstoreprojekti.domain.BookRepository;


@Controller
public class BookController {
	
	   // Spring-alusta luo sovelluksen käynnistyessä BookRepository-rajapintaa toteuttavan luokan olion 
	   // ja kytkee olion BookController-luokasta luodun olion attribuuttiolioksi
	@Autowired
	BookRepository bookRepository;

	@RequestMapping(value = "/index" , method = RequestMethod.GET)
	public String getindex(Model model) {
	return "index";

}
	
	// tyhjän kirjalomakkeen muodostaminen
	@RequestMapping(value = "/addbook" , method = RequestMethod.GET)
		public String getNewBookForm(Model model) {
		model.addAttribute("book", new Book());	// "tyhjä" book-olio
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
		bookRepository.save(book);
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
		@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
		public String deleteBook(@PathVariable("id") Long bookId, Model model) {
			bookRepository.deleteById(bookId);
			return "redirect:../booklist";
		}
		
		// now you will add current book object to model. You also have to send current
		//book id from the list page to controller (like you did in delete link)
		
		// /edit
		//haetaan annettu id kuten delete toiminnossa getillä
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
