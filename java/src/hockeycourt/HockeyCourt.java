/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hockeycourt;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.*;
import java.lang.Math;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author gustavo
 */
public class HockeyCourt extends Frame {

    /**
     * @param args the command line arguments
     */
    {
        addWindowListener(new FecharJanela());
    }
    
    public static HockeyCourt hc;
    public BasicStroke bs;
    public Graphics2D g2d;
    public GeneralPath gp;
    public Arc2D.Double arc;

    public Map<Character, Double> faceOffCenter = new HashMap<Character, Double>();

    public Double scale, width, height, borderRadius, fundWidth, halfHeight, halfWidth, fundArcRadius, faceOffArcRadius,
            pointRadius, centralTopArcRadius, centralArcRadius, atkDfsWidth, halfFaceOffArcRadius, baseGoalLine;

    public void initialize() {
        scale = 11.0;
        width = 61 * scale;
        height = 30 * scale;
        borderRadius = 8.5 * scale;
        fundWidth = 4 * scale;
        halfHeight = height / 2;
        halfWidth = width / 2;
        fundArcRadius = 1.83 * scale;
        faceOffArcRadius = 4.57 * scale;
        faceOffCenter.put('x', fundWidth + 6.7 * scale);
        faceOffCenter.put('y', 4 * scale + faceOffArcRadius);
        pointRadius = 0.3 * scale;
        centralTopArcRadius = 2.8 * scale;
        centralArcRadius = 4 * scale;
        atkDfsWidth = (width - 2 * fundWidth) / 3;
        halfFaceOffArcRadius = faceOffArcRadius / 2;
        baseGoalLine = borderRadius - Math.sqrt(Math.pow(borderRadius, 2) - Math.pow(borderRadius - fundWidth, 2));
        
        g2d.setPaint(Color.WHITE);
        g2d.fillRect(0, 0, hc.getWidth(), hc.getHeight());
        g2d.setPaint(Color.BLACK);
        
        g2d.translate(50, 50);
    }

    public void contour() {
        borderRadius *= 2;
        g2d.draw(new RoundRectangle2D.Double(0, 0, width, height, borderRadius, borderRadius));
    }

    public void goalLines() {
        gp = new GeneralPath();        
        arc = new Arc2D.Double(fundWidth - fundArcRadius, halfHeight - fundArcRadius, 2 *fundArcRadius, 2 * fundArcRadius, 270, 180, Arc2D.OPEN);        
        g2d.setColor(Color.RED);        
        g2d.draw(arc);
        g2d.setPaint(Color.decode("#AEC8E8"));
        g2d.fill(arc);       
        
        arc = new Arc2D.Double(width - fundWidth - fundArcRadius, halfHeight - fundArcRadius, 2 *fundArcRadius, 2 * fundArcRadius, 90, 180, Arc2D.OPEN);
        g2d.setColor(Color.RED);        
        g2d.draw(arc);
        g2d.setPaint(Color.decode("#AEC8E8"));
        g2d.fill(arc);
        
        g2d.setColor(Color.RED);
        
        gp.moveTo(fundWidth, baseGoalLine);
        gp.lineTo(fundWidth, height - baseGoalLine);
        
        gp.moveTo((width - fundWidth), baseGoalLine);
        gp.lineTo((width - fundWidth), height - baseGoalLine);
        
        g2d.draw(gp);
    }
    
    public void faceOffs() {
        gp = new GeneralPath();        
        
        gp.moveTo(faceOffCenter.get('x') - 3 * pointRadius, faceOffCenter.get('y') - faceOffArcRadius - scale);
        gp.lineTo(faceOffCenter.get('x') - 3 * pointRadius, faceOffCenter.get('y') + faceOffArcRadius + scale);
        gp.moveTo(faceOffCenter.get('x') + 3 * pointRadius, faceOffCenter.get('y') - faceOffArcRadius - scale);
        gp.lineTo(faceOffCenter.get('x') + 3 * pointRadius, faceOffCenter.get('y') + faceOffArcRadius + scale);
        g2d.draw(gp);
        
        gp = new GeneralPath();
        arc = new Arc2D.Double(faceOffCenter.get('x') - faceOffArcRadius, faceOffCenter.get('y') - faceOffArcRadius, 2 * faceOffArcRadius, 2 * faceOffArcRadius, 0, 360, Arc2D.OPEN);        
        g2d.setPaint(Color.WHITE);
        g2d.fill(arc);
        g2d.setColor(Color.RED); 
        g2d.draw(arc);
        
        gp.moveTo(faceOffCenter.get('x') - 2 * pointRadius - halfFaceOffArcRadius, faceOffCenter.get('y') - pointRadius);
        gp.lineTo(faceOffCenter.get('x') - 2 * pointRadius , faceOffCenter.get('y') - pointRadius);
        gp.lineTo(faceOffCenter.get('x') - 2 * pointRadius , faceOffCenter.get('y') - pointRadius - halfFaceOffArcRadius / 2);

        gp.moveTo(faceOffCenter.get('x') - 2 * pointRadius - halfFaceOffArcRadius, faceOffCenter.get('y') + pointRadius);
        gp.lineTo(faceOffCenter.get('x') - 2 * pointRadius , faceOffCenter.get('y') + pointRadius);
        gp.lineTo(faceOffCenter.get('x') - 2 * pointRadius , faceOffCenter.get('y') + pointRadius + halfFaceOffArcRadius / 2);

        gp.moveTo(faceOffCenter.get('x') + 2 * pointRadius + halfFaceOffArcRadius, faceOffCenter.get('y') - pointRadius);
        gp.lineTo(faceOffCenter.get('x') + 2 * pointRadius , faceOffCenter.get('y') - pointRadius);
        gp.lineTo(faceOffCenter.get('x') + 2 * pointRadius , faceOffCenter.get('y') - pointRadius - halfFaceOffArcRadius / 2);

        gp.moveTo(faceOffCenter.get('x') + 2 * pointRadius + halfFaceOffArcRadius, faceOffCenter.get('y') + pointRadius);
        gp.lineTo(faceOffCenter.get('x') + 2 * pointRadius , faceOffCenter.get('y') + pointRadius);
        gp.lineTo(faceOffCenter.get('x') + 2 * pointRadius , faceOffCenter.get('y') + pointRadius + halfFaceOffArcRadius / 2);
        
        gp.moveTo(faceOffCenter.get('x') - 3 * pointRadius, height - faceOffCenter.get('y') - faceOffArcRadius - scale);
        gp.lineTo(faceOffCenter.get('x') - 3 * pointRadius, height - faceOffCenter.get('y') + faceOffArcRadius + scale);
        gp.moveTo(faceOffCenter.get('x') + 3 * pointRadius, height - faceOffCenter.get('y') - faceOffArcRadius - scale);
        gp.lineTo(faceOffCenter.get('x') + 3 * pointRadius, height - faceOffCenter.get('y') + faceOffArcRadius + scale);
        
        g2d.draw(gp);
        
        gp = new GeneralPath();
        arc = new Arc2D.Double(faceOffCenter.get('x') - faceOffArcRadius, height - faceOffCenter.get('y') - faceOffArcRadius, 2 * faceOffArcRadius, 2 * faceOffArcRadius, 0, 360, Arc2D.OPEN);        
        g2d.setPaint(Color.WHITE);
        g2d.fill(arc);
        g2d.setColor(Color.RED); 
        g2d.draw(arc);
        
        gp.moveTo(faceOffCenter.get('x') - 2 * pointRadius - halfFaceOffArcRadius, height - faceOffCenter.get('y') - pointRadius);
        gp.lineTo(faceOffCenter.get('x') - 2 * pointRadius , height - faceOffCenter.get('y') - pointRadius);
        gp.lineTo(faceOffCenter.get('x') - 2 * pointRadius , height - faceOffCenter.get('y') - pointRadius - halfFaceOffArcRadius / 2);

        gp.moveTo(faceOffCenter.get('x') - 2 * pointRadius - halfFaceOffArcRadius, height - faceOffCenter.get('y') + pointRadius);
        gp.lineTo(faceOffCenter.get('x') - 2 * pointRadius , height - faceOffCenter.get('y') + pointRadius);
        gp.lineTo(faceOffCenter.get('x') - 2 * pointRadius , height - faceOffCenter.get('y') + pointRadius + halfFaceOffArcRadius / 2);

        gp.moveTo(faceOffCenter.get('x') + 2 * pointRadius + halfFaceOffArcRadius, height - faceOffCenter.get('y') - pointRadius);
        gp.lineTo(faceOffCenter.get('x') + 2 * pointRadius , height - faceOffCenter.get('y') - pointRadius);
        gp.lineTo(faceOffCenter.get('x') + 2 * pointRadius , height - faceOffCenter.get('y') - pointRadius - halfFaceOffArcRadius / 2);

        gp.moveTo(faceOffCenter.get('x') + 2 * pointRadius + halfFaceOffArcRadius, height - faceOffCenter.get('y') + pointRadius);
        gp.lineTo(faceOffCenter.get('x') + 2 * pointRadius , height - faceOffCenter.get('y') + pointRadius);
        gp.lineTo(faceOffCenter.get('x') + 2 * pointRadius , height - faceOffCenter.get('y') + pointRadius + halfFaceOffArcRadius / 2);
        
        gp.moveTo(width - faceOffCenter.get('x') - 3 * pointRadius, faceOffCenter.get('y') - faceOffArcRadius - scale);
        gp.lineTo(width - faceOffCenter.get('x') - 3 * pointRadius, faceOffCenter.get('y') + faceOffArcRadius + scale);
        gp.moveTo(width - faceOffCenter.get('x') + 3 * pointRadius, faceOffCenter.get('y') - faceOffArcRadius - scale);
        gp.lineTo(width - faceOffCenter.get('x') + 3 * pointRadius, faceOffCenter.get('y') + faceOffArcRadius + scale);
        
        g2d.draw(gp);
        
        gp = new GeneralPath();
        arc = new Arc2D.Double(width - faceOffCenter.get('x') - faceOffArcRadius, faceOffCenter.get('y') - faceOffArcRadius, 2 * faceOffArcRadius, 2 * faceOffArcRadius, 0, 360, Arc2D.OPEN);        
        g2d.setPaint(Color.WHITE);
        g2d.fill(arc);
        g2d.setColor(Color.RED); 
        g2d.draw(arc);
        
        gp.moveTo(width - faceOffCenter.get('x') - 2 * pointRadius - halfFaceOffArcRadius, faceOffCenter.get('y') - pointRadius);
        gp.lineTo(width - faceOffCenter.get('x') - 2 * pointRadius , faceOffCenter.get('y') - pointRadius);
        gp.lineTo(width - faceOffCenter.get('x') - 2 * pointRadius , faceOffCenter.get('y') - pointRadius - halfFaceOffArcRadius / 2);

        gp.moveTo(width - faceOffCenter.get('x') - 2 * pointRadius - halfFaceOffArcRadius, faceOffCenter.get('y') + pointRadius);
        gp.lineTo(width - faceOffCenter.get('x') - 2 * pointRadius , faceOffCenter.get('y') + pointRadius);
        gp.lineTo(width - faceOffCenter.get('x') - 2 * pointRadius , faceOffCenter.get('y') + pointRadius + halfFaceOffArcRadius / 2);

        gp.moveTo(width - faceOffCenter.get('x') + 2 * pointRadius + halfFaceOffArcRadius, faceOffCenter.get('y') - pointRadius);
        gp.lineTo(width - faceOffCenter.get('x') + 2 * pointRadius , faceOffCenter.get('y') - pointRadius);
        gp.lineTo(width - faceOffCenter.get('x') + 2 * pointRadius , faceOffCenter.get('y') - pointRadius - halfFaceOffArcRadius / 2);

        gp.moveTo(width - faceOffCenter.get('x') + 2 * pointRadius + halfFaceOffArcRadius, faceOffCenter.get('y') + pointRadius);
        gp.lineTo(width - faceOffCenter.get('x') + 2 * pointRadius , faceOffCenter.get('y') + pointRadius);
        gp.lineTo(width - faceOffCenter.get('x') + 2 * pointRadius , faceOffCenter.get('y') + pointRadius + halfFaceOffArcRadius / 2);
        
        gp.moveTo(width - faceOffCenter.get('x') - 3 * pointRadius, height - faceOffCenter.get('y') - faceOffArcRadius - scale);
        gp.lineTo(width - faceOffCenter.get('x') - 3 * pointRadius, height - faceOffCenter.get('y') + faceOffArcRadius + scale);
        gp.moveTo(width - faceOffCenter.get('x') + 3 * pointRadius, height - faceOffCenter.get('y') - faceOffArcRadius - scale);
        gp.lineTo(width - faceOffCenter.get('x') + 3 * pointRadius, height - faceOffCenter.get('y') + faceOffArcRadius + scale);
        
        g2d.draw(gp);
        
        gp = new GeneralPath();
        arc = new Arc2D.Double(width - faceOffCenter.get('x') - faceOffArcRadius, height - faceOffCenter.get('y') - faceOffArcRadius, 2 * faceOffArcRadius, 2 * faceOffArcRadius, 0, 360, Arc2D.OPEN);        
        g2d.setPaint(Color.WHITE);
        g2d.fill(arc);
        g2d.setColor(Color.RED); 
        g2d.draw(arc);
        
        gp.moveTo(width - faceOffCenter.get('x') - 2 * pointRadius - halfFaceOffArcRadius, height - faceOffCenter.get('y') - pointRadius);
        gp.lineTo(width - faceOffCenter.get('x') - 2 * pointRadius , height - faceOffCenter.get('y') - pointRadius);
        gp.lineTo(width - faceOffCenter.get('x') - 2 * pointRadius , height - faceOffCenter.get('y') - pointRadius - halfFaceOffArcRadius / 2);

        gp.moveTo(width - faceOffCenter.get('x') - 2 * pointRadius - halfFaceOffArcRadius, height - faceOffCenter.get('y') + pointRadius);
        gp.lineTo(width - faceOffCenter.get('x') - 2 * pointRadius , height - faceOffCenter.get('y') + pointRadius);
        gp.lineTo(width - faceOffCenter.get('x') - 2 * pointRadius , height - faceOffCenter.get('y') + pointRadius + halfFaceOffArcRadius / 2);

        gp.moveTo(width - faceOffCenter.get('x') + 2 * pointRadius + halfFaceOffArcRadius, height - faceOffCenter.get('y') - pointRadius);
        gp.lineTo(width - faceOffCenter.get('x') + 2 * pointRadius , height - faceOffCenter.get('y') - pointRadius);
        gp.lineTo(width - faceOffCenter.get('x') + 2 * pointRadius , height - faceOffCenter.get('y') - pointRadius - halfFaceOffArcRadius / 2);

        gp.moveTo(width - faceOffCenter.get('x') + 2 * pointRadius + halfFaceOffArcRadius, height - faceOffCenter.get('y') + pointRadius);
        gp.lineTo(width - faceOffCenter.get('x') + 2 * pointRadius , height - faceOffCenter.get('y') + pointRadius);
        gp.lineTo(width - faceOffCenter.get('x') + 2 * pointRadius , height - faceOffCenter.get('y') + pointRadius + halfFaceOffArcRadius / 2);
        
        g2d.draw(gp);
    }
    
    public void centralPoints() {
        arc = new Arc2D.Double(faceOffCenter.get('x') - pointRadius, faceOffCenter.get('y') - pointRadius, 2 * pointRadius, 2 * pointRadius, 0, 360, Arc2D.OPEN);
        g2d.fill(arc);
        g2d.draw(arc);
        
        arc = new Arc2D.Double(faceOffCenter.get('x') - pointRadius, height - faceOffCenter.get('y') - pointRadius, 2 * pointRadius, 2 * pointRadius, 0, 360, Arc2D.OPEN);
        g2d.fill(arc);
        g2d.draw(arc);
        
        arc = new Arc2D.Double(width - faceOffCenter.get('x') - pointRadius, faceOffCenter.get('y') - pointRadius, 2 * pointRadius, 2 * pointRadius, 0, 360, Arc2D.OPEN);
        g2d.fill(arc);
        g2d.draw(arc);
        
        arc = new Arc2D.Double(width - faceOffCenter.get('x') - pointRadius, height - faceOffCenter.get('y') - pointRadius, 2 * pointRadius, 2 * pointRadius, 0, 360, Arc2D.OPEN);
        g2d.fill(arc);
        g2d.draw(arc);
        
        arc = new Arc2D.Double(halfWidth - pointRadius, halfHeight - pointRadius, 2 * pointRadius, 2 * pointRadius, 0, 360, Arc2D.OPEN);
        g2d.fill(arc);
        g2d.draw(arc);
        
        arc = new Arc2D.Double(fundWidth + atkDfsWidth + scale - pointRadius, faceOffCenter.get('y') - pointRadius, 2 * pointRadius, 2 * pointRadius, 0, 360, Arc2D.OPEN);
        g2d.fill(arc);
        g2d.draw(arc);
        
        arc = new Arc2D.Double(width - fundWidth - atkDfsWidth - scale - pointRadius, faceOffCenter.get('y') - pointRadius, 2 * pointRadius, 2 * pointRadius, 0, 360, Arc2D.OPEN);
        g2d.fill(arc);
        g2d.draw(arc);
        
        arc = new Arc2D.Double(fundWidth + atkDfsWidth + scale - pointRadius, height - faceOffCenter.get('y') - pointRadius, 2 * pointRadius, 2 * pointRadius, 0, 360, Arc2D.OPEN);
        g2d.fill(arc);
        g2d.draw(arc);
        
        arc = new Arc2D.Double(width - fundWidth - atkDfsWidth - scale - pointRadius, height - faceOffCenter.get('y') - pointRadius, 2 * pointRadius, 2 * pointRadius, 0, 360, Arc2D.OPEN);
        g2d.fill(arc);
        g2d.draw(arc);
    }
    
    public void centralTopArc() {
        arc = new Arc2D.Double(halfWidth - centralTopArcRadius, -centralTopArcRadius, 2 * centralTopArcRadius, 2 * centralTopArcRadius, 180, 180, Arc2D.OPEN);
        g2d.draw(arc);
    }
    
    public void centralLine() {
        bs = new BasicStroke(3.0f);
        g2d.setStroke(bs);
        
        gp = new GeneralPath();
        
        gp.moveTo(halfWidth, 1);
        gp.lineTo(halfWidth, halfHeight - scale);
        gp.moveTo(halfWidth, height - 1);
        gp.lineTo(halfWidth, halfHeight + scale);
        
        g2d.draw(gp);
    }
    
    public void centralBlueLines() {
        g2d.setPaint(Color.decode("#116CA5"));
        
        gp = new GeneralPath();
        gp.moveTo(fundWidth + atkDfsWidth, 1);
        gp.lineTo(fundWidth + atkDfsWidth, height - 1);
        gp.moveTo(width - fundWidth - atkDfsWidth, 1);
        gp.lineTo(width - fundWidth - atkDfsWidth, height - 1);
        
        g2d.draw(gp);
    }
    
    public void centralArc() {
        bs = new BasicStroke(1.0f);
        g2d.setStroke(bs);
        
        arc = new Arc2D.Double(halfWidth - centralArcRadius, halfHeight - centralArcRadius, 2 * centralArcRadius, 2 * centralArcRadius, 0, 360, Arc2D.OPEN);
        g2d.draw(arc);
    }

    public void paint(Graphics g) {
        g2d = (Graphics2D) g;

        //Use of antialiasing to have nicer lines.
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //The lines should have a thickness of 3.0 instead of 1.0.
        bs = new BasicStroke(1.0f);
        g2d.setStroke(bs);        

        initialize();
        contour();
        goalLines();
        faceOffs();
        centralPoints();
        centralTopArc();
        centralLine();
        centralBlueLines();
        centralArc();
    }

    public class FecharJanela extends WindowAdapter {

        public void windowClosing(WindowEvent e) {
            System.out.println("hockeycourt.HockeyCourt.FecharJanela.windowClosing()");
            System.exit(0);
        }

    }

    public static void main(String[] args) {
        hc = new HockeyCourt();
        hc.setTitle("Hockey Court");
        hc.setSize(800, 600);
        hc.setVisible(true);
    }

}
