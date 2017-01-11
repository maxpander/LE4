package examples;

import com.vividsolutions.jts.geom.Geometry;

import model.Feature;

/**
 * Klasse Gebaeude als Beispiel für eine konkrete Kindklasse von Feature<br>
 * Bei der Klassen-Deklaration ist zu beachtet:
 * <li>Die Attribute der Kindklasse sollten öffentlich (public) deklariert sein, 
 * damit darauf zugegriffen werden kann.
 * <li>Die Kindklasse muss mindestens den Konstruktor mit dem Parameter Geometrie 
 * besitzen.
 * 
 * @author A.Zimmermann, HS BOCHUM, 2017
 *
 */
public class Gebaeude extends Feature {

	// private static final long serialVersionUID = 1L;
	
	public Gebaeude(Geometry geom) {
		super(geom);
		// TODO Auto-generated constructor stub
	}
	
	public String strasse = null;
	public int hsnr;
	public Flurstueck flstk = null;

}
