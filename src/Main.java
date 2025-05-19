import lib.MainMenu;
import lib.SUSignMenu;
import lib.UserRegisterMenu;
import lib.UserSignMenu;

public class Main {
    public static void main(String[] args) {
        /// 初始化菜单
        SUSignMenu suSignMenu = new SUSignMenu();
        UserSignMenu userSignMenu = new UserSignMenu();
        UserRegisterMenu userRegisterMenu = new UserRegisterMenu();
        MainMenu mainMenu = new MainMenu();
        /// 初始化用户账号存储表

        /// 进入主菜单
        mainMenu.displayMenu();

    }
}