module org.sehkah.ddon.tools {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.bootstrapicons;

    requires org.bouncycastle.provider;
    requires com.sun.jna;

    requires com.fasterxml.jackson.dataformat.yaml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    requires org.apache.logging.log4j;
    requires org.apache.commons.io;

    opens org.sehkah.ddon.tools.bruteforcer.view.main to javafx.fxml;
    opens org.sehkah.ddon.tools.streamdecryptor.model.pcap.packet to com.fasterxml.jackson.databind;
    opens org.sehkah.ddon.tools.streamdecryptor.model.pcap to com.fasterxml.jackson.databind;

    exports org.sehkah.ddon.tools.bruteforcer.view.main to javafx.fxml, com.fasterxml.jackson.databind;
    exports org.sehkah.ddon.tools.bruteforcer.controller.main to javafx.fxml;
    exports org.sehkah.ddon.tools.streamdecryptor.model.pcap to com.fasterxml.jackson.databind;
    exports org.sehkah.ddon.tools.streamdecryptor.model.pcap.packet to com.fasterxml.jackson.databind;
    exports org.sehkah.ddon.tools to javafx.graphics;
}