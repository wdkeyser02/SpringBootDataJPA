package willydekeyser.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

	private Long id;
	private String firstname;
	private String lastname;
	private LocalDate birthday;
}
