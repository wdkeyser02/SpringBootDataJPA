package willydekeyser.model;

import static jakarta.persistence.GenerationType.SEQUENCE;

import java.time.LocalDateTime;
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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "Book")
@Table(name = "book",
uniqueConstraints = {
		@UniqueConstraint(columnNames = { "book_name" },
				name = "book_bookname_unique")})
@Builder
@Data
@ToString(exclude = {"member", "authors"})
@NoArgsConstructor
@AllArgsConstructor
public class Book {

	@SuppressWarnings("removal")
	@Id
	@SequenceGenerator(
			name = "book_sequence",
			sequenceName = "book_sequence",
			allocationSize = 1)
	@GeneratedValue(
			strategy = SEQUENCE,
			generator = "book_sequence")
	@Column(
			name = "id",
			updatable = false)
	@org.hibernate.annotations.ForeignKey(name = "testen")
	private Long id;
	
	@Column(name = "book_name",
			nullable = false,
			columnDefinition = "TEXT")
	private String bookName;
	
	@Column(name = "created_at",
			nullable = false,
			columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private LocalDateTime createdAt;
	
	@ManyToOne(
			fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id",
				nullable = false,
				referencedColumnName = "id",
				foreignKey = @ForeignKey(
						name = "member_book_fk")
			)
	private Member member;
	
	@ManyToMany(mappedBy = "books", 
			cascade = CascadeType.PERSIST,
			fetch = FetchType.EAGER)
	@Builder.Default
	private List<Author> authors = new ArrayList<>();
	
	
	public void addAuthor(Author author) {
		authors.add(author);
		author.getBooks().add(this);
	}
	
	public void removeAuthor(Author author) {
		authors.remove(author);
		author.getBooks().remove(this);
	}

//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Book other = (Book) obj;
//		return true;
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(bookName);
//	}
	
	
}
