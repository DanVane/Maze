/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package First_OrderPredicate;

import java.util.ArrayList;
import java.util.List;

/**
 * 存储当前路径可达状态（从开始节点出发）
 *
 * @author weangdan
 */
public class AvailableState {

//    private static List<FOPoint> availPoints;
    private static List<String> availPoints;
//    private static int[] last = new int[2];
    private static String last;

    public static void initAvailPoints(String s) {
//        availPoints = new ArrayList<FOPoint>();
//        availPoints.add(RoadState.getFOPoint(s));
        availPoints = new ArrayList<String>();
        availPoints.add(s);
        //是否应该从road表中删除？不删，设置状态；
        RoadState.updateRoadAvail(s);
    }

    public static boolean checkExit(int[] exitPoint) {
        String temp = availPoints.get(availPoints.size() - 1);
        last = temp;
//        last[0] = temp.getX();
//        last[1] = temp.getY();
//        if (temp.getX() == exitPoint[0] && temp.getY() == exitPoint[1]) {
//            return true;
//        }
        if (temp.equals("(" + exitPoint[0] + "," + exitPoint[1] + ")")) {
            return true;
        }
        return false;
    }

    public static String getLast() {
//        return "(" + last[0] + "," + last[1] + ")";
        return last;
    }

    public static void updateAvailState(String s) {
//        System.out.println("正在将" + s + "加入路径可达表");
        FOPoint p = RoadState.getFOPoint(s);
//        System.out.println("实际加入值为(" + p.getX() + "," + p.getY() + ")");
        availPoints.add("(" + p.getX() + "," + p.getY() + ")");
        printlnRoad();
        //是否应该从road表中删除？不删，设置状态；
        RoadState.updateRoadAvail(s);
//        printlnRoad();
    }

    public static String deleteLast() {
        availPoints.remove(availPoints.size() - 1);
//        FOPoint temp = availPoints.get(availPoints.size() - 1);
//        last[0] = temp.getX();
//        last[1] = temp.getY();
        last = availPoints.get(availPoints.size() - 1);
        return getLast();
    }

    public static void printlnRoad() {
        String s = null;
        for (int i = 0; i < availPoints.size(); i++) {
//            s = "(" + availPoints.get(i).getX() + "," + availPoints.get(i).getX() + ")";
            s = availPoints.get(i);
            System.out.print(s + "==>");
        }
//        System.out.print("" + getLast());
    }

}
