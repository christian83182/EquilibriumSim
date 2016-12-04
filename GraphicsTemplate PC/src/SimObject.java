import java.awt.*;

public abstract class SimObject {

    ID objectId;
    int xpos;
    int ypos;
    double vel;
    double xVel;
    double yVel;
    boolean visible = true;
    boolean expired = false;
    double direction;

    public SimObject(int xpos, int ypos, ID objectId) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.objectId = objectId;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public ID getObjectId() {
        return objectId;
    }

    public void setObjectId(ID objectId) {
        this.objectId = objectId;
    }

    public int getXpos() {
        return xpos;
    }

    public void setXpos(int xpos) {
        this.xpos = xpos;
    }

    public int getYpos() {
        return ypos;
    }

    public void setYpos(int ypos) {
        this.ypos = ypos;
    }

    public double getVel() {
        return vel;
    }

    public void setVel(double vel) {
        this.vel = vel;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public double getxVel() {
        return xVel;
    }

    public void setxVel(double xVel) {
        this.xVel = xVel;
    }

    public double getyVel() {
        return yVel;
    }

    public void setyVel(double yVel) {
        this.yVel = yVel;
    }
}
