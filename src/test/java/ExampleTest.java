import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ExampleTest {
    @Test
    void testNahid() {
        String name = "Nahid";
        Assertions.assertFalse(name.isEmpty(), "Nahid should have a length greater than 0");
    }

    @Test
    void testJamil() {
        String name = "Jamil";
        Assertions.assertTrue(name.startsWith("J"), "Jamil should start with 'J'");
    }

    @Test
    void testMisha() {
        String name = "Misha";
        Assertions.assertTrue(name.endsWith("sha"), "Misha should end with 'sha'");
    }

    @Test
    void testGosha() {
        String name = "Gosha";
        Assertions.assertTrue(name.contains("os"), "Gosha should contain 'os'");
    }

    // @Test
    // void failTest() {
    //     String name = "Fail";
    //     Assertions.assertTrue(name.contains("os"), "Fail test :-)");
    // }
}