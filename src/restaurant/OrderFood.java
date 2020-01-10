
package restaurant;

/**
 *
 * @author Shakil Ahmed
 */
public class OrderFood {
    
    int OrderId;
    int FoodId;
    String FoodName;
    int Quantity;
    int TableNo;
    int KitchenNo;
    Double TotalPrice;
    String OrderType;

    public OrderFood() {
    }

    public OrderFood(int OrderId, int FoodId, String FoodName, int Quantity, int TableNo, int KitchenNo, Double TotalPrice, String OrderType) {
        this.OrderId = OrderId;
        this.FoodId = FoodId;
        this.FoodName = FoodName;
        this.Quantity = Quantity;
        this.TableNo = TableNo;
        this.KitchenNo = KitchenNo;
        this.TotalPrice = TotalPrice;
        this.OrderType = OrderType;
    }

    public OrderFood(int OrderId, String FoodName, int Quantity, Double TotalPrice, String OrderType) {
        this.OrderId = OrderId;
        this.FoodName = FoodName;
        this.Quantity = Quantity;
        this.TotalPrice = TotalPrice;
        this.OrderType = OrderType;
    }

    public OrderFood(int OrderId, String FoodName, int Quantity, int TableNo, int KitchenNo, String OrderType) {
        this.OrderId = OrderId;
        this.FoodName = FoodName;
        this.Quantity = Quantity;
        this.TableNo = TableNo;
        this.KitchenNo = KitchenNo;
        this.OrderType = OrderType;
    }

   
       
    public int getOrderId() {
        return OrderId;
    }

    public int getFoodId() {
        return FoodId;
    }

    public String getFoodName() {
        return FoodName;
    }

    public int getQuantity() {
        return Quantity;
    }

    public int getTableNo() {
        return TableNo;
    }

    public int getKitchenNo() {
        return KitchenNo;
    }

    public Double getTotalPrice() {
        return TotalPrice;
    }

    public String getOrderType() {
        return OrderType;
    }

    public void setOrderId(int OrderId) {
        this.OrderId = OrderId;
    }

    public void setFoodId(int FoodId) {
        this.FoodId = FoodId;
    }

    public void setFoodName(String FoodName) {
        this.FoodName = FoodName;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public void setTableNo(int TableNo) {
        this.TableNo = TableNo;
    }

    public void setKitchenNo(int KitchenNo) {
        this.KitchenNo = KitchenNo;
    }

    public void setTotalPrice(Double TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    public void setOrderType(String OrderType) {
        this.OrderType = OrderType;
    }

    @Override
    public String toString() {
        return "OrderFood{" + "OrderId=" + OrderId + ", FoodId=" + FoodId + ", FoodName=" + FoodName + ", Quantity=" + Quantity + ", TableNo=" + TableNo + ", KitchenNo=" + KitchenNo + ", TotalPrice=" + TotalPrice + ", OrderType=" + OrderType + '}';
    }
    
    
    
}
