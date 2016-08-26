import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by Martha on 8/23/2016.
 */
public class DBConnectionTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void actualConnectionTest() throws ClassNotFoundException{

//        Class.forName("com.mysql.jdbc.Driver");
//        Assert.assertFalse(thrown.equals(ExpectedException.none()));
//
//        Connection connection = DBConnection.setupConnection();
//        Assert.assertNotNull(connection);

    }

    @Test
    public void mockedConnectionTest() throws ClassNotFoundException{
//
//        MockDBConnection mockDBConnection = new MockDBConnection();
//
//        Class.forName("com.mysql.jdbc.Driver");
//        Assert.assertFalse(thrown.equals(ExpectedException.none()));
//
//        Connection connection = mockDBConnection.getConnection();
//        Assert.assertNotNull(connection);

    }
}
