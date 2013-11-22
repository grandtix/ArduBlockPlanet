package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class RepeatTimesBlock extends TranslatorBlock
{

	public RepeatTimesBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator);
	}

	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		String varName = translator.buildVariableName();
	//	translator.addDefinitionCommand("int " + varName + ";");
		String ret = "for (int i_0 = 0; i_0 < ( ";
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		ret = ret + translatorBlock.toCode();
		ret = ret + " ); ++i_0 )\n{\n";
		
		
		translatorBlock = getTranslatorBlockAtSocket(1);
		while (translatorBlock != null)
		{
			ret = ret + translatorBlock.toCode();
			translatorBlock = translatorBlock.nextTranslatorBlock();
		}
		
		ret = ret + "}\n\n";
		return ret;
	}

}
