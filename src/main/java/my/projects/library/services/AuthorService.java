package my.projects.library.services;

import java.util.List;

import org.springframework.stereotype.Service;

import my.projects.library.beans.Author;
import my.projects.library.db.MyBatisHelper;

@Service
public class AuthorService {
	List<Author> findAll(){
		return new MyBatisHelper().selectList("selectAuthors");
	}

}
