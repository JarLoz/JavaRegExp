/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package regexp;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author koheikki
 */
public class PostfixTest {

    public PostfixTest() {
    }

    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSimpleCatenation() {
        assertEquals("a.a", Postfix.addConcatenations("aa") );
        assertEquals("a.a.b", Postfix.addConcatenations("aab"));
        assertEquals("a|b.c", Postfix.addConcatenations("a|bc"));
        assertEquals("a*", Postfix.addConcatenations("a*"));
        assertEquals("(a.b)", Postfix.addConcatenations("(ab)"));
        assertEquals("a.(a|b)", Postfix.addConcatenations("a(a|b)"));
        assertEquals("a.b.(a|c).a.a", Postfix.addConcatenations("ab(a|c)aa"));
        assertEquals("a|b.c.b.(b.b)", Postfix.addConcatenations("a|bcb(bb)"));
        assertEquals("a*.b", Postfix.addConcatenations("a*b"));
        assertEquals("a?.b.(c|a*)?.(e.e|ä*).b", Postfix.addConcatenations("a?b(c|a*)?(ee|ä*)b"));
    }

    @Test
    public void testSimplePostfix(){
        assertEquals("ab.", Postfix.in2post("ab"));
        assertEquals("a*b.", Postfix.in2post("a*b"));
        assertEquals("ab.", Postfix.in2post("(ab)"));
        assertEquals("a*b.", Postfix.in2post("(a*b)"));
        assertEquals("ab|*a.", Postfix.in2post("(a|b)*a"));
    }

}