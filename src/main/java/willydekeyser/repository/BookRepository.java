package willydekeyser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import willydekeyser.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

	List<Book> findAllByMemberId(Long id);
}
