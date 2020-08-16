/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idat.edu.pe.models;

/**
 *
 * @author littman
 */
public class AlarmScheduler {
    public String idalarm;
    public String starth;
    public String endh;

    public AlarmScheduler(String idalarm, String starth, String endh) {
        this.idalarm = idalarm;
        this.starth = starth;
        this.endh = endh;
    }

    public String getIdalarm() {
        return idalarm;
    }

    public void setIdalarm(String idalarm) {
        this.idalarm = idalarm;
    }

    public String getStarth() {
        return starth;
    }

    public void setStarth(String starth) {
        this.starth = starth;
    }

    public String getEndh() {
        return endh;
    }

    public void setEndh(String endh) {
        this.endh = endh;
    }
    
    
    
}
