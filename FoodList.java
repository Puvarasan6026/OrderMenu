/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordermenu;

import java.util.List;
import java.util.ArrayList;


public class FoodList {
    List<Dessert> dessert_list = new ArrayList<>();
    List<Drink> drink_list = new ArrayList<>();
    List<Food> food_list = new ArrayList<>();
    List<Voucher> voucher_list = new ArrayList<>();
    Object[] objects =new Object[food_list.size()] ; 

    public FoodList() {
        
        Food f1 = new Food("Salad", "image/MedSalad.png", 3.50);
        Food f2 = new Food("Japanese Noodles", "image/JapanesePanNoodles.png", 4.50);
        Food f3 = new Food("Spaghetti", "image/spaghetti.jpg", 3.70);
        Food f4 = new Food("Spaghetti Meat Balls", "image/PadThai.png", 4.50);
        Food f5 = new Food("Noodles", "image/RamenNoodles.png", 5.50);
        Food f6 = new Food("Kids Spaghetti", "image/kids_spaghetti.png", 4.00);
        Food f7 = new Food("Chicken Rice", "image/chickenRice.jpg", 3.50);
        Food f8 = new Food("Thai Food", "image/thaiFood.jpg", 6.50);
        Food f9 = new Food("Vietnam Food", "image/vietnamFood.jpg", 6.50);
        food_list.add(f1);
        food_list.add(f2);
        food_list.add(f3);
        food_list.add(f4);
        food_list.add(f5);
        food_list.add(f6);
        food_list.add(f7);
        food_list.add(f8);
        food_list.add(f9);
        
        
        Drink d1 = new Drink("Raspberry", "image/raspberry.jpg", 3.50);
        Drink d2 = new Drink("Chocolate Pudding", "image/chocalate_pudding.jpg", 4.50);
        Drink d3 = new Drink("Blue Hawailan", "image/blue hawailan.jpg", 3.00);
        Drink d4 = new Drink("Pina", "image/Pina.jpg", 5.00);
        Drink d5 = new Drink("Lemon Ice", "image/lemon ice.jpg", 3.00);
        drink_list.add(d1);
        drink_list.add(d2);
        drink_list.add(d3);
        drink_list.add(d4);
        drink_list.add(d5);
        
        
        Dessert de1 = new Dessert("Strawberry Cake", "image/strawberry cake.jpg", 2.50);
        Dessert de2 = new Dessert("Chocolate Cake", "image/chocolate cake.jpg", 3.00);
        dessert_list.add(de1);
        dessert_list.add(de2);
        
        Voucher vo1 = new Voucher("0123", 10,"10 percent discount on total purchases");
        voucher_list.add(vo1);
        
        Voucher vo2 = new Voucher("01234", new String[]{"Strawberry Cake", "Chocolate Cake"}, 5,"RM 5 discount on bundled items for Strawberry Cake and Chocolate Cake");
        voucher_list.add(vo2);
        
     
    }
    
    
}
