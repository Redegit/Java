import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void testSum() {
        assertEquals(5, MyClass.sum(2, 3));
        assertEquals(0, MyClass.sum(0, 0));
        assertEquals(-5, MyClass.sum(-2, -3));
    }

    @Test
    public void testIsPositive() {
        assertTrue(MyClass.isPositive(5));
        assertFalse(MyClass.isPositive(-5));
        assertFalse(MyClass.isPositive(0));
    }

    @Test
    public void testReverseString() {
        assertEquals("cba", MyClass.reverseString("abc"));
        assertEquals("", MyClass.reverseString(""));
        assertEquals("racecar", MyClass.reverseString("racecar"));
    }
}

