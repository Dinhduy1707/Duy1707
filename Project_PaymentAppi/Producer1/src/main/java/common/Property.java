package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Property {
    private static final Logger LOG = LogManager.getLogger(Property.class);

    private static Properties instance;

    private Property() { }

    public static Properties getInstance() {
        if (instance == null) {
            synchronized (Property.class) {
                if (null == instance) {
                    instance = new Properties();
                    try {
                        instance.load(new FileInputStream("C://Users//Dinh Duy//IdeaProjects//Project_PaymentAppi//dev.properties"));
                        LOG.info("Load file src/dev.properties success");
                    } catch (IOException e) {
                        LOG.error("Error load file src/dev.properties: " , e);
                    }
                }
            }
        }
        return instance;
    }
}

