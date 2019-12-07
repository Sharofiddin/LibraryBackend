package my.projects.library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import my.projects.library.beans.Author;
import my.projects.library.beans.Response;
import my.projects.library.services.AuthorService;

@Slf4j
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;
	
	@GetMapping("/api/authors")
	public List<Author> getBookList(){
		log.info("getting all authors from db");
		return authorService.findAll();
	}
	
	@PostMapping("/api/insert_author")
	public Response insertAuthor(@RequestBody Author author){
		log.info("Insert author  to db. Author = {}", author);
		Response response = new Response();
		Boolean inserted  = authorService.insertAuthor(author);
		response.setStatus(inserted);
		response.setText("Muallif muvaffaqiyatli kiritildi");
		return response;
	}

}
