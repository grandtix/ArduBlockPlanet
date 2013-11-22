package com.ardublock.translator.block.robotplanet;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class RobotSoundBlock extends TranslatorBlock {

	

	public RobotSoundBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		String t=tb.toCode();
		String code=t+".son(";

		tb=this.getRequiredTranslatorBlockAtSocket(1);
		code=code+tb.toCode()+");\n";
		return code;
	}



}
