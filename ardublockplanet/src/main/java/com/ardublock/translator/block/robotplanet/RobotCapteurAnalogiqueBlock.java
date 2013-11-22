package com.ardublock.translator.block.robotplanet;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class RobotCapteurAnalogiqueBlock extends TranslatorBlock {



	public RobotCapteurAnalogiqueBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		String stb1 = this.getRequiredTranslatorBlockAtSocket(1).toCode();

		String t=tb.toCode();
		String code=t+".capteurAnalogique("+stb1+")";
		return code;
	}


}
