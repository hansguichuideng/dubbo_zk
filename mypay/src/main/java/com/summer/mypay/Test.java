package com.summer.mypay;

public class Test {

    int a = 0;
    volatile int b = 0;

    public void t1() {
        int r2 = a;
        System.out.println("---r2 : " + r2);
        b = 1;

    }

    public void t2() {
        int r1 = b;
        System.out.println("---r1 : " + r1);
        a = 2;
    }


    public static void main(String[] args) {
        Test t = new Test();


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2");
            }
        });


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1");
            }
        });

//        t2.start();
        t1.start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
//            t2.join();
            System.out.println(t1.isAlive());
            t1.wait();
//            Thread.yield();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("------");

    }
}
