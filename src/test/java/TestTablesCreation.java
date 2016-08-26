import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
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
//        DBConnection.createDB("test_server_monitoring");
//        connection = DBConnection.openConnection();
    }

    @Test
    public void testLocationTableCreation(){
//        connection = DBConnection.openConnection();
//        Assert.assertNotNull(DBTables.createLocationDBTable(connection));
    }

    @Test
    public void testMonitorTableCreation(){
//        connection = DBConnection.openConnection();
//        Assert.assertNotNull(DBTables.createMonitorDBTable(connection));
    }

    @Test
    public void testServerTableCreation(){
//        connection = DBConnection.openConnection();
//        Assert.assertNotNull(DBTables.createLocationDBTable(connection));
//        connection = DBConnection.openConnection();
//        Assert.assertNotNull(DBTables.createServerDBTable(connection));
    }

    @Test
    public void testMonitorServerCrossTableCreation(){
//        connection = DBConnection.openConnection();
//        Assert.assertNotNull(DBTables.createLocationDBTable(connection));
//        connection = DBConnection.openConnection();
//        Assert.assertNotNull(DBTables.createMonitorDBTable(connection));
//        connection = DBConnection.openConnection();
//        Assert.assertNotNull(DBTables.createServerDBTable(connection));
//        connection = DBConnection.openConnection();
//        Assert.assertNotNull(DBTables.createMonitorServerCrossDBTable(connection));
    }

    @Test
    public void testTableDropping(){
//        connection = DBConnection.openConnection();
//        Assert.assertNotNull(DBTables.createLocationDBTable(connection));
//        connection = DBConnection.openConnection();
//        Assert.assertNotNull(DBTables.dropDBTable("location", connection));
    }

    @After
     public void dropDB(){
//        connection = DBConnection.openConnection();
//        Assert.assertNotNull(DBTables.dropDB("test_server_monitoring", connection));
    }
}
