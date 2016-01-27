package modell.beans;

import view.Vista;

public class AgenteIzquierdoCentro extends Agente {

	public AgenteIzquierdoCentro(Vista vista) {
		super(vista);
	}

	@Override
	public void run() {
		this.getVista().setEjeY(this.getVista().getMain().getCENTROY());
		this.getVista().setNormalScaleIcon();
		while (this.getVista().getEjeX()<=326) {
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
		this.getVista().setEjeX(this.getVista().getEjeX()-8);
		this.getVista().setBounds(this.getVista().getEjeX(),
				this.getVista().getEjeY(),
				this.getVista().getAncho(), 
				this.getVista().getAlto());
		this.getVista().setLugar(2);
		this.getVista().getMain().cambiarProfundidad(this.getVista(),0);
		this.getVista().getMain().agregarAlaIzquierdaOmostrar(this.getVista().getAnt());
                this.getVista().getMain().setAvance(true);
	}
	

}
