

package regexp;
/**
 * The Main class contains the main method of the regexp application.
 * @author Kimmo Heikkinen
 */
public class Main {

    /**
     * The main method of the regexp application that handles reading command line arguments and passing them on.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args.length < 2 || args.length > 2){
            System.out.println("Usage: java -jar regexp.jar <Expression> <String>");
            return;
        }
        State startstate = NFA.post2nfa(Postfix.in2post(args[0]));
        Boolean matches = NFAsimulation.simulateNFA(startstate, args[1]);
        if(matches)
            System.out.println("It's a match");
        else
            System.out.println("No match");
    }
}
