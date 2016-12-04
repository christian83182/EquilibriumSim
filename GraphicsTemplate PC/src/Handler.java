import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Christian on 27/07/2016.
 */
public class Handler {

    LinkedList<SimObject> allSimObjects = new LinkedList<>();

    public SimObject addObject(SimObject newObject){
        allSimObjects.add(newObject);
        return newObject;
    }

    public int getObjectCount(){
        return allSimObjects.size();
    }

    public LinkedList<SimObject> getAllSimObjects(){
        return allSimObjects;
    }

    public void removeObjects(SimObject oldObject){
        allSimObjects.remove(oldObject);
    }

    public void tick(){

        Iterator<SimObject> tickIterator = allSimObjects.iterator();
        while (tickIterator.hasNext()){
            SimObject currentObject = tickIterator.next();
            if (currentObject.isExpired() == true){
                tickIterator.remove();
            } else {
                currentObject.tick();
            }
        }
    }

    public void render(Graphics g){
        Iterator<SimObject> tickIterator = allSimObjects.iterator();

        while(tickIterator.hasNext()){
            SimObject currentObject = tickIterator.next();
            if (currentObject.isVisible() != false){
                currentObject.render(g);
            }
        }
    }

}
