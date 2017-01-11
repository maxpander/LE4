package examples;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.util.List;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import model.Feature;
import model.Layer;
import model.Project;

/**
 * Einfache Map-Klasse zur graphischen Darstellung der Features
 * @author A.Zimmermann, HS BOCHUM, 2017
 *
 */
public class TestMap extends JPanel {
	
	Project project = null;

	/**
	 * Create the panel.
	 */
	public TestMap() {
		
	}
	
	/**
	 * Zentrale Methode zum Ausgeben der Shapes.
	 */
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setTransform(world2device(project.get(0).getExtend(), this.getSize()));
		for(int i=0; i<project.size(); i++) {
			Layer<? extends Feature> layer = project.get(i);
			List<Shape> shapes = layer.getShapes();
			if(layer.getPaint()!=null) {
				Paint paint = g2.getPaint();
				g2.setPaint(layer.getPaint());
				for(int j=0; j<shapes.size(); j++) 
					g2.fill(shapes.get(j));
				g2.setPaint(paint);
			}
			if(layer.getColor()!=null && layer.getStroke()!=null) {
				Stroke stroke = g2.getStroke();
				Color color = g2.getColor();
				g2.setStroke(layer.getStroke());
				g2.setColor(layer.getColor());
				for(int j=0; j<shapes.size(); j++) 
					g2.draw(shapes.get(j));
				g2.setStroke(stroke);
				g2.setColor(color);				
			}
		}
			
		
	}
	
	public static AffineTransform world2device(Rectangle2D world, Dimension device) {
		// Transformationsparameter berechnen
		// Rücktranslation in den Ursprung des WCS
		double twx = world.getCenterX();
		double twy = world.getCenterY();
		// Skalierung und Spiegelung (WCS->DCS)
		double sx = device.getWidth()/world.getWidth();
		double sy = device.getHeight()/world.getHeight();
		double s = Math.min(sx, sy);
		// Translation in den Mittelpunkt des DCS
		double tdx = device.getWidth()/2.;
		double tdy = device.getHeight()/2.;
		// Transformationsmatrix erstellen
		AffineTransform at = new AffineTransform();
		at.translate(tdx, tdy);
		at.scale(s, -s);
		at.translate(-twx, -twy);
		// Rückgabe der Matrix
		return at;
	}


	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	

}
