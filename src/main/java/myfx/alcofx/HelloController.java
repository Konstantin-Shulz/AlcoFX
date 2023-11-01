package myfx.alcofx;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static myfx.alcofx.Alcohol.getConnection;
public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    protected void onHelloButtonClick() {
    Alcohol ex = new Alcohol(1, "Водка", Alcocat.Strong, "водка", "Россия",40, 0, 1, 1, 1);
    ex.testConnection();}
    @FXML
    private TableView<Alcohol> table;
    @FXML
    private TextField txtaging;
    @FXML
    private TextField txtcategory;
    @FXML
    private TextField txtcountry;
    @FXML
    private TextField txtlabel;
    @FXML
    private TextField txtstrength;
    @FXML
    private TextField txtsubcategory;
    @FXML
    private TextField txtsugar;
    @FXML
    private TextField txtvolume;
    @FXML
    private TextField txtyear;
    ObservableList<Alcohol> alcohols = FXCollections.observableArrayList();
    public void initialize(){

        table.getColumns().clear();
        table.setItems(alcohols);

        TableColumn<Alcohol, Integer> col1 = new TableColumn<>("id");
        col1.setCellValueFactory(new PropertyValueFactory<>("id"));
        table.getColumns().add(col1);
        TableColumn<Alcohol, String> col2 = new TableColumn<>("марка");
        col2.setCellValueFactory(new PropertyValueFactory<>("label"));
        table.getColumns().add(col2);
        TableColumn<Alcohol, String> col3 = new TableColumn<>("категория");
        col3.setCellValueFactory(new PropertyValueFactory<>("category"));
        table.getColumns().add(col3);
        TableColumn<Alcohol, String> col4 = new TableColumn<>("подкатегория");
        col4.setCellValueFactory(new PropertyValueFactory<>("subcategory"));
        table.getColumns().add(col4);
        TableColumn<Alcohol, String> col5 = new TableColumn<>("страна");
        col5.setCellValueFactory(new PropertyValueFactory<>("country"));
        table.getColumns().add(col5);
        TableColumn<Alcohol, Double> col6 = new TableColumn<>("крепость");
        col6.setCellValueFactory(new PropertyValueFactory<>("strength"));
        table.getColumns().add(col6);
        TableColumn<Alcohol, Integer> col7 = new TableColumn<>("сахар");
        col7.setCellValueFactory(new PropertyValueFactory<>("sugar"));
        table.getColumns().add(col7);
        TableColumn<Alcohol, Double> col8 = new TableColumn<>("объём");
        col8.setCellValueFactory(new PropertyValueFactory<>("volume"));
        table.getColumns().add(col8);
        TableColumn<Alcohol, Double> col9 = new TableColumn<>("выдержка");
        col9.setCellValueFactory(new PropertyValueFactory<>("aging"));
        table.getColumns().add(col9);
        TableColumn<Alcohol, Integer> col10 = new TableColumn<>("год");
        col10.setCellValueFactory(new PropertyValueFactory<>("year"));
        table.getColumns().add(col10);
    }
    static ArrayList<Alcohol> readAlco() throws Exception {
        ArrayList<Alcohol> alco =new ArrayList<>();
        Connection conn = getConnection();
        Statement st = conn.createStatement();
        String query ="select * from \"alcohol\" a ;";//select *  from  alcohol
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            int id = rs.getInt("id");
            String label = rs.getString("label");
            Alcocat category = Alcocat.valueOf(rs.getString("category"));
            String subcategory = rs.getString("subcategory");
            String country = rs.getString("country");
            float strength = rs.getFloat("strength");
            int sugar = rs.getInt("sugar");
            float volume = rs.getFloat("volume");
            int aging = rs.getInt("aging");
            int year = rs.getInt("year");
            Alcohol a = new Alcohol(id, label, category, subcategory, country, strength, sugar,volume, aging, year);
            alco.add(a);
        }
        return alco;
    }
    @FXML
    void reloadFromBD(){
        try {
            List<Alcohol> lst = readAlco();//извлечь строки из базы и поместить в коллекцию
            alcohols.clear();
            alcohols.addAll(lst);
        }
        catch (Exception e){
            System.out.println("ошибка чтения "+e.getMessage());
        }
    }
    @FXML
    void saveToDB() {
        try {
            int id=3;
            String label = String.valueOf(txtlabel.getText());
            Alcocat category = Alcocat.valueOf(txtcategory.getText());
            String subcategory = String.valueOf(txtsubcategory.getText());
            String country = String.valueOf(txtcountry.getText());
            Float strength = Float.parseFloat(txtstrength.getText());
            Integer sugar = Integer.parseInt(txtsugar.getText());
            Float volume = Float.parseFloat(txtvolume.getText());
            Integer aging = Integer.parseInt(txtaging.getText());
            Integer year = Integer.parseInt(txtyear.getText());

            Alcohol a = new Alcohol(id,label,category,subcategory,country,strength,sugar,volume,aging,year);
            saveAlco(a);

        } catch (Exception e) {
            System.out.println("ошибка записи " + e.getMessage());
        }
    }
    static void saveAlco(Alcohol alc) throws SQLException {
        Connection conn = getConnection();

        String query = "insert into \"alcohol\" ( label, " +
                "category, subcategory, country, strength, sugar," +
                " volume, aging, year )" +
                " values ( ?, ?,?,?,?, ?,?,?,?);";
        //Statement st = conn.createStatement();
        // st.execute(query);
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, alc.getLabel() );
        pst.setString(2, alc.getCategory().name());
        pst.setString(3, alc.getSubcategory() );
        pst.setString(4, alc.getCountry());
        pst.setFloat( 5, alc.getStrength());
        pst.setFloat( 6, alc.getSugar());
        pst.setFloat( 7, alc.getVolume());
        pst.setInt(   8, alc.getAging());
        pst.setInt(   9, alc.getYear());
        pst.executeUpdate();
    }
}