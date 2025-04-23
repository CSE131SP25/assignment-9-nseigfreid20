package assignment9;

import java.awt.Color;
import java.awt.event.KeyEvent;
import edu.princeton.cs.introcs.StdDraw;

public class Game {
    private Snake snake;
    private Food food;
    private int score;
    
    public Game() {
        StdDraw.enableDoubleBuffering();
        snake = new Snake();
        food = new Food();
        score = 0;
    }
    
    public void play() {
        while (snake.isInbounds() && !snake.checkSelfCollision()) {
            int dir = getKeypress();
            if (dir != -1) {
                snake.changeDirection(dir);
            }
            
            snake.move();
            
            if (snake.eatFood(food)) {
                food.respawn();
                score++;
            }
            
            updateDrawing();
        }
        
        // Game over
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.text(0.5, 0.5, "Game Over! Score: " + score);
        StdDraw.show();
        StdDraw.pause(2000);
    }
    
    private int getKeypress() {
        if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
            return 1;
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
            return 2;
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
            return 3;
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
            return 4;
        } else {
            return -1;
        }
    }
    
    private void updateDrawing() {
        StdDraw.clear();
        
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.text(0.5, 0.95, "Score: " + score);
        
        snake.draw();
        food.draw();
        
        StdDraw.pause(50);
        StdDraw.show();
    }
    
    public static void main(String[] args) {
        Game g = new Game();
        g.play();
    }
}