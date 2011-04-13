

package regexp;
/**
 * The NFA class contains a static method for converting a postfix-form (also known as
 * reverse polish notation, RPN) regular expression
 * into a Non-deterministic Finite Automata (NFA) that is used for evaluating if a certain character
 * string matches the given regular expression.
 * @author Kimmo Heikkinen
 */
public class NFA {
    /**
     * Generates the NFA from given character string using a simple stack automatic. The algorithm follows closely
     * the one given by Ken Thompson in his 1968 paper concerning regular expression matching. The algorithm performs a finite
     * set of operations for each character in the given string. Therefore the time-requirement for one character is
     * O(1), and therefore the time-requirement for the whole algorithm is O(n). The space requirement is equal to
     * the maximum number of fragments in the stack automata, which is the length of the postfix character string, therefore
     * the space requirement is also O(n).
     * @param postfix The string that contains a regular expression in postfix format
     * @return the starting state of the generated NFA
     */
    public static State post2nfa(String postfix){
        OwnStack <Fragment> fragmentStack = new OwnStack <Fragment> ();
        Fragment completeNfa = new Fragment();
        State matchstate = new State();
        for (int i = 0; i < postfix.length(); i++){
            char c = postfix.charAt(i);
            if(isLiteral(c)){
                State literalState = new State(c);
                fragmentStack.push(new Fragment(literalState, literalState));
            }
            else if (isConcatenation(c)){
                Fragment previous2 = fragmentStack.pop();
                Fragment previous1 = fragmentStack.pop();
                patchFragmentToAState(previous1, previous2.getStart());
                fragmentStack.push(new Fragment(previous1.getStart(), previous2.getOutArrows() ) );
            }
            else if (isAlternation(c)){
                Fragment previous2 = fragmentStack.pop();
                Fragment previous1 = fragmentStack.pop();
                State newState = new State(previous1.getStart(), previous2.getStart());
                fragmentStack.push(new Fragment(newState, appendOutArrows(previous1.getOutArrows(), previous2.getOutArrows())));
            }
            else if (isStar(c)){
                Fragment previous = fragmentStack.pop();
                State newState = new State(previous.getStart(), null);
                patchFragmentToAState(previous, newState);
                fragmentStack.push(new Fragment(newState, newState));

            }
        }
        completeNfa = fragmentStack.pop();
        patchFragmentToAState(completeNfa, matchstate);
        return completeNfa.getStart();
    }

    private static boolean isLiteral(char c) {
        return !isConcatenation(c) && !isAlternation(c) && !isStar(c);
    }

    private static boolean isStar(char c) {
        return c == '*';
    }

    private static boolean isAlternation(char c) {
        return c == '|';
    }

    private static boolean isConcatenation(char c) {
        return c == '.';
    }
    private static OwnArrayList<State> appendOutArrows(OwnArrayList<State> a, OwnArrayList<State> b){
        OwnArrayList <State> appended = new OwnArrayList <State> ();
        for (int i = 0; i < a.size(); i++){
            appended.add(a.get(i));
        }
        for (int i = 0; i < b.size(); i++){
            appended.add(b.get(i));
        }
        return appended;
    }
    private static void patchFragmentToAState(Fragment a, State s){
        OwnArrayList<State> toBePatched = a.getOutArrows();
        for (int i = 0; i < toBePatched.size(); i++){
            State openarrows = toBePatched.get(i);
            openarrows.patch(s);
        }
    }
}
