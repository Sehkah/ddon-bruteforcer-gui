module org.sehkah.ddonbruteforcergui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.bouncycastle.provider;
    requires com.sun.jna;
    requires org.apache.logging.log4j;
    requires com.fasterxml.jackson.dataformat.yaml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires org.apache.commons.io;

    opens org.sehkah.ddonbruteforcergui.view.main to javafx.fxml;
    exports org.sehkah.ddonbruteforcergui to javafx.graphics;
    exports org.sehkah.ddonbruteforcergui.view.main to javafx.fxml, com.fasterxml.jackson.databind;
    exports org.sehkah.ddonbruteforcergui.controller.main to javafx.fxml;
}