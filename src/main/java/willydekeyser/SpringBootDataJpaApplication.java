package willydekeyser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import willydekeyser.model.Address;
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
			Book book = Book.builder()
					.bookname("Spring Boot in Action")
					.createdAt(LocalDateTime.now())
					.build();
			member.addBook(book);
			member.addBook(Book.builder()
					.bookname("Mastering Spring Boot")
					.createdAt(LocalDateTime.now())
					.build());
			member.addBook(Book.builder()
					.bookname("Learning Spring Boot")
					.createdAt(LocalDateTime.now())
					.build());
			memberRepository.save(member);
			
			System.err.println("\nSave Member & Books =============================================================\n");
			
			memberRepository.findById(1L).ifPresent(ret -> {
				List<Book> books = bookRepository.findAllByMemberId(ret.getId());
				System.err.print("\n\n" + ret.getId() + " " + ret.getFirstName() + " " + ret.getLastName() + " " + ret.getEmail());
				System.err.println(" - " + ret.getAddress().getId() + " " + ret.getAddress().getStreet() + " " + ret.getAddress().getNumber() + " " + 
					ret.getAddress().getZipCode() + " " + ret.getAddress().getCity() + " " + ret.getAddress().getPhoneNumber());		
				books.forEach(ret_book -> System.err.println("\t" + ret_book.getId() + " " + ret_book.getBookname() + " " + ret_book.getCreatedAt()));
				});
			
			System.err.println("\n=============================================================\n");
			
			bookRepository.findAll().forEach(ret -> {
				Member members = memberRepository.findById(ret.getMember().getId()).get();
				System.err.println(ret.getId()  + " " + ret.getBookname());
				System.err.println("\t" + members.getId() + " - " + members.getFirstName() + " " + members.getLastName() + "\n\n");
				
			
			
			});
			
			
			
			
			System.err.println("\n\nEND Run CommandLineRunner!\n\n");
		};
	}
}
