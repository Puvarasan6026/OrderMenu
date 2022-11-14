/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordermenu;

public class Food implements Product {
    String name, file_path;
    double price;

    public Food(String name, String file_path, double price) {
        this.name = name;
        this.file_path = file_path;
        this.price = price;
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getFile_path() {
        return file_path;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
