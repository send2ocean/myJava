/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author send2ocean
 */
public class Station {
    public static Color STATION_DEFAULT_COLOR = Color.GREEN;
    private Point localPoin;
    private int max_accommodate;
    private List<Ambulance> ambulances;
    private int act_accommodate;
    
    public Station(Point localPoint,int max_accommodate){
        this.localPoin= localPoint;
        this.max_accommodate = max_accommodate;
        this.act_accommodate = 0;
        this.ambulances = new ArrayList<>(  );
    }

    /**
     * @return the localPoin
     */
    public Point getLocalPoin() {
        return localPoin;
    }

    /**
     * @param localPoin the localPoin to set
     */
    public void setLocalPoin(Point localPoin) {
        this.localPoin = localPoin;
    }

    /**
     * @return the max_accommodate
     */
    public int getMax_accommodate() {
        return max_accommodate;
    }

    /**
     * @param max_accommodate the max_accommodate to set
     */
    public void setMax_accommodate(int max_accommodate) {
        this.max_accommodate = max_accommodate;
    }

    /**
     * @return the ambulances
     */
    public List<Ambulance> getAmbulances() {
        return ambulances;
    }

    /**
     * @param ambulances the ambulances to set
     */
    public void setAmbulances(List<Ambulance> ambulances) {
        this.ambulances = ambulances;
    }

    /**
     * @return the act_accommodate
     */
    public int getAct_accommodate() {
        return act_accommodate;
    }

    /**
     * @param act_accommodate the act_accommodate to set
     */
    public void setAct_accommodate(int act_accommodate) {
        this.act_accommodate = act_accommodate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.localPoin);
        hash = 31 * hash + this.max_accommodate;
        hash = 31 * hash + Objects.hashCode(this.ambulances);
        hash = 31 * hash + this.act_accommodate;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Station other = (Station) obj;
        if (!Objects.equals(this.localPoin, other.localPoin)) {
            return false;
        }
        return true;
    }
    
    
    
}
