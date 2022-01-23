package org.sehkah.ddonbruteforcergui.view.util;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class FxmlUtil {
    private static final Logger logger = LogManager.getLogger();

    public static <T> T loadFxml(String fxmlName, String i18nName) throws IOException {
        return loadFxml(fxmlName, i18nName, null, Locale.getDefault());
    }

    public static <T> T loadFxml(String fxmlName, String i18nName, Object controller) throws IOException {
        return loadFxml(fxmlName, i18nName, controller, Locale.getDefault());
    }

    public static <T> T loadFxml(String fxmlName, String i18nName, Object controller, Locale locale) throws IOException {
        logger.debug("Attempting to load fxml {} with locale {}", fxmlName, locale);
        URL resourceLocation = ClassLoader.getSystemClassLoader().getResource(fxmlName);
        FXMLLoader fxmlLoader = new FXMLLoader(resourceLocation);
        fxmlLoader.setResources(ResourceBundle.getBundle(i18nName, locale));
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        if (controller != null) {
            fxmlLoader.setController(controller);
        }
        logger.debug("Loading FXML from location: {}", resourceLocation);
        T root;
        try (InputStream in = Objects.requireNonNull(resourceLocation).openStream()) {
            root = fxmlLoader.load(in);
        }
        return root;
    }

}
