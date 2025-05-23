import data.Account;
import lib.*;

public class Main {
    public static void main(String[] args) {
        /// 初始化菜单
        MainMenu mainMenu = new MainMenu();
        /// 初始化用户账号存储哈希表
        InputData.inputUser();
        /// 初始化图书存储哈希表
        InputData.inputBook();
        /// 进入主菜单
        mainMenu.displayMenu();
    }
}