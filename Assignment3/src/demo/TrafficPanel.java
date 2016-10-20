/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author send2ocean
 */
public class TrafficPanel extends javax.swing.JPanel   {

    /**
     * Creates new form NewJPanel
     */
    public TrafficPanel() {
        initComponents();
    }

    private String shared;
    
    private List<Point> pointList;
    
    public void setShared(String s){
        this.shared = s;
    }
    
    public void setPoints(List<Point> pointList){
        this.pointList = pointList;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(400, 400));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


   
    @Override
    public void paint(Graphics g){
         
//         Graphics2D g2d = (Graphics2D)g; 
        if(g!=null){
            super.paint(g);
                     System.out.println("redraw run,point length is "+pointList.size());
             for(int i=0;i<pointList.size();i++){
                 Point p = pointList.get(i);
//                 System.out.println(p.getX());
                 g.setColor(p.getColor());
                 g.drawOval(p.getX()*4, p.getY()*4, p.getWidth(), p.getHeigh());
             }
        }
    }
    

    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
