package src.main.java.com.jwa.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.cj.jdbc.MysqlDataSource;


public class DataSourceFactory {
    private final MysqlDataSource daso;
    private static final Logger LOGGER = Logger.getLogger(DataSourceFactory.class.getName());

    DataSourceFactory() {
        MysqlDataSource daso = new MysqlDataSource();
        String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("my-webapp/src/main/webapp/META-INF/database.properties")).getPath();
        InputStream input = null;

        try { 
            input = new FileInputStream(rootPath);
            Properties prop = new Properties();
            prop.load(input);
            daso.setDatabaseName(prop.getProperty("database"));
            daso.setServerName(prop.getProperty("serverName"));
            daso.setPort(Integer.parseInt(prop.getProperty("port")));
            daso.setUser(prop.getProperty("user"));
            daso.setPassword(prop.getProperty("password"));
        }
        //Exception will occur when the file database.properties is not foud
        catch (FileNotFoundException e) {
            // for easier debugging log only the Exception
            LOGGER.log(Level.SEVERE, "Exception while loading properties file", e);
        }
        //Exception occurs when there is an I/O error
        catch (IOException e) {
            // for easier debugging log only the Exception
            LOGGER.log(Level.SEVERE, "IO Error", e);
        } finally {
            if(input != null) { 
                try {
                    input.close();
                }
                catch (IOException e) {
                    // for easier debugging log only the Exception
                    LOGGER.log(Level.SEVERE, "Failed to close streams.", e);
                }
            }
        }
        this.daso = daso;
    }

    public static Connection getConnection() throws SQLException {
        return SingletonHelper.INSTANCE.daso.getConnection();
    }

    private static class SingletonHelper {
        private static final DataSourceFactory INSTANCE = new DataSourceFactory();
    }
}
