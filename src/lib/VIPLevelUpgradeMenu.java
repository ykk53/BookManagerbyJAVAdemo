package lib;

import data.Account;
import data.VIPLevel;

import java.util.Scanner;

import static data.VIPLevel.*;

public class VIPLevelUpgradeMenu {
    public static void display(Scanner ioIn, Account loggedInAccount) {

        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- 会员等级升级 ---");
            VIPLevel currentLevel = loggedInAccount.getVIPLevel();
            System.out.println("您当前的会员等级是：" + currentLevel);
            if (loggedInAccount.getVIPLevel().equals(VIPLevel.DIAMOND)) {
                System.out.println("您已经是最高级会员，无需升级。");
                exit = true;
                break;
            }
            else{
                System.out.println("\n请选择您想要升级到的会员等级：");
                int choiceCounter = 1;
                for (VIPLevel level : VIPLevel.values()) {
                    if (level.ordinal() > currentLevel.ordinal()) { // 只显示比当前等级高的选项
                        System.out.println(choiceCounter + ". " + loggedInAccount.getLevelDisplayName(level) + " (需要差价:" + (loggedInAccount.getVIPPrice(level) - loggedInAccount.getVIPPrice()) + " 元)");
                        choiceCounter++;
                    }
                }
                System.out.println("0. 返回用户菜单");
                System.out.print("请输入您的选择：");

                if (ioIn.hasNextInt()) {
                    int choice = ioIn.nextInt();
                    ioIn.nextLine(); // 读取换行符

                    if (loggedInAccount.getVIPLevel().equals(VIPLevel.NORMAL)) {
                        switch (choice) {
                            case 1:
                                System.out.println("请支付差价");
                                // TODO：支付逻辑(暂不实现)
                                System.out.println("支付成功，正在为你升级为白银会员");
                                loggedInAccount.setVIPLevel(SILVER);
                                loggedInAccount.setFreeShippingCoupons();
                                System.out.println("升级完成，谢谢惠顾");
                                exit = true;
                                break;
                            case 2:
                                System.out.println("请支付差价");
                                System.out.println("支付成功，正在为你升级为黄金会员");
                                loggedInAccount.setVIPLevel(GOLD);
                                loggedInAccount.setFreeShippingCoupons();
                                System.out.println("升级完成，谢谢惠顾");
                                exit = true;
                                break;
                            case 3:
                                System.out.println("请支付差价");
                                System.out.println("支付成功，正在为你升级为钻石会员");
                                loggedInAccount.setVIPLevel(DIAMOND);
                                loggedInAccount.setFreeShippingCoupons();
                                System.out.println("升级完成，谢谢惠顾");
                                BooksFunction.ChooseEBook(ioIn);
                                exit = true;
                                break;
                            case 0:
                                System.out.println("返回用户菜单。");
                                exit = true;
                                break;
                            default:
                                System.out.println("无效的选择，请重新输入。");
                                break;
                        }
                    }
                    else if (loggedInAccount.getVIPLevel().equals(VIPLevel.SILVER)) {
                        switch (choice) {
                            case 1:
                                System.out.println("请支付差价");
                                System.out.println("支付成功，正在为你升级为黄金会员");
                                loggedInAccount.setVIPLevel(GOLD);
                                loggedInAccount.setFreeShippingCoupons();
                                System.out.println("升级完成，谢谢惠顾");
                                exit = true;
                                break;
                            case 2:
                                System.out.println("请支付差价");
                                System.out.println("支付成功，正在为你升级为钻石会员");
                                loggedInAccount.setVIPLevel(DIAMOND);
                                loggedInAccount.setFreeShippingCoupons();
                                System.out.println("升级完成，谢谢惠顾");
                                BooksFunction.ChooseEBook(ioIn);
                                exit = true;
                                break;
                            case 0:
                                System.out.println("返回用户菜单。");
                                exit = true;
                                break;
                            default:
                                System.out.println("无效的选择，请重新输入。");
                                break;
                        }
                    }
                    else if (loggedInAccount.getVIPLevel().equals(VIPLevel.GOLD)) {
                        switch (choice) {
                            case 1:
                                System.out.println("请支付差价");
                                System.out.println("支付成功，正在为你升级为钻石会员");
                                loggedInAccount.setVIPLevel(DIAMOND);
                                loggedInAccount.setFreeShippingCoupons();
                                System.out.println("升级完成，谢谢惠顾");
                                BooksFunction.ChooseEBook(ioIn);
                                exit = true;
                                break;
                            case 0:
                                System.out.println("返回用户菜单。");
                                exit = true;
                                break;
                            default:
                                System.out.println("无效的选择，请重新输入。");
                                break;
                        }
                    }
                } else {
                    System.out.println("输入格式错误，请输入数字选项。");
                    ioIn.nextLine(); // 清空缓冲区
                }
            }
        }
    }

}
