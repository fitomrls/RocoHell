package modell.factory;

import view.Main;
import view.Vista;

public class ListaCircular {

	private Vista raiz;

	public Vista getRaiz() {
		return raiz;
	}

	public void setRaiz(Vista raiz) {
		this.raiz = raiz;
	}

	public ListaCircular() {
		raiz = null;
	}

	public void insertarUltimo(Vista vista, Main main) {
		Vista nuevo = new Vista();
		nuevo = vista;
		if (raiz == null) {
			nuevo.setSig(nuevo);
			nuevo.setAnt(nuevo);
			raiz = nuevo;
		} else {
			Vista ultimo = raiz.getAnt();
			nuevo.setSig(raiz);
			nuevo.setAnt(ultimo);
			raiz.setAnt(nuevo);
			ultimo.setSig(nuevo);
		}
	}

	public boolean vacia() {
		if (raiz == null)
			return true;
		else
			return false;
	}

	public void imprimir() {
		if (!vacia()) {
			Vista reco = raiz;
			do {
				System.out.print(reco.getImg() + " - ");
				reco = reco.getSig();
			} while (reco != raiz);
			System.out.println();
		}
	}

	public int cantidad() {
		int cant = 0;
		if (!vacia()) {
			Vista reco = raiz;
			do {
				cant++;
				reco = reco.getSig();
			} while (reco != raiz);
		}
		return cant;
	}

}
