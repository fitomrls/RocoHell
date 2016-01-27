package modell.beans;

import view.Vista;

public class AgenteCentroIzquierda extends Agente{

	public AgenteCentroIzquierda(Vista vista) {
		super(vista);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		this.getVista().setEjeY(this.getVista().getMain().getIZQY());
		this.getVista().setHalfScaleIcon();
		while (this.getVista().getEjeX()>=30) {
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
		this.getVista().setEjeX(this.getVista().getEjeX() - 2);
		this.getVista().setBounds(this.getVista().getEjeX(),
				this.getVista().getEjeY(),
				this.getVista().getAncho(), 
				this.getVista().getAlto());
		this.getVista().setLugar(1);
		this.getVista().setProfundidad(-1);
		this.getVista().getMain().moverDerechoAcentro(this.getVista().getSig());
		
	}

}
