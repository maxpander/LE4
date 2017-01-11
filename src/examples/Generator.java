package examples;

import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Polygon;

public class Generator {
	
	static GeometryFactory gf = new GeometryFactory();
	
	public static Map<Integer, Gebaeude> createGebaeude(int n, Rectangle2D rect) {
		Map<Integer, Gebaeude> map = new HashMap<Integer, Gebaeude>();
		for(int i=0; i<n; i++) {
			Gebaeude g = new Gebaeude(createPolygon(rect,10));
			try {
				g.setAttributeValue("strasse", "Dorfanger");
				g.setAttributeValue("hsnr", i+13);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			map.put(g.getOid(), g);
		}
		return map;
	}
	
	public static Map<Integer, Flurstueck> createFlurstuecke(Map<Integer, Gebaeude> gebaeude, double dim) {
		Map<Integer, Flurstueck> map = new HashMap<Integer, Flurstueck>();
		Object[] gkey = (gebaeude.keySet()).toArray();
		for(int i=0; i<gkey.length; i++) {
			Gebaeude g = gebaeude.get(gkey[i]);
			Geometry gf = g.getGeom().buffer(dim, 5);
			Flurstueck f = new Flurstueck(gf);
			try {
				f.setAttributeValue("gemarkung", "Streuroden");
				f.setAttributeValue("flur", 1);
				f.setAttributeValue("flstnr", 100+i);
				f.setAttributeValue("flaeche", (float)gf.getArea());
				g.setAttributeValue("flstk", f);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			map.put(f.getOid(), f);
		}
		return map;
	}
	
	
	public static Polygon createPolygon(Rectangle2D rect, double dim) {
		
		double cx = rect.getX() + Math.random()*rect.getWidth();
		double cy = rect.getY() + Math.random()*rect.getHeight();
		
		double PIH = Math.PI/2;
		double a = Math.random()*PIH;
		Coordinate[] coords = new Coordinate[] {
				new Coordinate(cx+Math.cos(a+PIH*0)*dim, cy+Math.sin(a+PIH*0)*dim),
				new Coordinate(cx+Math.cos(a+PIH*1)*dim, cy+Math.sin(a+PIH*1)*dim),
				new Coordinate(cx+Math.cos(a+PIH*2)*dim, cy+Math.sin(a+PIH*2)*dim),
				new Coordinate(cx+Math.cos(a+PIH*3)*dim, cy+Math.sin(a+PIH*3)*dim),
				new Coordinate(cx+Math.cos(a+PIH*4)*dim, cy+Math.sin(a+PIH*4)*dim)
		};
		
		return gf.createPolygon(coords);
	}

}
