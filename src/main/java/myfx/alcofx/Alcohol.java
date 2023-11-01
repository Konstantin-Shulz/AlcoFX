package myfx.alcofx;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.sql.*;

public class Alcohol {
    int id; SimpleStringProperty label; Alcocat category; SimpleStringProperty subcategory;
    SimpleStringProperty country; SimpleFloatProperty strength; SimpleIntegerProperty sugar;
    SimpleFloatProperty volume; SimpleIntegerProperty aging; SimpleIntegerProperty year;

    public Alcohol(int id, String label, Alcocat category, String subcategory, String country, float strength, int sugar, float volume, int aging, int year) {
        this.id = id;
        this.label = new SimpleStringProperty(label);
        this.category = category;
        this.subcategory = new SimpleStringProperty(subcategory);
        this.country = new SimpleStringProperty(country);
        this.strength = new SimpleFloatProperty(strength);
        this.sugar = new SimpleIntegerProperty(sugar);
        this.volume = new SimpleFloatProperty(volume);
        this.aging = new SimpleIntegerProperty(aging);
        this.year = new SimpleIntegerProperty(year);
    }

    void testConnection(){
        String url = "jdbc:postgresql://10.10.104.136:5432/Geometry?user=postgres&password=123";
        try {
            Connection conn = DriverManager.getConnection(url);
            System.out.println("Подключено");
        } catch (SQLException e) {
            System.out.println("не удалось подключиться");
            System.out.println(e.getMessage());
        }
    }
    static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://10.10.104.136:5432/alko_konst?user=postgres&password=123";
        Connection conn = DriverManager.getConnection(url);
        return conn;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label.get();
    }

    public SimpleStringProperty labelProperty() {
        return label;
    }

    public Alcocat getCategory() {
        return category;
    }

    public String getSubcategory() {
        return subcategory.get();
    }

    public SimpleStringProperty subcategoryProperty() {
        return subcategory;
    }

    public String getCountry() {
        return country.get();
    }

    public SimpleStringProperty countryProperty() {
        return country;
    }

    public float getStrength() {
        return strength.get();
    }

    public SimpleFloatProperty strengthProperty() {
        return strength;
    }

    public int getSugar() {
        return sugar.get();
    }

    public SimpleIntegerProperty sugarProperty() {
        return sugar;
    }

    public float getVolume() {
        return volume.get();
    }

    public SimpleFloatProperty volumeProperty() {
        return volume;
    }

    public int getAging() {
        return aging.get();
    }

    public SimpleIntegerProperty agingProperty() {
        return aging;
    }

    public int getYear() {
        return year.get();
    }

    public SimpleIntegerProperty yearProperty() {
        return year;
    }
}
