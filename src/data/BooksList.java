package data;
import java.util.ArrayList;
import java.util.List;
/// 程序内部静态存储的书籍
public class BooksList {
    public static List<Book> initialBooks = new ArrayList<>();

    static {
        initialBooks.add(new Book("Java编程思想", 79.00, BookType.SCIENCE_TECHNOLOGY_PROGRAMMING));
        initialBooks.add(new Book("活着", 25.00, BookType.LITERATURE_FICTION));
        initialBooks.add(new Book("经济学原理", 120.00, BookType.ECONOMICS_MANAGEMENT));
        initialBooks.add(new Book("小王子", 19.90, BookType.CHILDREN_S_BOOKS));
        initialBooks.add(new Book("三体", 55.00, BookType.SCIENCE_TECHNOLOGY_PROGRAMMING));
        initialBooks.add(new Book("Effective Java", 68.00, BookType.SCIENCE_TECHNOLOGY_PROGRAMMING));
        initialBooks.add(new Book("平凡的世界", 45.00, BookType.LITERATURE_FICTION));
    }

    public static List<Book> getInitialBooks() {
        return initialBooks;
    }
}