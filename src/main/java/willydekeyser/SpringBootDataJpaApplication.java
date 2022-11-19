package willydekeyser;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import willydekeyser.model.Member;
import willydekeyser.repository.MemberRepository;

@SpringBootApplication
public class SpringBootDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataJpaApplication.class, args);
	}

	//@Bean
	CommandLineRunner commandLineRunner(MemberRepository memberRepository) {
		return args -> {
			System.err.println("\n\nStart Run CommandLineRunner!\n\n");
			Member member = Member.builder()
					.firstName("Willy")
					.lastName("De Keyser")
					.email("test@gmail.com")
					.birthday(LocalDate.of(1988, 7, 1))
					.build();
			memberRepository.save(member);
			member = Member.builder()
					.firstName("Ken")
					.lastName("De Keyser")
					.email("test1@gmail.com")
					.birthday(LocalDate.of(1999, 7, 1))
					.build();
			memberRepository.save(member);
			member = Member.builder()
					.firstName("Bill")
					.lastName("Gates")
					.email("bill@gmail.com")
					.birthday(LocalDate.of(1980, 1, 1))
					.build();
			memberRepository.save(member);
			System.err.println("\nMember list: " + memberRepository.findAll() + "\n");
			
			member = Member.builder()
					.id(1L)
					.firstName("Walter")
					.lastName("De Keyser")
					.email("test@gmail.com")
					.birthday(LocalDate.of(1999, 11, 25))
					.build();
			memberRepository.save(member);
			System.err.println("\nMember list: " + memberRepository.findAll() + "\n");
			
			
			System.err.println("\nFind Member By E-mail: " + memberRepository.findByEmail("test@gmail.com") + "\n");
			System.err.println("\nFind Member By lastname: " + memberRepository.findAllByLastName("De Keyser") + "\n");
			
			System.err.println("\n\nEND Run CommandLineRunner!\n\n");
		};
	}
}
