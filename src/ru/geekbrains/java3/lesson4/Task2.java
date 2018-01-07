package ru.geekbrains.java3.lesson4;

import java.io.*;

// •Написать совсем небольшой метод, в котором 3 потока построчно пишут данные в файл (штук по 10 записей,
//  с периодом в 20 мс)
public class Task2 {
    public static void main(String[] args) {
        System.out.println("Task2");
        class TextWriter implements Runnable{
            private PrintWriter pw = null;
            private int x;
            private int y = 10;
            public TextWriter(PrintWriter pw, int x){
                this.pw = pw;
                this.x = x;
            }
            @Override
            public void run() {
                for (int i = 0; i < y; i++) {
                    try {
                        Thread.sleep(20);
                        pw.print(x);
                        pw.flush();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }PrintWriter pw = null;
        try {
            pw = new PrintWriter("textForTask2.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Thread t1 = new Thread(new TextWriter(pw, 4));
        Thread t2 = new Thread(new TextWriter(pw, 7));
        Thread t3 = new Thread(new TextWriter(pw, 15));
        try {
            t1.start();
            t1.join();
            t2.start();
            t2.join();
            t3.start();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pw.close();
    }
}
