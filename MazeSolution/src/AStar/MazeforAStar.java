/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AStar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 存储迷宫矩阵
 *
 * @author weangdan
 */
public class MazeforAStar {

    private static int[] mazeSize;
    private static int[][] mazeMatrix;
    private static int[] enterPoint;
    private static int[] exitPoint;

    public static void initMazeMatrix(String fileLocation) {
        //迷宫文档形式为第一行：迷宫大小,长和宽
        // 第二行是入口位置
        //第三行是出口位置
        //迷宫矩阵：0为不通，1为通路
        File file = new File(fileLocation);
        mazeSize = new int[2];
        enterPoint = new int[2];
        exitPoint = new int[2];
        String rowString = "";
        String[] rowInt;
        int i = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            rowString = br.readLine();
            rowInt = rowString.split(";");
            mazeSize[0] = Integer.parseInt(rowInt[0]);
            mazeSize[1] = Integer.parseInt(rowInt[1]);
            mazeMatrix = new int[mazeSize[0]][mazeSize[1]];
            rowString = br.readLine();
            rowInt = rowString.split(";");
            enterPoint[0] = Integer.parseInt(rowInt[0]);
            enterPoint[1] = Integer.parseInt(rowInt[1]);
            rowString = br.readLine();
            rowInt = rowString.split(";");
            exitPoint[0] = Integer.parseInt(rowInt[0]);
            exitPoint[1] = Integer.parseInt(rowInt[1]);
            while ((rowString = br.readLine()) != null && i < mazeSize[0]) {
                rowInt = rowString.split(";");
                for (int j = 0; j < mazeSize[1]; j++) {
                    mazeMatrix[i][j] = Integer.parseInt(rowInt[j]);
                }
                i++;
            }
            //打印迷宫矩阵
            for (i = 0; i < mazeSize[0]; i++) {
                for (int j = 0; j < mazeSize[1]; j++) {
                    System.out.print(mazeMatrix[i][j] + "   ");
                }
                System.out.println("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Point initEnterPoints() {
        return new Point(enterPoint[0], enterPoint[1]);
    }

    public static Point initExitPoint() {
        return new Point(exitPoint[0], exitPoint[1]);
    }

    public static int getMazeSizeX() {
        return mazeSize[0];
    }

    public static int getMazeSizeY() {
        return mazeSize[1];
    }

    public static int getMazeMatrix(int x,int y) {
        return mazeMatrix[x][y];
    }

    
}
