package willydekeyser;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import willydekeyser.model.Address;
import willydekeyser.model.Member;
import willydekeyser.repository.AddressRepository;
import willydekeyser.repository.MemberRepository;

@SpringBootApplication
public class SpringBootDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataJpaApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(MemberRepository memberRepository, AddressRepository addressRepository) {
		return args -> {
			System.err.println("\n\nStart Run CommandLineRunner!\n\n");
			Address address = Address.builder()
					.street("Kerkstraat")
					.number("125")
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
			memberRepository.save(member);
			address = Address.builder()
					.street("Kerkstraat")
					.number("125")
					.zipCode("9000")
					.city("Gent")
					.phoneNumber("123456789")
					.build();
			member = Member.builder()
					.firstName("Ken")
					.lastName("De Keyser")
					.email("test1@gmail.com")
					.birthday(LocalDate.of(1999, 7, 1))
					.address(address)
					.build();
			memberRepository.save(member);
			
			address = Address.builder()
					.street("Kerkstraat")
					.number("125")
					.zipCode("9000")
					.city("Gent")
					.phoneNumber("123456789")
					.build();
			member = Member.builder()
					.firstName("Bill")
					.lastName("Gates")
					.email("bill@gmail.com")
					.birthday(LocalDate.of(1980, 1, 1))
					.address(address)
					.build();
			memberRepository.save(member);
			System.err.println("\nMember list: " + memberRepository.findAll() + "\n");
			
			address = Address.builder()
					.street("Kerkstraat")
					.number("125")
					.zipCode("9000")
					.city("Gent")
					.phoneNumber("123456789")
					.build();
			member = Member.builder()
					.firstName("John")
					.lastName("Gates")
					.email("john@gmail.com")
					.birthday(LocalDate.of(1980, 1, 1))
					.address(address)
					.build();
			memberRepository.save(member);
			System.err.println("\nAddress list: " + addressRepository.findAll() + "\n");
			
			address = Address.builder()
					.street("Kerkstraat")
					.number("1000")
					.zipCode("1000")
					.city("Brussel")
					.phoneNumber("222222222")
					.build();
			member = Member.builder()
					.id(1L)
					.firstName("Walter")
					.lastName("De Keyser")
					.email("test@gmail.com")
					.birthday(LocalDate.of(1999, 11, 25))
					.address(address)
					.build();
			memberRepository.save(member);
			System.err.println("\nMember list: " + memberRepository.findAll() + "\n");
			
			
			System.err.println("\nFind Member By E-mail: " + memberRepository.findByEmail("test@gmail.com") + "\n");
			System.err.println("\nFind Member By lastname: " + memberRepository.findAllByLastName("De Keyser") + "\n");
			
			System.err.println("\n\nEND Run CommandLineRunner!\n\n");
		};
	}
}
