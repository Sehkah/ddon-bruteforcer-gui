package org.sehkah.ddonbruteforcergui.view.util;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class FxmlUtil {
    private static final Logger logger = LogManager.getLogger();

    public static <T> T loadFxml(String fxmlName) throws IOException {
        return loadFxml(fxmlName, null, Locale.getDefault());
    }

    public static <T> T loadFxml(String fxmlName, Locale locale) throws IOException {
        return loadFxml(fxmlName, null, locale);
    }

    public static <T> T loadFxml(String fxmlName, Object controller) throws IOException {
        return loadFxml(fxmlName, controller, Locale.ENGLISH);
    }

    public static <T> T loadFxml(String fxmlName, Object controller, Locale locale) throws IOException {
        URL resourceLocation = FXMLLoader.getDefaultClassLoader().getResource("fxml/" + fxmlName);
        FXMLLoader fxmlLoader = new FXMLLoader(resourceLocation);
        fxmlLoader.setResources(ResourceBundle.getBundle("i18n." + fxmlName.substring(0, fxmlName.indexOf('.')), locale));
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        if (controller != null) {
            fxmlLoader.setController(controller);
        }
        logger.debug("Loading FXML from location: {}", resourceLocation);
        T root;
        try (InputStream in = resourceLocation.openStream()) {
            root = fxmlLoader.load(in);
        }
        return root;
    }

}
