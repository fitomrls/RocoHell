package modell.beans;

import view.player.Player;


public class AgentePlayer extends Thread {
	
	int time;
	Player media;
	
	public AgentePlayer(int time, Player media) {
		this.time = time;
		this.media = media;
	}

	@Override
	public void run() {
		System.out.println("time: "+time );
		while (time>=0) {
//			System.out.println("playing..., time:"+time);
			time = time -1000;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		media.terminado();
	}

}
