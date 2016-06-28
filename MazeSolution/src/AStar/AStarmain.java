/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AStar;

/**
 * 该算法不输入入口，而是检查边缘所有可进入节点，检查是否有相应出口
 *
 * @author weangdan
 */
public class AStarmain {

    public static Point enterPoint;
    public static Point exitPoint;

    //从文档中读入迷宫
    private void readMaze(String file) {
        MazeforAStar.initMazeMatrix(file);

    }

    //构建入口节点和出口节点
    private void initEnterPoints() {
        enterPoint = MazeforAStar.initEnterPoints();
        System.out.println("入口节点：" + enterPoint.getX() + ";" + enterPoint.getY());
    }

    private void initExitPoint() {
        exitPoint = MazeforAStar.initExitPoint();
        System.out.println("出口节点" + exitPoint.getX() + ";" + exitPoint.getY());
    }

    /**
     * A*算法
     */
    //初始化open表，将入口节点加入open表,并作为F值最低的节点
    private void initOpenTable() {
        Table.initParas(MazeforAStar.getMazeSizeX(), MazeforAStar.getMazeSizeY());
        enterPoint.setF(0);
        enterPoint.setFatherPoint(null);
        Table.insertOpenTable(enterPoint);
    }

    //扩展当前节点的子节点,计算F值，修改父节点,判断是否出口节点
//    private void openLowestPoint(){
//        Table.openLowestPoint();
//    }
    /**
     * A*算法结束
     */
    private void AStarStarts(String file) {
        long start = System.currentTimeMillis();    
        readMaze(file);
        initEnterPoints();
        initExitPoint();
        initOpenTable();
//        openLowestPoint();
        printRoad();
         long end = System.currentTimeMillis();        
         System.out.println("运行时间："+(end-start)+"毫秒");        
    }
    
    private void printRoad(){
        Point temp = exitPoint;
        do{
            System.out.print("("+temp.getX()+","+temp.getY()+")<==");
            temp = temp.getFatherPoint();
        }while(!(temp.getX() ==enterPoint.getX() &&temp.getY()==enterPoint.getY()));
         System.out.print("("+temp.getX()+","+temp.getY()+")");
    }

    public static void main(String[] args) {
        String file = AStarmain.class.getResource("/").getFile() + "maze/maze.txt";
        AStarmain a = new AStarmain();
        a.AStarStarts(file);
    }

}
