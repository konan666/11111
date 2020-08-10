import com.neusoft.domain.Food;
import com.neusoft.view.BusinessView;
import com.neusoft.view.impl.BusinessViewImpl;

import java.sql.SQLException;
import java.util.List;

public class test {
    public static void main(String[] args) throws SQLException {
        BusinessView view = new BusinessViewImpl();
        Food food = new Food();
        food.setFoodName("123123");
        food.setFoodExplain("123123");
        food.setFoodPrice(1.0);
        food.setBusinessId(10001);
        int sum = view.getinsert(food);
        System.out.println(sum);
    }
}
