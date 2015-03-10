package pe.nn.connor.languagemanager;

import java.awt.HeadlessException;

public class LanuageKeyNotFoundException extends HeadlessException{
	private static final long serialVersionUID = 5120397200996987696L;
	
	public LanuageKeyNotFoundException(String key){
    		super(key);
	}
}

