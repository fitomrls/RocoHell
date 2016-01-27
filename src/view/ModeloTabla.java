package view;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ModeloTabla implements TableModel {
	int i;
	Object valor;
	String[] columnasName;
	Object[][] data;

	public ModeloTabla(String[] colm, Object[][] dat) {
		columnasName = colm;
		data = dat;
	}

	public void setContenido() {
	}

	public int getRowCount() {
		return data.length;
	}

	public int getColumnCount() {
		return columnasName.length;
	}

	public Class getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}

	public String getColumnName(int col) {
		return columnasName[col];
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	}

	public void addTableModelListener(TableModelListener l) {
	}

	public void removeTableModelListener(TableModelListener l) {
	}

}

