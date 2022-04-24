package com.example.cookhelper.algorithms;

import com.example.cookhelper.entities.Ingredient;
import com.example.cookhelper.entities.Product;
import com.example.cookhelper.entities.Recipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class Matcher {
    private final List<Recipe> recipeList;
    private final List<Product> myProductList;
    private final List<Ingredient> ingredientList;
    private final TreeMap<Integer, Recipe> recipesBinary;


    public Matcher(List<Recipe> recipes, List<Product> products, List<Ingredient> ingredients) {
        recipeList = recipes;
        myProductList = products;
        ingredientList = ingredients;
        recipesBinary = new TreeMap<>(Collections.reverseOrder());
    }

    private void recipeMatcher() {
        for (Recipe recipe : recipeList) {
            List<Ingredient> thisIngredients = getIngredientsByRecipeId(recipe.getId());
            StringBuilder binary = new StringBuilder();
            for (Ingredient ingredient : thisIngredients) {
                if (myProductList.contains(ingredient.getProduct())) {
                    binary.append('1');
                } else {
                    binary.append('0');
                }
            }
            if (!binary.toString().equals("")) {
                int decimal = Integer.parseInt(String.valueOf(binary), 2);
                recipesBinary.put(decimal, recipe);
            }
        }
    }

    public TreeMap<Integer, Recipe> chooseBestMatches() {
        recipeMatcher();
        return recipesBinary;
    }

    private List<Ingredient> getIngredientsByRecipeId(int id) {
        List<Ingredient> result = new ArrayList<>();
        for (int i = 0; i < ingredientList.size(); i++) {
            if (ingredientList.get(i).getRecipe().getId() == id) {
                result.add(ingredientList.get(i));
            }
        }
        return result;
    }

}
