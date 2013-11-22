package com.ardublock.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.ardublock.core.Context;
import com.ardublock.ui.listener.ArdublockWorkspaceListener;
import com.ardublock.ui.listener.GenerateCodeButtonListener;
import com.ardublock.ui.listener.NewButtonListener;
import com.ardublock.ui.listener.OpenButtonListener;
import com.ardublock.ui.listener.OpenblocksFrameListener;
import com.ardublock.ui.listener.SaveAsButtonListener;
import com.ardublock.ui.listener.SaveButtonListener;

import edu.mit.blocks.controller.WorkspaceController;
import edu.mit.blocks.workspace.Workspace;

import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class OpenblocksFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2841155965906223806L;

	private Context context;
	private String saveFilePath;
	private String saveFileName;
	private JFileChooser fileChooser;
	private FileFilter ffilter;
	
	private ResourceBundle uiMessageBundle;
	
	public void addListener(OpenblocksFrameListener ofl)
	{
		context.registerOpenblocksFrameListener(ofl);
	}
	
	public String makeFrameTitle()
	{
		String title = Context.APP_NAME + " " + saveFileName;
		if (context.isWorkspaceChanged())
		{
			title = title + " *";
		}
		return title;
		
	}
	
	public OpenblocksFrame()
	{
		saveFilePath = null;
		saveFileName = "untitled";
		
		context = Context.getContext();
		this.setTitle(makeFrameTitle());
		this.setSize(new Dimension(800, 600));
		getContentPane().setLayout(new BorderLayout());
		//put the frame to the center of screen
		this.setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);
		
		JMenuItem mntmNouveau = new JMenuItem("Nouveau");
		mnFichier.add(mntmNouveau);
		mntmNouveau.addActionListener(new NewButtonListener(this));
		
		
		
		JMenuItem mntmOuvrir = new JMenuItem("Ouvrir...");
		mnFichier.add(mntmOuvrir);
		mntmOuvrir.addActionListener(new OpenButtonListener(this));
		JSeparator separator_1 = new JSeparator();
		mnFichier.add(separator_1);
		
		JMenuItem mntmEnregistrer = new JMenuItem("Enregistrer");
		mnFichier.add(mntmEnregistrer);
		mntmEnregistrer.addActionListener(new SaveButtonListener(this));
		JMenuItem mntmEnregistrerSous = new JMenuItem("Enregistrer sous...");
		mnFichier.add(mntmEnregistrerSous);
		mntmEnregistrerSous.addActionListener(new SaveAsButtonListener(this));
		JSeparator separator = new JSeparator();
		mnFichier.add(separator);
		
		JMenuItem mntmFermer = new JMenuItem("Fermer");
		mnFichier.add(mntmFermer);
		
		JMenu mnCode = new JMenu("Code");
		menuBar.add(mnCode);
		
		JMenuItem mntmAfficher = new JMenuItem("Afficher");
		mnCode.add(mntmAfficher);
		mntmAfficher.addActionListener(new GenerateCodeButtonListener(this,context,false));

		JMenuItem mntmTlverser = new JMenuItem("Téléverser");
		mnCode.add(mntmTlverser);
		mntmTlverser.addActionListener(new GenerateCodeButtonListener(this,context,true));

		JMenu menu = new JMenu("?");
		menuBar.add(menu);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		uiMessageBundle = ResourceBundle.getBundle("com/ardublock/block/ardublock");
		
		fileChooser = new JFileChooser();
		ffilter = new FileNameExtensionFilter(uiMessageBundle.getString("ardublock.file.suffix"), "abp");
		fileChooser.setFileFilter(ffilter);
		fileChooser.addChoosableFileFilter(ffilter);
		
		
		initOpenBlocks();
	}
	
	private void initOpenBlocks()
	{
		Context context = Context.getContext();
		
		/*
		WorkspaceController workspaceController = context.getWorkspaceController();
		JComponent workspaceComponent = workspaceController.getWorkspacePanel();
		*/
		
		Workspace workspace = context.getWorkspace();
		
		// WTF I can't add worksapcelistener by workspace contrller
		workspace.addWorkspaceListener(new ArdublockWorkspaceListener(this));
		workspace.setBackground(Color.black);
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		JButton openButton = new JButton();//uiMessageBundle.getString("ardublock.ui.load"));
		openButton.setToolTipText("ouvrir");
		openButton.setIcon(new ImageIcon(OpenblocksFrame.class.getResource("/com/ardublock/block/open.png")));
		openButton.addActionListener(new OpenButtonListener(this));
		JButton generateButton = new JButton();//uiMessageBundle.getString("ardublock.ui.upload"));
		generateButton.setToolTipText("téléverser sur le robot");
		generateButton.setIcon(new ImageIcon(OpenblocksFrame.class.getResource("/com/ardublock/block/dessinTeleverser.png")));
		generateButton.addActionListener(new GenerateCodeButtonListener(this, context, true));
		
		JButton button = new JButton();
		button.setIcon(new ImageIcon(OpenblocksFrame.class.getResource("/com/ardublock/block/planetrobot/dessinNouveau.png")));
		button.addActionListener(new NewButtonListener(this));

		button.setToolTipText("nouveau");
		buttons.add(button);
		buttons.add(openButton);
		JButton saveButton = new JButton();//uiMessageBundle.getString("ardublock.ui.save"));
		saveButton.setToolTipText("sauvegarder");
		saveButton.setIcon(new ImageIcon(OpenblocksFrame.class.getResource("/com/ardublock/block/save.png")));
		
				saveButton.addActionListener(new SaveButtonListener(this));
				

				buttons.add(saveButton);
		
		JButton button_1 = new JButton();
		button_1.setIcon(new ImageIcon(OpenblocksFrame.class.getResource("/com/ardublock/block/dessinAfficherCode.png")));
		button_1.setToolTipText("Afficher le code");
		button_1.addActionListener(new GenerateCodeButtonListener(this, context, false));
		
		JButton button_2 = new JButton();
		button_2.setIcon(new ImageIcon(OpenblocksFrame.class.getResource("/com/ardublock/block/saveAs.png")));
		button_2.setToolTipText("sauvegarder");
		button_2.addActionListener(new SaveAsButtonListener(this));

		buttons.add(button_2);

		buttons.add(button_1);
		buttons.add(generateButton);
		
		getContentPane().add(buttons, BorderLayout.NORTH);
		getContentPane().add(workspace, BorderLayout.CENTER);
	}
	
	public void doOpenArduBlockFile()
	{
		if (context.isWorkspaceChanged())
		{
			int optionValue = JOptionPane.showOptionDialog(this, uiMessageBundle.getString("message.content.open_unsaved"), uiMessageBundle.getString("message.title.question"), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, JOptionPane.YES_OPTION);
			if (optionValue == JOptionPane.YES_OPTION)
			{
				doSaveArduBlockFile(false);
				this.loadFile();
			}
			else
			{
				if (optionValue == JOptionPane.NO_OPTION)
				{
					this.loadFile();
				}
			}
		}
		else
		{
			this.loadFile();
		}
		this.setTitle(makeFrameTitle());
	}
	
	private void loadFile()
	{
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION)
		{
			File savedFile = fileChooser.getSelectedFile();
			if (!savedFile.exists())
			{
				JOptionPane.showOptionDialog(this, uiMessageBundle.getString("message.file_not_found"), uiMessageBundle.getString("message.title.error"), JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, null, JOptionPane.OK_OPTION);
				return ;
			}
			
			saveFilePath = savedFile.getAbsolutePath();
			saveFileName = savedFile.getName();
			try
			{
				context.loadArduBlockFile(savedFile);
				context.setWorkspaceChanged(false);
			}
			catch (IOException e)
			{
				JOptionPane.showOptionDialog(this, uiMessageBundle.getString("message.file_not_found"), uiMessageBundle.getString("message.title.error"), JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, null, JOptionPane.OK_OPTION);
				e.printStackTrace();
			}
		}
	}
	
	public void doSaveArduBlockFile(boolean as)
	{
		if (context.isWorkspaceChanged())
		{
			try
			{
				WorkspaceController workspaceController = context.getWorkspaceController();
				String saveString = workspaceController.getSaveString();
				
				if (saveFilePath == null || as)
				{
					int chooseResult;
					chooseResult = fileChooser.showSaveDialog(this);
					if (chooseResult == JFileChooser.APPROVE_OPTION)
					{
						File saveFile = fileChooser.getSelectedFile();
						saveFile = checkFileSuffix(saveFile);
						if (saveFile != null)
						{
							if (saveFile.exists())
							{
								int optionValue = JOptionPane.showOptionDialog(this, uiMessageBundle.getString("message.content.overwrite"), uiMessageBundle.getString("message.title.question"), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, JOptionPane.YES_OPTION);
								if (optionValue != JOptionPane.YES_OPTION)
								{
									return ;
								}
							}
							context.saveArduBlockFile(saveFile, saveString);
							saveFilePath = saveFile.getAbsolutePath();
							saveFileName = saveFile.getName();
							context.setWorkspaceChanged(false);
							this.setTitle(this.makeFrameTitle());
							
						}
					}
				}
				else
				{
					File saveFile = new File(saveFilePath);
					context.saveArduBlockFile(saveFile, saveString);
					saveFilePath = saveFile.getAbsolutePath();
					saveFileName = saveFile.getName();
					context.setWorkspaceChanged(false);
					this.setTitle(this.makeFrameTitle());
					
				}
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}
	
	private File checkFileSuffix(File saveFile)
	{
		String filePath = saveFile.getAbsolutePath();
		if (filePath.endsWith(".abp"))
		{
			return saveFile;
		}
		else
		{
			return new File(filePath + ".abp");
		}
	}

	public void doNewArduBlockFile()
	{

		if (context.isWorkspaceChanged())
		{
			int optionValue = JOptionPane.showOptionDialog(this, uiMessageBundle.getString("message.content.open_unsaved"), uiMessageBundle.getString("message.title.question"), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, JOptionPane.YES_OPTION);
			if (optionValue == JOptionPane.YES_OPTION)
			{
				doSaveArduBlockFile(false);
			}
		}
		
		context.getWorkspaceController().resetWorkspace();
		context.getWorkspaceController().loadFreshWorkspace();
		
	}
}
