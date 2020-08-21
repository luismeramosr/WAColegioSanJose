/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idat.edu.pe.scheduler;

/**
 *
 * @author luisr
 */
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskScheduler {
    
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public void scheduleTask(Task function, int delay) {
        scheduler.schedule(() -> function.run(), delay, TimeUnit.SECONDS);
    }       
}
