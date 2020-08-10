package com.neusoft.view;

import com.neusoft.domain.Business;
import com.neusoft.domain.Food;


import java.sql.SQLException;
import java.util.List;

public interface BusinessView {
    public void listBusinessAll();

    public void listBusinessBySearch();

    public void saveBusiness();

    public List<Food> getmanage(int id) throws SQLException;

    public int getupdate(Food food) throws SQLException;

    public int getinsert(Food food) throws SQLException;

    public int getdelete(int foodId, int businessId) throws SQLException;

    // 添加一个商家登录验证的方法
    public Business login(Integer businessId, String password);

}
