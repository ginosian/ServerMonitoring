import db.DBConnection;
import db.DBTables;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.sql.Connection;

/**
 * Created by Martha on 8/24/2016.
 * WARNING: All test should be run separately as Junit doesn't guarantee order of
 * running test, @Before and @After methods are called before tests and after tests,
 * not before and after each test.
 */
public class TestTablesCreation {

    Connection connection;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void initConnection(){
        DBConnection.createDB("test_server_monitoring");
        connection = DBConnection.getConnection();
    }

    @Test
    public void testLocationTableCreation(){
        connection = DBConnection.getConnection();
        Assert.assertNotNull(DBTables.createLocationDBTable(connection));
    }

    @Test
    public void testMonitorTableCreation(){
        connection = DBConnection.getConnection();
        Assert.assertNotNull(DBTables.createMonitorDBTable(connection));
    }

    @Test
    public void testServerTableCreation(){
        connection = DBConnection.getConnection();
        Assert.assertNotNull(DBTables.createLocationDBTable(connection));
        connection = DBConnection.getConnection();
        Assert.assertNotNull(DBTables.createServerDBTable(connection));
    }

    @Test
    public void testMonitorServerCrossTableCreation(){
        connection = DBConnection.getConnection();
        Assert.assertNotNull(DBTables.createLocationDBTable(connection));
        connection = DBConnection.getConnection();
        Assert.assertNotNull(DBTables.createMonitorDBTable(connection));
        connection = DBConnection.getConnection();
        Assert.assertNotNull(DBTables.createServerDBTable(connection));
        connection = DBConnection.getConnection();
        Assert.assertNotNull(DBTables.createMonitorServerCrossDBTable(connection));
    }

    @Test
    public void testTableDropping(){
        connection = DBConnection.getConnection();
        Assert.assertNotNull(DBTables.createLocationDBTable(connection));
        connection = DBConnection.getConnection();
        Assert.assertNotNull(DBTables.dropDBTable("location", connection));
    }

    @After
     public void dropDB(){
        connection = DBConnection.getConnection();
        Assert.assertNotNull(DBTables.dropDB("test_server_monitoring", connection));
    }
}
