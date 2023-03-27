module com.example.cs213_cafe_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cs213_cafe_project to javafx.fxml;
    exports com.example.cs213_cafe_project;
    exports com.example.cs213_cafe_project.donut;
    opens com.example.cs213_cafe_project.donut to javafx.fxml;
    exports com.example.cs213_cafe_project.donut.flavors;
    opens com.example.cs213_cafe_project.donut.flavors to javafx.fxml;
}