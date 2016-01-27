package modell.beans;

import view.Vista;

abstract public class Agente extends Thread {

	private Vista vista=null;
	private final int TIMELAPSE = 1;
	private int avance = 10;

//	public Agente(Main main,Vista vista){
	public Agente(Vista vista){
		super(vista.getImg());
		this.vista = vista;
	}

	public Vista getVista() {
		return vista;
	}

	public int getAvance() {
		return avance;
	}

	public void setAvance(int avance) {
		this.avance = avance;
	}

	public void setVista(Vista vista) {
		this.vista = vista;
	}
	
	public abstract void run();

	public int getTIMELAPSE() {
		return TIMELAPSE;
	}
	
}
