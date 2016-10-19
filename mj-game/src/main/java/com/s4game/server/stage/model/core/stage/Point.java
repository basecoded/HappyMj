package com.s4game.server.stage.model.core.stage;

public class Point {

    private int x;

    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Point)) {
            return false;
        }
        Point point = (Point) o;
        return (point.getX() == getX()) && (point.getY() == getY());
    }

    public String toString() {
        return "x=" + this.x + ",y=" + this.y;
    }
}