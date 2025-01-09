package tr.com.huseyinaydin;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot.
 * @since 1994
 */

public class QRCodeGeneratorTest extends TestCase {

    public QRCodeGeneratorTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(QRCodeGeneratorTest.class);
    }

    public void testApp() {
        assertTrue(true);
    }
}