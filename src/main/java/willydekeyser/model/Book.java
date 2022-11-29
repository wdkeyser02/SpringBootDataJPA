package willydekeyser.model;

import static jakarta.persistence.GenerationType.SEQUENCE;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Book")
@Table(name = "book")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

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
	private Long id;
	
	@Column(name = "book_name",
			nullable = false)
	private String bookname;
	
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
}
