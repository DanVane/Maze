/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package First_OrderPredicate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 存储邻域状态信息，在寻路状态中动态变化
 *
 * @author weangdan
 */
public class NeighborState {

    private static Map<String, List<FOPoint>> map = new HashMap<String, List<FOPoint>>();

    public static void addToMap(String s) {
//        System.out.println("正在构建"+s+"的邻居节点");
        FOPoint p = RoadState.getFOPoint(s);
        List<FOPoint> list = new ArrayList<FOPoint>();
        String location = null;
        int tempx = p.getX();
        int tempy = p.getY();
        location = "(" + tempx + "," + (tempy + 1) + ")";
        if ((p = RoadState.getFOPoint(location)) != null) {//右
            if(!p.isAvail()){
                 list.add(p);
            }
           
        }
        location = "(" + tempx + "," + (tempy-1 ) + ")";
        if ((p = RoadState.getFOPoint(location)) != null) {//左
             if(!p.isAvail()){
                 list.add(p);
            }
        }
        location = "(" + (tempx-1) + "," + tempy  + ")";
        if ((p = RoadState.getFOPoint(location)) != null) {//上
             if(!p.isAvail()){
                 list.add(p);
            }
        }
        location = "(" + (tempx+1) + "," + tempy + ")";
        if ((p = RoadState.getFOPoint(location)) != null) {//下
             if(!p.isAvail()){
                 list.add(p);
            }
        }
        map.put(s, list);
//          System.out.println(s+"的邻居节点有"+list.size()+"个");
    }

    public static void updateMap(String s,FOPoint p) {

    }

   public  static String getNeighbor(String s) {
//       System.out.println("正在查找节点"+s+"的邻居");
       if(map.get(s).size()>0){
           String s1 = "("+map.get(s).get(0).getX()+","+map.get(s).get(0).getY()+")";
           RoadState.updateRoadAvail(s1);
           map.get(s).remove(0);
           return s1;
       }
        return null;
    }

}
