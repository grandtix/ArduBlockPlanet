package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class DeclareVariableNumberIntBlock extends TranslatorBlock
{
	public DeclareVariableNumberIntBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		if (!(tb instanceof VariableNumberBlock))
		{
			throw new BlockException(blockId, "digital var must be digital var");
		}

		
		String ret =tb.toCode();
		
		if (forGlobal)
			{
			translator.addNumberVariable(label, ret);
			tb = this.getRequiredTranslatorBlockAtSocket(1);
			ret = ret + " = " + tb.toCode() + " ;\n";
			
			translator.addDefinitionCommand("int " + ret);
			return "";
			}

		
		tb = this.getRequiredTranslatorBlockAtSocket(1);
		ret ="int "+ ret + " = " + tb.toCode() + " ;\n";
		
	//	translator.addSetupCommand(ret);
		return ret;
	}

}
