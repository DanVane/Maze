/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AStar;

import java.util.List;

/**
 *存储每个节点的fgh值，父节点和子节点信息
 * @author weangdan
 */
public class Point {
    private int x;
    private int y;
    private int f;
    private int g;
    private int h;
    private Point fatherPoint;
    private List<Point> sonPoints;
    private final int distance1 = 10;
    private final int distance2 = 14;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point getFatherPoint() {
        return fatherPoint;
    }

    public void setFatherPoint(Point fatherPoint) {
        this.fatherPoint = fatherPoint;
    }

    public List<Point> getSonPoints() {
        return sonPoints;
    }

    public void setSonPoints(List<Point> sonPoints) {
        this.sonPoints = sonPoints;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }
    
    

    public void setF(boolean isLine) {
        if(isLine){//沿直线移动
            setG(fatherPoint.getG()+distance1);
        }else{
            setG(fatherPoint.getG()+distance2);
        }
        setH();
        setF(g+h);
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }

    public void setH() {
//        h= Math.abs(x-AStarmain.exitPoint.getX())+Math.abs(y-AStarmain.exitPoint.getY());
h = (int) Math.sqrt((x-AStarmain.exitPoint.getX())*(x-AStarmain.exitPoint.getX())+(y-AStarmain.exitPoint.getY())*(y-AStarmain.exitPoint.getY()))*10;
    }
    
    
    
}
