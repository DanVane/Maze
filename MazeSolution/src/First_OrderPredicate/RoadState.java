/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package First_OrderPredicate;

import java.util.HashMap;
import java.util.Map;

/**
 *存储节点路状态和墙状态（1 of N），全局信息，初始化一次，是全局状态集
 * @author weangdan
 */
public class RoadState {
    private static Map<String,FOPoint> roadPoints;
    
    public static void initRoadPoints(){
        roadPoints =new HashMap<String,FOPoint>();
    }
    
    public static void addToRoad(String s,FOPoint p){
        roadPoints.put(s,p);
    }
    
    public static int getRoadNum(){
        return roadPoints.size();
    }
    
    public static FOPoint getFOPoint(String s){
//        System.out.println("正在从roadPoints表中取出key"+s+"对应的值");
//        if( roadPoints.get(s)!=null){
//            System.out.println("正在从roadPoints表中取出key"+s+"对应的值"+roadPoints.get(s).getX()+","+roadPoints.get(s).getY());
//        }
        
        return roadPoints.get(s);
    }
    
    public static void updateRoadAvail(String s){
        FOPoint p = roadPoints.get(s);
        p.setAvail(true);
        roadPoints.replace(s, p);
    }
    
}
