package com;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Main {

    public static void main(String[] args) {

        Runtime runtime=Runtime.getRuntime();
        FutureTask task=new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("111");
                return "hahaha";
            }
        });
       new Thread(task).start();
        try {
            String name= (String) task.get();
            System.out.println(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
