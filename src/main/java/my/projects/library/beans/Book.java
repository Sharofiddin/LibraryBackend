package my.projects.library.beans;
import my.projects.library.beans.enums.BookStatus;
import lombok.Data;

@Data
public class Book {
	private Long id;
	private String name;
	private Long author_id;
	private Long publisher_id;
	private String inventor_number;
	private Integer page;
	private BookStatus status;
}
