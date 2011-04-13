

package regexp;
/**
 * The NFAsimulation class contains the static method used for simulating an NFA.
 * @author Kimmo Heikkinen
 */
public class NFAsimulation {
    /**
     * The simulateNFA method contains the algorithm used for simulating an NFA. The algorithm used is the one introduced by
     * Ken Thompson in his 1968 paper concerning regular expression checking, the most important feature of which is multiple
     * state simulation, meaning that the algorithm keeps a list of all possible states of the automata and advances all these
     * possibilities in one step, instead of recursively trying each of the possible paths of the automata. So, for each character
     * in the input string, the algorithm tries to advance onwards from all the possible states it can be. When a single
     * advance in the automata has a time-requirement of O(1), this gives us a total maximum time requirement of O(nm), where n
     * is the length of the string that is being checked, and m is the number of states in the NFA (which corresponds to
     * the length of the regular expression used to construct the NFA).
     * @param startstate the starting state of the NFA
     * @param input the string that is to be checked against the NFA
     * @return true, if the string given as input matches the regular expression that the NFA represents, otherwise false.
     */
    public static boolean simulateNFA(State startstate, String input){
        OwnArrayList <State> currentList = new OwnArrayList<State> ();
        OwnArrayList <State> nextList = new OwnArrayList<State> ();
        int listID = 0;
        addState(currentList, startstate, listID);
        for (int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            listID = step(currentList, c, nextList, listID);
            currentList = nextList;
            nextList = new OwnArrayList<State>();
        }
        return containsMatchState(currentList);
    }
    private static boolean containsMatchState(OwnArrayList<State> finalList){
        for (int i = 0; i < finalList.size(); i++){
            State s = finalList.get(i);
            if (s.isMatch()){
                return true;
            }
        }
        return false;
    }
    private static int step(OwnArrayList<State> currentList, char c, OwnArrayList<State> nextList, int listID){
        listID++;
        for (int i = 0; i < currentList.size(); i++){
            State s = currentList.get(i);
            if(c == s.getChar()){
                addState(nextList, s.getOut1(), listID);
            }
        }
        return listID;
    }
    private static void addState(OwnArrayList<State> list, State s, int listID){
        if (s == null || s.getLastlist() == listID)
            return;
        s.setLastlist(listID);
        if(s.isSplit()){
            addState(list, s.getOut1(), listID);
            addState(list, s.getOut2(), listID);
            return;
        }
        list.add(s);
    }
}
