package com.sbuhacks.misohungry;

import java.util.ArrayList;

public class Recipe {
    private String title;
    private ArrayList ingredients;
    private String instructions;
    private String yields;
    private int totalTime;

    public Recipe(String title, ArrayList ingredients, String instructions, String yields, int totalTime){
        this.title = title;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.yields = yields;
        this.totalTime = totalTime;
    }

//    public Recipe setRecipe(String recipeString){
//        recipeString = recipeString.replace("=",":");
//
//        return new Recipe();
//    }
    public ArrayList getIngredients() {
        return ingredients;
    }

    public String getTitle() {
        return title;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getYields() {
        return yields;
    }

    public int getTotalTime() {
        return totalTime;
    }
    public String toString(){
        return title+","+ingredients+","+instructions+","+yields+","+totalTime;
    }
}
