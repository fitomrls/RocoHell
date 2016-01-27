package util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTexFieldLimit extends PlainDocument {
	
	private int limite;
	
	public JTexFieldLimit( int limite){
		super();
		this.limite = limite;
	}
	public JTexFieldLimit( int limite, boolean upper){
		super();
		this.limite = limite;
	}
  
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		if(str==null){
			return;
		}
		if( (getLength() + str.length())<= limite ){
			super.insertString(offset, str, attr);
		}
	}
}
