package modell.beans;

import view.Vista;

public class AgenteCentro extends Agente{

	public AgenteCentro(Vista vista) {
		super(vista);
	}

	@Override
	public void run() {
		this.getVista().setEjeY(this.getVista().getMain().getIZQY());
		this.getVista().setHalfScaleIcon();
		while (this.getVista().getEjeX()<=928) {
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
		this.getVista().setLugar(3);
		this.getVista().getMain().cambiarProfundidad(this.getVista(),-1);
		this.getVista().getMain().moverIzquierdoAlCentro(this.getVista().getAnt());		
	}

}
