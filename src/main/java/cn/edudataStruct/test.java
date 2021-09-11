package cn.edudataStruct;

import java.io.*;
import java.util.stream.Stream;

/**
 * @author xuguohai
 * @version 1.0
 * @date 2021/9/10
 * @Description: 主要功能：
 */
public class test {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\尚硅谷\\java\\数据结构\\tmp\\sparseArray.text");
        FileReader fr = new FileReader(file.getAbsoluteFile());
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        System.out.println(line);
        String line2 = br.readLine();
        System.out.println(line2);

        String[] strings = line2.split("\\t");

//        strings[]
        System.out.println(strings[0]);
        Stream<String> lines = br.lines();
        Object[] array = lines.toArray();
        for (int i =0; i < array.length; i++){
            System.out.println(array[i]);
        }

        System.out.println("ccc" + lines);

//        FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
//        BufferedWriter bw = new BufferedWriter(fw);
//       bw.write(Integer.toString(13));
//
//       bw.flush();
//       bw.close();

//        fw.flush();
//        fw.close();
    }
}
