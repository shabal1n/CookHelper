package com.example.cookhelper;

import com.example.cookhelper.algorithms.Matcher;
import com.example.cookhelper.entities.History;
import com.example.cookhelper.entities.Ingredient;
import com.example.cookhelper.entities.Product;
import com.example.cookhelper.entities.Recipe;
import com.example.cookhelper.entities.Recipe_category;
import com.example.cookhelper.entities.Step;
import com.example.cookhelper.entities.User;
import oracle.jdbc.driver.OracleDriver;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DataDAO {
    public List<History> historyList;
    public List<Ingredient> ingredientsList;
    public List<Product> productsList;
    public List<Recipe> allRecipesList;
    public List<Step> recipeSteps;
    private final TreeMap<Integer, Recipe> matchingRecipes;

    public DataDAO() {
        historyList = new ArrayList<History>();
        ingredientsList = new ArrayList<Ingredient>();
        productsList = new ArrayList<Product>();
        allRecipesList = new ArrayList<Recipe>();
        recipeSteps = new ArrayList<Step>();
        fetchData();
        Matcher matcher = new Matcher(allRecipesList, productsList, ingredientsList);
        matcher.chooseBestMatches();
        matchingRecipes = matcher.chooseBestMatches();
    }

    public void testOracle() {
        try {
            //DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:XE", "admin", "admin");
            Statement stmt = con.createStatement();
            System.out.println("Connection established!!!");
            ResultSet rs = stmt.executeQuery("select * from USERS");
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));

            con.close();
        } catch (NoClassDefFoundError | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void fetchData() {
        User user = new User(1, "Artur", "artur.shab@yandex.ru", "artur233", "image",
                "male", 70, 193);
        Recipe_category cat = new Recipe_category(1, "Fast food");

        productsList.add(new Product(1, "Bread", "white bread", "image"));
        productsList.add(new Product(2, "Meat", "Cow meat", "image"));
        productsList.add(new Product(3, "Tomato", "Red tomato", "image"));
        productsList.add(new Product(4, "Iceberg", "Iceberg leaf", "image"));
        productsList.add(new Product(5, "Ketchup", "Red tomato ketchup", "image"));
        productsList.add(new Product(6, "Potatoes", "Potatoes", "image"));
        productsList.add(new Product(7, "Hot dog bun", "Bread for hot dog", "image"));
        productsList.add(new Product(8, "Sausage", "Sausage", "image"));
        productsList.add(new Product(9, "Lavash", "Lavash", "image"));

        allRecipesList.add(new Recipe(1, cat, "Burger", "Junk food", "image", cat.getName()));
        allRecipesList.add(new Recipe(2, cat, "French Fries", "Junk food", "image", cat.getName()));
        allRecipesList.add(new Recipe(3, cat, "Hot Dog", "Junk food", "image", cat.getName()));
        allRecipesList.add(new Recipe(4, cat, "Doner", "Junk food", "image", cat.getName()));


        ingredientsList.add(new Ingredient(1, getRecipeById(1), getProductById(1)));
        ingredientsList.add(new Ingredient(2, getRecipeById(1), getProductById(2)));
        ingredientsList.add(new Ingredient(3, getRecipeById(1), getProductById(3)));
        ingredientsList.add(new Ingredient(4, getRecipeById(1), getProductById(4)));
        ingredientsList.add(new Ingredient(5, getRecipeById(1), getProductById(5)));
        ingredientsList.add(new Ingredient(6, getRecipeById(2), getProductById(6)));
        ingredientsList.add(new Ingredient(7, getRecipeById(3), getProductById(7)));
        ingredientsList.add(new Ingredient(8, getRecipeById(3), getProductById(8)));
        ingredientsList.add(new Ingredient(9, getRecipeById(3), getProductById(5)));

        historyList.add(new History(1, user, allRecipesList.get(0), History.HistoryType.INFO, "23-05-2002"));
        historyList.add(new History(2, user, allRecipesList.get(1), History.HistoryType.INFO, "24-05-2002"));
        historyList.add(new History(3, user, allRecipesList.get(2), History.HistoryType.INFO, "25-05-2002"));
        historyList.add(new History(4, user, allRecipesList.get(3), History.HistoryType.INFO, "23-05-2002"));
        sortHistoryByDate();
    }

    public List<History> getHistoryList() {
        return historyList;
    }

    public List<Recipe> getAllRecipesList() {
        return allRecipesList;
    }

    public List<Product> getProductsList() {
        return productsList;
    }

    private void sortHistoryByDate() {
        Collections.sort(historyList, historyList.get(0).dateComparator());
    }

    public Recipe getRecipeById(int id) {
        for (int i = 0; i < allRecipesList.size(); i++) {
            if (allRecipesList.get(i).getId() == id) {
                return (Recipe) allRecipesList.get(i);
            }
        }
        throw new NullPointerException("Null pointed to recipe");
    }

    public Product getProductById(int id) {
        for (int i = 0; i < productsList.size(); i++) {
            if (productsList.get(i).getId() == id) {
                return (Product) productsList.get(i);
            }
        }
        throw new NullPointerException("Null pointed to product");
    }

    public List<Ingredient> getIngredientsByRecipeId(int id) {
        List<Ingredient> result = new ArrayList<>();
        for (int i = 0; i < ingredientsList.size(); i++) {
            if (ingredientsList.get(i).getRecipe().getId() == id) {
                result.add(ingredientsList.get(i));
            }
        }
        return result;
    }

    public List<Recipe> getMatchingRecipes() {
        List<Recipe> result = new ArrayList<>();
        for (Map.Entry<Integer, Recipe> entry : matchingRecipes.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }

}
