package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 * Layer ist die Klassendeklaration für einen Vektor-Layer für Features vom Datentyp T.
 * Folgende Funktionalität ist implementiert:
 * <li>Die Features eines Layers werden in einer Hashmap mit dem Schlüsselwert oid:int verwaltet.
 * <li>Layer stellt (einfache) Methoden zur räumlichen Suche bereit.
 * <li>Layer stellt (einfache) Methoden für die Ausgabe-Graphik (Shapes) der Features bereit.
 * <li>Layer stellt (einfache) Methoden zur Gestaltung der Ausgabe-Graphik bereit.
 * 
 * @author A.Zimmermann, HS BOCHUM, 2017
 *
 * @param <T> Datentyp der Feature, Kindklasse von Feature
 */
public class Layer<T extends Feature> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	HashMap<Integer,T> map = new HashMap<Integer,T>();
	String name = "unknown";
	Color color = Color.BLACK;
	Stroke stroke = new BasicStroke(1.f);
	Paint paint = null;
	boolean visible = true;
	
	/**
	 * Getter für den Layer-Namen
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter für den Layer-Namen
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter für die Linien-Farbe.
	 * @return
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Setter für die Linien-Farbe.
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * Getter für den Linien-Modus.
	 * @return
	 */	
	public Stroke getStroke() {
		return stroke;
	}
	
	/**
	 * Setter für den Linien-Modus.
	 * @param stroke
	 */
	public void setStroke(Stroke stroke) {
		this.stroke = stroke;
	}
	
	/**
	 * Getter für den Flächenfüll-Modus
	 * @return
	 */
	public Paint getPaint() {
		return paint;
	}

	/**
	 * Setter für den Flächenfüll-Modus.
	 * @param paint
	 */
	public void setPaint(Paint paint) {
		this.paint = paint;
	}
	
	/**
	 * Fragt die Sichtbarkeit des Layers ab.
	 * @return
	 */
	public boolean isVisible() {
		return visible;
	}
	
	/**
	 * Setzt die Sichtbarkeit des Layers.
	 * @param visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	/**
	 * Fügt dem Layer ein neues Feature hinzu.
	 * @param feature
	 */
	public void add(T feature) {
		map.put(feature.getOid(), feature);
	}
	

	/**
	 * Delegat von HashMap map
	 * @param m
	 */
	public void putAll(Map<Integer, T> m) {
		map.putAll(m);
	}

	/**
	 * Entfernt das Feature aus dem Layer.
	 * @param feature
	 * @return
	 */
	public T remove(T feature) {
		return map.remove(feature);
	}
	
	/**
	 * Gibt das Feature mit der ID oid zurück.
	 * @param oid
	 * @return Feature vom Typ T | null, wenn das Feature nicht existiert
	 */
	public T get(int oid) {
		return this.get(oid);
	}

	/**
	 * Gibt die Ausgabegraphik des Layers zurück. 
	 * @return List<Shapes> | null, wenn Layer unsichtbar
	 */
	public List<Shape> getShapes() {
		if(this.isVisible()) {
			List<Shape> shapes = new ArrayList<Shape>();
			List<T> features = this.getFeatures();
			for(int i=0; i<features.size(); i++)
				shapes.add(Feature.toShape(features.get(i).getGeom()));
			return shapes;
		}
		else 
			return null;
	}
	
	/**
	 * Gibt die Ausgabegraphik des Layers für das Weltkoordinatenfenster 
	 * rectangle zurück. 
	 * @param rectangle
	 * @return List<Shapes> | null, wenn Layer unsichtbar
	 */
	public List<Shape> getShapes(Rectangle2D rectangle) {
		if(this.isVisible()) {
			List<Shape> list = new ArrayList<Shape>();
			Object[] objects =  (map.values()).toArray();
			for(int i=0; i<objects.length; i++) {
				@SuppressWarnings("unchecked")
				T t = ((T)objects[i]);
				Shape shape = Feature.toShape(t.getGeom());
				if(shape.intersects(rectangle) || shape.contains(rectangle))
					list.add(shape);
			}
			return list;		
		}
		else
			return null;
	}
	
	/**
	 * Gibt die Features des Layers zurück.
	 * @return List<T>
	 */
	public List<T> getFeatures() {
		List<T> list = new ArrayList<T>();
		Collection<T> c =  map.values();
		list.addAll(c);
		return list;		
	}
	
	/**
	 * Gibt die Features als Map zurück.
	 * @return
	 */
	public HashMap<Integer, T> getFeatureMap() {
		return map;
	}	

	/**
	 * Gibt die Features des Layers für das Weltkoordinatenfenster 
	 * rectangle zurück. 
	 * @param rectangle
	 * @return List<T>
	 */
	public List<T> getFeatures(Rectangle2D rectangle) {
		List<T> list = new ArrayList<T>();
		Object[] objects =  (map.values()).toArray();
		for(int i=0; i<objects.length; i++) {
			@SuppressWarnings("unchecked")
			T t = ((T)objects[i]);
			Shape shape = Feature.toShape(t.getGeom());
			if(shape.intersects(rectangle) || shape.contains(rectangle))
				list.add(t);
		}
		return list;		
	}
	
	/**
	 * Gibt die Features des Layers zurück, die sich mit dem Punkt point 
	 * überdecken (intersection).
	 * @param point
	 * @return
	 */
	public List<T> getFeatures(Point2D point) {
		List<T> list = new ArrayList<T>();
		Object[] objects =  (map.values()).toArray();
		for(int i=0; i<objects.length; i++) {
			@SuppressWarnings("unchecked")
			T t = ((T)objects[i]);
			Shape shape = Feature.toShape(t.getGeom());
			if(shape.contains(point))
				list.add(t);
		}
		return list;		
	}
	
	/** 
	 * Gibt das Weltkoordinatenfenster aller Features des Layers zurück. 
	 * @return
	 */
	public Rectangle2D getExtend() {
		Path2D path = new Path2D.Double();
		Object[] objects =  (map.values()).toArray();
		for(int i=0; i<objects.length; i++) {
			@SuppressWarnings("unchecked")
			T t = ((T)objects[i]);
			Shape shape = Feature.toShape(t.getGeom());
			path.append(shape, false);
		}
		return path.getBounds2D();
	}

	@Override
	public String toString() {
		String s = "Layer [name=" + name + "\n";
		Object[] objects =  (map.values()).toArray();
		for(int i=0; i<objects.length; i++)
			s = s + objects[i].toString() + "\n";
		s = s + "]";
		return s;
	}
	
	
}
