/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.awt.Color;
import java.util.Objects;

/**
 *
 * @author send2ocean
 */
public class Point {
    public static final int POINT_DEFAULT_WIDTH = 5;
    private static final int POINT_DEFAULT_HEIGH = 5;
    private int x;
    private int y;
    private Color color;
    private int width;
    private int heigh;

    public Point(int x,int y){
        this.x = x;
        this.y = y;
        this.color = Color.BLACK;
    }
    public Point(int x,int y,Color color){
        this.x = x;
        this.y = y;
        this.color = color;
    }
    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    

    /**
     * @return the width
     */
    public int getWidth() {
        
        return POINT_DEFAULT_WIDTH;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the heigh
     */
    public int getHeigh() {
        return POINT_DEFAULT_HEIGH;
    }

    /**
     * @param heigh the heigh to set
     */
    public void setHeigh(int heigh) {
        this.heigh = heigh;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.x;
        hash = 79 * hash + this.y;
        hash = 79 * hash + Objects.hashCode(this.color);
        hash = 79 * hash + this.width;
        hash = 79 * hash + this.heigh;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Point other = (Point) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }
 
    
    
    
}
