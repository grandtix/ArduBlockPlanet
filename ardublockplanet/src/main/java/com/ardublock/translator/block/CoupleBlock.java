package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class CoupleBlock extends TranslatorBlock
	{

		public CoupleBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
		{
			super(blockId, translator, codePrefix, codeSuffix, label);
		}

		public String toCode() throws SocketNullException, SubroutineNotDeclaredException
		{
			String t1= this.getRequiredTranslatorBlockAtSocket(0).toCode();
			String t2=this.getRequiredTranslatorBlockAtSocket(1).toCode();
			String ret = t1 + ","+t2;
			return  ret ;
		}

	}
