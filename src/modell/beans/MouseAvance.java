package modell.beans;

import view.Vista;

public class MouseAvance extends Agente {

	public MouseAvance(Vista vista) {
		super(vista);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		while (this.getVista().getEjeX() <= 928) {
			this.getVista().setEjeX(this.getVista().getEjeX() + this.getAvance());
			this.getVista().setBounds(this.getVista().getEjeX(),
					this.getVista().getEjeY(), this.getVista().getAncho(),
					this.getVista().getAlto());
			try {
				Thread.sleep(getTIMELAPSE());
			} catch (InterruptedException e) {
			}
		}
	}// fin run

}
