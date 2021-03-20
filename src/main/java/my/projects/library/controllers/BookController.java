package my.projects.library.controllers;

import java.util.List;

import my.projects.library.beans.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public void insertBook(@RequestBody Book book) {
        log.info("Inserting book  to db. Book = {} ...", book);
        Boolean inserted = bookService.insertBook(book);
        if (Boolean.TRUE.equals(inserted))
            log.info("Book inserted into db successfully");
        else
            log.error("Inserting book into db is failed!");
    }

    @PostMapping("/api/update_book")
    public Response updateBook(@RequestBody Book book) {
        log.info("Updating book  to db. Book = {} ...", book);
        Boolean updated = bookService.updateBook(book);
        Response response = new Response();
        response.setStatus(updated);
        if (updated) {
            response.setText("Kitob yangilandi");
        } else {
            response.setText("Bunday kitob mavjud emas");
        }
        return response;
    }

    @DeleteMapping("/api/delete_book/{id}")
    public Response deleteBook(@PathVariable Long id) {
        log.info("Delete book from db. B0ook = {} ...", id);
        Boolean deleted = bookService.deleteBook(id);
        Response response = new Response();
        response.setStatus(deleted);

        if(deleted){
			response.setText("Kitob o'chirildi");
		}else {
			response.setText("Kitob mavjud emas");
		}
        return  response;
    }
}
