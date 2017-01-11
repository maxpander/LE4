package examples;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import model.Layer;
import model.Project;

/**
 * Beispielprogramm zum Anlegen und Verwalten eines GIS-Projektes.
 * @author A.Zimmermann, HS BOCHUM, 2017
 *
 */
public class AppProjectTest {

	public static void main(String[] args) {
		// Projekt anlegen und Inhalt ausgeben
		Project project = init();
		for(int i=0; i<project.size(); i++)
			System.out.println(project.get(i));
	}
	
	/**
	 * Initialisiert ein Beispielprojekt mit zwei Layern.
	 * @return
	 */
	public static Project init() {
		// GIS-Projekt anlegen
		Project project = new Project();
		project.setName("TestProjekt");
		
		// Layer für Gebäude anlegen
		Layer<Gebaeude> gebaeude = new Layer<Gebaeude>();
		gebaeude.setName("Gebaeude");
		gebaeude.setPaint(Color.RED);
		gebaeude.putAll(Generator.createGebaeude(50, new Rectangle2D.Double(
				540000, 5700000, 2000, 2000)));
		
		
		// Layer für Flurstücke anlegen
		Layer<Flurstueck> flurstuecke = new Layer<Flurstueck>();
		flurstuecke.setName("Flurstuecke");
		flurstuecke.setPaint(Color.GREEN);
		flurstuecke.putAll(Generator.createFlurstuecke(gebaeude.getFeatureMap(), 100));
		
		// Anlegen der Layer im Projekt
		// Unten Flurstücke
		project.add(flurstuecke);	
		// darüber Gebäude
		project.add(gebaeude);
				
		return project;
	}

}
