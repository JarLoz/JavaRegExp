
package regexp;

/**
 * The State class specifies the State object, which is the basic building block of an NFA (Non-deterministic finite automata).
 * There are three different kinds of states that the NFA can consist of. The literal state, specified by a character,
 * represents a state with one outgoing transition, labelled with the given character. The split state, specified by the two outgoing
 * states, represents a state with two free transitions to the two given states. The final option is the match state, that
 * has represents a matching state in the NFA with no outgoing transitions.
 *
 * The State also keeps track of lastlist-variable, that is used in the NFA simulation. The lastlist is the generation number
 * of the last list of current states this state has been added. This can be used to check if a given state is already added
 * in the list of current states that is being generated. For more information, see the documentation of NFAsimulation.
 * @author Kimmo Heikkinen
 */
public class State {
    private char c;
    private State out1;
    private State out2;
    private boolean split;
    private boolean match;
    private boolean hasChar;
    private int lastlist;
    /**
     * Initializes a new literal state with one outgoing transition labelled with the given character.
     * @param c the label for the outgoing transition
     */
    public State(char c){
        this.c = c;
        this.out1 = null;
        this.out2 = null;
        this.hasChar = true;
        this.split = false;
        this.match = false;
        this.lastlist = -1;
    }
    /**
     * Initializes a new split state with two outgoing free transitions to the given two states.
     * @param out1 The state in the end of the first free transition
     * @param out2 The state in the end of the second free transition
     */
    public State(State out1, State out2){
        this.hasChar = false;
        this.split = true;
        this.match = false;
        this.out1 = out1;
        this.out2 = out2;
        this.lastlist = -1;
    }
    /**
     * Initializes a new match state with no outgoing transitions.
     */
    public State(){
        this.hasChar = false;
        this.split = false;
        this.match = true;
        this.out1 = null;
        this.out2 = null;
        this.lastlist = -1;
    }
    /**
     *
     * @return true, if the state is a literal state, otherwise false
     */
    public boolean isLiteralState(){
        return hasChar;
    }
    /**
     * Returns the character associated with a literal state. No error checking, so check if the state
     * is a literal state before using.
     * @return the character associated with a literal state.
     */
    public char getChar(){
        return c;
    }
    /**
     *
     * @return true, if the state is a split state, otherwise false.
     */
    public boolean isSplit(){
        return split;
    }
    /**
     *
     * @return true, if the state is a match state.
     */
    public boolean isMatch(){
        return match;
    }
    /**
     *
     * @return the state in the end of the first transition
     */
    public State getOut1(){
        return out1;
    }
    /**
     *
     * @return the state in the end of the second transition.
     */
    public State getOut2(){
        return out2;
    }
    /**
     * If the first state transition doesn't point anywhere yet, this method makes it to point at the state
     * specified in the parameter. If the state is a split state, it does the same thing for the second
     * transition.
     * @param s the state to attach the transitions to.
     */
    public void patch(State s){
        if(out1 == null)
            out1 = s;
        if(out2 == null && split)
            out2 = s;
    }
    /**
     *
     * @return The last generation of the current states list this state has been added to. If the state hasn't been added to any list yet, returns -1.
     */
    public int getLastlist(){
        return lastlist;
    }
    /**
     * Sets the lastlist variable to the specified parameter
     * @param listindex the generation number of a list of current states
     */
    public void setLastlist(int listindex){
        lastlist = listindex;
    }
    /**
     * Prints debugging information of the current state to the standard output stream, not used in the actual application.
     *
     * Prints "Match" if the state is a match state, "Split" if it is a split state and the character associated
     * with the outgoing transition if a literal state.
     */
    public void testPrint(){
        if(split)
            System.out.println("Split");
        else if (match)
            System.out.println("Match");
        else
            System.out.println(c);
    }
}
