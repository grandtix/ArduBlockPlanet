package com.ardublock.translator.block.robotplanet;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class DeclarationNewRobotBlock extends TranslatorBlock
{
	
	
	public DeclarationNewRobotBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
			translator.addHeaderFile("MiniRobot2.h");
			translator.addHeaderFile("Servo.h");
			TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);

			String t=tb.toCode();
			translator.addDefinitionCommand("MiniRobot2 "+t+";");
			translator.addSetupCommand(t+".initialise();");
		

		return "";
	}

}
