package user.admin.libraian.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class book {
    @Id
    int bookId;
    String bookName;
    String authorName;
}
