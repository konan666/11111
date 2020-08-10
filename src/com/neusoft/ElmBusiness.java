package com.neusoft;

import com.neusoft.dao.Impl.BusinessDaoImpl;
import com.neusoft.domain.Admin;
import com.neusoft.domain.Business;
import com.neusoft.domain.Food;
import com.neusoft.view.AdminView;
import com.neusoft.view.BusinessView;
import com.neusoft.view.impl.AdminViewImpl;
import com.neusoft.view.impl.BusinessViewImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * @author Eric Lee
 * @date 2020/8/10 09:04
 */
public class ElmBusiness {
    public static void main(String[] args) throws SQLException {
        work();
    }

    public static void work() throws SQLException {
        Scanner input = new Scanner(System.in);

        System.out.println("-----------------------------------------------------------");
        System.out.println("|\t\t\t\t饿了么控制台版后台管理系统 V1.0\t\t\t\t|");
        System.out.println("-----------------------------------------------------------");

        // 调用商家登录
        BusinessView businessView = new BusinessViewImpl();
        System.out.println("请输入商家编号：");
        Integer businessId = input.nextInt();
        System.out.println("请输入密码：");
        String password = input.next();
        Business business = businessView.login(businessId, password);


        if (business != null) {
            int menu = 0;
            System.out.println("~欢迎来到饿了么商家管理系统~");
            while (menu != 5) {

                // 创建一个菜单
                System.out.println("========= 一级菜单1.查看商家信息=2.修改商家信息=3.更新密码=4.所属商品管理=5.退出系统 =========");
                System.out.println("请选择相应的菜单编号");
                menu = input.nextInt();

                switch (menu) {
                    case 1:
                        businessView.listBusinessAll();
                        break;
                    case 2:
                        businessView.listBusinessBySearch();
                        break;
                    case 3:
                        businessView.saveBusiness();
                        break;
                    case 4:
                        System.out.println("========= 二级菜单1.查看食品列表=2.修改食品=3.新增食品=4.删除食品=5.退出系统 =========");

                        int menu1 = 0;
                        Scanner scanner = new Scanner(System.in);
                        menu1 = scanner.nextInt();

                        switch (menu1) {
                            case 1:
                                int id = businessId;
                                List<Food> list = businessView.getmanage(id);
                                for (Food i : list) {
                                    System.out.println(i);
                                }
                                break;
                            case 2:
                                Food food = new Food();
                                int id1 = businessId;
                                List<Food> list1 = businessView.getmanage(id1);
                                for (Food i : list1) {
                                    System.out.println(i);
                                }
                                Scanner input1 = new Scanner(System.in);
                                System.out.println("请输入你要修改的食品id");
                                int foodId = input1.nextInt();
                                System.out.println("请输入你要修改的食品名称");
                                String foodName = input1.next();
                                System.out.println("请输入你要修改的食品分类");
                                String foodExplain = input1.next();
                                System.out.println("请输入你要修改的食品金额");
                                Double foodPrice = input1.nextDouble();
                                food.setFoodId(foodId);
                                food.setFoodName(foodName);
                                food.setFoodExplain(foodExplain);
                                food.setFoodPrice(foodPrice);
                                food.setBusinessId(id1);
                                int sum = businessView.getupdate(food);
                                if (sum == 1) {
                                    System.out.println("修改成功");
                                    break;
                                } else {
                                    System.out.println("修改失败");

                                }
                                break;
                            case 3:
                                int id2 = businessId;
                                Food food1 = new Food();
                                Scanner input2 = new Scanner(System.in);
                                System.out.println("请输入你要新增的食品名称");
                                String foodName1 = input2.next();
                                System.out.println("请输入你要新增的食品分类");
                                String foodExplain1 = input2.next();
                                System.out.println("请输入你要新增的食品金额");
                                Double foodPrice1 = input2.nextDouble();
                                food1.setFoodName(foodName1);
                                food1.setFoodExplain(foodExplain1);
                                food1.setFoodPrice(foodPrice1);
                                food1.setBusinessId(id2);
                                int sum1 = businessView.getinsert(food1);
                                if (sum1 == 1) {
                                    System.out.println("新增成功");
                                    break;
                                } else {
                                    System.out.println("新增失败");

                                }
                                break;
                            case 4:
                                int id3 = businessId;
                                List<Food> list2 = businessView.getmanage(id3);
                                for (Food i : list2) {
                                    System.out.println(i);
                                }
                                Scanner input3 = new Scanner(System.in);
                                System.out.println("请输入你要删除的食品ID");
                                int foodId2 = input3.nextInt();
                                int sum2 = businessView.getdelete(foodId2, id3);
                                if (sum2 == 1) {
                                    System.out.println("删除成功");
                                    break;
                                } else {
                                    System.out.println("删除失败");

                                }
                                break;
                            case 5:
                                break;
                            default:
                                System.out.println("没有这个菜单项");
                                break;
                        }

                        break;
                    case 5:
                        System.out.println("========= 欢迎下次光临饿了么系统 =========");
                        break;
                    default:
                        System.out.println("没有这个菜单项");
                        break;
                }

            }

        } else {
            System.out.println("账号或密码有误请重新输入");
        }

    }
}
