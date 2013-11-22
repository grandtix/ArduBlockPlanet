package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class NumberToStringBlock extends TranslatorBlock
{

	public NumberToStringBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		String tmpvar="tempvar_"+System.currentTimeMillis()%1000;
		String ret="char "+tmpvar+"[]=\"\";\n";
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
		ret =ret+"dtostrf("+translatorBlock.toCode()+",1,2,"+tmpvar+");\n";
		
		translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		ret =ret+translatorBlock.toCode()+" = String("+tmpvar+");\n";
		
		
		

		return  ret ;
	}
	
}
