package de.qaware.showcase.spock.basics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static java.lang.Math.round;
import static java.lang.Math.sqrt;
import static org.junit.Assert.assertTrue;

/**
 * The same Pythagoras test using parameterized JUnit.
 */
@RunWith(Parameterized.class)
public class PythagorasTest {

    private double a;
    private double b;
    private double c;

    public PythagorasTest(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Test
    public void testPythagoras() throws Exception {
        double aSquare = Math.pow(a, 2);
        double bSquare = Math.pow(b, 2);
        double cSquare = round(Math.pow(c, 2));

        assertTrue(aSquare + bSquare == cSquare);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1, 4, sqrt(17)},
                {2, 5, sqrt(29)},
                {3, 6, sqrt(45)}});
    }
}
