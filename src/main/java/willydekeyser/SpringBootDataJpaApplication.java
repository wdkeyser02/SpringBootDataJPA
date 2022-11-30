package willydekeyser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import willydekeyser.model.Address;
import willydekeyser.model.Author;
import willydekeyser.model.Book;
import willydekeyser.model.Member;
import willydekeyser.repository.AddressRepository;
import willydekeyser.repository.BookRepository;
import willydekeyser.repository.MemberRepository;

@SpringBootApplication
public class SpringBootDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataJpaApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(MemberRepository memberRepository, AddressRepository addressRepository, BookRepository bookRepository) {
		return args -> {
			System.err.println("\n\nStart Run CommandLineRunner!\n\n");
			
			Address address = Address.builder()
					.street("Kerkstraat")
					.number("1")
					.zipCode("9000")
					.city("Gent")
					.phoneNumber("123456789")
					.build();
			
			Member member = Member.builder()
					.firstName("Willy")
					.lastName("De Keyser")
					.email("test@gmail.com")
					.birthday(LocalDate.of(1988, 7, 1))
					.address(address)
					.build();
			
			Book book1 = Book.builder()
					.bookName("Spring Boot in Action")
					.createdAt(LocalDateTime.now())
					.build();
			Book book2 = Book.builder()
					.bookName("Mastering Spring Boot")
					.createdAt(LocalDateTime.now())
					.build();
			Book book3 = Book.builder()
					.bookName("Learning Spring Boot")
					.createdAt(LocalDateTime.now())
					.build();
			
			Author author1 = Author.builder()
					.authorName("Willy de Keyser")
					.build();
			Author author2 = Author.builder()
					.authorName("Ken de Keyser")
					.build();
			Author author3 = Author.builder()
					.authorName("Bill Gates")
					.build();
			Author author4 = Author.builder()
					.authorName("Kesha Williams")
					.build();
			
			book1.addAuthor(author1);
			book1.addAuthor(author2);
			book2.addAuthor(author3);
			book3.addAuthor(author4);
			
			member.addBook(book1);
			member.addBook(book2);
			member.addBook(book3);
			
			memberRepository.save(member);
	
			memberRepository.findById(1L).ifPresent(return_member -> {
				List<Book> books = bookRepository.findAllByMemberId(return_member.getId());
				System.err.println(return_member);
				books.forEach(
						book -> {
							System.out.println("\t" + book);
							book.getAuthors().forEach(
								author -> {
									System.err.println("\t\t" + author);}
								);});
				});
			
			
			System.err.println("\n\nEND Run CommandLineRunner!\n\n");
		};
	}
}
