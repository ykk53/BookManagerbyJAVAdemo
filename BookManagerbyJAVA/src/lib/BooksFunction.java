package lib;
import data.Book;
import data.BookStorage;
import data.BookType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/// 存放所有对图书进行管理的操作 包括 增加 删除 查看列表 管理菜单
public class BooksFunction {
    //TODO：书籍查看的筛选功能
    public static Book displayBook(Scanner ioIn){
        int pageSize = 7;
        int pageNow = 0;
        List<Book> allBooks = new ArrayList<>( BookStorage.books.values());
        if (allBooks.isEmpty()){
            System.out.println("\n书库中暂时没有书籍。");
            return null; // 书库为空，返回 null
        }
        int totalPages = (int) Math.ceil((double) allBooks.size() / pageSize);

        while (true) {
            System.out.println("\n--- 书库 (第 " + (pageNow + 1) + " 页，共 " + totalPages + " 页) ---");

            int startIndex = pageNow * pageSize;
            int endIndex = Math.min(startIndex + pageSize, allBooks.size());

            for (int i = startIndex; i < endIndex; i++) {
                Book book = allBooks.get(i);
                System.out.println((i - startIndex + 1) + ". 书名: " + book.getName() + ", 价格: " + book.getPrice() + ", 类型: " + book.getType());
            }

            System.out.println("\n请选择操作：");
            System.out.println("输入 1-" + Math.min(pageSize, allBooks.size() - startIndex) + " 选择书籍");
            System.out.println("输入 8 查看下一页");
            System.out.println("输入 9 查看上一页");
            System.out.println("输入 10 进行筛选"); // 修改为返回
            System.out.println("输入 0 返回"); // 修改为返回
            System.out.print("请输入您的选择：");

            if (ioIn.hasNextInt()) {
                int choice = ioIn.nextInt();
                ioIn.nextLine(); // 读取换行符

                if (choice >= 1 && choice <= Math.min(pageSize, allBooks.size() - startIndex)) {
                    // 用户选择了书籍
                    Book selectedBook = allBooks.get(startIndex + choice - 1);
                    return selectedBook; // 返回选中的书籍
                } else if (choice == 8) {
                    // 查看下一页
                    if (pageNow < totalPages - 1) {
                        pageNow++;
                    } else {
                        System.out.println("已是最后一页。");
                    }
                } else if (choice == 9) {
                    // 查看上一页
                    if (pageNow > 0) {
                        pageNow--;
                    } else {
                        System.out.println("已是第一页。");
                    }
                } else if (choice == 0) {
                    // 退出
                    System.out.println("返回。");
                    return null; // 返回 null 表示没有选择书籍或退出
                } else if (choice == 10) {
                    // 筛选功能
                    BookFilterType(ioIn);
                }
                else {
                    System.out.println("无效的选择，请重新输入。");
                }
            } else {
                System.out.println("输入格式错误，请输入数字。");
                ioIn.nextLine(); // 清空缓冲区
            }
        }

    }///展示图书
    public static void add(Scanner ioIn) {
        System.out.println("\n请输入要添加的书籍名称：");
        String name = ioIn.nextLine();

        System.out.println("请输入书籍价格：");
        double price;
        if (ioIn.hasNextDouble()) {
            price = ioIn.nextDouble();
            ioIn.nextLine(); // 清空缓冲区
        } else {
            System.out.println("价格格式错误，请重新输入。");
            ioIn.nextLine(); // 清空缓冲区
            return;
        }

        System.out.println("请选择书籍类型 (输入对应的数字)：");
        BookType[] types = BookType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + ". " + types[i]);
        }

        int typeChoice;
        BookType selectedType = null;
        if (ioIn.hasNextInt()) {
            typeChoice = ioIn.nextInt();
            ioIn.nextLine(); // Consume newline
            if (typeChoice >= 1 && typeChoice <= types.length) {
                selectedType = types[typeChoice - 1];
            } else {
                System.out.println("无效的类型选择，请重新输入。");
                return;
            }
        } else {
            System.out.println("类型选择格式错误，请重新输入。");
            ioIn.nextLine(); // Consume invalid input
            return;
        }

        // 生成 BookID (简单地使用书名作为 Key)
        String bookID = name; // 暂时使用书名作为ID，之后可以完善

        Book newBook = new Book(name, price, selectedType);
        BookStorage.books.put(bookID, newBook);
        System.out.println("\n书籍 '" + name + "' 已成功添加到书库。");
    }///增加图书
    public static void delete(Scanner ioIn) {
        System.out.println("\n请输入要删除的书籍名称：");
        String nameToDelete = ioIn.nextLine();

        boolean removed = false;
        // 需要遍历 HashMap 的 KeySet 来安全地删除元素
        for (String key : BookStorage.books.keySet()) {
            Book book = BookStorage.books.get(key);
            if (book.getName().equals(nameToDelete)) {
                BookStorage.books.remove(key);
                System.out.println("书籍 '" + nameToDelete + "' 已成功删除。");
                removed = true;
                break; // 假设书名是唯一的，找到第一个匹配的就删除
            }
        }

        if (!removed) {
            System.out.println("书库中没有找到名为 '" + nameToDelete + "' 的书籍。");
        }
    }///删除图书
    public static void displayMenu(Scanner ioIn) {
        int choice;
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- 管理书籍 ---");
            System.out.println("请选择操作：");
            System.out.println("1. 添加书籍");
            System.out.println("2. 删除书籍");
            System.out.println("3. 在所有书籍中选择"); // 沿用之前的展示书籍功能
            System.out.println("0. 返回管理员菜单");
            System.out.print("请输入您的选择：");

            if (ioIn.hasNextInt()) {
                choice = ioIn.nextInt();
                ioIn.nextLine(); // 读取换行符

                if (choice == 1) {
                    add(ioIn); // 调用添加书籍的方法
                } else if (choice == 2) {
                    delete(ioIn); // 调用删除书籍的方法
                } else if (choice == 3) {
                    displayBookManage(ioIn); // 调用展示书籍的方法
                } else if (choice == 0) {
                    System.out.println("返回管理员菜单。");
                    exit = true;
                    break;
                } else {
                    System.out.println("无效的选择，请重新输入。");
                }
            } else {
                System.out.println("输入格式错误，请输入数字。");
                ioIn.nextLine(); // 清空缓冲区
            }
        }
    }///展示图书管理菜单

    public static void displayBookManage(Scanner ioIn){
        int pageSize = 7;
        int pageNow = 0;
        List<Book> allBooks = new ArrayList<>( BookStorage.books.values());
        if (allBooks.isEmpty()){
            System.out.println("\n书库中暂时没有书籍。");
        }
        int totalPages = (int) Math.ceil((double) allBooks.size() / pageSize);

        boolean exit = true;
        while (exit) {
            System.out.println("\n--- 书库 (第 " + (pageNow + 1) + " 页，共 " + totalPages + " 页) ---");

            int startIndex = pageNow * pageSize;
            int endIndex = Math.min(startIndex + pageSize, allBooks.size());

            for (int i = startIndex; i < endIndex; i++) {
                Book book = allBooks.get(i);
                System.out.println((i - startIndex + 1) + ". 书名: " + book.getName() + ", 价格: " + book.getPrice() + ", 类型: " + book.getType());
            }

            System.out.println("\n请选择操作：");
            System.out.println("输入 1-" + Math.min(pageSize, allBooks.size() - startIndex) + " 选择书籍");
            System.out.println("输入 8 查看下一页");
            System.out.println("输入 9 查看上一页");
            System.out.println("输入 0 返回"); // 修改为返回
            System.out.print("请输入您的选择：");

            if (ioIn.hasNextInt()) {
                int choice = ioIn.nextInt();
                ioIn.nextLine(); // 读取换行符

                if (choice >= 1 && choice <= Math.min(pageSize, allBooks.size() - startIndex)) {
                    // 用户选择了书籍
                    Book selectedBook = allBooks.get(startIndex + choice - 1);
                    System.out.println("您选择了书籍 '" + selectedBook.getName() + "'。");
                    System.out.println("请选择操作：");
                    System.out.println("1. 删除书籍");
                    System.out.println("2. 修改书籍价格");
                    System.out.println("0. 返回"); // 修改为返回

                    int subChoice;
                    if (ioIn.hasNextInt()) {
                        subChoice = ioIn.nextInt();
                        ioIn.nextLine(); // Consume newline
                        if (subChoice == 1) {
                            // 需要遍历 HashMap 的 KeySet 来安全地删除元素
                            for (String key : BookStorage.books.keySet()) {
                                Book book = BookStorage.books.get(key);
                                if (book.getName().equals(selectedBook.getName())) {
                                    BookStorage.books.remove(key);
                                    System.out.println("书籍 '" + selectedBook.getName() + "' 已成功删除。");
                                    break; 
                                }
                            }
                            exit = false; // 退出管理菜单
                        } else if (subChoice == 2) {
                            System.out.println("请输入新的价格：");
                            double newPrice;
                            if (ioIn.hasNextDouble()) {
                                newPrice = ioIn.nextDouble();
                                ioIn.nextLine();
                                selectedBook.setPrice(newPrice);
                                System.out.println("书籍 '" + selectedBook.getName() + "' 的价格已成功修改为 " + newPrice + "。");
                            } else {
                                System.out.println("价格格式错误，请重新输入。");
                            }
                            exit = false; // 退出管理菜单
                        }
                        else if (subChoice == 0) {  
                            exit = false; // 退出管理菜单
                        }
                        else {
                            System.out.println("无效的选择，请重新输入。");
                        }
                    } else {
                        System.out.println("输入格式错误，请输入数字。");
                        ioIn.nextLine();
                    }
                } else if (choice == 8) {
                    // 查看下一页
                    if (pageNow < totalPages - 1) {
                        pageNow++;
                    } else {
                        System.out.println("已是最后一页。");
                    }
                } else if (choice == 9) {
                    // 查看上一页
                    if (pageNow > 0) {
                        pageNow--;
                    } else {
                        System.out.println("已是第一页。");
                    }
                } else if (choice == 0) {
                    // 退出
                    System.out.println("返回。");
                    exit = false;
                } else {
                    System.out.println("无效的选择，请重新输入。");
                }
            } else {
                System.out.println("输入格式错误，请输入数字。");
                ioIn.nextLine(); // 清空缓冲区
            }
        }
    }

    public static void BookFilterType(Scanner ioIn){
        List<Book> allBooks = new ArrayList<>( BookStorage.books.values());
        if (allBooks.isEmpty()){
            System.out.println("\n书库中暂时没有书籍。");
        }
        BookType[] types = BookType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + ". " + types[i]);
        }
        System.out.println("请输入要筛选的书籍类型：");
        int typeChoice = ioIn.nextInt();
        BookType selectedType;
        if (typeChoice >= 1 && typeChoice <= types.length) {
            selectedType = types[typeChoice - 1];
        } else {
            System.out.println("无效的类型选择，请重新输入。");
            return;
        }
        List<Book> filterBooks = new ArrayList<>();
        for(Book book:allBooks){
            if(book.getType().equals(selectedType)){
                filterBooks.add(book);
            }
        }
        if(filterBooks.isEmpty()){
            System.out.println("没有找到符合条件的书籍。");
        }else{
            System.out.println("以下是符合条件的书籍：");
            for(Book book:filterBooks){
                System.out.println(book.getName());
            }
        }
    }    
}//按类型筛选书籍

