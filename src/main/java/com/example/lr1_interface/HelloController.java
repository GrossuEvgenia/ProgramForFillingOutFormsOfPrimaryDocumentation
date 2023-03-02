package com.example.lr1_interface;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.*;
import org.apache.commons.io.FileUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.awt.Desktop;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
public class HelloController {
    @FXML
    TableView tableView;
    @FXML
    TableColumn<act,Integer> columnId;
    @FXML
    TableColumn<act,ComboBox> columnNameDish;
    @FXML
    TableColumn<act,TextField> columnCodeDish;
    @FXML
    TableColumn <act,TextField>columnPrice;
    @FXML
    TableColumn <act,TextField>columnNumberBroke;
    @FXML
    TableColumn <act,TextField>columnPriceBroke;
    @FXML
    TableColumn<act,TextField> columnNumberLost;
    @FXML
    TableColumn <act,TextField>columnPriceLost;
    @FXML
    TableColumn<act,TextField> columnNumberAll;
    @FXML
    TableColumn<act,TextField> columnPriceAll;
    @FXML
    TableColumn<act,TextField> columnReason;
    @FXML
    TableColumn <act,TextField>columnDescription;
    private ObservableList<act> actData= FXCollections.observableArrayList();


    @FXML
    ComboBox organization;
    @FXML
    ComboBox unit;
    @FXML
    ComboBox post;

    @FXML
    Hyperlink hyperlink;

    @FXML
    TextField number;
    @FXML
    DatePicker date;
    @FXML
    DatePicker dateStart;
    @FXML
    DatePicker dateEnd;
    @FXML
    TextField okpo;
    @FXML
    TextField okpd;
    @FXML
    TextField operation;
    @FXML
    TextField fullName;
    @FXML
    Button addButton;
    @FXML
    Button deleteButton;
    @FXML
    Button exportButton;
    @FXML
    Button cancelButton;
    @FXML
    Label allBroke;
    @FXML
    Label allAddBroke;
    @FXML
    Label allLost;
    @FXML
    Label allAddLost;
    @FXML
    Label allNumber;
    @FXML
    Label allAddNumber;
    @FXML
    TextArea solveText;
    private int idColumn=1;

    private String post1="";
    private String post2="";
    private String post3="";

    private String fio1="";
    private String fio2="";
    private String fio3="";

    private ModalWindowController modalWindowController;
    private List<String[]> listOrganization = new ArrayList<>();



    private ObservableList<String>getData(String fileName, boolean flag) throws IOException {
        File file = new File(fileName);
        ObservableList<String> listDish= FXCollections.observableArrayList();

        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line = bufferedReader.readLine();
        while(line!=null){
            String[] words = line.split(";");
            if(flag==true){
                listOrganization.add(words);
            }
            listDish.add(words[0]);
            line= bufferedReader.readLine();
        }
        bufferedReader.close();
        reader.close();


        return listDish;
    }


    @FXML
    public void initialize() throws IOException {
        //pane.setMinSize(1148,600);
        //pane.setMaxSize(1148,600);
        organization.setEditable(true);
        organization.setItems(getData("C:\\Users\\User\\Desktop\\8 semester THE END\\useability interfeyse\\lr1_interface\\src\\main\\resources\\files\\organization.txt",
                true));
        new AutoCompleteComboBoxListener<>(organization);
        unit.setEditable(true);
        unit.setItems(getData("C:\\Users\\User\\Desktop\\8 semester THE END\\useability interfeyse\\lr1_interface\\src\\main\\resources\\files\\unit.txt",
                false));
        new AutoCompleteComboBoxListener<>(unit);
        post.setEditable(true);
        post.setItems(getData("C:\\Users\\User\\Desktop\\8 semester THE END\\useability interfeyse\\lr1_interface\\src\\main\\resources\\files\\post.txt",
                false));
        new AutoCompleteComboBoxListener<>(post);

        organization.setOnAction(actionEvent -> outputCode((String) organization.getValue()));

        tableView.setEditable(true);

        actData.addListener((ListChangeListener.Change<? extends act> c) -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    System.out.println("Added:");
                    c.getAddedSubList().forEach(System.out::println);
                    System.out.println();
                }
                if (c.wasRemoved()) {
                    System.out.println("Removed:");
                    c.getRemoved().forEach(System.out::println);
                    System.out.println();
                }
                if (c.wasUpdated()) {
                    System.out.println("Updated:");
                    actData.subList(c.getFrom(), c.getTo()).forEach(System.out::println);
                    System.out.println();
                }
            }
        });
        columnId.setCellValueFactory(new PropertyValueFactory<act,Integer>("id"));
        columnNameDish.setCellValueFactory(new PropertyValueFactory<act,ComboBox>("dish"));
        columnNameDish.setEditable(true);

        columnCodeDish.setCellValueFactory(new PropertyValueFactory<act,TextField>("codeDish"));
        columnNameDish.setEditable(true);
        columnPrice.setCellValueFactory(new PropertyValueFactory<act,TextField>("price"));
        columnNumberBroke.setCellValueFactory(new PropertyValueFactory<act, TextField>("numberBroke"));
        columnPriceBroke.setCellValueFactory(new PropertyValueFactory<act,TextField>("addPriceBroke"));
        columnNumberLost.setCellValueFactory(new PropertyValueFactory<act, TextField>("numberLost"));
        columnPriceLost.setCellValueFactory(new PropertyValueFactory<act,TextField>("addPriceLost"));
        columnNumberAll.setCellValueFactory(new PropertyValueFactory<act,TextField>("numberAll"));
        columnPriceAll.setCellValueFactory(new PropertyValueFactory<act,TextField>("addPriceAll"));
        columnReason.setCellValueFactory(new PropertyValueFactory<act,TextField>("reason"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<act,TextField>("description"));



        tableView.setItems(actData);

    }

    public void setFio1(String fio1) {
        this.fio1 = fio1;
    }

    public void setFio2(String fio2) {
        this.fio2 = fio2;
    }

    public void setFio3(String fio3) {
        this.fio3 = fio3;
    }

    public void setPost1(String post1) {
        this.post1 = post1;
    }

    public void setPost2(String post2) {
        this.post2 = post2;
    }

    public void setPost3(String post3) {
        this.post3 = post3;
    }

    private String getNewSum(Label oldVal, String newVal, String old){
        String newSum="";
        int sum = Integer.parseInt(oldVal.getText())-Integer.parseInt(old)+Integer.parseInt(newVal);
        newSum = Integer.toString(sum);
        System.out.println(newSum);
        return newSum;

    }

    private String getNewSumDouble(Label oldVal, String newVal, String old){
        String newSum="";
        double sum = Double.parseDouble(oldVal.getText())-Double.parseDouble(old)+Double.parseDouble(newVal);
        newSum = Double.toString(sum);
        return newSum;
    }

    public void setAllBroke(String allBroke, String oldV) {
        this.allBroke.setText(getNewSum(this.allBroke, allBroke, oldV));
      //  System.out.println(this.allBroke.getText());
    }


    public void setAllLost(String allLost, String oldV) {
        this.allLost.setText(getNewSum(this.allLost,allLost, oldV));
    }

    public void setAllNumber(String allNumber, String oldV) {
        this.allNumber.setText(getNewSum(this.allNumber,allNumber, oldV));
    }

    public void setAllAddBroke(String allAddBroke, String oldV) {
        this.allAddBroke.setText(getNewSumDouble(this.allAddBroke,allAddBroke, oldV));
    }

    public void setAllAddLost(String allAddLost, String oldV) {
        this.allAddLost.setText(getNewSumDouble(this.allAddLost,allAddLost, oldV));
    }

    public void setAllAddNumber(String allAddNumber,String oldV) {
        this.allAddNumber.setText(getNewSumDouble(this.allAddNumber, allAddNumber, oldV));

    }

    private void outputCode(String text){
        for(String[] lo : listOrganization){
            if(lo[0].compareTo(text)==0){
                okpo.setText(lo[1]);
                okpd.setText(lo[2]);
                operation.setText(lo[3]);
            }
        }
    }

    @FXML
    public void openModalForm() throws IOException {
        Stage stage1 = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("modal_window.fxml"));

         Scene scene = new Scene(fxmlLoader.load(), 440,200 );
        stage1.setResizable(false);
        stage1.setTitle("Расшифровка подписей");
        stage1.setScene(scene);
        stage1.show();
        modalWindowController=fxmlLoader.getController();
        modalWindowController.setHelloController(this);
    }
    @FXML
    public void addString() throws IOException {
        actData.add(new act(this));
        actData.get(actData.size()-1).setId(actData.size());
        TablePosition pos = new TablePosition(tableView,idColumn-1,columnNameDish);
        tableView.getFocusModel().focus(pos);
      tableView.requestFocus();

        //  columnCodeDish.set
        idColumn++;

        //tableView.setItems(actData);

    }

    private void printLabel()
    {
       //if(columnNumberBroke.getCellObservableValue(idColumn-1).getValue().getText())
    }
    @FXML
    public void clear(){
        number.setText("");
        date.getEditor().clear();
        dateStart.getEditor().clear();
        dateEnd.getEditor().clear();
        organization.getEditor().clear();
        unit.getEditor().clear();
        okpo.setText("");
        okpd.setText("");
        operation.setText("");
        post.getEditor().clear();
        fullName.setText("");
        actData.clear();
        post1="";
        post2="";
        post3="";
        fio1="";
        fio2="";
        fio3="";
        solveText.setText("");
        allBroke.setText("0");
        allLost.setText("0");
        allNumber.setText("0");
        allAddBroke.setText("0.0");
        allAddLost.setText("0.0");
        allAddNumber.setText("0.0");
    }

    @FXML
    public  void removeRowButton(){

        String oldBroke = "0";
        String oldLost = "0";
        String oldNumber = "0";

        String oldAllb = "0.0";
        String oldAlll = "0.0";
        String oldAllN = "0.0";

        if(idColumn==2){
            System.out.println("0");
        }
         int row=tableView.getSelectionModel().getSelectedIndex();

        oldBroke =actData.get(row).getNumberBroke().getText();
        oldLost =actData.get(row).getNumberLost().getText();
        oldNumber =actData.get(row).getNumberAll().getText();
        oldAllb =actData.get(row).getAddPriceBroke().getText();
        oldAlll =actData.get(row).getAddPriceLost().getText();
        oldAllN =actData.get(row).getAddPriceAll().getText();


        setAllBroke("0", oldBroke);
        setAllLost("0", oldLost);
        setAllNumber("0", oldNumber);
        setAllAddBroke("0.0", oldAllb);
        setAllAddLost("0.0", oldAlll);
        setAllAddNumber("0.0", oldAllN);

    tableView.getItems().remove(row);
    for(int i=row; i<actData.size(); i++){
        actData.get(i).setId(i+1);
    }


    System.out.println(allBroke.getText()+" "+allAddBroke.getText());
    }
    private static void copyFileUsingApacheCommonsIO(File source, File dest) throws IOException {
        FileUtils.copyFile(source, dest);
    }
    @FXML
    public void exportButton() throws IOException {
        String text = allBroke.getText();
        Double tmp = Double.parseDouble(text);
        String col=new RussianMoney().digits2Text(tmp);

        String nameFile = "C:\\Users\\User\\Desktop\\op-8.xls";
        String nameNewFile="C:\\Users\\User\\Desktop\\op-81.xls";

        Path from = Paths.get(nameFile);
        Path to = Paths.get(nameNewFile);
        Files.copy(from,to, StandardCopyOption.REPLACE_EXISTING);


        FileInputStream file = new FileInputStream(new File(nameNewFile));
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet = workbook.getSheetAt(0);
        Cell cell = null;

        if(organization.getValue()!=null) {
            cell = sheet.getRow(5).getCell(0);
            cell.setCellValue(organization.getValue().toString());
        }
        cell=sheet.getRow(5).getCell(68);
        cell.setCellValue(okpo.getText());

        if(unit.getValue()!=null) {
            cell = sheet.getRow(7).getCell(0);
            cell.setCellValue(unit.getValue().toString());
        }
        cell=sheet.getRow(8).getCell(68);
        cell.setCellValue(okpd.getText());

        cell=sheet.getRow(9).getCell(68);
        cell.setCellValue(operation.getText());

        cell=sheet.getRow(13).getCell(36);
        cell.setCellValue(number.getText());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        if(date.getValue()!=null) {
            cell = sheet.getRow(13).getCell(44);
            cell.setCellValue(date.getValue().format(formatter));
        }

        if(dateStart.getValue()!=null) {
            cell = sheet.getRow(13).getCell(52);
            cell.setCellValue(dateStart.getValue().format(formatter));
        }
        if(dateEnd.getValue()!=null) {
            cell = sheet.getRow(13).getCell(57);
            cell.setCellValue(dateEnd.getValue().format(formatter));
        }
        if(post.getValue()!=null) {
            cell = sheet.getRow(17).getCell(20);
            cell.setCellValue(post.getValue().toString());
        }

        cell=sheet.getRow(17).getCell(38);
        cell.setCellValue(fullName.getText());

        int indR=27;
        for(int i=0; i<actData.size();i++){

            if(i==10){
                indR=37;
            }

            cell=sheet.getRow(indR+i).getCell(0);
            cell.setCellValue(actData.get(i).getId());
            if(actData.get(i).getDish().getValue()!=null) {
                cell = sheet.getRow(indR + i).getCell(4);
                cell.setCellValue(actData.get(i).getDish().getValue().toString());
            }

            cell=sheet.getRow(indR+i).getCell(14);
            cell.setCellValue(actData.get(i).getCodeDish().getText());

            cell=sheet.getRow(indR+i).getCell(19);
            cell.setCellValue(actData.get(i).getPrice().getText());

            cell=sheet.getRow(indR+i).getCell(25);
            cell.setCellValue(actData.get(i).getNumberBroke().getText());

            cell=sheet.getRow(indR+i).getCell(29);
            cell.setCellValue(actData.get(i).getAddPriceBroke().getText());

            cell=sheet.getRow(indR+i).getCell(34);
            cell.setCellValue(actData.get(i).getNumberLost().getText());

            cell=sheet.getRow(indR+i).getCell(38);
            cell.setCellValue(actData.get(i).getAddPriceLost().getText());

            cell=sheet.getRow(indR+i).getCell(43);
            cell.setCellValue(actData.get(i).getNumberAll().getText());

            cell=sheet.getRow(indR+i).getCell(47);
            cell.setCellValue(actData.get(i).getAddPriceAll().getText());

            cell=sheet.getRow(indR+i).getCell(52);
            cell.setCellValue(actData.get(i).getReason().getText());

            cell=sheet.getRow(indR+i).getCell(69);
            cell.setCellValue(actData.get(i).getDescription().getText());
        }

        cell=sheet.getRow(58).getCell(25);
        cell.setCellValue(allBroke.getText());

        cell=sheet.getRow(58).getCell(29);
        cell.setCellValue(allAddBroke.getText());

        cell=sheet.getRow(58).getCell(34);
        cell.setCellValue(allLost.getText());

        cell=sheet.getRow(58).getCell(38);
        cell.setCellValue(allAddLost.getText());

        cell=sheet.getRow(58).getCell(43);
        cell.setCellValue(allNumber.getText());

        cell=sheet.getRow(58).getCell(47);
        cell.setCellValue(allAddNumber.getText());

        cell=sheet.getRow(60).getCell(34);
        cell.setCellValue(col);

        cell=sheet.getRow(71).getCell(13);
        cell.setCellValue(solveText.getText());

        cell=sheet.getRow(64).getCell(13);
        cell.setCellValue(post1);

        cell=sheet.getRow(66).getCell(13);
        cell.setCellValue(post2);

        cell=sheet.getRow(68).getCell(13);
        cell.setCellValue(post3);

        cell=sheet.getRow(64).getCell(42);
        cell.setCellValue(fio1);


        cell=sheet.getRow(66).getCell(42);
        cell.setCellValue(fio2);

        cell=sheet.getRow(68).getCell(42);
        cell.setCellValue(fio3);


        file.close();
        FileOutputStream outputStream = new FileOutputStream(new File(nameNewFile));
        workbook.write(outputStream);
        outputStream.close();
        Desktop.getDesktop().open(new File(nameNewFile));



    }


}