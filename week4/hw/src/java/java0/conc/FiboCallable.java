package java0.conc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FiboCallable {
    

    public static void main(String[] args) throws Exception{

        long start=System.currentTimeMillis();

        LinkedList<Future<Integer>> list = new LinkedList<>();
        ExecutorService excutor = Executors.newCachedThreadPool();

        for (int i = 0; i < 36; i++) {
            excutor.submit(new MyCallable(i + 1));
        }

        // create a thread of threadpool
        // Async run
        MyCallable mycallable = new MyCallable();
        int result = sum(); //get return value
        
        // get result, output
        System.out.println("Async result："+result);
        System.out.println("time cost："+ (System.currentTimeMillis()-start) + " ms");
        
        // exit main thread
        excutor.shutdown();
    }
    
}

class MyCallable implements Callable<Integer> {
    private int n;
    MyCallable(int n){
        this.n = n;
    }

    @Override
    public Integer call() {
        return fibo(36);
    }
    private int fibo(int n){
        if(n<2)
            return 1;
        else return fibo(n-1) + fibo(n-2);
    }
}
