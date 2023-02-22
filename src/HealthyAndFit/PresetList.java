package HealthyAndFit;

public class PresetList {
    
    private String newFoodName;
    private int newCal;

    public String getNewFoodName(){
        return newFoodName;
    }

    public void setFoodName(String newFoodName){
        this.newFoodName = newFoodName;
    }

    public int getNewCal(){
        return newCal;
    }

    public void setCalNumber(int newCal){
        this.newCal = newCal;
    }


    //Constructor
    public PresetList(String newFoodName, int newCal){
        this.newFoodName = newFoodName;
        this.newCal = newCal;
        
    }



    









}
