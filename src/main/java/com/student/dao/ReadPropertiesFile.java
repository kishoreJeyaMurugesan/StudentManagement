package dao;


import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ReadPropertiesFile {


    public void getFile() throws Exception {
        final String username;
        final String password;
        final String url;
        final FileInputStream fileInputStream = new FileInputStream("C:/Users/MY PC/IdeaProjects/StudentManagementCrudOperation"
                + "/resources/config.properties");
        final Properties properties = new Properties();

        properties.load(fileInputStream);

        final String driver = properties.getProperty("jdbc.driver");
        Connection connection = null;
        username = properties.getProperty("username");
        password = properties.getProperty("password");
        url = properties.getProperty("url");

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected database");
        } catch (SQLException e) {
            System.out.println("Not Connected");
        }
    }
    public static void main(String[] args) throws Exception {
        ReadPropertiesFile readPropertiesFile = new ReadPropertiesFile();
        readPropertiesFile.getFile();
    }
}