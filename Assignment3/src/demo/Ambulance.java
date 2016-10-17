/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.awt.Color;
import java.util.Date;
import java.util.List;

 

/**
 *
 * @author send2ocean
 */
public class Ambulance {
    public static Color AMBULANCE_DEFAULT_COLOR = Color.ORANGE;
    private String id;
    private Point localPoint;
    private String status;
    private  Patient patient;
    private Point stationPoint;
    
    public Ambulance(){
        
    }
    
    public Ambulance(String csvLines){
         String[] parts = csvLines.split(",");
            id =  parts[0];
            int _x = Integer.parseInt(parts[1]);
            int _y = Integer.parseInt(parts[2]);
            Point p = new Point(_x, _y,AMBULANCE_DEFAULT_COLOR);
            this.localPoint = p;
            status = parts[3].trim().replace("\"", "");
            if(parts.length>4 && parts[4]!=null){
                this.patient = new Patient();
                patient.setId(Integer.parseInt(parts[4]));
            }
             
    }
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the localPoint
     */
    public Point getLocalPoint() {
        return localPoint;
    }

    /**
     * @param localPoint the localPoint to set
     */
    public void setLocalPoint(Point localPoint) {
        this.localPoint = localPoint;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the patients
     */
    public Patient getPatient() {
        return patient;
        
    }
 

    /**
     * @param patients the patients to set
     */
    public void setPatient( Patient patient) {
        this.patient = patient;
    }

    /**
     * @return the stationPoint
     */
    public Point getStationPoint() {
        return stationPoint;
    }

    /**
     * @param stationPoint the stationPoint to set
     */
    public void setStationPoint(Point stationPoint) {
        this.stationPoint = stationPoint;
    }
 
    
    public String getLine(){
        	return "" + id + "," +
				Integer.toString(this.localPoint.getX()) + "," + 
				Integer.toString(this.localPoint.getY()) + ",\"" + 
				status + "\"," +
				(this.getPatient() == null ? "" : this.getPatient().getId());
    }
    
    
}
