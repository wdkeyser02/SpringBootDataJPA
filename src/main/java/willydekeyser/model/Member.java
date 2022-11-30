package willydekeyser.model;

import static jakarta.persistence.GenerationType.SEQUENCE;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "Member")
@Table(
		name = "member",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = { "email" },
						name = "member_email_unique")}
		)
@Builder
@Data
@ToString(exclude = {"address", "books"})
@NoArgsConstructor
@AllArgsConstructor
public class Member {

	@Id
	@SequenceGenerator(
			name = "member_sequence",
			sequenceName = "member_sequence",
			allocationSize = 1)
	@GeneratedValue(
			strategy = SEQUENCE,
			generator = "member_sequence")
	@Column(
			name = "id",
			updatable = false)
	private Long id;
	
	@Column(
			name = "first_mame",
			nullable = false,
			columnDefinition = "TEXT")
	private String firstName;
	
	@Column(
			name = "last_mame",
			nullable = false,
			columnDefinition = "TEXT")
	private String lastName;
	
	@Column(
			name = "email",
			nullable = false,
			columnDefinition = "TEXT")
	private String email;
	
	@Column(
			name = "birthday",
			nullable = false,
			columnDefinition = "DATE")
	private LocalDate birthday;
	
	
	@OneToOne(
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.EAGER)
	@JoinColumn(
			name = "address_id",
			referencedColumnName = "id",
			foreignKey = @ForeignKey(name="Member_Address_FK"))
	private Address address;
	
	@OneToMany(mappedBy = "member",
			orphanRemoval = true,
			cascade = CascadeType.ALL,
			fetch = FetchType.EAGER
			)
	@Builder.Default
	private List<Book> books = new ArrayList<>();
	
	public void addBook(Book book) {
		if (!this.books.contains(book)) {
			this.books.add(book);
			book.setMember(this);
		}		
	}
	
	public void removeBook(Book book) {
		if (this.books.contains(book)) {
			this.books.remove(book);
			book.setMember(null);
		}
		
	}
	
}
