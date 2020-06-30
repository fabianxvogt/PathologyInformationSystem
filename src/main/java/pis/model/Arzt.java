package pis.model;

public class Arzt extends Person {
	private int arztID;
	public Arzt() {
	}
	
	public int getArztID() {
		return this.arztID;
	}
	public void setArztID(int arztID) {
		this.arztID = arztID;
	}
	@Override
	public String toString() {
		return  String.format("%08d", this.arztID) + "|" +
				super.toString();
	}
	public String getArztIDFormatted() {
		return String.format("%08d" , this.arztID);
	}
}
