package my.projects.library.controllers;

import java.util.List;

import my.projects.library.beans.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import my.projects.library.beans.Publisher;
import my.projects.library.services.PublisherService;

@Slf4j
@RestController
public class PublisherController {
	@Autowired
	private PublisherService publisherService;

	@GetMapping("/api/publishers")
	public List<Publisher> getPublisherList() {
		log.info("getting all publishers from db");
		return publisherService.findAll();
	}

	@PostMapping("/api/insert_publisher")
	public void insertPublisher(@RequestBody Publisher publisher){
		log.info("Insert pulisher  to db. Publisher = {}", publisher);
		publisherService.insertPublisher(publisher);
	}

	@PostMapping("/api/update_publisher")
	public Response updatePublisher(@RequestBody Publisher publisher){
		log.info("Update pulisher  to db. Publisher = {}", publisher);
		Boolean updated =  publisherService.updatePublisher(publisher);
		Response response = new Response();
		response.setStatus(updated);
		if (updated){
			response.setText("Publisher yangilandi");
		}else {
			response.setText("Bunday publisher mavjud emas");
		}
		return response;
	}

	@DeleteMapping("/api/delete_publisher/{id}")
	public Response deletePublisher(@PathVariable Long id){
		log.info("Delete pulisher  to db. Publisher = {}", id);
		log.info("Delete book from db. B0ook = {} ...", id);
		Boolean deleted = publisherService.deletePublisher(id);
		Response response = new Response();
		response.setStatus(deleted);

		if(deleted){
			response.setText("Publisher o'chirildi");
		}else {
			response.setText("Publisher mavjud emas");
		}
		return  response;
	}
}
