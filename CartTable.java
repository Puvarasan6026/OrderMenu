/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordermenu;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


class CartTable extends JTable {
    
static CartTable cart = new CartTable();

    public CartTable() {
		
    }
    
    public static CartTable getInstance()
    {
        return cart;
    }
}
