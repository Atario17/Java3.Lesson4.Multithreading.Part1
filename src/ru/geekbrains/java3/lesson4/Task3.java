package ru.geekbrains.java3.lesson4;
//•	Написать класс МФУ на котором возможны одновременная печать и сканирование документов,
//        при этом нельзя одновременно печатать два документа или сканировать (при печати в
//        консоль выводится сообщения "отпечатано 1, 2, 3,... страницы", при сканировании то
//        же самое только "отсканировано...", вывод в консоль все также с периодом в 50 мс.)
public class Task3 {
    static class MFU {
        Object lock1 = new Object();
        Object lock2 = new Object();
        public void print(String doc, int n){
            synchronized (lock1){
                System.out.println("Начинается печать документа: "+doc+"\n--------------------------");
                for (int i = 0; i < n; i++) {
                    System.out.println("Страница "+i+" документа "+doc+" печатается...");
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }System.out.println("Печать документа "+doc+" завершена.\n================================");
        }
        public void scan(String doc, int n){
            synchronized (lock2){
                System.out.println("Начинается сканирование документа: "+doc+"\n--------------------------");
                for (int i = 0; i < n; i++) {
                    System.out.println("Страница "+i+" документа "+doc+" сканируется...");
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Сканирование документа "+doc+" завершено.\n================================");
        }
    }
    public static void main(String[] args) {
        MFU mfu = new MFU();
        new Thread(() -> mfu.scan("A", 4)).start();
        new Thread(() -> mfu.print("A", 4)).start();
        new Thread(() -> mfu.print("B", 7)).start();
    }
}
