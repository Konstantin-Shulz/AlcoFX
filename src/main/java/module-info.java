module myfx.alcofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens myfx.alcofx to javafx.fxml;
    exports myfx.alcofx;
}