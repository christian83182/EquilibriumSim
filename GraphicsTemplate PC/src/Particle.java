import java.awt.*;
import java.util.Iterator;

/**
 * Created by Christian on 23/11/2016.
 */
public class Particle extends SimObject {
    private int radius;
    private StaticObject parentContainer;
    Handler parentHandler;

    public Particle(int xpos, int ypos, double direction, Handler parentHandler,  ID objectId) {
        super(xpos, ypos, objectId);
        this.direction = direction;
    }

    @Override
    public void tick() {
        xVel = resolveXComponent();
        yVel = resolveYComponent();
        checkContainerCollision();
        checkParticleCollision();
        updateDirection();
        xpos += xVel;
        ypos += yVel;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(this.getXpos() - this.getRadius(), this.getYpos() - this.getRadius(), this.getRadius() * 2, this.getRadius() * 2);
    }

    public void checkParticleCollision(){
        Iterator<SimObject> collisionIterator = parentHandler.getAllSimObjects().iterator();
        while (collisionIterator.hasNext()){
            SimObject currentParticle = collisionIterator.next();
            if (Misc.getDistance(this,currentParticle)<= radius*2 && currentParticle!=this){
                System.out.println("COLLISION");
            }
        }
    }

    public boolean checkContainerCollision() {
        double lowerXbound;
        double upperXbound;
        double lowerYbound;
        double upperYbound;
        if (parentContainer == null) {
            lowerXbound = 0;
            upperXbound = Game.WIDTH;
            lowerYbound = 0;
            upperYbound = Game.HEIGHT;
        } else {
            lowerXbound = parentContainer.getXpos() - parentContainer.getXdim() / 2;
            upperXbound = parentContainer.getXpos() + parentContainer.getXdim() / 2;
            lowerYbound = parentContainer.getYpos() - parentContainer.getYdim() / 2;
            upperYbound = parentContainer.getYpos() + parentContainer.getYdim() / 2;
        }
        if (xpos - radius <= lowerXbound) {
            setxVel(-xVel);
            while (xpos - radius <= lowerXbound) {
                xpos++;
            }
            return true;
        }
        if (xpos + radius >= upperXbound) {
            setxVel(-xVel);
            while (xpos + radius >= upperXbound) {
                xpos--;
            }
            return true;
        }
        if (ypos - radius <= lowerYbound) {
            setyVel(-yVel);
            while (ypos - radius <= lowerYbound) {
                ypos++;
            }
            return true;
        }
        if (ypos + radius >= upperYbound) {
            setyVel(-yVel);
            while (ypos + radius >= upperYbound) {
                ypos--;
            }
            return true;
        }
        return false;
    }

    public void updateDirection() {
        if (yVel <= 0) {
            setDirection(Math.toDegrees(Math.acos((xVel / vel) / (Math.sqrt(Math.pow(xVel / vel, 2) + Math.pow(yVel / vel, 2))))));
        } else if (yVel > 0) {
            setDirection(360 - Math.toDegrees(Math.acos((xVel / vel) / (Math.sqrt(Math.pow(xVel / vel, 2) + Math.pow(yVel / vel, 2))))));
        }
    }

    public double resolveXComponent() {
        return vel * Math.cos(Math.toRadians(direction));
    }

    public double resolveYComponent() {
        return vel * -Math.sin(Math.toRadians(direction));
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public StaticObject getParentContainer() {
        return parentContainer;
    }

    public void setParentContainer(StaticObject parentContainer) {
        this.parentContainer = parentContainer;
    }

    public Handler getParentHandler() {
        return parentHandler;
    }

    public void setParentHandler(Handler parentHandler) {
        this.parentHandler = parentHandler;
    }
}
