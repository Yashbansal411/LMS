package user.admin.libraian.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import user.admin.libraian.Entity.book;
import user.admin.libraian.Service.bookService;

@RestController
public class bookController {
    @Autowired
    bookService service;

    // get mapping
    @GetMapping("/getBook/{id}")
    public book getBook(@PathVariable int id){
    return service.getBook(id);
    }

    // post mapping
    @PostMapping("/add")
    public book addBook(@RequestBody book newBook){
        return service.addBook(newBook);
    }
    // PUT
    @PutMapping("/update")
    book updateBook(@RequestBody book update){
        return service.updateBook(update);
    }

    // delete
    @DeleteMapping("/delete/{id}")
    String deleteBook(@PathVariable int id){
        return service.deleteBook(id);
    }

}
