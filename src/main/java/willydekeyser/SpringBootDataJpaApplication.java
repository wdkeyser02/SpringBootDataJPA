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
			memberRepository.save(member);
			System.err.println("\nSave Member & Address =============================================================\n");
			address = Address.builder()
					.street("Kerkstraat")
					.number("2")
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
			System.err.println("\nSave Member & Address =============================================================\n");
			address = Address.builder()
					.street("Kerkstraat")
					.number("3")
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
			System.err.println("\nSave Member & Address =============================================================\n");
			
			memberRepository.findAll().forEach(ret -> System.err.println(ret.getId() + " " + ret.getFirstName() + " " + 
					ret.getLastName() + " " + ret.getEmail() + " " + ret.getBirthday() + " " + ret.getAddress().getId() + " " + 
							ret.getAddress().getStreet() + " " + ret.getAddress().getNumber() + " " + ret.getAddress().getZipCode() + " " + 
					ret.getAddress().getCity() + " " + ret.getAddress().getPhoneNumber()));
			
			System.err.println("\nFind All Member & Address =============================================================\n");
			
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
			
			memberRepository.findAll().forEach(ret -> System.err.println(ret.getId() + " " + ret.getFirstName() + " " + 
			ret.getLastName() + " " + ret.getEmail() + " " + ret.getBirthday() + " " + ret.getAddress().getId() + " " + 
					ret.getAddress().getStreet() + " " + ret.getAddress().getNumber() + " " + ret.getAddress().getZipCode() + " " + 
			ret.getAddress().getCity() + " " + ret.getAddress().getPhoneNumber()));
			
			System.err.println("\nFindAll Member & Address =============================================================\n");	
		
			addressRepository.findAll().forEach(ret -> System.err.println(ret.getId() + " " + ret.getStreet() + " " + 
			ret.getZipCode() + " " + ret.getCity() + " " + ret.getPhoneNumber() + " " + ret.getMember().getId() + " " + 
					ret.getMember().getFirstName() + " " + ret.getMember().getLastName() + " " + ret.getMember().getEmail() + " " + ret.getMember().getBirthday()));
			
			System.err.println("\nSave Member & Address =============================================================\n");
			
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
			
			System.err.println("\nUpdate Member with id = 1 =============================================================\n");
			
			memberRepository.findAll().forEach(ret -> System.err.println(ret.getId() + " " + ret.getFirstName() + " " + 
					ret.getLastName() + " " + ret.getEmail() + " " + ret.getBirthday() + " " + ret.getAddress().getId() + " " + 
							ret.getAddress().getStreet() + " " + ret.getAddress().getNumber() + " " + ret.getAddress().getZipCode() + " " + 
					ret.getAddress().getCity() + " " + ret.getAddress().getPhoneNumber()));
			
			System.err.println("\nFind All Members =============================================================\n");
		
			memberRepository.findByEmail("test@gmail.com").ifPresent(ret -> System.err.println(ret.getId() + " " + ret.getFirstName() + " " + 
					ret.getLastName() + " " + ret.getEmail() + " " + ret.getBirthday() + " " + ret.getAddress().getId() + " " + 
							ret.getAddress().getStreet() + " " + ret.getAddress().getNumber() + " " + ret.getAddress().getZipCode() + " " + 
					ret.getAddress().getCity() + " " + ret.getAddress().getPhoneNumber()));
			
			System.err.println("\nMember findByEmail =============================================================\n");
			
			memberRepository.findAllByLastName("De Keyser").get().forEach(ret -> System.err.println(ret.getId() + " " + ret.getFirstName() + " " + 
					ret.getLastName() + " " + ret.getEmail() + " " + ret.getBirthday() + " " + ret.getAddress().getId() + " " + 
							ret.getAddress().getStreet() + " " + ret.getAddress().getNumber() + " " + ret.getAddress().getZipCode() + " " + 
					ret.getAddress().getCity() + " " + ret.getAddress().getPhoneNumber()));
			
			System.err.println("\nMember findByLastname ==============================================\n");
			
			memberRepository.findById(1L).ifPresent(ret -> System.err.println(ret.getId() + " " + ret.getFirstName() + " " + 
					ret.getLastName() + " " + ret.getEmail() + " " + ret.getBirthday() + " " + ret.getAddress().getId() + " " + 
							ret.getAddress().getStreet() + " " + ret.getAddress().getNumber() + " " + ret.getAddress().getZipCode() + " " + 
					ret.getAddress().getCity() + " " + ret.getAddress().getPhoneNumber()));
			
			System.err.println("\nMember findById ===========================================================\n");
			
			addressRepository.findById(5L).ifPresent(ret -> System.err.println(ret.getId() + " " + ret.getStreet() + " " + 
					ret.getZipCode() + " " + ret.getCity() + " " + ret.getPhoneNumber() + " " + ret.getMember().getId() + " " + 
							ret.getMember().getFirstName() + " " + ret.getMember().getLastName() + " " + ret.getMember().getEmail() + " " + ret.getMember().getBirthday()));
			System.err.println("\nAddress findById ===========================================================\n");
			System.err.println("\n\nEND Run CommandLineRunner!\n\n");
		};
	}
}
