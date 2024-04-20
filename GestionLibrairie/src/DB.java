import java.sql.*;

public class DB {
  // Méthode pour obtenir la connexion à la base de données
  public static Connection getConnection() throws ClassNotFoundException, SQLException {

    // Étape 1: Charger la classe de driver
    Class.forName("com.mysql.jdbc.Driver");

    // Étape 2: Créer l'objet de connexion
    return DriverManager.getConnection("jdbc:mysql://localhost:3306/dev_lib", "root", "root");
  }
}
