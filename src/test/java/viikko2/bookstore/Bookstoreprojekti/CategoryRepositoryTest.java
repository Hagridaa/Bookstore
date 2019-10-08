

	package viikko2.bookstore.Bookstoreprojekti;

	import static org.assertj.core.api.Assertions.assertThat;
	import java.util.List;

	import org.junit.Test;
	import org.junit.runner.RunWith;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
	import org.springframework.test.context.junit4.SpringRunner;

	
	import viikko2.bookstore.Bookstoreprojekti.domain.Category;
	import viikko2.bookstore.Bookstoreprojekti.domain.CategoryRepository;

	//Add JPA repository tests (for all repositories)
	//i. Test create, delete and search functionalities
	@RunWith(SpringRunner.class)
	@DataJpaTest
	public class CategoryRepositoryTest {

		@Autowired
		private CategoryRepository crepository;
		
		@Test
		public void searchCategory() {

			assertThat(crepository.findByName("Food")).isNotNull();
		}


}
