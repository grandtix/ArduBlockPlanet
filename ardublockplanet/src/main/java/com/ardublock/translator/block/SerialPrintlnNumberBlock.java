package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class SerialPrintlnNumberBlock extends TranslatorBlock
{
	public SerialPrintlnNumberBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		translator.addSetupCommand("Serial.begin(9600);");
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		
		String ret0 = translatorBlock.toCode();
		
		String ret = "Serial.print(\"valeur : \");\n";
		ret=ret+"Serial.println("+ret0+");\n";
		
		return ret;
	}
}
