module org.sehkah.ddonbrutforcergui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.bouncycastle.provider;
    requires com.sun.jna;

    opens org.sehkah.ddonbrutforcergui to javafx.fxml;
    exports org.sehkah.ddonbrutforcergui;
}