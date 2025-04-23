package assignment9;

import java.util.LinkedList;

public class Snake {
    private static final double SEGMENT_SIZE = 0.03;
    private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.25;
    private LinkedList<BodySegment> segments;
    private double deltaX;
    private double deltaY;
    
    public Snake() {
        segments = new LinkedList<>();
        // Start with one segment in the center
        segments.add(new BodySegment(0.5, 0.5, SEGMENT_SIZE));
        deltaX = 0;
        deltaY = 0;
    }
    
    public void changeDirection(int direction) {
        if(direction == 1 && deltaY == 0) { //up (only if not currently moving down)
            deltaY = MOVEMENT_SIZE;
            deltaX = 0;
        } else if (direction == 2 && deltaY == 0) { //down (only if not currently moving up)
            deltaY = -MOVEMENT_SIZE;
            deltaX = 0;
        } else if (direction == 3 && deltaX == 0) { //left (only if not currently moving right)
            deltaY = 0;
            deltaX = -MOVEMENT_SIZE;
        } else if (direction == 4 && deltaX == 0) { //right (only if not currently moving left)
            deltaY = 0;
            deltaX = MOVEMENT_SIZE;
        }
    }
    
    public void move() {
        if (segments.isEmpty()) return;
        
        // Store previous positions for body segments
        double prevX = segments.getFirst().getX();
        double prevY = segments.getFirst().getY();
        
        // Move head
        BodySegment head = segments.getFirst();
        head.setPosition(head.getX() + deltaX, head.getY() + deltaY);
        
        // Move each subsequent segment to the position of the previous one
        for (int i = 1; i < segments.size(); i++) {
            BodySegment current = segments.get(i);
            double tempX = current.getX();
            double tempY = current.getY();
            current.setPosition(prevX, prevY);
            prevX = tempX;
            prevY = tempY;
        }
    }
    
    public void draw() {
        for (BodySegment segment : segments) {
            segment.draw();
        }
    }
    
    public boolean eatFood(Food f) {
        if (segments.isEmpty()) return false;
        
        BodySegment head = segments.getFirst();
        double distance = Math.sqrt(Math.pow(head.getX() - f.getX(), 2) + 
                                  Math.pow(head.getY() - f.getY(), 2));
        
        if (distance < (SEGMENT_SIZE/2 + Food.FOOD_SIZE/2)) {
            BodySegment last = segments.getLast();
            segments.add(new BodySegment(last.getX(), last.getY(), SEGMENT_SIZE));
            return true;
        }
        return false;
    }
    
    public boolean isInbounds() {
        if (segments.isEmpty()) return false;
        
        BodySegment head = segments.getFirst();
        double x = head.getX();
        double y = head.getY();
        double halfSize = SEGMENT_SIZE/2;
        
        return x >= halfSize && x <= 1.0 - halfSize &&
               y >= halfSize && y <= 1.0 - halfSize;
    }
    
    public boolean checkSelfCollision() {
        if (segments.size() < 5) return false;
        
        BodySegment head = segments.getFirst();
        for (int i = 4; i < segments.size(); i++) {
            BodySegment segment = segments.get(i);
            double distance = Math.sqrt(Math.pow(head.getX() - segment.getX(), 2) + 
                                  Math.pow(head.getY() - segment.getY(), 2));
            if (distance < SEGMENT_SIZE/2) {
                return true;
            }
        }
        return false;
    }
}