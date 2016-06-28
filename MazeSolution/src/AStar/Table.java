/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AStar;

import java.util.ArrayList;
import java.util.List;

/**
 * open表和closed表
 *
 * @author weangdan
 */
public class Table {

    private static List<Point> openTable;
    private static List<Point> closedTable;
    private static int currentOpenPoint;//永远指向当前操作节点
    private static int[][] closedPointLocation;
    private static int lowestLocation;
    private static int sizeX;
    private static int sizeY;
    private static boolean isStop = false;

    public static void initParas(int x, int y) {
        openTable = new ArrayList<Point>();
        closedTable = new ArrayList<Point>();
        sizeX = x;
        sizeY = y;
        closedPointLocation = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                closedPointLocation[i][j] = 0;
            }
        }
    }

    public static void insertOpenTable(Point p) {
        openTable.add(p);
//        findLowestF(null);
       findLowestF();
       
        isStop = false;
        starts();
    }

    public static void removeOpenToClosed() {
        closedTable.add(openTable.get(currentOpenPoint));
        closedPointLocation[openTable.get(currentOpenPoint).getX()][openTable.get(currentOpenPoint).getY()] = 1;
        openTable.remove(currentOpenPoint);

    }

    public static int checkPointInClosed(Point p) {

        return closedPointLocation[p.getX()][p.getY()];
    }

    public static void findLowestF() {
       
        boolean justTry = false;
        int temp = Integer.MAX_VALUE;
        //按照父节点选择子节点
//        if (fatherPoint == null) {//入口节点
//            lowestLocation = 0;
//            return;
//        }
//         System.out.println("查找父节点"+fatherPoint.getX()+";"+fatherPoint.getY()+"的子节点");
        for (int i = 0; i < openTable.size(); i++) {
//            if (openTable.get(i).getFatherPoint().equals(fatherPoint)) {
                if (openTable.get(i).getF() <= temp) {//相同的值选最后加入的
                    temp = openTable.get(i).getF();
                    lowestLocation = i;
                    justTry = true;//正确更新
                }
//            }
        }
        //错误更新，当前父节点所有的子节点全部不是通路
//        if (!justTry) {
//            //需要重新选择最优节点
//            temp = Integer.MAX_VALUE;
//            for (int i = 0; i < openTable.size(); i++) {
//                if (openTable.get(i).getF() <= temp) {//相同的值选最后加入的
//                    temp = openTable.get(i).getF();
//                    lowestLocation = i;
//                }
//            }
//        }

    }

    public static void openLowestPoint(Point temp) {
        int tempX = temp.getX();
        int tempY = temp.getY();
        

        if (tempY + 1 < sizeY) {
            //(x,y+1)
            if (checkRoad(tempX, tempY + 1)) {//右
                Point p1 = new Point(tempX, tempY + 1);
                p1.setFatherPoint(temp);
                p1.setF(true);
                checkOpenTable(p1);
            }
/**
//            openTable.add(p1);
            if (tempX - 1 > 0) {
                //(x-1,y+1)
                if (checkRoad(tempX - 1, tempY + 1)) {//右上
                    Point p2 = new Point(tempX - 1, tempY + 1);
                    p2.setFatherPoint(temp);
                    p2.setF(false);
                    checkOpenTable(p2);
//                openTable.add(p2);
                }

            }
            if (tempX + 1 < sizeX) {
                //(x+1,y+1)
                if (checkRoad(tempX + 1, tempY + 1)) {//右下
                    Point p3 = new Point(tempX + 1, tempY + 1);
                    p3.setFatherPoint(temp);
                    p3.setF(false);
                    checkOpenTable(p3);
//                openTable.add(p3);
                }

            }
            */
        }
        if (tempY - 1 >= 0) {
            if (checkRoad(tempX, tempY - 1)) {//左
                Point p4 = new Point(tempX, tempY - 1);
                p4.setFatherPoint(temp);
                p4.setF(true);
                checkOpenTable(p4);
//            openTable.add(p4);
            }
/**
            if (tempX - 1 > 0) {
                if (checkRoad(tempX - 1, tempY - 1)) {//左上
                    Point p5 = new Point(tempX - 1, tempY - 1);
                    p5.setFatherPoint(temp);
                    p5.setF(false);
                    checkOpenTable(p5);
//                openTable.add(p5);     
                }

            }
            if (tempX + 1 < sizeX) {//左下
                if (checkRoad(tempX + 1, tempY - 1)) {
                    Point p6 = new Point(tempX + 1, tempY - 1);
                    p6.setFatherPoint(temp);
                    p6.setF(false);
                    checkOpenTable(p6);
//                openTable.add(p6);
                }

            }
            */
        }
        if (tempX - 1 >= 0) {//上
            if (checkRoad(tempX - 1, tempY)) {
                Point p7 = new Point(tempX - 1, tempY);
                p7.setFatherPoint(temp);
                p7.setF(true);
                checkOpenTable(p7);
//            openTable.add(p7);
            }

        }
        if (tempX + 1 < sizeX) {//下
            if (checkRoad(tempX + 1, tempY)) {
                Point p8 = new Point(tempX + 1, tempY);
                p8.setFatherPoint(temp);
                checkOpenTable(p8);
//            openTable.add(p8);
            }

        }
        //应该从这四个点中寻找最优点，如果四个节点全在closed表中，就从open表中找最有点，否则从这四个点中找最优点
        
    }

    private static void checkOpenTable(Point P) {
        //判断是否出口节点
        if (P.getX() == AStarmain.exitPoint.getX() && P.getY() == AStarmain.exitPoint.getY()) {
            System.out.println("已经找到出口！！");
            AStarmain.exitPoint=P;
            isStop = true;
            return;
        }
        //检查是否在closed表中
        if (closedPointLocation[P.getX()][P.getY()] == 1) {
            return;
        }
        //是否在open表中
        for (int i = 0; i < openTable.size(); i++) {
            if (isSame(openTable.get(i), P)) {//已经存在在open表中
                if (openTable.get(i).getF() > P.getF()) {
//                    openTable.get(i).setFatherPoint(P.getFatherPoint());
                    openTable.remove(i);
                    openTable.set(i, P);
                    return;
                }
            }
        }
        //新节点
        openTable.add(P);
    }

    public static boolean isSame(Point p1, Point p2) {
        return (p1.getX() == p2.getX()) && (p1.getY() == p2.getY());
    }

    private static boolean checkRoad(int x, int y) {
        return MazeforAStar.getMazeMatrix(x, y) == 1;
    }

    private static void starts() {
        System.out.println("stats函数启动");
        int i = 0;
        do {
            System.out.print("第" + (i++) + "轮迭代:  ");
//            System.out.println("openTable大小是" + openTable.size() + ";" + "当前选择：" + lowestLocation);
            Point temp = openTable.get(lowestLocation);
            System.out.print("当前选择的节点是" + temp.getX() + ";" + temp.getY()+"   ");
            if(i!=1)
             System.out.println("它的父节点是" + temp.getFatherPoint().getX() + ";" + temp.getFatherPoint().getY());
            closedTable.add(temp);
            closedPointLocation[temp.getX()][temp.getY()] = 1;
            openTable.remove(temp);
            openLowestPoint(temp);
//            Point temp = openTable.get(lowestLocation);     
            findLowestF();

        } while (!isStop);
    }
}
