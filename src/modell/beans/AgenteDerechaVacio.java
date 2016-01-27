package modell.beans;

import view.Vista;

public class AgenteDerechaVacio extends Agente{

	public AgenteDerechaVacio(Vista vista) {
		super(vista);
	}

	@Override
	public void run() {
		while (this.getVista().getEjeX()<=1255) {
			this.getVista().setEjeX(this.getVista().getEjeX() + this.getAvance());
			this.getVista().setBounds(this.getVista().getEjeX(),
					this.getVista().getEjeY(),
					this.getVista().getAncho(), 
					this.getVista().getAlto());
			try {
				Thread.sleep(getTIMELAPSE());
			} catch (InterruptedException e) {
			}
		}
		this.getVista().setLugar(4);
		this.getVista().setVisible(false);
		this.getVista().getMain().moverCentroAderecha(this.getVista());
	}

}
