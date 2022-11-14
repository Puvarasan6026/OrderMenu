/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordermenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import static ordermenu.CartTable.cart;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class FoodMenu {
	static private JFrame frame;
	static private JButton backButton, orderButton, voucherButton;
	static private JTextField textField, textFieldVoucher;
	static private GridBagConstraints gbc;
	private JTable table;
	DefaultTableModel dtm;
	Double[] price;
	Double[] priceDrinks;
	Double[] priceDesserts;
//        List<Double>tp = new ArrayList<>();
        Map<String,Double> tpdes = new HashMap<String,Double>();
        Map<String,Double> tpfood = new HashMap<String,Double>();
        Map<String,Double> tpdrink = new HashMap<String,Double>();
	double totalPrice = 0;
	double p1, p2, p3, p4, p5, p6, p7, p8, p9;
	double d1, d2, d3, d4, d5;
	double de1, de2;

	private JSpinner[] numSpinner;
	static private JLabel[] foodLabel;
	static private JLabel[] foodImage;
	private String[] file;

	private JSpinner[] numSpinnerDrinks;
	static private JLabel[] drinkLabel;
	static private JLabel[] drinkImage;
	private String[] fileDrinks;

	private JSpinner[] numSpinnerDesserts;
	static private JLabel[] dessertLabel;
	static private JLabel[] dessertImage;
	private String[] fileDesserts;

	private static final int ELEMENTS = 9;
	private static final int DRINK_ELEMENTS = 5;
	private static final int DESSERT_ELEMENTS = 2;

	double total = 0;
	double food1, food2, food3, food4, food5, food6, food7, food8, food9;
	double drink1, drink2, drink3, drink4, drink5;
	double pr, pr1;

	double totalForFoods;
	double totalForDrinks;
	double totalForDesserts;
        FoodList food = new FoodList();
        String filepath = "vouchers.txt";
        

	void createAndShowGUI() throws IOException {
		frame = new JFrame("Main Menu ");
		frame.setBounds(100, 150, 850, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		JLabel lblFoodOrdered = new JLabel("Food Ordered");
		lblFoodOrdered.setBounds(529, 11, 81, 14);

		frame.getContentPane().add(lblFoodOrdered);

		backButton = new JButton();
		orderButton = new JButton();
		dtm = new DefaultTableModel(0, 0);
		final String header[] = new String[] { "Item", "Qty", "Price", "Spinner" };
		dtm.setColumnIdentifiers(header);
		dtm.addRow(header);
		table = new JTable();
                table.setModel(dtm);
		table.setBounds(475, 31, 1, 1); // int x, int y, int width, int height
		table.setSize(245, 300); // width,height
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(30);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.getColumnModel().getColumn(3).setMinWidth(0); // hide spinner
															// column
		table.getColumnModel().getColumn(3).setMaxWidth(0); // hide spinner
															// column
		table.setShowGrid(false); // remove cell boarder
                
		frame.getContentPane().add(table);
		JLabel lblTotal = new JLabel("Total  : ");
		lblTotal.setBounds(519, 340, 46, 14);
		frame.getContentPane().add(lblTotal);
		textField = new JTextField();
		textField.setBounds(585, 340, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		orderButton = new JButton("Order");
		orderButton.setBounds(500, 405, 89, 23);
		frame.getContentPane().add(orderButton);
		backButton = new JButton("Back");
		backButton.setBounds(610, 405, 89, 23);
		frame.getContentPane().add(backButton);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		addIt(tabbedPane, "Foods");
		addIt1(tabbedPane, "Drinks");
		addIt2(tabbedPane, "Desserts");
                addIt3(tabbedPane,"Voucher") ; 
		tabbedPane.setBounds(18, 11, 450, 450);
		frame.getContentPane().add(tabbedPane);
		frame.setVisible(true);

//                Voucher

                JLabel lblVoucher = new JLabel("Voucher No : ");
		lblVoucher.setBounds(479, 370, 80, 14);
		frame.getContentPane().add(lblVoucher);
                textFieldVoucher = new JTextField();
		textFieldVoucher.setBounds(585, 370, 86, 20);
		frame.getContentPane().add(textFieldVoucher);
		textFieldVoucher.setColumns(10);
                voucherButton = new JButton("Add voucher");
		voucherButton.setBounds(680, 368, 105, 23);
		frame.getContentPane().add(voucherButton);
                
                
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MainMenu menu = new MainMenu();
					menu.main(header);
					// menu.createAndShowGUI();
					menu.setVisible(true);
					// setVisible(false);
					frame.dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		orderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "You not ordered anything yet");
				} else {
					try {
						OrderMenu order = new OrderMenu();
						order.main(header);
						order.setVisible(true);
						setVisible(false);
						frame.dispose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		});
                
                voucherButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldVoucher.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "You have not entered voucher no yet!");
				} else {
					for (int i=0; i<food.voucher_list.size(); i++){
                                                                if (textFieldVoucher.getText().equals(food.voucher_list.get(i).getNumber())) {
                                                                        if (food.voucher_list.get(i).food == null)
                                                                        {
                                                                            total = total * (100- food.voucher_list.get(i).getPricePercentage()) / 100;
                                                                            textField.setText(String.format("%.2f",total));
                                                                            textFieldVoucher.setText("");
                                                                            JOptionPane.showMessageDialog(null, "Voucher accepted");
                                                                            return;
                                                                        }
                                                                        else 
                                                                        {
                                                                            final int rows = table.getRowCount();
                                                                            String [] vd = food.voucher_list.get(i).getFood();
                                                                            int check_voucher = 0;
                                                                            for (int row = 0; row < rows; row++) {
                                                                                for (int j=0; j<vd.length; j++) 
                                                                                {
                                                                                    if (dtm.getValueAt(row, 0) == vd[j]) {
                                                                                        check_voucher++;
                                                                                    }
                                                                                }
                                                                            }
                                                                            if (vd.length == check_voucher)
                                                                            {
                                                                                total = total - food.voucher_list.get(i).getPrice_deduct();
                                                                                textField.setText(String.format("%.2f",total));
                                                                                textFieldVoucher.setText("");
                                                                                JOptionPane.showMessageDialog(null, "Voucher accepted");
                                                                                return;
                                                                            }
                                                                            else
                                                                            {
                                                                                JOptionPane.showMessageDialog(null, "Voucher requirements not fulfilled in order list");
                                                                                return;
                                                                            }
                                                                        }
                                                                }
//                                                                else
//                                                                            {
//                                                                                JOptionPane.showMessageDialog(null, "Voucher not valid");
//                                                                            }
                                                            }
                                        
                                         JOptionPane.showMessageDialog(null, "Voucher not valid");
//						final int rows = table.getRowCount();
//                                                for (int row = 0; row < rows; row++) {
//                                                        if (dtm.getValueAt(row, 3) == e.getSource()) {
//                                                            for (int i=0; i<food.voucher_list.size(); i++){
//                                                                if (dtm.getValueAt(row, 0).equals(food.voucher_list.get(i).getNumber())) {
//                                                                        dtm.setValueAt(quantity, row, 1); // obj, row, column
//                                                                        dtm.setValueAt(p1 * quantity, row, 2);
//                                                                        food1 = p1 * quantity;
//                                                                }
//                                                            }
//                                                        }
//                                                }

//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
				}
			}

		});
	}

	void addIt(JTabbedPane tabbedPane, String text) throws IOException {
		JPanel panel = new JPanel(new GridBagLayout());
		gbc = new GridBagConstraints(); // getting constraints for the specified
										// component
		gbc.insets = new Insets(10, 0, 0, 0);
		foodImage = new JLabel[ELEMENTS];
		foodLabel = new JLabel[ELEMENTS];
		numSpinner = new JSpinner[ELEMENTS];
		file = new String[ELEMENTS];
		price = new Double[ELEMENTS];
                
                for (int i=0; i<food.food_list.size(); i++)
                {
		file[i] = food.food_list.get(i).getFile_path();

                
		foodLabel[i] = new JLabel(food.food_list.get(i).getName());
                
		price[i] = food.food_list.get(i).getPrice();
                }
//		file[0] = "image/MedSalad.png";
//		file[1] = "image/JapanesePanNoodles.png";
//		file[2] = "image/spaghetti.jpg";
//		file[3] = "image/PadThai.png";
//		file[4] = "image/RamenNoodles.png";
//		file[5] = "image/kids_spaghetti.png";
//		file[6] = "image/chickenRice.jpg";
//		file[7] = "image/thaiFood.jpg";
//		file[8] = "image/vietnamFood.jpg";
//		foodLabel[0] = new JLabel("Salad");
//		foodLabel[1] = new JLabel("Japanese Noodles");
//		foodLabel[2] = new JLabel("Spaghetti");
//		foodLabel[3] = new JLabel("Spaghetti Meat Balls");
//		foodLabel[4] = new JLabel("Noodles");
//		foodLabel[5] = new JLabel("Kids Spaghetti");
//		foodLabel[6] = new JLabel("Chicken Rice");
//		foodLabel[7] = new JLabel("Thai Food");
//		foodLabel[8] = new JLabel("Vietnam Food");
//		price[0] = 3.50;
//		price[1] = 4.50;
//		price[2] = 3.70;
//		price[3] = 4.50;
//		price[4] = 5.50;
//		price[5] = 4.00;
//		price[6] = 3.50;
//		price[7] = 6.50;
//		price[8] = 6.50;
		for (int i = 0; i < ELEMENTS; i++) {
			
			System.out.println(file[i]);	
			try {
			
			Image image = ImageIO.read(this.getClass().getResource(file[i]));
			Image imageScaled = image.getScaledInstance(80, 95, Image.SCALE_SMOOTH);
//			Image image = ImageIO.read(file[i]);
//			Image imageScaled = image.getScaledInstance(80, 95, Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(imageScaled);
			SpinnerNumberModel spnummodel = new SpinnerNumberModel(0, 0, 10, 1); // value,minimum,maximum,stepSize
			numSpinner[i] = new JSpinner(spnummodel);
			numSpinner[i].addChangeListener(listener);
			foodImage[i] = new JLabel(imageIcon);
			}catch(Exception e) {
				System.out.print(e);
			}
		}
		gbc.gridx = 0; // gridx 0 represent the most left
		for (int i = 0; i < ELEMENTS; i++) {
			if (i % 3 == 0) {
				gbc.gridy += 2;
				gbc.gridx = 0;
			}
			panel.add(foodImage[i], gbc);
			gbc.gridy++; // gridy---> add one row,for foodLabel
			panel.add(foodLabel[i], gbc);
			gbc.gridy--; // remove the row
			gbc.gridx++; // move to next column
			panel.add(numSpinner[i], gbc);
			gbc.gridx++; // move to next column
			tabbedPane.addTab(text, panel);
		}
	}

	void addIt2(JTabbedPane tabbedPane, String text) throws IOException {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		dessertImage = new JLabel[DESSERT_ELEMENTS];
		dessertLabel = new JLabel[DESSERT_ELEMENTS];
		numSpinnerDesserts = new JSpinner[DESSERT_ELEMENTS];
		priceDesserts = new Double[DESSERT_ELEMENTS];

		fileDesserts = new String[DESSERT_ELEMENTS];
                
//                fileDesserts[0] = new String("image/strawberry cake.jpg");
//		fileDesserts[1] = new String("image/chocolate cake.jpg");
//
//		dessertLabel[0] = new JLabel("Strawberry Cake");
//		dessertLabel[1] = new JLabel("Chocolate Cake");
//
//		priceDesserts[0] = 2.50;
//		priceDesserts[1] = 3.00;

                 
                for (int i=0; i<food.dessert_list.size(); i++)
                {
		fileDesserts[i] = food.dessert_list.get(i).getFile_path();

                
		dessertLabel[i] = new JLabel(food.dessert_list.get(i).getName());
                
		priceDesserts[i] = food.dessert_list.get(i).getPrice();
                }
		for (int i = 0; i < DESSERT_ELEMENTS; i++) {
			Image image = ImageIO.read(this.getClass().getResource(fileDesserts[i]));
			Image imageScaled = image.getScaledInstance(80, 95, Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(imageScaled);
			SpinnerNumberModel spnummodel = new SpinnerNumberModel(0, 0, 10, 1); // value,minimum,maximum,stepSize
			numSpinnerDesserts[i] = new JSpinner(spnummodel);
			numSpinnerDesserts[i].addChangeListener(listenerForDesserts);
			dessertImage[i] = new JLabel(imageIcon);
		}
		gbc.gridx = 0; // gridx 0 represent the most left
		gbc.insets = new Insets(10, 5, 0, 0); // top,left,bottom,right
		for (int i = 0; i < DESSERT_ELEMENTS; i++) {
			if (i % 3 == 0) {
				gbc.gridx = 0;
				gbc.gridy += 2;
			}
			panel.add(dessertImage[i], gbc);
			gbc.gridy++; // gridy---> add one row,for foodLabel
			panel.add(dessertLabel[i], gbc);
			gbc.gridy--; // remove the row
			gbc.gridx++; // move to next column
			panel.add(numSpinnerDesserts[i], gbc);
			gbc.gridx++; // move to next column
			tabbedPane.addTab(text, panel);
		}

	}

	void addIt1(JTabbedPane tabbedPane, String text) throws IOException {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		drinkImage = new JLabel[DRINK_ELEMENTS];
		drinkLabel = new JLabel[DRINK_ELEMENTS];
		numSpinnerDrinks = new JSpinner[DRINK_ELEMENTS];
		priceDrinks = new Double[DRINK_ELEMENTS];

		fileDrinks = new String[DRINK_ELEMENTS];
//		fileDrinks[0] = new String("image/raspberry.jpg");
//		fileDrinks[1] = new String("image/chocalate_pudding.jpg");
//		fileDrinks[2] = new String("image/blue hawailan.jpg");
//		fileDrinks[3] = new String("image/Pina.jpg");
//		fileDrinks[4] = new String("image/lemon ice.jpg");
//
//		drinkLabel[0] = new JLabel("Raspberry");
//		drinkLabel[1] = new JLabel("Chocolate Pudding");
//		drinkLabel[2] = new JLabel("Blue Hawailan");
//		drinkLabel[3] = new JLabel("Pina");
//		drinkLabel[4] = new JLabel("Lemon Ice");
//
//		priceDrinks[0] = 3.50;
//		priceDrinks[1] = 4.50;
//		priceDrinks[2] = 3.00;
//		priceDrinks[3] = 5.00;
//		priceDrinks[4] = 3.00;

                for (int i=0; i<food.drink_list.size(); i++)
                {
		fileDrinks[i] = food.drink_list.get(i).getFile_path();

                
		drinkLabel[i] = new JLabel(food.drink_list.get(i).getName());
                
		priceDrinks[i] = food.drink_list.get(i).getPrice();
                }
                
		for (int i = 0; i < DRINK_ELEMENTS; i++) {
			Image image = ImageIO.read(this.getClass().getResource(fileDrinks[i]));
			Image imageScaled = image.getScaledInstance(80, 95, Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(imageScaled);
			SpinnerNumberModel spnummodel = new SpinnerNumberModel(0, 0, 10, 1); // value,minimum,maximum,stepSize
			numSpinnerDrinks[i] = new JSpinner(spnummodel);
			numSpinnerDrinks[i].addChangeListener(listenerForDrinks);
			drinkImage[i] = new JLabel(imageIcon);
		}
		gbc.gridx = 0; // gridx 0 represent the most left
		gbc.insets = new Insets(10, 5, 0, 0); // top,left,bottom,right
		for (int i = 0; i < DRINK_ELEMENTS; i++) {
			if (i % 3 == 0) {
				gbc.gridx = 0;
				gbc.gridy += 2;
			}
			panel.add(drinkImage[i], gbc);
			gbc.gridy++; // gridy---> add one row,for foodLabel
			panel.add(drinkLabel[i], gbc);
			gbc.gridy--; // remove the row
			gbc.gridx++; // move to next column
			panel.add(numSpinnerDrinks[i], gbc);
			gbc.gridx++; // move to next column
			tabbedPane.addTab(text, panel);

		}
	}
        public void WriteObjectToFile(Voucher serObj, String filepath, boolean overwrite) {
 
            BufferedWriter bw = null;
        try {
            
//            FileOutputStream fileOut = new FileOutputStream(filepath);
//            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
//            objectOut.writeObject(serObj);
//            objectOut.flush();
//            objectOut.close();
//            System.out.println("The Object  was succesfully written to a file");
//            System.out.println(serObj);
            
            //Specify the file name and path here
//            File file = new File("C:/myfile.txt");
            File file = new File(filepath);
            /* This logic will make sure that the file 
             * gets created if it is not present at the
             * specified location*/
             if (!file.exists()) {
                file.createNewFile();
             }
             
             FileWriter fw = new FileWriter(file, overwrite);
	  bw = new BufferedWriter(fw);
          bw.newLine();
	  bw.write(serObj.toString());
          System.out.println("File written Successfully");
          bw.close();
          
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
        
        public void ReadFileToObject(String filepath)
        {
            try {
            BufferedReader br=new BufferedReader(
                          new FileReader(new File(filepath)));
            String line;
            String text = "";
            try {
                while ((line = br.readLine()) != null) {
//                       line = br.readLine();
                       String[] wordss = line.split("\"");
                       String[] words = line.split(" ");
                       if (words.length == 3)
                       {
                            Voucher voc = new Voucher(words[0], Double.parseDouble(words[1]), words[2]);
                            food.voucher_list.add(voc);
                            System.out.println(voc);
                       }
                       else 
                       {
                           int food_array_length = words.length - 2;
                           String[] food_array = new String[food_array_length];
                           
                           for (int i = 1; i< words.length; i++)
                               food_array[i-1] = words[i];
                           Voucher voc = new Voucher(words[0], food_array, Double.parseDouble(words[2]), words[3]);
                           food.voucher_list.add(voc);
                           System.out.println(voc);
                       }
                }
                System.out.println(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        }
        void addVoucherFrame (DefaultTableModel model){
               
                            ArrayList<String> dessert_list2 = new ArrayList<>();
                            ArrayList<String> drink_list2 = new ArrayList<>();
                            ArrayList<String> food_list2 = new ArrayList<>();
                          for(int i =0 ; i < food.food_list.size();i++){
                          
                              food_list2.add(food.food_list.get(i).getName());
                          }
                           for(int i =0 ; i < food.drink_list.size();i++){
                          
                              drink_list2.add(food.drink_list.get(i).getName());
                          }
                             for(int i =0 ; i < food.dessert_list.size();i++){
                          
                              dessert_list2.add(food.dessert_list.get(i).getName());
                          }
                          ArrayList <String> foods = new ArrayList(); 
                          foods.addAll(food_list2);
                          foods.addAll(drink_list2);
                          foods.addAll(dessert_list2);
                        
                        JFrame addFrame = new JFrame() ; 
                        JTextField code = new JTextField(10); 
                        JTextField price_ded_per = new JTextField(10); 
                        JTextField desc = new JTextField(10); 
 
                        DefaultListModel dm = new DefaultListModel(); 
                         for (int i = 0 ; i < foods.size();i++){
                              dm.add(i,foods.get(i));
                          }
                        

                        System.out.println(dm);
                        JList listFood = new JList(dm) ;
                        listFood.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                        
                        JLabel codeLabel = new JLabel("Code") ; 
                        JLabel price_per_label = new JLabel("Price deduct/ Percentage") ; 
                        JLabel description = new JLabel("Description") ; 
                         JLabel bundle_food = new JLabel("Bundle Food") ;
                        addFrame.setVisible(true); 
                        addFrame.setSize(600,400); 
                        addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
                        JPanel addForm = new JPanel(new GridBagLayout()) ; 
                        JButton addVoucher = new JButton("Add") ; 
                        GridBagConstraints c = new  GridBagConstraints();
                        c.insets = new Insets(10,10,10,10); 
                        c.gridx=  1 ; 
                        c.gridy=0;
                        addForm.add(codeLabel,c);
                        c.gridx= 2 ; 
                        c.gridy=0;
                         addForm.add(code,c); 
                        c.gridx= 1 ; 
                        c.gridy=1;
                        addForm.add(bundle_food,c); 
                         c.gridx= 2 ; 
                        c.gridy=1;
                        
                        addForm.add(new JScrollPane(listFood),c); 
                         c.gridx= 1 ; 
                        c.gridy=2;
                        addForm.add(price_per_label,c) ; 
                        c.gridx= 2 ; 
                        c.gridy=2;
                        addForm.add(price_ded_per,c); 
                        c.gridx= 1 ; 
                        c.gridy=3;
                        addForm.add(description,c); 
                        c.gridx= 2 ; 
                        c.gridy=3;
                        addForm.add(desc,c);
                        c.gridx= 3 ; 
                        c.gridy=4;
                        addForm.add(addVoucher,c); 
                        addFrame.add(addForm); 
                        addVoucher.addActionListener(new ActionListener(){
                      @Override
                      public void actionPerformed(ActionEvent e) {
                          String cd = code.getText();
                          double price_percent ;
                          try{
                              price_percent = Double.parseDouble(price_ded_per.getText()); 
                          }catch(NumberFormatException r ){
                              System.out.println(r);
                              return ; 
                          }
                          
                          //double price_percent = Double.valueOf(price_per_label.getText());
                          String descript = desc.getText();
                         
                              
                              Voucher[] voucher = null ; 
                          if (!listFood.isSelectionEmpty()){
                             String[] food_voucher = new String [listFood.getSelectedValuesList().size()];
                             int i = 0;
                              for (Object list: listFood.getSelectedValuesList())
                              {
                                  food_voucher[i] = list.toString();
                                  i++;
                              }
                               Voucher voc = new Voucher(code.getText(), food_voucher, price_percent, desc.getText());
                               food.voucher_list.add(voc);
//                              WriteObjectToFile(voc, filepath, true);
                              
                          }else if (listFood.isSelectionEmpty() ){
                             
//                             
                              Voucher voc = new Voucher(code.getText(), price_percent, desc.getText());
                              food.voucher_list.add(voc);
                              //WriteObjectToFile(voc, filepath, true);
                           }
                            JOptionPane.showMessageDialog(null, "Voucher added successfully");
                            addFrame.dispose();
                            Object[][] columnData = new Object[1][2];
                            columnData[0][0] = food.voucher_list.get(food.voucher_list.size()-1).getNumber();
                            columnData[0][1] = food.voucher_list.get(food.voucher_list.size()-1).getDesc();
                            model.addRow(columnData[0]);
                             System.out.println( columnData[0][0]);
                      }
                        
                        }); 
        }
        
        void addIt3(JTabbedPane tabbedPane, String text) throws IOException {
		JPanel panel = new JPanel(new BorderLayout());
               
		gbc = new GridBagConstraints(); // getting constraints for the specified
										// component
		gbc.insets = new Insets(10, 0, 0, 0);
//               ReadFileToObject(filepath);
              
                String[] columnNames = {"Code","Description"};
                Object[][] data = new Object[food.voucher_list.size()][food.voucher_list.size()+1] ; 
                for(int i=0 ; i<food.voucher_list.size();i++){
                    for(int x=0;x<1; x++){
                        data[i][x] =food.voucher_list.get(i).getNumber() ; 
                        System.out.println(data[i][x]);
                    data[i][x+1] =food.voucher_list.get(i).getDesc() ; 
                        System.out.println( data[i][x+1]);
                    }
                    
                }

                   DefaultTableModel model = new DefaultTableModel(data,columnNames); 
                   JTable voucher = new JTable(model) ; 
                   voucher.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
                   JButton button = new JButton ("Add") ; 
                   button.setBackground(Color.DARK_GRAY); 
                   button.setForeground(Color.white);
                   
                   
                   JButton button2 = new JButton ("Delete"); 
                   button2.setBackground(Color.RED); 
                   button2.setForeground(Color.white);
                   button2.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(voucher.getSelectedRow() != -1){
                            food.voucher_list.remove(voucher.getSelectedRow());
//                            int i = 0;
//                              for(Voucher vouc: food.voucher_list)
//                              {
//                                  if (i == 0)
//                                      WriteObjectToFile(vouc, filepath, false);
//                                  else
//                                      WriteObjectToFile(vouc, filepath, true);
//                              }
                            model.removeRow(voucher.getSelectedRow());
                            JOptionPane.showMessageDialog(null, "Selected Voucher deleted successfully");
                        }
                    }
                       
                   });
                   JPanel bottomPanel = new JPanel(new BorderLayout()); 
                   bottomPanel.add(button,BorderLayout.WEST); 
                   bottomPanel.add(button2,BorderLayout.EAST); 
                   
                 button.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            addVoucherFrame(model); 
//                            data[data.length][0] =food.voucher_list.get(food.voucher_list.size()).getNumber() ; 
//                            System.out.println(data[data.length][0]);
//                            data[data.length][1] =food.voucher_list.get(food.voucher_list.size()).getDesc() ; 
                    }
                   });
                   panel.add(new JScrollPane(voucher)) ; 
                    panel.add(bottomPanel,BorderLayout.PAGE_END);
                    panel.revalidate(); 
                    panel.repaint();
                    tabbedPane.addTab(text, panel);
                
                   
            
	}

	ChangeListener listener = new ChangeListener() {
		public void stateChanged(ChangeEvent e) {

			final int quantity = (int) ((JSpinner) e.getSource()).getValue();
			final int rows = table.getRowCount();
			for (int row = 0; row < rows; row++) {
                            
                            if (dtm.getValueAt(row, 3) == e.getSource()) {
                                        for (int j=0; j<food.food_list.size(); j++){
                                            if (dtm.getValueAt(row, 0).equals(food.food_list.get(j).getName())) {
                                                    dtm.setValueAt(quantity, row, 1); // obj, row,
                                                    //tp[row] = food.dessert_list.get(j).getPrice() * quantity; // column
                                                   for (Map.Entry<String,Double> tp1 : tpfood.entrySet())
                                                   {
                                                        if (tp1.getKey().equals(dtm.getValueAt(row, 0))) {
                                                            tp1.setValue(food.food_list.get(j).getPrice() * quantity);
                                                        }
                                                    }
                                                    dtm.setValueAt(food.food_list.get(j).getPrice() * quantity, row, 2);
                                            } 
                                        }
					if (quantity == 0) {
						dtm.removeRow(row);
					}
                                        totalForFoods = 0;
                                    for (Map.Entry<String,Double> tp1 : tpfood.entrySet())
                                        {
                                            if (tp1.getValue() != null) {
                                                totalForFoods += tp1.getValue();
                                            }
                                        }
					total = totalForFoods + totalForDrinks + totalForDesserts;
					textField.setText(String.format("%.2f",total));
					return;
				}
                            
                            /////
//				if (dtm.getValueAt(row, 3) == e.getSource()) {
//					if (dtm.getValueAt(row, 0).equals("Salad")) {
//						dtm.setValueAt(quantity, row, 1); // obj, row, column
//						dtm.setValueAt(p1 * quantity, row, 2);
//						food1 = p1 * quantity;
//
//					} else if (dtm.getValueAt(row, 0).equals("Japanese Noodles")) {
//
//						dtm.setValueAt(quantity, row, 1);
//						dtm.setValueAt(p2 * quantity, row, 2);
//						food2 = p2 * quantity;
//					} else if (dtm.getValueAt(row, 0).equals("Spaghetti")) {
//
//						dtm.setValueAt(quantity, row, 1);
//						dtm.setValueAt(p3 * quantity, row, 2);
//						food3 = p3 * quantity;
//					} else if (dtm.getValueAt(row, 0).equals("Spaghetti Meat Balls")) {
//
//						dtm.setValueAt(quantity, row, 1);
//						dtm.setValueAt(p4 * quantity, row, 2);
//						food4 = p4 * quantity;
//					} else if (dtm.getValueAt(row, 0).equals("Noodles")) {
//
//						dtm.setValueAt(quantity, row, 1);
//						dtm.setValueAt(p5 * quantity, row, 2);
//						food5 = p5 * quantity;
//					} else if (dtm.getValueAt(row, 0).equals("Kids Spaghetti")) {
//
//						dtm.setValueAt(quantity, row, 1);
//						dtm.setValueAt(p6 * quantity, row, 2);
//						food6 = p6 * quantity;
//					} else if (dtm.getValueAt(row, 0).equals("Chicken Rice")) {
//
//						dtm.setValueAt(quantity, row, 1); // obj, row,
//															// column
//						dtm.setValueAt(p7 * quantity, row, 2);
//						food7 = p7 * quantity;
//					} else if (dtm.getValueAt(row, 0).equals("Thai Food")) {
//
//						dtm.setValueAt(quantity, row, 1); // obj, row,
//															// column
//						dtm.setValueAt(p8 * quantity, row, 2);
//						food8 = p8 * quantity;
//					} else if (dtm.getValueAt(row, 0).equals("Vietnam Food")) {
//
//						dtm.setValueAt(quantity, row, 1); // obj, row,
//															// column
//						dtm.setValueAt(p9 * quantity, row, 2);
//						food9 = p9 * quantity;
//					}
//
//					if (quantity == 0) {
//						dtm.removeRow(row);
//					}
//					totalForFoods = food1 + food2 + food3 + food4 + food5 + food6 + food7 + food8 + food9;
//					total = totalForFoods + totalForDrinks + totalForDesserts;
//					textField.setText(total + "");
//					return;
//				}
			}

			// there was no row with this JSpinner, so we have to add it
			for (int i = 0; i < ELEMENTS; i++) {
//				// looking for the "clicked" JSpinner
				if (numSpinner[i] == e.getSource()) {
					totalPrice = price[i];
                                            for (int j=0; j<food.food_list.size(); j++)
                                        {
                                                if (foodLabel[i].getText().equals(food.food_list.get(j).getName()))
                                                {
//                                                    totalForDesserts += food.dessert_list.get(j).getPrice();
                                                    tpfood.put(food.food_list.get(j).getName(), food.food_list.get(j).getPrice());
                                                    break;
                                                }
					}
                                        totalForFoods = 0;
                                        for (Map.Entry<String,Double> tp1 : tpfood.entrySet())
                                        {
                                            if (tp1.getValue() != null) {
                                                totalForFoods += tp1.getValue();
                                            }
                                        }
					total = totalForFoods + totalForDrinks + totalForDesserts;
                                        textField.setText(String.format("%.2f",total));
					dtm.addRow(new Object[] { foodLabel[i].getText(), quantity, totalPrice, numSpinner[i] });
					return;
				}
///////////////////////////////////////
                                     // looking for the "clicked" JSpinner
//				if (numSpinner[i] == e.getSource()) {
//					totalPrice = price[i];
//					switch (foodLabel[i].getText()) {
//					case "Salad":
//						p1 = 3.50;
//						food1 = p1;
//						break;
//					case "Japanese Noodles":
//						p2 = 4.50;
//						food2 = p2;
//						break;
//					case "Spaghetti":
//						p3 = 3.70;
//						food3 = p3;
//						break;
//					case "Spaghetti Meat Balls":
//						p4 = 4.50;
//						food4 = p4;
//						break;
//					case "Noodles":
//						p5 = 5.50;
//						food5 = p5;
//						break;
//					case "Kids Spaghetti":
//						p6 = 4.00;
//						food6 = p6;
//						break;
//					case "Chicken Rice":
//						p7 = 3.50;
//						food7 = p7;
//						break;
//					case "Thai Food":
//						p8 = 6.50;
//						food8 = p8;
//						break;
//					case "Vietnam Food":
//						p9 = 6.50;
//						food9 = p9;
//						break;
//					}
//					totalForFoods = food1 + food2 + food3 + food4 + food5 + food6 + food7 + food8 + food9;
//					total = totalForFoods + totalForDrinks + totalForDesserts;
//					textField.setText(total + "");
//					dtm.addRow(new Object[] { foodLabel[i].getText(), quantity, totalPrice, numSpinner[i] });
//					return;
//				}

			}
		}
	};

	ChangeListener listenerForDesserts = new ChangeListener() {
		public void stateChanged(ChangeEvent e) {
                    

			final int quantity = (int) ((JSpinner) e.getSource()).getValue();
			final int rows = table.getRowCount();

                        
			for (int row = 0; row < rows; row++) {
				if (dtm.getValueAt(row, 3) == e.getSource()) {
                                        for (int j=0; j<food.dessert_list.size(); j++){
                                            if (dtm.getValueAt(row, 0).equals(food.dessert_list.get(j).getName())) {
                                                    dtm.setValueAt(quantity, row, 1); // obj, row,
                                                    //tp[row] = food.dessert_list.get(j).getPrice() * quantity; // column
                                                   for (Map.Entry<String,Double> tp1 : tpdes.entrySet())
                                                   {
                                                        if (tp1.getKey().equals(dtm.getValueAt(row, 0))) {
                                                            tp1.setValue(food.dessert_list.get(j).getPrice() * quantity);
                                                        }
                                                    }
                                                    dtm.setValueAt(food.dessert_list.get(j).getPrice() * quantity, row, 2);
                                            } 
                                        }
					if (quantity == 0) {
						dtm.removeRow(row);
					}
                                        totalForDesserts = 0;
                                    for (Map.Entry<String,Double> tp1 : tpdes.entrySet())
                                        {
                                            if (tp1.getValue() != null) {
                                                totalForDesserts += tp1.getValue();
                                            }
                                        }
					total = totalForFoods + totalForDrinks + totalForDesserts;
					textField.setText(String.format("%.2f",total));
					return;
				}
			}

			// there was no row with this JSpinner, so we have to add it
                       
			for (int i = 0; i < DESSERT_ELEMENTS; i++) {
				// looking for the "clicked" JSpinner
				if (numSpinnerDesserts[i] == e.getSource()) {
					totalPrice = priceDesserts[i];
                                            for (int j=0; j<food.dessert_list.size(); j++)
                                        {
                                                if (dessertLabel[i].getText().equals(food.dessert_list.get(j).getName()))
                                                {
//                                                    totalForDesserts += food.dessert_list.get(j).getPrice();
                                                    tpdes.put(food.dessert_list.get(j).getName(), food.dessert_list.get(j).getPrice());
                                                    break;
                                                }
					}
                                        totalForDesserts = 0;
                                        for (Map.Entry<String,Double> tp1 : tpdes.entrySet())
                                        {
                                            if (tp1.getValue() != null) {
                                                totalForDesserts += tp1.getValue();
                                            }
                                        }
					total = totalForFoods + totalForDrinks + totalForDesserts;
                                        textField.setText(String.format("%.2f",total));
					dtm.addRow(new Object[] { dessertLabel[i].getText(), quantity, totalPrice, numSpinnerDesserts[i] });
					return;
				}

			}
		}

	};

	ChangeListener listenerForDrinks = new ChangeListener() {
		public void stateChanged(ChangeEvent e) {

			final int quantity = (int) ((JSpinner) e.getSource()).getValue();
			final int rows = table.getRowCount();
			for (int row = 0; row < rows; row++) {
                            
                            if (dtm.getValueAt(row, 3) == e.getSource()) {
                                        for (int j=0; j<food.drink_list.size(); j++){
                                            if (dtm.getValueAt(row, 0).equals(food.drink_list.get(j).getName())) {
                                                    dtm.setValueAt(quantity, row, 1); // obj, row,
                                                    //tp[row] = food.dessert_list.get(j).getPrice() * quantity; // column
                                                   for (Map.Entry<String,Double> tp1 : tpdrink.entrySet())
                                                   {
                                                        if (tp1.getKey().equals(dtm.getValueAt(row, 0))) {
                                                            tp1.setValue(food.drink_list.get(j).getPrice() * quantity);
                                                        }
                                                    }
                                                    dtm.setValueAt(food.drink_list.get(j).getPrice() * quantity, row, 2);
                                            } 
                                        }
					if (quantity == 0) {
						dtm.removeRow(row);
					}
                                        totalForDrinks = 0;
                                    for (Map.Entry<String,Double> tp1 : tpdrink.entrySet())
                                        {
                                            if (tp1.getValue() != null) {
                                                totalForDrinks += tp1.getValue();
                                            }
                                        }
					total = totalForFoods + totalForDrinks + totalForDesserts;
					textField.setText(String.format("%.2f",total));
					return;
				}
                            
//				if (dtm.getValueAt(row, 3) == e.getSource()) {
//					if (dtm.getValueAt(row, 0).equals("Raspberry")) {
//						dtm.setValueAt(quantity, row, 1);
//						dtm.setValueAt(d1 * quantity, row, 2);
//						drink1 = d1 * quantity;
//
//					} else if (dtm.getValueAt(row, 0).equals("Chocolate Pudding")) {
//						dtm.setValueAt(quantity, row, 1); // obj, row,
//															// column
//						dtm.setValueAt(d2 * quantity, row, 2);
//						drink2 = d2 * quantity;
//
//					} else if (dtm.getValueAt(row, 0).equals("Blue Hawailan")) {
//
//						dtm.setValueAt(quantity, row, 1);
//						dtm.setValueAt(d3 * quantity, row, 2);
//						drink3 = d3 * quantity;
//
//					} else if (dtm.getValueAt(row, 0).equals("Pina")) {
//
//						dtm.setValueAt(quantity, row, 1);
//						dtm.setValueAt(d4 * quantity, row, 2);
//						drink4 = d4 * quantity;
//
//					} else if (dtm.getValueAt(row, 0).equals("Lemon Ice")) {
//
//						dtm.setValueAt(quantity, row, 1); // obj, row,
//															// column
//						dtm.setValueAt(d5 * quantity, row, 2);
//						drink5 = d5 * quantity;
//
//					}
//					if (quantity == 0) {
//						dtm.removeRow(row);
//					}
//					totalForDrinks = drink1 + drink2 + drink3 + drink4 + drink5;
//					total = totalForFoods + totalForDrinks + totalForDesserts;
//					textField.setText(total + "");
//
//					return;
//				}
			}

			// there was no row with this JSpinner, so we have to add it
			for (int i = 0; i < DRINK_ELEMENTS; i++) {
                            
                            // looking for the "clicked" JSpinner
				if (numSpinnerDrinks[i] == e.getSource()) {
					totalPrice = priceDrinks[i];
                                            for (int j=0; j<food.drink_list.size(); j++)
                                        {
                                                if (drinkLabel[i].getText().equals(food.drink_list.get(j).getName()))
                                                {
//                                                    totalForDesserts += food.dessert_list.get(j).getPrice();
                                                    tpdrink.put(food.drink_list.get(j).getName(), food.drink_list.get(j).getPrice());
                                                    break;
                                                }
					}
                                        totalForDrinks = 0;
                                        for (Map.Entry<String,Double> tp1 : tpdrink.entrySet())
                                        {
                                            if (tp1.getValue() != null) {
                                                totalForDrinks += tp1.getValue();
                                            }
                                        }
					total = totalForFoods + totalForDrinks + totalForDesserts;
					textField.setText(String.format("%.2f",total));
					dtm.addRow(new Object[] { drinkLabel[i].getText(), quantity, totalPrice, numSpinnerDrinks[i] });
					return;
                                }
                                        
                                        
//				// looking for the "clicked" JSpinner
//				if (numSpinnerDrinks[i] == e.getSource()) {
//					totalPrice = priceDrinks[i];
//					switch (drinkLabel[i].getText()) {
//					case "Raspberry":
//						d1 = 3.50;
//						drink1 = d1;
//						break;
//					case "Chocolate Pudding":
//						d2 = 4.50;
//						drink2 = d2;
//						break;
//					case "Blue Hawailan":
//						d3 = 3.00;
//						drink3 = d3;
//						break;
//					case "Pina":
//						d4 = 5.00;
//						drink4 = d4;
//						break;
//					case "Lemon Ice":
//						d5 = 3.00;
//						drink5 = d5;
//						break;
//					}
//					totalForDrinks = drink1 + drink2 + drink3 + drink4 + drink5;
//					total = totalForFoods + totalForDrinks + totalForDesserts;
//					textField.setText(total + "");
//					dtm.addRow(new Object[] { drinkLabel[i].getText(), quantity, totalPrice, numSpinnerDrinks[i] });
//					return;
//				}

			}

		}
	};

	public void setVisible(boolean b) throws IOException {
	}
}
