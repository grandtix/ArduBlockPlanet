package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class DeclareVariableDigitalBlock extends TranslatorBlock
{
	public DeclareVariableDigitalBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		if (!(tb instanceof VariableDigitalBlock))
		{
			throw new BlockException(blockId, "digital var must be digital var");
		}

		
		String ret =tb.toCode();
		if (forGlobal)
			{
			translator.addNumberVariable(label, ret);
			tb = this.getRequiredTranslatorBlockAtSocket(1);
			ret = ret + " = " + tb.toCode() + " ;\n";
			
			translator.addDefinitionCommand("boolean " + ret );
return "";			
			}

		
		tb = this.getRequiredTranslatorBlockAtSocket(1);
		ret ="boolean "+ ret + " = " + tb.toCode() + " ;\n";
		
	//	translator.addSetupCommand(ret);
		return ret;
	}

}
