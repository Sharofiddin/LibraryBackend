package my.projects.library.services;

import java.util.List;

import org.springframework.stereotype.Service;

import my.projects.library.beans.Book;
import my.projects.library.db.MyBatisHelper;

@Service
public class BookService {

	public List<Book> findAll() {
		return new MyBatisHelper().selectList("selectBooks");
	}
	
	public Boolean insertBook(Book book) {
		int count = new MyBatisHelper().insert("insertBook", book);
		return count > 0;
	}
}
