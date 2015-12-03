package com.bisikennadi.androidNews.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BNnadi on 12/3/2015.
 * com.bisikennadi.androidNews.model
 */
public class Recipe {

    public static final String URL_BASE = "http://bisikennadi.com/recipe/";
    private String id;
    private String title;
    private String photo;
    private String description;
    private String prepTime;

    private List<Ingredient> ingredients = new ArrayList<Ingredient>();
    private List<Step> instructions = new ArrayList<Step>();

    public Recipe(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return URL_BASE + this.id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(String prepTime) {
        this.prepTime = prepTime;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public List<Step> getInstructions() {
        return instructions;
    }

    public void addStep(Step step) {
        instructions.add(step);
    }

    public static class Ingredient {
        private String amount;
        private String description;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public static class Step {
        private String description;
        private  String photo;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }
}
