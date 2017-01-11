package model;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Klassendeklaration für ein GIS-Projekt, zugleich zentraler Knoten für den Zugriff auf die 
 * Objekte der Fachlogikschicht (model). Folgende Funktionalität ist implementiert:
 * <li>Die Klasse Project ist von ArrayList<Layer> abgeleitet und unterstützt damit alle Methoden 
 * zur Verwaltung von Layern als Elemente einer Liste.
 * <li>Dem Projekt kann ein Projektname zugewiesen werden.
 * @author A.Zimmermann, HS BOCHUM, 2017
 *
 */
public class Project extends ArrayList<Layer<? extends Feature>> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String name = "unknown";

	/**
	 * Getter für den Projektnamen.
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter für den Projektnamen.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

}
