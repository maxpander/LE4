package model;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;

import com.vividsolutions.jts.awt.ShapeReader;
import com.vividsolutions.jts.awt.ShapeWriter;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;

/**
 * Abstrakte Elternklasse für alle Realweltobjekte (Features).
 * Folgende Funktionalität ist implementiert:
 * <li> Alle Features erhalten beim Erzeugen eine systemweit eindeutige ID (oid:int).
 * <li> Jedes Feature besitzt ein geometrisches Objekt (geom:Geometry) als Pflichtattribut.
 *  -> Es gibt nur einen Konstruktor mit Übergabe des Geo-Objektes.
 * <li> Die Klaase Geometry verwendet das Framework Java Topology Suite (jtsXXXX.jar). 
 * <li> Die Feature-Klasse verfügt über Methoden zum Abfragen der ID und der Geometrie.
 * <li> Die Feature-Klasse verwendet das Reflection-Framework zur Verwaltung der Attribute 
 * der Kindklassen. 
 * @author A.Zimmermann, HS BOCHUM, 2017
 *
 */
public abstract class Feature implements Serializable {
	
	private static final long serialVersionUID = 1L;
	static ShapeWriter writer = new ShapeWriter();
	static ShapeReader reader = new ShapeReader(new GeometryFactory());
	
	private static int n = 0;
	
	final int oid;
	Geometry geom;
	
	/**
	 * Konstruktor mit Übergabe des geometrischen Objektes.
	 * Ist das geometrische Objekt null, wird eine NullPointerException geworfen.
	 * @param geom
	 */
	public Feature(Geometry geom) {
		super();
		if(geom==null)
			throw new NullPointerException("Feature must have a geometry != null");
		this.oid = n++;
		this.geom = geom;
	}
	
	/**
	 * Gibt die Klasse eines Feature-Objektes zurück.  
	 * @return
	 */
	public Class<?> getType() {
		return this.getClass();
	}
	
	/**
	 * Gibt den Klassennamen eines Feature-Objektes zurück.  
	 * @return
	 */	
	public String getTypeName() {
		return this.getClass().getName();
	}
	
	/**
	 * Gibt die öffentlichen (public) Attribute eines Feature-Objektes zurück. 
	 * @return
	 */
	public Field[] getAttributes() {
		return this.getClass().getDeclaredFields();
	}
	
	/**
	 * Gibt die Bezeichner der öffentlichen (public) Attribute eines Feature-Objektes zurück. 
	 * @return
	 */	
	public String[] getAttributeNames() {
		Field[] fields = this.getAttributes();
		String[] names = new String[fields.length]; 
		for(int i=0; i<fields.length; i++) {
			names[i] = fields[i].getName();
		}
		return names;
	}
	
	/**
	 * Gibt die Datentypen der öffentlichen (public) Attribute eines Feature-Objektes zurück. 
	 * @return
	 */		
	public Class<?>[] getAttributeTypes() {
		Field[] fields = this.getAttributes();
		Class<?>[] types = new Class[fields.length]; 
		for(int i=0; i<fields.length; i++) {
			types[i] = fields[i].getType();
		}
		return types;
	}
	
	/**
	 * Gibt die Daten (Werte) der öffentlichen (public) Attribute eines Feature-Objektes zurück. 
	 * @return
	 */	
	public Object[] getAttributeValues() throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = this.getAttributes();
		Object[] values = new Object[fields.length]; 
		for(int i=0; i<fields.length; i++) {
			values[i] = fields[i].get(this);
		}
		return values;
	}
	
	/**
	 * Gibt die Bezeichner der öffentlichen (public) Attribute eines Feature-Objektes zurück. 
	 * @return
	 */	
	public Object getAttributeValue(String name) 
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		return this.getType().getDeclaredField(name).get(this);
	}
	
	/**
	 * Setzt den Wert des öffentlichen Attributes.
	 * @param name
	 * @param value
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	public void setAttributeValue(String name, Object value) 
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		this.getType().getDeclaredField(name).set(this, value);
	}

	/**
	 * Gibt die ID des Features zurück.
	 * @return
	 */
	public int getOid() {
		return oid;
	}

	/**
	 * Gibt das Geometrie-Objekt des Features zurück.
	 * @return
	 */
	public Geometry getGeom() {
		return geom;
	}
	
	@Override
	public String toString() {
		try {
			return "Feature [TypeName=" + getTypeName() + ", Oid="
					+ getOid() + ", Geom=" + getGeom().toText()
					+ ", AttributeValues="
					+ Arrays.toString(getAttributeValues()) + "]";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	}

	/**
	 * Statische Methode: Transformiert das Geo-Objekt in ein Java2D-Shape.
	 * @param geom
	 * @return
	 */
	public static Shape toShape(Geometry geom) {
		return writer.toShape(geom);
	}

	/**
	 * Statische Methode: Transformiert das Java2D-Shape in ein Geo-Objekt.
	 * @param shape
	 * @return
	 */
	public static Geometry fromShape(Shape shape) {
		return reader.read(shape.getPathIterator(new AffineTransform()));
	}
	
	
	
}
