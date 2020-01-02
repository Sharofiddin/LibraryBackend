package my.projects.library.services;

import java.util.List;

import org.springframework.stereotype.Service;

import my.projects.library.beans.Author;
import my.projects.library.db.MyBatisHelper;

@Service
public class AuthorService {
	public List<Author> findAll() {
		return new MyBatisHelper().selectList("selectAuthors");
	}

	public Boolean insertAuthor(Author author) {
		int count = new MyBatisHelper().insert("insertAuthor", author);
		return count > 0;
	}

	public Boolean updateAuthor(Author author) {
		int count = new MyBatisHelper().update("updateAuthor", author);
		return count > 0;
	}

}
