package com.ardublock.translator.block;

import com.ardublock.translator.Translator;

public class StringBlock extends TranslatorBlock
{
	public StringBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode()
	{
		return "\"" + label + "\"";
	}

}
