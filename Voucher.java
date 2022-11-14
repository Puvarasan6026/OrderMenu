/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordermenu;
import java.io.Serializable;

public class Voucher implements Serializable{
    String number;
    double price_percentage, price_deduct;
    String[] food;
    String description ; 
    public Voucher(String number, double price_percentage, String description) {
        this.number = number;
        this.price_percentage = price_percentage;
        this.description = description ; 
    }

    public Voucher(String number, String[] food, double price_deduct, String description) {
        this.number = number;
        this.food = food;
        this.price_deduct = price_deduct;
        this.description = description ; 
    }
    public String getDesc() {
        return description ; 
    }
    public String getNumber() {
        return number;
    }

    public double getPricePercentage() {
        return price_percentage;
    }

    public double getPrice_deduct() {
        return price_deduct;
    }

    
    public String[] getFood() {
        return food;
    }
    
    @Override
    public String toString() {
        return new StringBuffer(this.getNumber()).append(" ").append(this.price_percentage).append(" \"")
                .append(this.description).append("\"").toString();
    }
    
    
}
