package cn.edudataStruct.queue;

import java.util.Scanner;

/**
 * @author xuguohai
 * @version 1.0
 * @date 2021/9/10
 * @Description: 主要功能：
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        // 创建一个环形队列
        ArrayQueue queue = new ArrayQueue(5);
        char key ; // 接收用户输入
        Scanner scanner = new Scanner(System.in);
        // 输出菜单
        boolean flag = true;

        while (flag){
            System.out.println();
            System.out.println("*****测试数组模拟环形队列的案例*****");
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队头元素");
            System.out.println("c(count):查看队列中元素个数");
            System.out.println("请输入您要操作的字符：");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'e':
                    System.out.println("Bye!!!");
                    flag = false;
                    break;
                case 'a':
                    System.out.print("输入一个数：");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        System.out.printf("取出元素 %d 成功！！！",queue.getFront());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        System.out.printf("队头元素为：%d",queue.showHead());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'c':
                    System.out.printf("队列中共有 %d 个元素！！！",queue.getSize());
                default:
                    break;

            }
        }
    }


}

// 使用数组模拟队列
class ArrayQueue{
    private final int Maxsize;
    private int front;
    private int rear;
    private final int[] arr;

    // 创建队列的构造器
    public ArrayQueue (int size){
        Maxsize = size;
        front = 0;  // 指向队列的头
        rear = 0;   // 指向队列的最后一个元素的后一个位置
        arr = new int[Maxsize];
    }

    // 判断队列是否为空
    public Boolean isEmpty(){
        return rear == front;
    }

    // 判断队列是否满
    public Boolean isFull(){
        return (rear + 1)%Maxsize == front;
    }

    // 添加元素--入队

    public void addQueue(int ele){
        if (isFull()){
            System.out.println("队列已满，不能入队！！！");
        }
        else {
            arr[rear] = ele;
            // 队尾指针后移，必须考虑取模
            rear = (rear + 1) % Maxsize;
        }
    }

    // 删除元素--出队
    public int getFront() {
        if (isEmpty()){
            throw new RuntimeException("队列为空，不能出队！！！");
        }
        else {
            int tem = arr[front];
            front = (front + 1) % Maxsize;
            return tem;
        }
    }

    // 获取队列中有效元素
    public int getSize(){
        return (rear - front + Maxsize) % Maxsize;
    }

    // 打印队列中所有元素
    public void showQueue(){
        int size = getSize();
        if (isEmpty()){
            System.out.println("队列为空！！！");
            return;
        }
        System.out.println("队列中的元素有：");
        for (int i = front; i < front + size; i++){
            System.out.printf("%d\t",arr[i%Maxsize]);
        }
        System.out.println();
    }

    // 显示头元素
    public int showHead(){
        if (isEmpty()){
            throw new RuntimeException("队列为空！！！");
        }
        else {
            return arr[front];
        }
    }
}
