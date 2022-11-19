package willydekeyser.model;

import static jakarta.persistence.GenerationType.SEQUENCE;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Member_Address")
@Table(name = "member_address")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	@Id
	@SequenceGenerator(
			name = "address_sequence",
			sequenceName = "address_sequence",
			allocationSize = 1
			)
	@GeneratedValue(
			strategy = SEQUENCE,
			generator = "addressr_sequence"
			)
	@Column(
			name = "id",
			updatable = false,
			unique = true
			)
	private Long id;
	
	@Column(
			name = "street",
			nullable = false,
			columnDefinition = "TEXT"
			)
	private String street;
	
	@Column(
			name = "number",
			nullable = false,
			columnDefinition = "TEXT"
			)
	private String number;
	
	@Column(
			name = "zip_code",
			nullable = false,
			columnDefinition = "TEXT"
			)
	private String zipCode;
	
	@Column(
			name = "city",
			nullable = false,
			columnDefinition = "TEXT"
			)
	private String city;
	
	@Column(
			name = "phone_number",
			nullable = true,
			columnDefinition = "TEXT"
			)
	private String phoneNumber;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(
			name = "member_id",
			referencedColumnName = "id",
			foreignKey = @ForeignKey(
					name = "member_address_member_if_fk"
					)
			)
	private Member member;
}
