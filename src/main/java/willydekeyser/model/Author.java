package willydekeyser.model;

import static jakarta.persistence.GenerationType.SEQUENCE;

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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "Author")
@Table(name = "author",
uniqueConstraints = {
		@UniqueConstraint(columnNames = { "author_name" },
				name = "author_authorname_unique")})
@Builder
@Data
@ToString(exclude = "books")
@NoArgsConstructor
@AllArgsConstructor
public class Author {

	@Id
	@SequenceGenerator(
			name = "author_sequence",
			sequenceName = "author_sequence",
			allocationSize = 1)
	@GeneratedValue(
			strategy = SEQUENCE,
			generator = "author_sequence")
	@Column(
			name = "id",
			updatable = false)
	private Long id;
	
	@Column(name = "author_name",
			nullable = false,
			unique = true,
			columnDefinition = "TEXT")
	private String authorName;
	
	@ManyToMany(cascade = CascadeType.ALL,
			fetch = FetchType.EAGER
			)
	@JoinTable(name = "book_author",
		joinColumns = @JoinColumn(
			name = "author_id",
			foreignKey = @ForeignKey(name = "book_author_author_id_fk")),
		inverseJoinColumns = @JoinColumn(
			name = "book_id",
			foreignKey = @ForeignKey(name = "book_author_book_id_fk"))
			)
	@Builder.Default
	private List<Book> books = new ArrayList<>();
}