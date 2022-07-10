package user.admin.libraian.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.admin.libraian.Entity.book;
import user.admin.libraian.Repository.bookRepo;

import java.util.List;

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

    // search by book name or author
    public List<book> getAll(String name){
        return repo.findAllCustom(name);
    }

    // issue book
    public String issueBook(book obj){
        int copies = obj.getNumber_of_copies();
        if(copies>0){
            obj.setNumber_of_copies(copies-1);
            // need to add book issue data in user table
            return "book issued successfully";
        }
        return "no copies left";
    }

    public String returnBook(book obj){
        int copies = obj.getNumber_of_copies();
        obj.setNumber_of_copies(copies+1);
        // need to add book return data in user table
        return "book returned successfully";
    }
}
