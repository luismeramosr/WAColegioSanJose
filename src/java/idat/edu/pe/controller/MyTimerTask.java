/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idat.edu.pe.controller;

/**
 *
 * @author luisr
 */
import java.util.Date;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyTimerTask extends TimerTask {
    
    private int delay;
    
    public MyTimerTask(int delay) {
        this.delay = delay;
        this.run();
    }
    
    @Override
    public void run() {
        System.out.println("Timer task started at:"+new Date());
        try {
            task();
        } catch (InterruptedException err) {
            Logger.getLogger(MyTimerTask.class.getName()).log(Level.SEVERE, null, err);
        }        
    }

    private void task() throws InterruptedException {
        try {
            //assuming it takes 5 secs to complete the task
            Thread.sleep(this.delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("Timer task finished at:"+new Date());
            Thread.currentThread().join();
        }
    }
    
    private void desactivarPrueba() {
        
    }
}
