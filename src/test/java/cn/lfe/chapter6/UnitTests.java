package cn.lfe.chapter6;

import cn.lfe.Container;
import cn.lfe.chapter2.ReferenceContainer;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author chen yue
 * @date 2024-08-10 16:56:39
 */
public class UnitTests {

    private Container a, b, c;

    @Before
    public void setUp() {
        a = new ReferenceContainer();
        b = new ReferenceContainer();
        c = new ReferenceContainer();
    }

    @Test
    public void testNewContainerIsEmpty() {
        assertEquals("new container is not empty", 0, a.getAmount(), 0.0);
    }

    @Test
    public void testNewContainerIsEmptyUseHamcrest() {
        assertThat("new container is not empty", a.getAmount(), new BaseMatcher<Double>() {
            @Override
            public boolean matches(Object o) {
                if (!(o instanceof Double)) {
                    return false;
                }

                return (double) o == 0;
            }

            @Override
            public void describeTo(Description description) {}
        });
    }

    @Test
    public void testAddValidNegativeToConnected() {
        a.connectTo(b);
        a.addWater(10);
        a.addWater(-4);
        assertEquals("should be 3", 3, a.getAmount(), 0.0);
    }

    @Test
    public void testAddPositiveToIsolated() {
        a.addWater(1);
        assertEquals("should be 1.0", 1, a.getAmount(), 0.0);
    }

    @Test
    public void testAddZeroToIsolated() {
        a.addWater(10.5);
        a.addWater(-2.5);
        assertEquals("should be 8", 8, a.getAmount(), 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddInvalidNegativeToIsolated() {
        a.addWater(-1);
    }

    @Test
    public void testAConnectB() {
        a.connectTo(b);
        a.addWater(2);
        assertEquals("should be 1.0", 1.0, a.getAmount(), 0.0);
    }

    @Test
    public void testAConnectBAndAConnectC() {
        a.connectTo(b);
        a.connectTo(c);
        a.addWater(3);
        assertEquals("should be 1.0", 1.0, a.getAmount(), 0.0);
    }

    @Test
    public void testAConnectBAndBConnectC() {
        a.connectTo(b);
        b.connectTo(c);
        a.addWater(3);
        assertEquals("should be 1.0", 1.0, a.getAmount(), 0.0);
    }
}
