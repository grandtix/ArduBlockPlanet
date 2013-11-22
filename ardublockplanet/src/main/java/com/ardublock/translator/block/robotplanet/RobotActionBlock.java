package com.ardublock.translator.block.robotplanet;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class RobotActionBlock extends TranslatorBlock {



	public RobotActionBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}


	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		String stb0 = this.getRequiredTranslatorBlockAtSocket(0).toCode();


		String stb1 = this.getRequiredTranslatorBlockAtSocket(1).toCode();


		String code=stb0+".action("+stb1+");\n";
		return code;
	}


}
