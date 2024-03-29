package dao;

import java.sql.*;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import model.EncryptionDecryption;
import model.User;

public class UserDao {
    private Connection connection;
    private String query;
    private PreparedStatement stmnt;
    private ResultSet resultset;
    private static SecretKey secretKey; // Fixed secret key
    static Cipher cipher;

    static {
        generateSecretKey();
    }
    
    public UserDao(Connection con) {
        this.connection = con;
        // Initialize the secret key
    }

    // Generate and initialize the secret key
    private static void generateSecretKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128); // block size is 128 bits
            secretKey = keyGenerator.generateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User userLogin(String email, String password) {
        User user = null;
        String encryptedPassword = "";
        try {
            encryptedPassword = encryptPassword(password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            query = "select * from users where users_email=? and users_password=?";
            stmnt = connection.prepareStatement(query);
            stmnt.setString(1, email);
            stmnt.setString(2, encryptedPassword);
            resultset = stmnt.executeQuery();

            if (resultset.next()) {
                user = new User();
                user.setId(resultset.getInt("users_id"));
                user.setName(resultset.getString("users_name"));
                user.setEmail(resultset.getString("users_email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public boolean insertUserDetails(String name, String email, String contact, String password) {
        boolean result = false;

        String encryptedPassword = "";
        try {
            encryptedPassword = encryptPassword(password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            query = "insert into ecommerce_website.users (users_name, users_email, users_ContactNo, users_password) values(?,?,?,?)";
            stmnt = this.connection.prepareStatement(query);
            stmnt.setString(1, name);
            stmnt.setString(2, email);
            stmnt.setString(3, contact);
            stmnt.setString(4, encryptedPassword);
            stmnt.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.out.print(e);
            e.printStackTrace();
        }

        return result;
    }

    public static String encryptPassword(String plaintext) throws Exception {
        EncryptionDecryption obj = new EncryptionDecryption();
        String encryptPassword = obj.encrypt(plaintext, secretKey);
        System.out.println("secret key in encrypt method :" + secretKey);
        return encryptPassword;
    }

    public static String decryptPassword(String plaintext) throws Exception {
        EncryptionDecryption obj = new EncryptionDecryption();
        String decryptPassword = obj.decrypt(plaintext, secretKey);
        return decryptPassword;
    }
}
