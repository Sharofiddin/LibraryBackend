package my.projects.library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import my.projects.library.beans.Book;
import my.projects.library.services.BookService;

@Slf4j
@RestController
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping("/api/books")
	public List<Book> getPublisherList() {
		log.info("getting all books from db");
		return bookService.findAll();
	}
	
	@PostMapping("/api/insert_book")
	public void insertBook(@RequestBody Book book){
		log.info("Inserting book  to db. Book = {} ...", book);
		Boolean inserted = bookService.insertBook(book);
		if(inserted)
			log.info("Book inserted into db successfully");
		else
			log.error("Inserting book into db is failed!");
	}
}
