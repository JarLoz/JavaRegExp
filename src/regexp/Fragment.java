

package regexp;
/**
 * The Fragment class specifies the Fragment object, that is used in the generation of an NFA. The Fragment is
 * a partial NFA, and by patching these Fragments together the algorithm specified in the NFA-class generates
 * the automata. The Fragment keeps track of the starting state of the partial automata, as well as the states with
 * "dangling arrows", that is, state transitions that do not point to anything yet.
 * @author Kimmo Heikkinen
 */
public class Fragment {
    private State start;
    private OwnArrayList <State> outArrows;
    /**
     * Initializes a new instance of Fragment, with starting state and one outwards-pointing state.
     * @param start The starting state of the fragment
     * @param out The outwards-pointing state
     */
    public Fragment(State start, State out){
        this.outArrows = new OwnArrayList<State>();
        outArrows.add(out);
        this.start = start;
    }
    /**
     * Initializes a new instance of Fragment, with starting state and a number of outwards-pointing
     * states, contained in an instance of OwnArrayList.
     * @param start The starting state of the fragment
     * @param outArrows The instance of OwnArrayList that contains the outwards-pointing states.
     */
    public Fragment(State start, OwnArrayList outArrows){
        this.outArrows = outArrows;
        this.start = start;
    }
    /**
     * Initializes a new instance of Fragment with no starting state and no outwards pointing arrows,
     * creating in essence an empty fragment.
     */
    public Fragment(){
        start = null;
        outArrows = null;
    }
    /**
     *
     * @return the starting state of the Fragment
     */
    public State getStart(){
        return start;
    }
    /**
     *
     * @return the outwards pointing arrows contained in an instance of OwnArrayList
     */
    public OwnArrayList getOutArrows(){
        return outArrows;
    }

}
