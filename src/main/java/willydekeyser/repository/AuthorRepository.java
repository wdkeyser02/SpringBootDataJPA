package willydekeyser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import willydekeyser.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{

}
