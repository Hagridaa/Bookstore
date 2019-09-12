package viikko2.bookstore.Bookstoreprojekti.web;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
		public String deleteBook(@PathVariable("id") Long BookId, Model model) {
			bookRepository.deleteById(BookId);
			return "redirect:../booklist";
		}
}
