package lib;

import data.*;
import java.util.List;
/// 初始化程序导入预存储的数据，可拓展从数据库导入

public class InputData {

    public static void inputUser() {
        System.out.println("开始导入初始用户数据...");
        List<Account> users = UsersList.getInitialUsers();
        for (Account user : users) {
            AccountStorage.accounts.put(user.getUserName(), user);
        }
        System.out.println("初始用户数据导入完成。");
    }

    public static void inputBook() {
        System.out.println("开始导入初始图书数据...");
        List<Book> books = BooksList.getInitialBooks();
        for (Book book : books) {
            BookStorage.books.put(book.getName(), book); // 假设书名作为 Key
        }
        System.out.println("初始图书数据导入完成。");
    }
}