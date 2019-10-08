package viikko2.bookstore.Bookstoreprojekti;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import viikko2.bookstore.Bookstoreprojekti.web.BookController;
import viikko2.bookstore.Bookstoreprojekti.web.BookRestController;

//a. Add Smoke testing to your Bookstore application (for all controllers)
//testing the major functions of software before carrying out formal testing
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookstoreprojektiApplicationTests {

	@Autowired
	private BookController controller;
	
	@Test
	public void contextLoads()  throws Exception {
		assertThat(controller).isNotNull();
	}
	
	@Autowired
	private BookRestController rcontroller;
	
	@Test
	public void contextLoads1() throws Exception {
		assertThat(rcontroller).isNotNull();
	}
}
// @Autowired
//private HelloController controller;
//@Test
//public void contexLoads() throws Exception {
//assertThat(controller).isNotNull();
//}