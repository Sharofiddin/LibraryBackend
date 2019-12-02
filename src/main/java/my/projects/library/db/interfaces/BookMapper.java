package my.projects.library.db.interfaces;

import java.util.List;

import my.projects.library.beans.Book;

public interface BookMapper {
	List<Book> selectBooks();

}
