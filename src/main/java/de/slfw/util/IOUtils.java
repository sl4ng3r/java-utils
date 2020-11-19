package de.slfw.util;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Random;

/**
 * 
 * <p>A class witch different IO-Operations</p>
 *
 * @author Alex S.
 * 
 *
 */
public class IOUtils {
	
	
	public static void addGridBagComponent( Container cont, GridBagLayout gbl, Component co, 
             int x, int y, 
             int width, int height, 
             double weightx, double weighty ) 
	{ 
		GridBagConstraints gbc = new GridBagConstraints(); 
		
		gbc.fill = GridBagConstraints.BOTH; 
		gbc.gridx = x; gbc.gridy = y; 
		gbc.gridwidth = width; gbc.gridheight = height; 
		gbc.weightx = weightx; gbc.weighty = weighty; 
		gbl.setConstraints( co, gbc ); 
		cont.add( co ); 
	}
	
	
	/**
	 * A generic method that returns one of the Objects given. The return Type is the same as the parameter type
	 * @param <T>
	 * @param randoms Defined as T... You can give over Arrays or an unlimited amount of Parameters of any Type
	 * @return An instance of type T
	 */
	public static <T> T getOneRandom(T... randoms){
		int count = randoms.length;
		Random rnd = new Random();
		int index = rnd.nextInt(count);
		return randoms[index];
	}
	
}
