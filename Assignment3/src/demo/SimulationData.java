/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author send2ocean
 */
public class SimulationData {

    //all point,used to show in TrafficPanel
    private List<Point> pointList = new ArrayList<>();
    // 
    private List<Ambulance> ambulances = new ArrayList<>();
    //
    private List<Patient> patients = new ArrayList<>();
    //
    private List<Station> stations = new ArrayList<>();
    
            //The hospital: this exists on the grid at location (50, 50)
    private  Point hospitalPoint = new Point(50, 50, Color.RED);
        
    public SimulationData() {
        
        ambulances = DataLoader.loadAbmulances();
        initStationPoints();
        patients = DataLoader.loadPatients();
        for(Ambulance a:ambulances){
            pointList.add(a.getLocalPoint());
            //update amb num in station
            this.updateStation(a);
            //set patient
            if(a.getPatient()!=null){
                for(Patient p:patients){
                    if(p.getId()==a.getPatient().getId()){
                        a.setPatient(p);
                    }
                }
            }
        }
        for(Patient p:patients){
            pointList.add(p.getLocalPoint());
            //set ambulance 
            if(p.getAmbulance()!=null){
                for(Ambulance am:ambulances){
                    if(p.getAmbulance().getId().equals(am.getId())){
                        p.setAmbulance(am);
                    }
                }
            }
        }
        initHospitalPointPoints();
        
    }
    /**
     * get all point to show
     * @return 
     */
    public List<Point> countList(){
        if(pointList!=null){
            pointList.clear();
        }
        pointList.add(hospitalPoint);
        for(Station s : stations){
            pointList.add(s.getLocalPoin());
        }
        
        for(Patient p:patients){
            pointList.add(p.getLocalPoint());
        }
        for(Ambulance a:ambulances){
            pointList.add(a.getLocalPoint());
        }
        return pointList;
    }

    private void initStationPoints() {
        int totalAmb=Math.round(ambulances.size()/3);
        //o Greenfields–(10,0)
        Point station1 = new Point(10, 0, Station.STATION_DEFAULT_COLOR);
        getPointList().add(station1);
        getStations().add(new Station(station1,totalAmb));
        //Bluelane–(30,80)  
        Point station2 = new Point(30, 80, Station.STATION_DEFAULT_COLOR);
        getPointList().add(station2);
        getStations().add(new Station(station2,totalAmb));
        //Redvill – (90, 20)
        Point station3 = new Point(90, 20, Station.STATION_DEFAULT_COLOR);
        getPointList().add(station3);
        getStations().add(new Station(station3,totalAmb));
    }

    private void initHospitalPointPoints() {
        getPointList().add(getHospitalPoint());
    }
    
    
    public Patient getUnassignedPatient(){
        Patient patient = null;
        for(Patient p:patients){
            if("Pending".equals(p.getStatus())){
                patient = p;
            }
        }
        return patient;
    }
    
    public List<Ambulance> getUnAssignAmbulances(){
        List<Ambulance>  result = new ArrayList<>();
         for(Ambulance amb:this.ambulances){
             String st = amb.getStatus() ;
             if("At Station".equals( st ) && amb.getPatient() == null){
                 result.add(amb);
             } 
         }
         return result;
    }

    //update number of ambulance in station
    public void updateStation(Ambulance ambu){
        for(Station s : getStations()){
            if(s.getLocalPoin().equals( ambu.getLocalPoint() )){
                s.setAct_accommodate(s.getAct_accommodate()+1);
            }
        }
    }
    
    public Station getNearestStation(){
        Station p = null;
        List<Station> substation = new ArrayList();
        for(Station s:stations){
            if(s.getAct_accommodate()==s.getMax_accommodate()){
                continue;
            }else{
                substation.add(s);
            }
        }
        double max = Double.MAX_VALUE;
        for(Station ss:substation){
            double d = SimulatioinUtil.getDistance(ss.getLocalPoin(),this.hospitalPoint);
            if(d<max){
                p=ss;
            }
        }
        return p;
    }
    /**
     * @return the pointList
     */
    public List<Point> getPointList() {
        return pointList;
    }

    /**
     * @param pointList the pointList to set
     */
    public void setPointList(List<Point> pointList) {
        this.pointList = pointList;
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
     * @return the patients
     */
    public List<Patient> getPatients() {
        return patients;
    }

    /**
     * @param patients the patients to set
     */
    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    /**
     * @return the stations
     */
    public List<Station> getStations() {
        return stations;
    }

    /**
     * @param stations the stations to set
     */
    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    /**
     * @return the hospitalPoint
     */
    public Point getHospitalPoint() {
        return hospitalPoint;
    }

    /**
     * @param hospitalPoint the hospitalPoint to set
     */
    public void setHospitalPoint(Point hospitalPoint) {
        this.hospitalPoint = hospitalPoint;
    }

}
