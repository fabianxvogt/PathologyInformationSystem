package pis.model.resektat;

import java.util.ArrayList;
import java.util.List;

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
	public int getAnzahlStuecke() {
		return this.rechts.size() + this.links.size();
	}
}
