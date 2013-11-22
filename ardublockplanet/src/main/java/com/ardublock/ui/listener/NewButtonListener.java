package com.ardublock.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.ardublock.core.Context;
import com.ardublock.ui.OpenblocksFrame;

public class NewButtonListener implements ActionListener
{
	private Context context;
	private OpenblocksFrame parentFrame;
	private ResourceBundle uiMessageBundle;
	public NewButtonListener(OpenblocksFrame frame)
	{
		context = Context.getContext();
		parentFrame = frame;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
		parentFrame.doNewArduBlockFile();
		
	}


}
