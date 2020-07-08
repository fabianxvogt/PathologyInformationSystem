package pis.model.resektat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * Scheibe eines Materials
 * @author fabia
 *
 */
public class Scheibe {
	private List<Stueck> rechts, links;
	public Scheibe() {
		this.rechts = new ArrayList<Stueck>();
		this.links = new ArrayList<Stueck>();
	}
	public List<Stueck> getRechteStuecke() {
		return rechts;
	}
	public void addStueckRechts(Stueck stueck) {
		this.rechts.add(stueck);
	}
	public List<Stueck> getLinkeStuecke() {
		return links;
	}
	public void addStueckLinks(Stueck stueck) {
		this.links.add(stueck);
	}
	/**
	 * Gibt eine Liste aller verwendeten Objekttraeger ohne Dopplungen
	 * @return Objekttraeger
	 */
	public List<Character> getObjekttraeger() {
		List<Character> objekttraeger = new ArrayList<Character>();
		for (Stueck s : getStuecke())
			if(!objekttraeger.contains(s.getObjektTraeger()))
				objekttraeger.add(s.getObjektTraeger());
		return objekttraeger;
	}
	/**
	 * Gibt alle erfassten stuecke zurueck (rechte Seite und linke Seite)
	 * @return stuecke
	 */
	public List<Stueck> getStuecke() {
		return Stream.of(this.rechts, this.links)
    			.flatMap(Collection::stream)
    			.collect(Collectors.toList());
	}
}
