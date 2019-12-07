package my.projects.library.beans;
import my.projects.library.beans.enums.BookStatus;
import lombok.Data;

@Data
public class Book {
	private Long id;
	private String name;
	private Author author;
	private Publisher publisher;
	private String inventor_number;
	private Integer page;
	private BookStatus status;
}
