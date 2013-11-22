package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class DeclareVariableStringBlock extends TranslatorBlock
{
	public DeclareVariableStringBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		if (!(tb instanceof VariableStringBlock))
		{
			throw new BlockException(blockId, "digital var must be digital var");
		}

		
		String ret =tb.toCode();
		
		if (forGlobal)
			{
			translator.addStringVariable(label, ret);
			
			
			
			translator.addDefinitionCommand("String " + ret+";\n" );
			return "";
			}

		
		
		ret ="String "+ ret +";\n";
		
	//	translator.addSetupCommand(ret);
		return ret;
	}

}
