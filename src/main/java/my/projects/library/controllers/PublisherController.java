package my.projects.library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import my.projects.library.beans.Publisher;
import my.projects.library.services.PublisherService;

@Slf4j
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class PublisherController {
	@Autowired
	private PublisherService publisherService;
	
	@GetMapping("/api/publishers")
	public List<Publisher> getPublisherList(){
		log.info("getting all publishers from db");
		return publisherService.findAll();
	}
	
	@PostMapping("/api/insert_publisher")
	public void insertAuthor(@RequestBody Publisher publisher){
		log.info("Insert author  to db. Author = {}", publisher);
		publisherService.insertPublisher(publisher);
	}
}
