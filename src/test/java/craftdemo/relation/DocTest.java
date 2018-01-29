package craftdemo.relation;

import com.craftdemo.backend.UserService;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DocTest {

    @Test
    public void testUserFind() throws Exception {
        UserService service = new UserService("bolt://1neo4j:1neo4j@localhost");
        Iterable<Map<String,Object>>  data = service.search("Howard Roark");
        for (Map<String,Object> ob: data) {
             assertNotNull(ob.get("fullName"));
        }
        
    }
}
