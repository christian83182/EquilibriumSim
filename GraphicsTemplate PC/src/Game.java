import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

    final static double SCALE= 2 ;
    final static int HEIGHT = (int) (360*SCALE);
    final static int WIDTH = (int)(640*SCALE);
    final String title = "Graphics Test";
    public Thread thread;
    public boolean isRunning = false;
    private Handler handler;


    public Game(){
        Window frame = new Window(WIDTH,HEIGHT+36,title,this);
        this.start();
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        isRunning = true;
    }

    public synchronized void stop(){
        try {
            thread.join();
            isRunning = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){

        handler = new Handler();

        long lastTime = System.nanoTime();
        final double AMMOUNT_OF_TICKS = 60.0;
        final double TIME_PER_TICK = 1000000000/AMMOUNT_OF_TICKS;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        StaticObject container = new StaticObject(WIDTH/2,HEIGHT/2,0.9*WIDTH,0.9*HEIGHT,ID.Container);
        handler.addObject(container);

        Particle par1 = new Particle(WIDTH/4,HEIGHT/2,31, handler,ID.Particle);
        par1.setVel(10);
        par1.setRadius(10);
        par1.setParentHandler(handler);
        par1.setParentContainer(container);
        handler.addObject(par1);

        Particle par2 = new Particle(3*WIDTH/4,HEIGHT/2,31, handler,ID.Particle);
        par2.setVel(10);
        par2.setRadius(10);
        par2.setParentHandler(handler);
        par2.setParentContainer(container);
        handler.addObject(par2);

        Particle par3 = new Particle(5*WIDTH/1,HEIGHT/3,31, handler,ID.Particle);
        par3.setVel(10);
        par3.setRadius(10);
        par3.setParentHandler(handler);
        par3.setParentContainer(container);
        handler.addObject(par2);

        while(isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) /TIME_PER_TICK;
            lastTime = now;

            while (delta >= 1){
                tick();
                delta--;
            }

            if (isRunning){
                render();
            }
            frames++;

            if (System.currentTimeMillis() - timer > 1000){
                timer +=1000;
                System.out.println("[FPS: "+frames+"] [Objects: "+handler.getObjectCount()+"]");
                frames = 0;
            }
        }
    }

    public void tick(){
        handler.tick();
    }

    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if (bs == null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0,getWidth(),getHeight());
        handler.render(g);
        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        Game newGame = new Game();
    }

}
