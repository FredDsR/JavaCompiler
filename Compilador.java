class Compilador{

	public static void main(String[]args)
	{	
		ArvoreSintatica arv;
	
		try{

			//frontend
			AnaliseLexica al = new AnaliseLexica(args[0]);
			Parser as = new Parser(al);
		
			arv = as.parseProg();
		
			//backend
			System.out.println("Compile code:");
			CodeGen backend = new CodeGen();
			String codigo = backend.geraCodigo(arv);
			System.out.println(codigo);

			System.out.println("Run code:");
			CodeRun runner = new CodeRun();
			int result = runner.runCodigo(arv);
			System.out.println("Result: " + result);

		}catch(Exception e)
		{			
			System.out.println("Erro de compilação:\n" + e);
		}



	}
}
