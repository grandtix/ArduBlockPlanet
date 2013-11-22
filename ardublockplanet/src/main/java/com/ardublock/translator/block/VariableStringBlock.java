package com.ardublock.translator.block;

import com.ardublock.translator.Translator;

public class VariableStringBlock extends TranslatorBlock
{
	public VariableStringBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode()
	{
		String internalVariableName = translator.getStringVariable(label);
		if (internalVariableName == null)
		{
			internalVariableName = translator.buildVariableName(label);
			translator.addStringVariable(label, internalVariableName);
		//	translator.addDefinitionCommand("String " + internalVariableName + ";");
		}
		//String ret = " ( " + internalVariableName + " ? true : false )";
		String ret = internalVariableName;
		return  ret ;
	}

}
