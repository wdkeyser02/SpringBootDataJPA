package willydekeyser.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import willydekeyser.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

	Optional<Member> findByEmail(String email);
	
	Optional<List<Member>> findAllByLastName(String lastname);
}