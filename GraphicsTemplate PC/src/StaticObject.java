import java.awt.*;

/**
 * Created by Christian on 23/11/2016.
 */
public class StaticObject extends SimObject{
    double xdim;
    double ydim;

    public StaticObject(int xpos, int ypos, double xdim, double ydim, ID objectId) {
        super(xpos, ypos, objectId);
        this.xdim = xdim;
        this.ydim = ydim;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect((int)(xpos-(xdim/2)),(int)(ypos-(ydim/2)), (int)xdim,(int)ydim);
    }

    public double getXdim() {
        return xdim;
    }

    public void setXdim(double xdim) {
        this.xdim = xdim;
    }

    public double getYdim() {
        return ydim;
    }

    public void setYdim(double ydim) {
        this.ydim = ydim;
    }
}
