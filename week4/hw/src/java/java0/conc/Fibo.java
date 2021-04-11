package java0.conc;

import java.util.concurrent.CountDownLatch;

public class Fibo {
    
    public static void main(String[] args) {
        Thread MyThread = new MyThread();
        MyThread.start();

        long start=System.currentTimeMillis();

        // create a thread of threadpool
        // Async run
    
        int result = sum(); //get return value
        
        // get result, output
        System.out.println("Async result："+result);
        System.out.println("time cost："+ (System.currentTimeMillis()-start) + " ms");
        
        // exit main thread
    }
    
    private static int sum() {
        return fibo(36);
    }
    
    // Fibonacci
    private static int fibo(int a) {
        if ( a < 2) 
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
