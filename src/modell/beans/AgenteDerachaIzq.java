package modell.beans;

import view.Vista;

public class AgenteDerachaIzq extends Agente{

	public AgenteDerachaIzq(Vista vista) {
		super(vista);
	}

	@Override
	public void run() {
		while (this.getVista().getEjeX()>=-250) {
			this.getVista().setEjeX(this.getVista().getEjeX() - this.getAvance());
			this.getVista().setBounds(this.getVista().getEjeX(),
					this.getVista().getEjeY(),
					this.getVista().getAncho(), 
					this.getVista().getAlto());
			try {
				Thread.sleep(getTIMELAPSE());
			} catch (InterruptedException e) {
			}
		}
		this.getVista().setVisible(false);
//		this.getVista().getMain().moverAnteriorIzquierda(this.getVista().getSig());
	}

}
