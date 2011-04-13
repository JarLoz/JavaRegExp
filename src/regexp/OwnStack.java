

package regexp;


/**
 * The OwnStack specifies an explicit implementation of Stack, as demanded by Matti Luukkainen
 * for the Data Structures Project course. The functionality is equal to your run-of-the-mill generic stack structure.
 * @author Kimmo Heikkinen
 */
public class OwnStack<E>{
    private E[] stack;
    private int top;
    /**
     * Initializes a new instance of OwnStack
     */
    public OwnStack(){
        stack = (E[])new Object[10];
        top = -1;
    }
    /**
     * Pushes a new object into the stack
     * @param object the object to be pushed
     */
    public void push(E object){
        top++;
        checkSize();
        stack[top] = object;
    }
    /**
     * Removes the first object in the stack and returns it.
     * @return the first object in the stack.
     */
    public E pop(){
        E returnObject = stack[top];
        top--;
        return returnObject;
    }
    /**
     * Like pop(), but doesn't remove the object
     * @return the first object in the stack.
     */
    public E peek(){
        return stack[top];
    }
    /**
     *
     * @return true, if the stack is empty, otherwise false.
     */
    public boolean isEmpty(){
        return (top < 0);
    }
    private void checkSize(){
        if(stack.length == top){
            E[] newArray = (E[])new Object[stack.length*2];
            for(int i = 0; i < stack.length; i++){
                newArray[i] = stack[i];
            }
            stack = newArray;
        }

    }
}
