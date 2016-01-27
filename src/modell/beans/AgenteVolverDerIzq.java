package modell.beans;

import view.Vista;

public class AgenteVolverDerIzq extends Agente {
	int aqui = 26;
	public AgenteVolverDerIzq(Vista vista) {
		super(vista);
	}
	
	public void setAlcance(int aqui){
		this.aqui = aqui;
	}

	@Override
	public void run() {
		while (this.getVista().getEjeX()>=aqui) {
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
	}

}
