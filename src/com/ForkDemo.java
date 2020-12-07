package com;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkDemo extends RecursiveTask<Long> {
    Long start;
    Long end;
    Long flag=1000000l;
    public ForkDemo(Long start,Long end){
        this.start=start;
        this.end=end;
    }

    @Override
    protected Long compute() {
        if((end-start)<flag){
            Long sum=0l;
            for(long i=start;i<=end;i++){
                sum+=i;
            }
           return sum;
        }else{
            Long middle=(start+end)/2;
            ForkDemo demo1=new ForkDemo(start,middle);
            demo1.fork();
            ForkDemo demo2=new ForkDemo(middle+1,end);
            demo2.fork();
            return demo1.join()+demo2.join();
        }


    }
    @Test
    public static void fortest(){
        long stime=System.currentTimeMillis();
        Long sum=0l;
        for(long i=1;i<=4000000000l;i++){
            sum+=i;
        }
        System.out.println(sum);
        long etime=System.currentTimeMillis();
        System.out.println(etime-stime);
    }
    @Test
    public static void forktest(){
        long stime=System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        ForkDemo task = new ForkDemo(1l,4000000000l);
        Future<Long> result = pool.submit(task);
        try {
            System.out.println(result.get());
            long etime=System.currentTimeMillis();
            System.out.println(etime-stime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
//        fortest();
        forktest();
    }
}
