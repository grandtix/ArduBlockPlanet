package com.ardublock.translator.block;

import com.ardublock.translator.Translator;

public class NumberIntBlock extends TranslatorBlock
{
	public NumberIntBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode()
	{
		return codePrefix + label + codeSuffix;
	}

}
