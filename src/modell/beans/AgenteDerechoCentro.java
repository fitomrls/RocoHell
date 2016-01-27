package modell.beans;

import view.Vista;

public class AgenteDerechoCentro extends Agente {

	public AgenteDerechoCentro(Vista vista) {
		super(vista);
	}

	@Override
	public void run() {
		this.getVista().setEjeY(this.getVista().getMain().getCENTROY());
		this.getVista().setNormalScaleIcon();
		while (this.getVista().getEjeX()>=336) {
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
		this.getVista().setEjeX(this.getVista().getMain().getCENTROX());
		this.getVista().setBounds(this.getVista().getEjeX(),
				this.getVista().getEjeY(),
				this.getVista().getAncho(), 
				this.getVista().getAlto());
		this.getVista().setLugar(2);
		this.getVista().setProfundidad(0);
		this.getVista().getMain().agregarAlaDerechaOmostrar(this.getVista().getSig());
                this.getVista().getMain().setAvance(true);
	}
}
