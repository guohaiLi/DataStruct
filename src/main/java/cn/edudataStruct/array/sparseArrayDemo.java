package cn.edudataStruct.array;

/**
 * @author xuguohai
 * @version 1.0
 * @date 2021/9/9
 * @Description: 主要功能：
 */


import java.io.*;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 摘要：
 *  1、二维数组本质是一维数组，行数=数组名.length,列数=数组名[0].length;
 *  2、FileWrite的 Write(int)方法 无法写入纯int文件，因为这里的int实际上是char字符，当你的int数据在unicode码表里找不到对应的char值时就会出现乱码。
 *      想写入真正的int数据，可以提供一个toString方法转成字符串。(Integer.toString(data))、bw.write(data + "\t");
 *  3、写入文件后，如果不手动刷新缓存并且关闭流,很可能文件内容为空 【 bw.flush();bw.close();】
 *
 * */
public class sparseArrayDemo {
    public static void main(String[] args) {
        // 初始化一个二维数组
        int[][] chessArr1 = new int[11][12];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[2][6] = 2;
        chessArr1[3][7] = 1;

        int sum = 0; //统计二维数组中有数据的个数
        System.out.println("原二维数组：");
        for (int[]row : chessArr1){
            for (int data : row){
                System.out.printf("%d\t", data);
                if (data != 0){
                    sum++;
                }
            }
            System.out.println();
        }

        // 将二维数组转换成稀疏数组
        int[][] sparesArr = new int[sum + 1][3];
        int count = 0; // 记录二维数组是第几个不为0的数据
        sparesArr[0][0] = chessArr1.length;
        sparesArr[0][1] = chessArr1[0].length;
        sparesArr[0][2] = sum;
        for (int i = 0; i<chessArr1.length; i++){
            for (int j = 0; j<chessArr1[0].length; j++){
                if (chessArr1[i][j] != 0){
                    count ++;
                    sparesArr[count][0] = i;
                    sparesArr[count][1] = j;
                    sparesArr[count][2] = chessArr1[i][j];
                }
            }
        }

        try {
            File file = new File("C:\\尚硅谷\\java\\数据结构\\tmp\\sparseArray.text");
            if (file.exists()){
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file.getPath());
            BufferedWriter bw = new BufferedWriter(writer);

            // 打印稀疏数组并写入文件
            System.out.println("稀疏数组：");
            for (int[]row : sparesArr){
                for (int data : row){
                    System.out.printf("%d\t",data);
//                    bw.write(Integer.toString(data) + "\t");
                    bw.write(data + "\t");
                }
                System.out.println();
                bw.newLine();
            }

            bw.flush();
            bw.close();

            // 由文件中读入稀疏数组，并转换成二维数组
            FileReader fr = new FileReader(file.getAbsoluteFile());
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            String[] array = line.split("\\t");

            int[][] chess2Array = new int[Integer.parseInt(array[0])][Integer.parseInt(array[1])];
            Object[] array1 = br.lines().toArray();
            for (int i = 0; i<array1.length; i++){
                String[] str = array1[i].toString().split("\\t");
                chess2Array[Integer.parseInt(str[0])][Integer.parseInt(str[1])] = Integer.parseInt(str[2]);
            }

            System.out.println("新二维数组：");
            for (int[]row : chess2Array){
                for (int data : row){
                    System.out.printf("%d\t", data);
                }
                System.out.println();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
