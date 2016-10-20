/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.awt.Color;

/**
 *
 * @author send2ocean
 */
public class Patient {
    public static Color PATIENT_DEFAULT_COLOR = Color.BLUE;
    private int id;
    private Point localPoint;
    private String status;

    private Ambulance ambulance;

    public Patient(){
        
    }
    public Patient(String csvLineData) {
        csvLineData = csvLineData.replace("\"", "");
        String[] parts = csvLineData.split(",");
            id = Integer.parseInt(parts[0]);
            int _x = Integer.parseInt(parts[1]);
            int _y = Integer.parseInt(parts[2]);
            Point p = new Point(_x, _y,PATIENT_DEFAULT_COLOR);
            this.localPoint = p;
            status = parts[3];
            if(parts.length>4 && parts[4]!=null){
                this.ambulance = new Ambulance();
                ambulance.setId( parts[4] );
            }

    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
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
     * @return the ambulance
     */
    public Ambulance getAmbulance() {
        return ambulance;
    }

    /**
     * @param ambulance the ambulance to set
     */
    public void setAmbulance(Ambulance ambulance) {
        this.ambulance = ambulance;
    }
    
     public String getLine(){
        	return "" + id + "," +
				Integer.toString(this.localPoint.getX()) + "," + 
				Integer.toString(this.localPoint.getY()) + "," + 
				status + "," +
				(this.getAmbulance()== null ? "" : this.getAmbulance().getId());
    }

}
