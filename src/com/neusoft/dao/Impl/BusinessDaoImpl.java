package com.neusoft.dao.Impl;

import com.neusoft.dao.BusinessDao;
import com.neusoft.domain.Business;
import com.neusoft.domain.Food;
import com.neusoft.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eric Lee
 * @date 2020/8/7 14:52
 */
public class BusinessDaoImpl implements BusinessDao {
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    @Override
    public List<Business> listBusiness(String businessName, String businessAddress) {
        List<Business> list = new ArrayList<>();
        StringBuffer sql = new StringBuffer("select * from business where 1=1");
        if (businessName != null && !businessName.equals("")) {
            // 传入了商家名
            sql.append(" and businessName like '%").append(businessName).append("%' ");
        }
        if (businessAddress != null && !businessAddress.equals("")) {
            // 传入了商家名
            sql.append(" and businessAddress like '%").append(businessAddress).append("%' ");
        }
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Business business = new Business();
                business.setBusinessId(rs.getInt("businessId"));
                business.setPassword(rs.getString("password"));
                business.setBusinessName(rs.getString("businessName"));
                business.setBusinessAddress(rs.getString("businessAddress"));
                business.setBusinessExplain(rs.getString("businessExplain"));
                business.setStartPrice(rs.getDouble("starPrice"));
                business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
                list.add(business);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, pstmt, conn);
        }


        return list;
    }

    @Override
    public int saveBusiness(String businessName) {
        int businessId = 0;
        // 附带一个初始密码
        String sql = "insert into business(businessName, password)values(?, '123456')";
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            // 可以在prepareStatement中设置返回自增长列的值
            pstmt.setString(1, businessName);
            pstmt.executeUpdate();
            // 获取自增长的列
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                businessId = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, pstmt, conn);
        }
        return businessId;
    }

    @Override
    public List<Food> manage(int id) throws SQLException {
        List list = new ArrayList();
        conn = JDBCUtils.getConnection();
        String sql = "select * from food where businessId=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            Food food = new Food();
            food.setFoodId(rs.getInt(1));
            food.setFoodName(rs.getString(2));
            food.setFoodExplain(rs.getString(3));
            food.setFoodPrice(rs.getDouble(4));
            food.setBusinessId(rs.getInt(5));
            list.add(food);

        }
        return list;
    }

    @Override
    public int updata(Food food) throws SQLException {
        conn = JDBCUtils.getConnection();
        int sum = 0;
        String sql = "update food set foodName=?,foodExplain=?,foodPrice=? where foodId=? and businessId=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, food.getFoodName());
            pstmt.setString(2, food.getFoodExplain());
            pstmt.setDouble(3, food.getFoodPrice());
            pstmt.setInt(4, food.getFoodId());
            pstmt.setInt(5, food.getBusinessId());
            sum = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }

    @Override
    public int insert(Food food) throws SQLException {
        conn = JDBCUtils.getConnection();
        int sum = 0;
        int sum1 = 0;
        String sql = "select count(*) count from food where businessId=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, food.getBusinessId());
        rs = pstmt.executeQuery();
        while (rs.next()) {
            sum = rs.getInt("count");
        }
        if (sum > 0) {
            String sql1 = "insert into food(foodName,foodExplain,foodPrice,businessId)value(?,?,?,?)";
            pstmt = conn.prepareStatement(sql1);
            pstmt.setString(1, food.getFoodName());
            pstmt.setString(2, food.getFoodExplain());
            pstmt.setDouble(3, food.getFoodPrice());
            pstmt.setInt(4, food.getBusinessId());
            sum1 = pstmt.executeUpdate();
        } else {
            String sql2 = "insert into food(foodName,foodExplain,foodPrice,businessId)value(?,?,?,?)";
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, food.getFoodName());
            pstmt.setString(2, food.getFoodExplain());
            pstmt.setDouble(3, food.getFoodPrice());
            pstmt.setInt(4, food.getBusinessId());
            sum1 = pstmt.executeUpdate();
        }
        return sum1;
    }

    @Override
    public int delete(int foodId, int businessId) throws SQLException {
        conn = JDBCUtils.getConnection();
        System.out.println(foodId + "" + businessId);
        String sql = "delete from food where foodId=? and businessId=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, foodId);
        pstmt.setInt(2, businessId);
        int sum = pstmt.executeUpdate();
        return sum;
    }

    @Override
    public Business getBusinessByNameByPass(Integer businessId, String password) {
        Business business = null;
        String sql = "select * from business where businessId = ? and password = ?";
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, businessId);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                business = new Business();
                business.setBusinessId(rs.getInt("businessId"));
                business.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, pstmt, conn);
        }

        return business;
    }


}
