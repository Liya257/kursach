module sourse.cp_progsp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.swing;


    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens sourse.cp_progsp to javafx.fxml;
    exports sourse.cp_progsp;
    exports Controllers;
    opens Controllers to javafx.fxml, javafx.graphics, javafx.base, javafx.controls;
    exports sample.Classes;
    opens sample.Classes to javafx.fxml, javafx.base;
    opens tableClasses to javafx.fxml, javafx.base;
}