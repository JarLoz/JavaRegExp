
package regexp;
/**
 * The Postfix class contains the static methods used for converting a regular expression from infix to postfix
 * (also known as Reverse Polish Notation) form.
 * @author Kimmo Heikkinen
 */
public class Postfix {
    /**
     * The in2post method contains the algorithm for converting a regular expression to postfix form. It is a simple stack
     * automaton that uses Edsger Djikstra's "shunting yard algorithm" for the conversion. The time-requirement is O(n), where
     * n is the length of the input string, because each character invokes only a finite set of operations.
     * @param input The original string in infix form.
     * @return the postfix form of the original string.
     */
    public static String in2post(String input){
        input = addConcatenations(input);
        OwnStack <Character> operatorStack = new OwnStack<Character>();
        StringBuffer outputString = new StringBuffer();
        for(int i = 0; i < input.length(); i++){
            char c = input.charAt(i);


            if(isAnOperator(c)){
                if(operatorStack.isEmpty()){
                     operatorStack.push(c);
                }
                else{
                    char topOfStack = operatorStack.peek();
                    if(isAnOperator(topOfStack)){
                        int presedenceValue = precedence(c, topOfStack);
                        if (presedenceValue <= 0){
                            topOfStack = operatorStack.pop();
                            outputString.append(topOfStack);
                        }
                    }
                    operatorStack.push(c);
                }
            }


           else if(isOpenParenthesis(c)){
               operatorStack.push(c);

            }


           else if(isCloseParenthesis(c)){
               char topOfStack = operatorStack.pop();
               while(!isOpenParenthesis(topOfStack)){
                   outputString.append(topOfStack);
                   topOfStack = operatorStack.pop();
               }
            }
            else{
                outputString.append(c);
            }
        }
        while(!operatorStack.isEmpty()){
            outputString.append(operatorStack.pop());
        }
        return outputString.toString();
    }

    /**
     * addConcatenations simply adds the concatenation symbols to a regular expression string. For example,
     * the regular expression "aa" becomes "a.a" . The symbol used in this implementation is '.' for practicality's
     * sake, and adding the explicit concatenation symbols simplifies rest of the implementation.
     * @param original the original regular expression
     * @return the original with concatenation symbols added.
     */
    public static String addConcatenations(String original){
        StringBuffer withConcatenations = new StringBuffer();
        withConcatenations.append(original.charAt(0));

        for(int i = 1; i < original.length(); i++){
            char c = original.charAt(i);
            if(possibleConcatenation(withConcatenations))
                if(isCharacter(c) || isOpenParenthesis(c))
                    withConcatenations.append('.');
            withConcatenations.append(c);

        }
        return withConcatenations.toString();

    }



    private static int precedence(char a, char b){
        if(isMultiplication(a)){
            if(isMultiplication(b))
                return 0;
            else
                return 1;
        }

        else if(isConcatenation(a)){
            if(isMultiplication(b))
                return -1;
            else if (b == a)
                return 0;
            else
                return 1;
        }

        else if(isAlternation(a)){
            if(a == b)
                return 0;
            else return -1;
        }

        return -2;
    }
    private static boolean possibleConcatenation(StringBuffer withConcatenations) {
        return isCharacter(withConcatenations.charAt(withConcatenations.length() - 1))
                || isCloseParenthesis(withConcatenations.charAt(withConcatenations.length() - 1))
                || isMultiplication(withConcatenations.charAt(withConcatenations.length() - 1));
    }
    private static boolean isCharacter(char a){
        return !isAnOperator(a) && !isCloseParenthesis(a) && !isOpenParenthesis(a);
    }
    private static boolean isAlternation(char a) {
        return a == '|';
    }

    private static boolean isConcatenation(char a) {
        return a == '.';
    }

    private static boolean isMultiplication(char a) {
        return a == '*' || a == '+' || a == '?';
    }
    private static boolean isAnOperator(char a){
        return a == '*' || a == '+' || a == '?' || a == '|' ||a == '.';
    }

    private static boolean isCloseParenthesis(char c) {
        return c == ')';
    }

    private static boolean isOpenParenthesis(char c) {
        return c == '(';
    }
}
