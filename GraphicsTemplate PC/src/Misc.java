/**
 * Created by Christian on 25/11/2016.
 */
public class Misc {

    public static double getDistance(SimObject obj1,SimObject obj2){
        return Math.sqrt(Math.pow(obj2.getXpos()-obj1.getXpos(),2)+Math.pow(obj2.getYpos()-obj1.getYpos(),2));
    }

}
