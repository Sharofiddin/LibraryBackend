package my.projects.library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import my.projects.library.beans.Book;
import my.projects.library.services.BookService;
@Slf4j
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/api/books")
	public List<Book> getBookList(){
		log.info("getting all books from db");
		return bookService.findAll();
	}

}
