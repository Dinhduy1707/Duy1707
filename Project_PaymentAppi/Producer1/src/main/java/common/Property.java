package common;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Log4j2
public class Property {


    private static Properties instance;

    private Property() { }

    public static Properties getInstance() {
        if (instance == null) {
            synchronized (Property.class) {
                if (null == instance) {
                    instance = new Properties();
                    try {
                        instance.load(new FileInputStream("C:\\Users\\Dinh Duy\\OneDrive\\Máy tính\\GPCode\\Project_PaymentAppi\\dev.properties"));
                        log.info("Load file src/dev.properties success");
                    } catch (IOException e) {
                        log.error("Error load file src/dev.properties: " , e);
                    }
                }
            }
        }
        return instance;
    }
}

