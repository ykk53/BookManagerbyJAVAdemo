package data;
import com.sun.xml.internal.bind.v2.TODO;
/// 书籍类型
public class Book {
    /// 书籍属性
    private String name;
    private double price;
    private BookType type;
    private int bookID;

    public Book(String name, double price, BookType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }
/// 常规set和get指令
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public BookType getType() {
        return type;
    }

    public void setType(BookType type) {
        this.type = type;
    }
}