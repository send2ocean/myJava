/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author send2ocean
 */
public class SimulatioinUtil {
    public static final int SPEED = 1;
    
    public static boolean isSamePoint(Point local,Point target){
        boolean res = false;
        int lx = local.getX();
        int ly = local.getY();
        int tx = target.getX();
        int ty = target.getY();
        if(tx-lx==0 && ty-ly==0){
            res = true;
        }
        return res;
    }
    
    public static Point getMovedPoint(Point local,Point target){
        int x=0,y = 0;
        Point p = new Point(x,y);;
        
        int lx = local.getX();
        int ly = local.getY();
        int tx = target.getX();
        int ty = target.getY();
        if(tx-lx==0 && ty-ly==0){
            //same point
            p.setX(lx);p.setY(ly);
        }else{
            if(tx>lx){
               p.setX(lx+SPEED);p.setY(ly); 
            }else if(ty>ly){
                p.setX(lx);p.setY(ly+SPEED); 
            }else if(tx<lx){
                p.setX(lx-SPEED);p.setY(ly); 
            }else if(ty<ly){
                p.setX(lx);p.setY(ly-SPEED);
            }
        }
        return p;
    }
    public static Point getMovedPoint(Point local,Point target,int speed){
        int x=0,y = 0;
        Point p = new Point(x,y);;
        
        int lx = local.getX();
        int ly = local.getY();
        int tx = target.getX();
        int ty = target.getY();
        if(tx-lx==0 && ty-ly==0){
            //same point
            p.setX(lx);p.setY(ly);
        }else{
            if(tx>lx){
               p.setX( (lx+speed)>tx?tx:lx+speed   );p.setY(ly); 
            }else if(ty>ly){
                p.setX(lx);p.setY( (ly+speed)>ty?ty:ly+speed   ); 
            }else if(tx<lx){
                p.setX( (lx-speed)<tx?tx:lx-speed   );p.setY(ly); 
            }else if(ty<ly){
                p.setX(lx);p.setY( (ly-speed)<ty?ty:ly-speed  );
            }
        }
        return p;
    }
    
    public static double getDistance(Point a,Point b){
        int x = Math.abs(a.getX()-b.getX());
        int y = Math.abs(a.getY()-b.getY(   ));
        return Math.sqrt(x*x+y*y);
    }
    
    public static Ambulance getclosestAmbulance(List<Ambulance> ambulaces,Point patientPoint){
        Ambulance ambulance = null;
        double max = Double.MAX_VALUE;
        for(Ambulance amb : ambulaces){
            double d = getDistance(amb.getLocalPoint(),patientPoint);
            if(d<max){
                ambulance = amb;
            }
        }
        return ambulance;
    }
    
    public Station getNearestStation(List<Station> stations){
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
            double d = SimulatioinUtil.getDistance(ss.getLocalPoin(),Application.HostpitalPoint);
            if(d<max){
                p=ss;
            }
        }
        return p;
    }
    
    public static void saveCSV(SimulationData data){
        List<String> lines = new ArrayList<String>();
	lines.add("\"id\",\"x.location\",\"y.location\",\"status\",\"patient\"");
        for (Ambulance ambulance: data.getAmbulances()) {
                lines.add(ambulance.getLine());
        }	
        Path ambulancePath = Paths.get("ambulances-2.csv");
        try {
            Files.deleteIfExists(ambulancePath);
            Files.write(ambulancePath,  lines , StandardOpenOption.CREATE);
        } catch (IOException ex) {
            Logger.getLogger(SimulatioinUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lines.clear();
        
        lines.add("\"id\",\"x.location\",\"y.location\",\"status\",\"ambulance\"");
        for(Patient p :data.getPatients()){
            lines.add(p.getLine());
        }
        
        Path patientPath = Paths.get("patients-2.csv");
        try {
            Files.deleteIfExists(patientPath);
            Files.write(patientPath,  lines , StandardOpenOption.CREATE);
        } catch (IOException ex) {
            Logger.getLogger(SimulatioinUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
	
    }
    

}
