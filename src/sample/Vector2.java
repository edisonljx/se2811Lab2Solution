package sample;

public class Vector2 {
    public double x;
    public double y;
    public Vector2()
    {
        x = 0;
        y = 0;
    }
    public Vector2(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    private static Vector2 left = new Vector2(-1, 0);
    private static Vector2 right = new Vector2(1, 0);
    private static Vector2 up = new Vector2(0, 1);
    private static Vector2 down = new Vector2(0, -1);
    private static Vector2 zero = new Vector2(0, 0);
    public static Vector2 getLeft(){
        return left;
    }
    public static Vector2 getRight(){
        return right;
    }
    public static Vector2 getUp(){
        return up;
    }
    public static Vector2 getDown(){
        return down;
    }
    public static Vector2 getZero(){
        return zero;
    }

    public static double distance(Vector2 a, Vector2 b){
        double x = a.x - b.x;
        x *= x;
        double y = a.y - b.y;
        y *= y;
        return Math.sqrt(x + y);
    }
}
