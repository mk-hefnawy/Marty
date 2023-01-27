module com.hefnawy.marty {
    requires javafx.controls;
    requires javafx.fxml;


    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires java.prefs;


    //opens com.hefnawy.marty to org.hibernate.orm.core, javafx.fxml;
    opens com.hefnawy.marty.auth.model to org.hibernate.orm.core;

    exports com.hefnawy.marty.app;
    exports com.hefnawy.marty.home.view;
    opens com.hefnawy.marty.app to javafx.fxml, org.hibernate.orm.core;
}