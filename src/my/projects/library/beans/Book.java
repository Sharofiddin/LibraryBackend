package my.projects.library.beans;

import lombok.Data;
import my.projects.library.beans.emuns.BookStatus;

@Data
public class Book {
	private Long id;
	private String name;
	private Long author_id;
	private Long publisher_id;
	private String inventor_number;
	private BookStatus status;
}
