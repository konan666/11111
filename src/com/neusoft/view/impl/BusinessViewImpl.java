package com.neusoft.view.impl;

import com.neusoft.dao.BusinessDao;
import com.neusoft.dao.Impl.BusinessDaoImpl;
import com.neusoft.domain.Business;
import com.neusoft.domain.Food;
import com.neusoft.view.BusinessView;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * @author Eric Lee
 * @date 2020/8/7 15:18
 */
public class BusinessViewImpl implements BusinessView {
    Scanner input = new Scanner(System.in);

    @Override
    public void listBusinessAll() {
        BusinessDaoImpl dao = new BusinessDaoImpl();
        List<Business> list = dao.listBusiness(null, null);
        System.out.println("商家编号\t商家名称\t商家地址\t商家介绍\t起送费\t配送费");
        for (Business b : list) {
            System.out.println(b.getBusinessId() + "\t" + b.getBusinessName() + "\t" + b.getBusinessAddress() + "\t" + b.getBusinessExplain() + "\t" + b.getStartPrice() + "\t" + b.getDeliveryPrice());
        }
    }

    /**
     * 搜索
     */
    @Override
    public void listBusinessBySearch() {
        String businessName = "";
        String businessAddress = "";
        String inputStr = "";
        System.out.println("是否需要输入商家名称关键字(y/n): ");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输入商家名称关键字：");
            businessName = input.next();
        }
        System.out.println("是否需要输入商家地址关键字(y/n): ");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输入商家地址关键字：");
            businessAddress = input.next();
        }

        BusinessDao dao = new BusinessDaoImpl();
        List<Business> list = dao.listBusiness(businessName, businessAddress);
        System.out.println("商家编号\t商家名称\t商家地址\t商家介绍\t起送费\t配送费");
        for (Business b : list) {
            System.out.println(b.getBusinessId() + "\t" + b.getBusinessName() + "\t" + b.getBusinessAddress() + "\t" + b.getBusinessExplain() + "\t" + b.getStartPrice() + "\t" + b.getDeliveryPrice());
        }

    }

    /**
     * 保存商家
     */
    @Override
    public void saveBusiness() {
        System.out.println("请输入商家名字：");
        String businessName = input.next();

        BusinessDaoImpl dao = new BusinessDaoImpl();
        int businessId = dao.saveBusiness(businessName);
        if (businessId > 0) {
            System.out.println("新建商家成功！ 商家编号为" + businessId);

        } else {
            System.out.println("新建商家失败！");

        }

    }

    @Override
    public List<Food> getmanage(int id) throws SQLException {
        BusinessDaoImpl dao = new BusinessDaoImpl();
        List list = dao.manage(id);
        return list;
    }

    @Override
    public int getupdate(Food food) throws SQLException {
        BusinessDaoImpl dao = new BusinessDaoImpl();
        int sum = dao.updata(food);
        return sum;
    }

    @Override
    public int getinsert(Food food) throws SQLException {
        BusinessDaoImpl dao = new BusinessDaoImpl();
        int sum = dao.insert(food);
        return sum;
    }

    @Override
    public int getdelete(int foodId, int businessId) throws SQLException {
        BusinessDaoImpl dao = new BusinessDaoImpl();
        int sum = dao.delete(foodId, businessId);
        return sum;
    }

    @Override
    public Business login(Integer businessId, String password) {
        BusinessDaoImpl dao = new BusinessDaoImpl();
        Business business = dao.getBusinessByNameByPass(businessId, password);
        return business;

    }
}
