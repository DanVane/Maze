/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package First_OrderPredicate;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author weangdan
 */
public class FOPredicate {

    private int[] enterPoint;
    private int[] exitPoint;
    private int sizeX;
    private int sizeY;
    private List<FOPoint> uselessPoints;
    private List<FOPoint> roadPoints;
    private MazeforFO maze;
    String s = null;

    //初始化地图，形成初始状态集
    private void initMaze(String s) {
        maze = new MazeforFO();
        maze.initMazeMatrix(s);
        sizeX = maze.getMazeSizeX();
        sizeY = maze.getMazeSizeY();
        maze.changeToPoints();
    }

    private void initEnterandExitPoint() {
        enterPoint = maze.initEnterPoints();
        exitPoint = maze.initExitPoint();
    }//maze作用到此为止

    //初始状态为Available（EnterPoint）；
    private void initAvailState() {
        AvailableState.initAvailPoints("(" + enterPoint[0] + "," + enterPoint[1] + ")");
    }

    //终止状态为Available（EnterPoint，……，ExitPoint）；
    private boolean checkExitState() {
        return AvailableState.checkExit(exitPoint);
    }

    /**
     * 两个操作符：findNeighbor； updateAvailable；
     */
    //寻找邻居节点,从AvailableState的最后节点查找
    private void findNeighbor() {
        s = AvailableState.getLast();
        NeighborState.addToMap(s);
    }

    //更新路径可达状态
    private void updateAvailState() {
        String s1 = NeighborState.getNeighbor(s);
//        System.out.println("找到的邻居是"+s1);
        if (s1 == null) {//当前路径是非法AvailableSate，删去最后一个节点 
                s=AvailableState.deleteLast();
                 updateAvailState() ;
        } else {
            AvailableState.updateAvailState(s1);
        }
    }

    private void startFOP(String s) {
        initMaze(s);
        initEnterandExitPoint();
        initAvailState();
        int i=0;
        while (!checkExitState()) {
            System.out.println("第"+i+++"次循环");
            findNeighbor();
            updateAvailState();
//              printRoad();
        }
        System.out.println("程序结束");
//        printRoad();
    }

    public static void main(String[] args) {
        String s =  FOPredicate.class.getResource("/").getFile() + "maze/maze.txt";
        FOPredicate fo = new FOPredicate();
        fo.startFOP(s);
    }

    private void printRoad() {
        AvailableState.printlnRoad();
    }
}
