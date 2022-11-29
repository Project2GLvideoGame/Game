package engine;

public class Frequences{

    private static final int FPS = 60;
    private static final double DRAWINTERNAL = 1_000_000_000/FPS;
    private static long lastTime = System.nanoTime();
    private static double delta = 0;
    private static long currentTime;

    public static boolean shouldRefresh(){
        currentTime = System.nanoTime();
        delta += (currentTime - lastTime) / DRAWINTERNAL;
        lastTime = currentTime;

        if(delta < 1) return false;
        delta--;
        return true;
    }
    

}