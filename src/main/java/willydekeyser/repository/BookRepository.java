package willydekeyser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import willydekeyser.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

	List<Book> findAllByMemberId(Long id);
}
