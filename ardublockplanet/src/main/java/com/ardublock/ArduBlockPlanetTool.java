package com.ardublock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import processing.app.Editor;
import processing.app.tools.Tool;

import com.ardublock.core.Context;
import com.ardublock.ui.ArduBlockToolFrame;
import com.ardublock.ui.listener.OpenblocksFrameListener;

public class ArduBlockPlanetTool implements Tool, OpenblocksFrameListener
{
	static Editor editor;
	static ArduBlockToolFrame openblocksFrame;
	
	public void init(Editor editor) {
		if (ArduBlockPlanetTool.editor == null )
		{
			ArduBlockPlanetTool.editor = editor;
			ArduBlockPlanetTool.openblocksFrame = new ArduBlockToolFrame();
			ArduBlockPlanetTool.openblocksFrame.addListener(this);
			Context context = Context.getContext();
			String arduinoVersion = this.getArduinoVersion();
			context.setInArduino(true);
			context.setArduinoVersionString(arduinoVersion);
			System.out.println("Arduino Version: " + arduinoVersion);
		}
	}

	public void run() {
		try {
			ArduBlockPlanetTool.editor.toFront();
			ArduBlockPlanetTool.openblocksFrame.setVisible(true);
			ArduBlockPlanetTool.openblocksFrame.toFront();
		} catch (Exception e) {
			
		}
	}

	public String getMenuTitle() {
		return Context.APP_NAME;
	}

	public void didSave() {
		
	}
	
	public void didLoad() {
		
	}
	
	public void didGenerate(String source) {
		ArduBlockPlanetTool.editor.setText(source);
		ArduBlockPlanetTool.editor.handleExport(false);
		ArduBlockPlanetTool.openblocksFrame.toBack();
	}
	
	public void didPrintOnScreen(String source) {
		ArduBlockPlanetTool.editor.setText(source);
	//	ArduBlockPlanetTool.editor.handleExport(false);
	}
	
	
	private String getArduinoVersion()
	{
		Context context = Context.getContext();
		File versionFile = context.getArduinoFile("lib/version.txt");
		if (versionFile.exists())
		{
			try
			{
				InputStream is = new FileInputStream(versionFile);
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				String line = reader.readLine();
				if (line == null)
				{
					return Context.ARDUINO_VERSION_UNKNOWN;
				}
				line = line.trim();
				if (line.length() == 0)
				{
					return Context.ARDUINO_VERSION_UNKNOWN;
				}
				return line;
				
			}
			catch (FileNotFoundException e)
			{
				return Context.ARDUINO_VERSION_UNKNOWN;
			}
			catch (UnsupportedEncodingException e)
			{
				return Context.ARDUINO_VERSION_UNKNOWN;
			}
			catch (IOException e)
			{
				e.printStackTrace();
				return Context.ARDUINO_VERSION_UNKNOWN;
			}
		}
		else
		{
			return Context.ARDUINO_VERSION_UNKNOWN;
		}
		
	}
}