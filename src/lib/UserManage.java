package lib;

import data.Account;
import data.AccountStorage;
import data.VIPLevel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserManage {

    public static void displayMenu(Scanner ioIn) {
        int choice;
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- 用户管理系统 ---");
            System.out.println("请选择操作：");
            System.out.println("1. 修改用户VIP等级");
            System.out.println("2. (功能待定)");
            System.out.println("3. (功能待定)");
            System.out.println("0. 返回管理员菜单");
            System.out.print("请输入您的选择：");

            if (ioIn.hasNextInt()) {
                choice = ioIn.nextInt();
                ioIn.nextLine(); // 读取换行符

                if (choice == 1) {
                    System.out.println("您选择了修改用户VIP等级 (功能待实现)。");
                    while(true){
                        Account selectedAccount =UserManage.viewAllUsers(ioIn);
                        if(selectedAccount == null) break;
                        else {
                            UserManage.UserVIPLevelChange(ioIn, selectedAccount);
                        }
                    }
                } else if (choice == 2) {
                    System.out.println("该功能暂未实现。");
                    // TODO: 审核用户注册信息
                } else if (choice == 3) {
                    System.out.println("该功能暂未实现。");
                    // TODO: 修改用户权限
                } else if (choice == 0) {
                    System.out.println("返回管理员菜单。");
                    exit = true;
                    break;
                } else {
                    System.out.println("无效的选择，请重新输入。");
                }
            } else {
                System.out.println("输入格式错误，请输入数字选项。");
                ioIn.nextLine(); // 清空缓冲区
            }
        }
    }

    public static void UserVIPLevelChange(Scanner ioIn, Account accountToModify) {
        System.out.println("\n--- 修改用户 " + accountToModify.getUserName() + " 的VIP等级 ---");
        System.out.println("当前VIP等级为：" + accountToModify.getVIPLevel());
        System.out.println("\n请选择要修改到的VIP等级：");
        int levelCounter = 1;
        for (VIPLevel level : VIPLevel.values()) {
            System.out.println(levelCounter + ". " + Account.getLevelDisplayName(level));
            levelCounter++;
        }
        System.out.print("请输入您的选择：");
        if (ioIn.hasNextInt()) {
            int levelChoice = ioIn.nextInt();
            ioIn.nextLine(); // Consume newline

            if (levelChoice >= 1 && levelChoice <= VIPLevel.values().length) {
                VIPLevel newVIPLevel = VIPLevel.values()[levelChoice - 1];
                accountToModify.setVIPLevel(newVIPLevel);
                accountToModify.setFreeShippingCoupons(); // 更新邮费券
                System.out.println("用户 " + accountToModify.getUserName() + " 的VIP等级已成功修改为：" + newVIPLevel);
            } else {
                System.out.println("无效的VIP等级选择。");
            }
        } else {
            System.out.println("输入格式错误，请输入数字选项。");
            ioIn.nextLine(); // Clear buffer
        }
    }

    public static Account viewAllUsers(Scanner ioIn) {
        int pageSize = 7; // 设置每页显示的用户数量
        int pageNow = 0;
        Map<String, Account> accounts = AccountStorage.accounts;
        List<Account> userList = new ArrayList<>(accounts.values());

        if (userList.isEmpty()) {
            System.out.println("\n当前没有任何注册用户。");
            return null;
        }

        int totalPages = (int) Math.ceil((double) userList.size() / pageSize);
        Account selectedAccount = null;
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- 所有用户信息 (第 " + (pageNow + 1) + " 页，共 " + totalPages + " 页) ---");

            int startIndex = pageNow * pageSize;
            int endIndex = Math.min(startIndex + pageSize, userList.size());

            for (int i = startIndex; i < endIndex; i++) {
                Account account = userList.get(i);
                System.out.println((i - startIndex + 1) + ". 用户名: " + account.getUserName() +
                        ", VIP等级: " + account.getVIPLevel() +
                        ", 邮费券数量: " + account.getFreeShippingCoupons());
            }

            System.out.println("\n请选择操作：");
            System.out.println("输入 1-" + Math.min(pageSize, userList.size() - startIndex) + " 选择用户");
            System.out.println("输入 8 查看下一页");
            System.out.println("输入 9 查看上一页");
            System.out.println("输入 0 返回用户管理菜单");
            System.out.print("请输入您的选择：");

            if (ioIn.hasNextInt()) {
                int choice = ioIn.nextInt();
                ioIn.nextLine(); // 读取换行符

                if (choice >= 1 && choice <= Math.min(pageSize, userList.size() - startIndex)) {
                    selectedAccount = userList.get(startIndex + choice - 1);
                    return selectedAccount;// 用户已选择，跳出循环
                } else if (choice == 8) {
                    if (pageNow < totalPages - 1) {
                        pageNow++;
                    } else {
                        System.out.println("已是最后一页。");
                    }
                } else if (choice == 9) {
                    if (pageNow > 0) {
                        pageNow--;
                    } else {
                        System.out.println("已是第一页。");
                    }
                } else if (choice == 0) {
                    System.out.println("返回用户管理菜单。");
                    return null; // 用户选择返回
                } else {
                    System.out.println("无效的选择，请重新输入。");
                }
            } else {
                System.out.println("输入格式错误，请输入数字。");
                ioIn.nextLine(); // 清空缓冲区
            }
        }
        return null;
    }
}