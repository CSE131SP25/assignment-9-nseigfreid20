package assignment9;

import java.awt.Color;
import edu.princeton.cs.introcs.StdDraw;

public class Food {
    public static final double FOOD_SIZE = 0.04;
    private double x, y;
    
    public Food() {
        respawn();
    }
    
    public void respawn() {
        x = Math.random() * (1.0 - FOOD_SIZE) + FOOD_SIZE/2;
        y = Math.random() * (1.0 - FOOD_SIZE) + FOOD_SIZE/2;
    }
    
    public void draw() {
        StdDraw.setPenColor(Color.RED);
        StdDraw.filledCircle(x, y, FOOD_SIZE/2);
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
}