

package regexp;


/**
 * The OwnArrayList specifies an explicit implementation of ArrayList, as demanded by Matti Luukkainen
 * for the Data Structures Project course. The functionality is that of a dynamically resizing array.
 * @author Kimmo Heikkinen
 */
public class OwnArrayList <E>{

    private E[] array;
    private int size;
    /**
     * Initializes a new instance of OwnArrayList.
     */
    public OwnArrayList(){
        array = (E[])new Object[10];
        size = 0;
    }
    /**
     * Appends the given object in the end of the array.
     * @param object the object to be appended.
     */
    public void add(E object){
        checkSize();
        array[size] = object;
        size++;
    }
    /**
     *
     * @param i An index of the array
     * @return The object in the specified index of the array
     */

    public E get(int i){
        return array[i];
    }
    /**
     *
     * @return the size of the array
     */

    public int size(){
        return size;
    }

    private void checkSize(){
        if(array.length == size){
            E[] newArray = (E[])new Object[array.length*2];
            for(int i = 0; i < array.length; i++){
                newArray[i] = array[i];
            }
            array = newArray;
        }

    }


}
