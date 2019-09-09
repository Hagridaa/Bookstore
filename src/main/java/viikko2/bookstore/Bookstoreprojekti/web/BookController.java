package viikko2.bookstore.Bookstoreprojekti.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
