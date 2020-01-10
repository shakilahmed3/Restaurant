
package restaurant;

/**
 *
 * @author Shakil Ahmed
 */
public class Food {

    int FoodId;
    String FoodName;
    Double ByingPrice;
    Double SellingPrice;
    Double Profit;

    public Food() {
    }

    public Food(String FoodName, Double ByingPrice, Double SellingPrice) {
        this.FoodName = FoodName;
        this.ByingPrice = ByingPrice;
        this.SellingPrice = SellingPrice;
       
    }

    
    public Food(int FoodId, String FoodName, Double ByingPrice, Double SellingPrice, Double Profit) {
        this.FoodId = FoodId;
        this.FoodName = FoodName;
        this.ByingPrice = ByingPrice;
        this.SellingPrice = SellingPrice;
        this.Profit = Profit;
    }

    public int getFoodId() {
        return FoodId;
    }

    public String getFoodName() {
        return FoodName;
    }

    public Double getByingPrice() {
        return ByingPrice;
    }

    public Double getSellingPrice() {
        return SellingPrice;
    }

    public Double getProfit() {
        return Profit;
    }

    public void setFoodId(int FoodId) {
        this.FoodId = FoodId;
    }

    public void setFoodName(String FoodName) {
        this.FoodName = FoodName;
    }

    public void setByingPrice(Double ByingPrice) {
        this.ByingPrice = ByingPrice;
    }

    public void setSellingPrice(Double SellingPrice) {
        this.SellingPrice = SellingPrice;
    }

    public void setProfit(Double Profit) {
        this.Profit = Profit;
    }

    @Override
    public String toString() {
        return "Food{" + "FoodId=" + FoodId + ", FoodName=" + FoodName + ", ByingPrice=" + ByingPrice + ", SellingPrice=" + SellingPrice + ", Profit=" + Profit + '}';
    }
    
    
    
}
