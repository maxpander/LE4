package examples;

import java.util.Arrays;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;

/**
 * Beispielprogramm zum Erzeugen, Anzeigen und Bearbeiten von Features 
 * des Typs Gebaeude und Flurstueck.
 * @author A.Zimmermann, HS BOCHUM, 2017
 *
 */
public class AppFeatureTest {

	public static void main(String[] args) throws Exception {
		// Feature vom Ryp Flurstueck
		// Geometrie vorbereiten
		Geometry geomF = (new GeometryFactory()).createPolygon(
				new Coordinate[] {
						new Coordinate(100,100), 
						new Coordinate(200,100),
						new Coordinate(200,200), 
						new Coordinate(100,200),
						new Coordinate(100,100)});
		// Neues Flurstueck erzeugen
		Flurstueck f = new Flurstueck(geomF);
		System.out.println(f.getTypeName());
		System.out.println(Arrays.toString(f.getAttributes()));
		System.out.println(Arrays.toString(f.getAttributeNames()));
		System.out.println(Arrays.toString(f.getAttributeTypes()));
		System.out.println(f);
		// System.out.println(Arrays.toString(f.getAttributeValues()));
		// Attribute des Flurstuecks setzen und anzeigen
		f.setAttributeValue("gemarkung", "Auf der Lauer");
		f.setAttributeValue("flur", 10);
		f.setAttributeValue("flstnr", 103);
		f.setAttributeValue("flaeche", (float)f.getGeom().getArea());
		System.out.println(Arrays.toString(f.getAttributeValues()));
		System.out.println(f);
		System.out.println();
		
		//Feature vom Typ Gebaeude
		Geometry geomG = (new GeometryFactory()).createPolygon(
				new Coordinate[] {
						new Coordinate(140,140), 
						new Coordinate(160,140),
						new Coordinate(160,160), 
						new Coordinate(140,160),
						new Coordinate(140,140)});		
		Gebaeude g = new Gebaeude(geomG);
		System.out.println(g);
		g.setAttributeValue("strasse", "Räuberstraße");
		g.setAttributeValue("hsnr", 20);
		g.setAttributeValue("flstk", f);
		System.out.println(g);

	}

}
