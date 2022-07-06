package user.admin.libraian.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.admin.libraian.Entity.book;
import user.admin.libraian.Repository.bookRepo;

@Service
public class bookService {
    @Autowired
    bookRepo repo;

    // add book
    public book addBook(book bookAdded){
        return repo.save(bookAdded);
    }

    // update book
    public book updateBook(book newData){
        if(repo.findById(newData.getBookId()).orElse(null)==null)
            return null;
        return repo.save(newData);
    }

    // find book
    public book getBook(int id){
        book currentBook = repo.findById(id).orElse(null);
        if(currentBook==null)
            return null;
        return currentBook;
    }

    // delete book
    public String deleteBook(int id){
        book currentBook = repo.findById(id).orElse(null);
        if(currentBook==null)
            return "no book with id "+id;
        repo.deleteById(id);
        return "deleted";
    }
}
