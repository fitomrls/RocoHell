package util;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import javax.swing.table.TableCellRenderer;

public class FormatoTabla implements TableCellRenderer{
    
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        JFormattedTextField campoTexto = new JFormattedTextField();
        campoTexto.setBorder(BorderFactory.createEmptyBorder());
        
        if(value instanceof String){
            System.out.println("String value = " + value);
            campoTexto.setText((String)value);
        }
        
        if(value instanceof Integer){
            campoTexto.setText(value+"");
            campoTexto.setHorizontalAlignment(SwingConstants.CENTER);
        }
        if(value instanceof Float){
        	campoTexto.setText(value+"");
        	campoTexto.setHorizontalAlignment(SwingConstants.CENTER);
        }
 	
        if(table.getValueAt(row,7).equals("")){//if((Integer)table.getValueAt(row,7) <= 5){
        	campoTexto.setBackground(new Color(0xFE899B));
        	campoTexto.setOpaque(true);
        }
        
        if(isSelected==true){
        	campoTexto.setBackground(Color.gray);
        	
        }

        return campoTexto;
    }
    
}