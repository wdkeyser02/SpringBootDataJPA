package willydekeyser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import willydekeyser.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
