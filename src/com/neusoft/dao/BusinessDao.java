package com.neusoft.dao;

import com.neusoft.domain.Admin;
import com.neusoft.domain.Business;
import com.neusoft.domain.Food;

import java.sql.SQLException;
import java.util.List;

public interface BusinessDao {
    // 显示所有商家列表  可选输入businessName和businessAddress
    public List<Business> listBusiness(String businessName, String businessAddress);

    public int saveBusiness(String businessName);

    public List<Food> manage(int id) throws SQLException;

    public int updata(Food food) throws SQLException;

    public int insert(Food food) throws SQLException;

    public int delete(int foodId, int businessId) throws SQLException;

    public Business getBusinessByNameByPass(Integer businessId, String password);

}
