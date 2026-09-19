package db;

import domm.AbstractDomainObject;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

// Genericki broker koji radi sa bilo kojim AbstractDomainObject-om.
// Zna samo da poziva tableName(), alias(), join(), columnsForInsert(), valuesForInsert(), valuesForUpdate(), requirement(), requirementForSelect(),
//      za sve ostalo je glup !!!
// a konkretna domenska klasa (Korisnik, Trener, Licenca...) zna svoje detalje.
public class DBBroker {
    private static DBBroker instance;
    private Connection connection;

    private DBBroker() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("dbconfig.properties"));
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DBBroker getInstance() {
        if (instance == null) {
            instance = new DBBroker();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public ArrayList<AbstractDomainObject> select(AbstractDomainObject ado) throws SQLException {
        String upit = "SELECT * FROM " + ado.tableName() + " " + ado.alias()
                + " " + ado.join() + " " + ado.requirementForSelect(ado);
        System.out.println(upit);
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(upit);
        return ado.getList(rs);
    }

    public PreparedStatement insert(AbstractDomainObject ado) throws SQLException {
        String naredba = "INSERT INTO " + ado.tableName() + " "
                + ado.columnsForInsert() + " VALUES(" + ado.valuesForInsert() + ")";
        System.out.println(naredba);
        PreparedStatement ps = connection.prepareStatement(naredba, Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
        return ps;
    }

    public void update(AbstractDomainObject ado) throws SQLException {
        String naredba = "UPDATE " + ado.tableName() + " SET "
                + ado.valuesForUpdate() + " WHERE " + ado.requirement();
        System.out.println(naredba);
        Statement s = connection.createStatement();
        s.executeUpdate(naredba);
            int brojIzmenjenih = s.executeUpdate(naredba);

    System.out.println("Broj izmenjenih redova: " + brojIzmenjenih);
    }

    public void delete(AbstractDomainObject ado) throws SQLException {
        String naredba = "DELETE FROM " + ado.tableName() + " WHERE " + ado.requirement();
        System.out.println(naredba);
        Statement s = connection.createStatement();
        s.executeUpdate(naredba);
    }
}
