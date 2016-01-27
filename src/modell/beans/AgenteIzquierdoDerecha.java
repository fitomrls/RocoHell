package modell.beans;

import view.Vista;

public class AgenteIzquierdoDerecha extends Agente {

	public AgenteIzquierdoDerecha(Vista vista) {
		super(vista);
	}

	@Override
	public void run() {
		while (this.getVista().getEjeX() <= 1230) {
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
//		this.getVista().getMain().moverAlaIzquierda(this.getVista());
		this.getVista().setVisible(false);
		this.getVista().setLugar(4);
		this.getVista().getMain().moverAnteriorAlCentro(this.getVista().getAnt());
	}

}
