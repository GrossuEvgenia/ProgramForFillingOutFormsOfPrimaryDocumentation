package com.example.lr1_interface;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class act {
    private int id;
    /*private String nameDish;
    private String codeDish;
    private String price;
    private String numberBroke;
    private String addPriceBroke;
    private String numberLost;
    private String addPriceLost;
    private String numberAll;
    private String addPriceAll;
    private String reason;
    private String description;*/
    private String nameDish;
    private TextField codeDish;
    private TextField price;
    private TextField numberBroke;
    private TextField addPriceBroke;
    private TextField numberLost;
    private TextField addPriceLost;
    private TextField numberAll;
    private TextField addPriceAll;
    private TextField reason;
    private TextField description;
    private ComboBox dish;
    private List<String []> listDish1;
    private SimpleStringProperty tmp=new SimpleStringProperty("hui");


    private HelloController helloController;;



    /*
    *
    * Перенести в контроллер, доработать запись остальных данных
    *
    */
    private ObservableList<String>getData() throws IOException {
        File file = new File("C:\\Users\\User\\Desktop\\8 semester THE END\\useability interfeyse\\lr1_interface\\src\\main\\resources\\files\\input.txt");
        listDish1=new ArrayList<>();
        ObservableList<String> listDish= FXCollections.observableArrayList();

        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line = bufferedReader.readLine();
        while(line!=null){
            String[] words = line.split(";");
            listDish1.add(words);
            listDish.add(words[0]);
            line= bufferedReader.readLine();
        }




        return listDish;
    }

    public String getTmp() {
        return tmp.get();
    }

    public SimpleStringProperty tmpProperty() {
        return tmp;
    }

    public act(HelloController controller) throws IOException {
        this.id = 0;
      //  this.nameDish = new TextField();
        this.codeDish = new TextField();
        this.price = new TextField();
        this.numberBroke = new TextField();
        this.addPriceBroke = new TextField();
        this.numberLost = new TextField();
        this.addPriceLost =new TextField();
        this.numberAll = new TextField();
        this.addPriceAll = new TextField();
        this.reason = new TextField();
        this.description = new TextField();
        this.dish = new ComboBox<String>(getData());
        this.helloController=controller;
        dish.setEditable(true);
        dish.setOnAction(actionEvent -> setOutCodeAndPrice((String) dish.getValue()));
        codeDish.setEditable(true);
        new AutoCompleteComboBoxListener<>(dish);

        AtomicReference<String> oldInt= new AtomicReference<>("0");
        AtomicReference<String> oldDouble = new AtomicReference<>("0.0");

        AtomicReference<String> oldIntp= new AtomicReference<>("0");
        AtomicReference<String> oldDoublep = new AtomicReference<>("0.0");



        price.setText("0.0");
        price.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("(^\\d.[0-9]*)?")) {
                    price.setText(newValue.replaceAll("[^\\d.]", ""));
                    StringBuilder aus = new StringBuilder(newValue);
                    boolean firstPointFound = false;
                    for (int i = 0; i < aus.length(); i++){
                        if(aus.charAt(i) == '.') {
                            if(!firstPointFound)
                                firstPointFound = true;
                            else
                                aus.deleteCharAt(i);
                        }
                    }
                    newValue = aus.toString();
                }
            }
        });
        price.focusedProperty().addListener( (obs, oldValue, newValue) -> {
            AtomicReference<String> oldDouble1= new AtomicReference<>("0.0");
            AtomicReference<String> oldDouble2= new AtomicReference<>("0.0");
            AtomicReference<String> oldDouble3= new AtomicReference<>("0.0");
            if (newValue) {
            oldDouble.set(price.getText());

            }
            else {
                if(price.getText().compareTo("")==0)
                    price.setText(String.valueOf(oldDouble));
                else if(price.getText().compareTo(String.valueOf(oldDouble))!=0){

                    oldDouble1.set(addPriceBroke.getText());
                    oldDouble2.set(addPriceLost.getText());
                    oldDouble3.set(addPriceAll.getText());
                addPriceBroke.setText(countPrice(price.getText(), numberBroke.getText()));
                addPriceLost.setText(countPrice(price.getText(),numberLost.getText()));
                addPriceAll.setText(countPriceAll(addPriceBroke.getText(),addPriceLost.getText()));


                helloController.setAllAddBroke(addPriceBroke.getText(), String.valueOf(oldDouble1));
                helloController.setAllAddLost(addPriceLost.getText(), String.valueOf(oldDouble2));
                helloController.setAllAddNumber(addPriceAll.getText(), String.valueOf(oldDouble3));
                }

                oldDouble.set("0.0");


            }});
       numberBroke.setText("0");
       numberBroke.textProperty().addListener(new ChangeListener<String>() {
           @Override
           public void changed(
                   ObservableValue<? extends String> observable,
                   String oldValue, String newValue) {
               if (!newValue.matches("\\d*")) {
                   numberBroke.setText(newValue.replaceAll("[^\\d]", ""));
               }
           }
       });
       numberBroke.focusedProperty().addListener( (obs, oldValue, newValue) -> {
           if (newValue) {
               oldInt.set(numberBroke.getText());
               oldDouble.set(addPriceBroke.getText());
               oldIntp.set(numberAll.getText());
               oldDoublep.set(addPriceAll.getText());
               /* при получении фокуса */ }
           else {
                if(numberBroke.getText().compareTo("")==0)
                    numberBroke.setText("0");

                addPriceBroke.setText(countPrice(price.getText(), numberBroke.getText()));
                numberAll.setText(countNumberAll(numberBroke.getText(),numberLost.getText()));
                addPriceAll.setText(countPriceAll(addPriceBroke.getText(),addPriceLost.getText()));

                helloController.setAllBroke(numberBroke.getText(), String.valueOf(oldInt));
                helloController.setAllAddBroke(addPriceBroke.getText(), String.valueOf(oldDouble));
                helloController.setAllNumber(numberAll.getText(), String.valueOf(oldIntp));
                helloController.setAllAddNumber(addPriceAll.getText(), String.valueOf(oldDoublep));
                oldDouble.set("0.0");
                oldInt.set("0");
                oldDoublep.set("0.0");
                oldIntp.set("0");
               /* при потере */ }
       });

       addPriceBroke.setText("0.0");
       addPriceBroke.setEditable(false);

       numberLost.setText("0");
       numberLost.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(
                    ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    numberLost.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
       numberLost.focusedProperty().addListener( (obs, oldValue, newValue) -> {
           if (newValue) { /* при получении фокуса */
           oldInt.set(numberLost.getText());
           oldDouble.set(addPriceLost.getText());
           oldIntp.set(numberAll.getText());
           oldDoublep.set(addPriceAll.getText());


           }
           else {
               if(numberLost.getText().compareTo("")==0)
                   numberLost.setText("0");

                    addPriceLost.setText(countPrice(price.getText(), numberLost.getText()));
                    numberAll.setText(countNumberAll(numberBroke.getText(),numberLost.getText()));
                    addPriceAll.setText(countPriceAll(addPriceBroke.getText(),addPriceLost.getText()));


                    helloController.setAllLost(numberLost.getText(), String.valueOf(oldInt));
                    helloController.setAllAddLost(addPriceLost.getText(),String.valueOf(oldDouble));
                    helloController.setAllNumber(numberAll.getText(), String.valueOf(oldIntp));
                    helloController.setAllAddNumber(addPriceAll.getText(), String.valueOf(oldDoublep));
                    oldDouble.set("0.0");
                    oldInt.set("0");
                    oldDoublep.set("0.0");
                    oldIntp.set("0");
               //   helloController.setAllBroke(reason.getText());
               /* при потере */

           oldInt.set("0.0");}
       });

        addPriceLost.setText("0.0");
        addPriceLost.setEditable(false);

        numberAll.setText("0");
        numberAll.setEditable(false);
       addPriceAll.setText("0.0");
       addPriceAll.setEditable(false);


    }

    private void setOutCodeAndPrice(String text){
     for(String [] ld: listDish1 ){
         if(ld[0].compareTo(text)==0){
             codeDish.setText(ld[1]);
             price.setText(ld[2]);
         }
     }

    }
   /* public act(int id, String nameDish, String codeDish, double price, int numberBroke,
               double addPriceBroke, int numberLost, double addPriceLost,
               int numberAll, double addPriceAll, String reason, String description) throws IOException {
        this.id = id;
        this.nameDish = nameDish;
        this.codeDish = codeDish;
        this.price = price;
        this.numberBroke = numberBroke;
        this.addPriceBroke = addPriceBroke;
        this.numberLost = numberLost;
        this.addPriceLost = addPriceLost;
        this.numberAll = numberAll;
        this.addPriceAll = addPriceAll;
        this.reason = reason;
        this.description = description;
        this.dish = new ComboBox<String>(getData());
    }*/


    private String countPrice(String p1, String col){
        String pr = Double.toString(Double.parseDouble(p1)*Double.parseDouble(col));
        return pr;
    }

    private String countNumberAll( String newBroke, String newLost ){
        String result=Integer.toString(Integer.parseInt(newBroke)+Integer.parseInt(newLost));
        return result;
    }


    private String countPriceAll( String newBroke, String newLost ){
        String result=Double.toString(Double.parseDouble(newBroke)+Double.parseDouble(newLost));
        return result;
    }
    public ComboBox getDish() {
        return dish;
    }

    public void setDish(ComboBox dish) {
        this.dish = dish;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameDish() {
        return nameDish;
    }

    public void setNameDish(String nameDish) {
        this.nameDish = nameDish;
    }

    public TextField getCodeDish() {
        return codeDish;
    }

    public void setCodeDish(TextField codeDish) {
        this.codeDish = codeDish;
    }

    public TextField getPrice() {
        return price;
    }

    public void setPrice(TextField price) {
        this.price = price;
    }

    public TextField getNumberBroke() {
        return numberBroke;
    }

    public void setNumberBroke(TextField numberBroke) {
        this.numberBroke = numberBroke;
    }

    public TextField getAddPriceBroke() {
        return addPriceBroke;
    }

    public void setAddPriceBroke(TextField addPriceBroke) {
        this.addPriceBroke = addPriceBroke;
    }

    public TextField getNumberLost() {
        return numberLost;
    }

    public void setNumberLost(TextField numberLost) {
        this.numberLost = numberLost;
    }

    public TextField getAddPriceLost() {
        return addPriceLost;
    }

    public void setAddPriceLost(TextField addPriceLost) {
        this.addPriceLost = addPriceLost;
    }

    public TextField getNumberAll() {
        return numberAll;
    }

    public void setNumberAll(TextField numberAll) {
        this.numberAll = numberAll;
    }

    public TextField getAddPriceAll() {
        return addPriceAll;
    }

    public void setAddPriceAll(TextField addPriceAll) {
        this.addPriceAll = addPriceAll;
    }

    public TextField getReason() {
        return reason;
    }

    public void setReason(TextField reason) {
        this.reason = reason;
    }

    public TextField getDescription() {
        return description;
    }

    public void setDescription(TextField description) {
        this.description = description;
    }
}
