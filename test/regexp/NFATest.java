

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
public class NFATest {

    public NFATest() {
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
    public void testNFASimulationOnlyConcatenation(){
        State state = NFA.post2nfa(Postfix.in2post("aa"));
        assertTrue(NFAsimulation.simulateNFA(state, "aa"));
        state = NFA.post2nfa(Postfix.in2post("aa"));
        assertFalse(NFAsimulation.simulateNFA(state, "aaa"));
        state = NFA.post2nfa(Postfix.in2post("aa"));
        assertFalse(NFAsimulation.simulateNFA(state, "b"));
    }
    @Test
    public void testNFASimulationOnlyAlternation(){
        State state = NFA.post2nfa(Postfix.in2post("a|d"));
        assertTrue(NFAsimulation.simulateNFA(state, "a"));
        state = NFA.post2nfa(Postfix.in2post("a|d"));
        assertTrue(NFAsimulation.simulateNFA(state, "d"));
        state = NFA.post2nfa(Postfix.in2post("a|d"));
        assertFalse(NFAsimulation.simulateNFA(state, "p"));
        state = NFA.post2nfa(Postfix.in2post("a|d"));
        assertFalse(NFAsimulation.simulateNFA(state, "ad"));
    }
    @Test
    public void testNFASimulationConcatenationAndAlternation(){
        State state = NFA.post2nfa(Postfix.in2post("ab|cd"));
        assertTrue(NFAsimulation.simulateNFA(state, "ab"));
        state = NFA.post2nfa(Postfix.in2post("ab|cd"));
        assertTrue(NFAsimulation.simulateNFA(state, "cd"));
        state = NFA.post2nfa(Postfix.in2post("ab|cd"));
        assertFalse(NFAsimulation.simulateNFA(state, "ad"));
        state = NFA.post2nfa(Postfix.in2post("ab|cd"));
        assertFalse(NFAsimulation.simulateNFA(state, "cb"));
        state = NFA.post2nfa(Postfix.in2post("ab|cd"));
        assertFalse(NFAsimulation.simulateNFA(state, "abcd"));
        state = NFA.post2nfa(Postfix.in2post("ab|cd"));
        assertFalse(NFAsimulation.simulateNFA(state, "a"));
        state = NFA.post2nfa(Postfix.in2post("ab|cd"));
        assertFalse(NFAsimulation.simulateNFA(state, "b"));

    }

    @Test
    public void testNFASimulationConcatenationAlternationAndParenthesis(){
        State state = NFA.post2nfa(Postfix.in2post("(a|b)c"));
        assertTrue(NFAsimulation.simulateNFA(state, "ac"));
        state = NFA.post2nfa(Postfix.in2post("(a|b)c"));
        assertTrue(NFAsimulation.simulateNFA(state, "bc"));
        state = NFA.post2nfa(Postfix.in2post("(a|b)c"));
        assertFalse(NFAsimulation.simulateNFA(state, "ab"));
        state = NFA.post2nfa(Postfix.in2post("(a|b)c"));
        assertFalse(NFAsimulation.simulateNFA(state, "abc"));
    }

    @Test
    public void testNFASimulationStar(){
        State state = NFA.post2nfa(Postfix.in2post("a*"));
        assertTrue(NFAsimulation.simulateNFA(state, ""));
        state = NFA.post2nfa(Postfix.in2post("a*"));
        assertTrue(NFAsimulation.simulateNFA(state, "a"));
        state = NFA.post2nfa(Postfix.in2post("a*"));
        assertTrue(NFAsimulation.simulateNFA(state, "aaaaa"));
        state = NFA.post2nfa(Postfix.in2post("a*"));
        assertFalse(NFAsimulation.simulateNFA(state, "b"));
    }
    @Test
    public void testNFASimulationStarAndConcatenation(){
        State state = NFA.post2nfa(Postfix.in2post("a*b"));
        assertTrue(NFAsimulation.simulateNFA(state, "b"));
        state = NFA.post2nfa(Postfix.in2post("a*b"));
        assertTrue(NFAsimulation.simulateNFA(state, "ab"));
        state = NFA.post2nfa(Postfix.in2post("a*b"));
        assertTrue(NFAsimulation.simulateNFA(state, "aaaab"));
        state = NFA.post2nfa(Postfix.in2post("a*b"));
        assertFalse(NFAsimulation.simulateNFA(state, ""));
        state = NFA.post2nfa(Postfix.in2post("a*b"));
        assertFalse(NFAsimulation.simulateNFA(state, "a"));
        state = NFA.post2nfa(Postfix.in2post("a*b"));
        assertFalse(NFAsimulation.simulateNFA(state, "aaa"));
    }
    @Test
    public void testNFASimulationStarAlternationConcatenationAndParenthesis(){
        State state = NFA.post2nfa(Postfix.in2post("(ab)*"));
        assertTrue(NFAsimulation.simulateNFA(state, ""));
        state = NFA.post2nfa(Postfix.in2post("(ab)*"));
        assertTrue(NFAsimulation.simulateNFA(state, "abab"));
        state = NFA.post2nfa(Postfix.in2post("(ab)*|xxx"));
        assertTrue(NFAsimulation.simulateNFA(state, "abab"));
        state = NFA.post2nfa(Postfix.in2post("(ab)*|xxx"));
        assertTrue(NFAsimulation.simulateNFA(state, "xxx"));
        state = NFA.post2nfa(Postfix.in2post("((ab)*|xxx)*"));
        assertTrue(NFAsimulation.simulateNFA(state, "ababxxxab"));
        state = NFA.post2nfa(Postfix.in2post("((ab)*|xxx)*"));
        assertFalse(NFAsimulation.simulateNFA(state, "ababxab"));
        state = NFA.post2nfa(Postfix.in2post("((ab)*|xxx)*"));
        assertFalse(NFAsimulation.simulateNFA(state, "ccc"));
        state = NFA.post2nfa(Postfix.in2post("((ab)*|xxx)*"));
        assertTrue(NFAsimulation.simulateNFA(state, ""));
    }
}