package viikko2.bookstore.Bookstoreprojekti.domain;

import org.springframework.data.repository.CrudRepository;

//Create crud repository for user entity
public interface UserRepository extends CrudRepository<User,Long> {
User findByUsername(String username);
//Metodi= kysely, Nyt voidaan etsiä tietokannasta usernamella käyttäjiä
}
