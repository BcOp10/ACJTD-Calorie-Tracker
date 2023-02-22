package HealthyAndFit;

public class FoodList {
    protected String foodName;
    private int calNumber;
    private String mealTime;

    public String getFoodName(){
        return foodName;
    }

    public void setFoodName(String foodName){
        this.foodName = foodName;
    }

    public int getCalNumber(){
        return calNumber;
    }

    public void setCalNumber(int calNumber){
        this.calNumber = calNumber;
    }

    public String getMealTime(){
        return mealTime;
    }

   public void setMealTime(String mealTime){
    this.mealTime = mealTime;
   }

    //Constructor
    public FoodList(String foodName, int calNumber, String mealTime){
        this.foodName = foodName;
        this.calNumber = calNumber;
        this.mealTime = mealTime;
        
    }
}