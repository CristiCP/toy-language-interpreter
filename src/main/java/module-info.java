module com.example.temagui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.temagui to javafx.fxml;
    exports com.example.temagui;
    opens Model.Heap to javafx.base;
    opens Model.SymbolTable;
}