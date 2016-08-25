import db.DBConnection;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.sql.Connection;

/**
 * Created by Martha on 8/23/2016.
 */
public class DBConnectionTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void actualConnectionTest() throws ClassNotFoundException{

        Class.forName("com.mysql.jdbc.Driver");
        Assert.assertFalse(thrown.equals(ExpectedException.none()));

        Connection connection = DBConnection.getConnection();
        Assert.assertNotNull(connection);

    }

    @Test
    public void mockedConnectionTest() throws ClassNotFoundException{

        MockDBConnection mockDBConnection = new MockDBConnection();

        Class.forName("com.mysql.jdbc.Driver");
        Assert.assertFalse(thrown.equals(ExpectedException.none()));

        Connection connection = mockDBConnection.getConnection();
        Assert.assertNotNull(connection);

    }
}
