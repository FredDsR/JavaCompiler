class CodeRun {

	int runCodigo (ArvoreSintatica arv) throws Exception
	{

		if (arv instanceof Soma)
			return (runCodigo(((Soma) arv).arg1) +
					runCodigo(((Soma) arv).arg2));

		if (arv instanceof Sub)
			return (runCodigo(((Sub) arv).arg1) -
					runCodigo(((Sub) arv).arg2));

		if (arv instanceof Mult)
			return (runCodigo(((Mult) arv).arg1) *
				runCodigo(((Mult) arv).arg2));

		if (arv instanceof Div)
			return (runCodigo(((Div) arv).arg1) /
				runCodigo(((Div) arv).arg2));

		if (arv instanceof Num)
			return ((Num) arv).num;

		throw new Exception("Expressão não reconhecida: " + arv);
	}
}
