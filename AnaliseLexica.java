import java.io.*;

enum TokenType{ NUM, SOMA, SUB, MULT, DIV, APar, FPar, EOF }

class Token{
  String lexema;
  TokenType token;

 Token (String l, TokenType t)
 	{ lexema=l;token = t;}	

}

class AnaliseLexica {

	BufferedReader arquivo;
	char currchar;
	int currcode;
	StringBuilder lexema;
	boolean nextCharStaged = false;

	AnaliseLexica(String a) throws Exception
	{
		
	 	this.arquivo = new BufferedReader(new FileReader(a));
		
	}

	void readChar() throws Exception{
		if (nextCharStaged) {
			nextCharStaged = false;
		} else {
			currcode = arquivo.read();
			currchar = (char) currcode;
		}
	}

	Token getNextToken() throws Exception
	{
		int eof = -1;
		lexema = new StringBuilder();

			do{
				this.readChar();
			} while (currchar == '\n' || currchar == ' ' || currchar =='\t' || currchar == '\r');
			
			if(currcode != eof && currcode !=10)
			{
				if (currchar >= '0' && currchar <= '9') {
					do {
						lexema.append(currchar);
						this.readChar();
					} while (currchar >= '0' && currchar <= '9');
					nextCharStaged = true;
					return (new Token(lexema.toString(), TokenType.NUM));
				}
				else {
					lexema.append(currchar);
					return switch (currchar) {
						case '(' -> (new Token(lexema.toString(), TokenType.APar));
						case ')' -> (new Token(lexema.toString(), TokenType.FPar));
						case '+' -> (new Token(lexema.toString(), TokenType.SOMA));
						case '-' -> (new Token(lexema.toString(), TokenType.SUB));
						case '*' -> (new Token(lexema.toString(), TokenType.MULT));
						case '/' -> (new Token(lexema.toString(), TokenType.DIV));
						default -> throw (new Exception("Caractere inválido: " + ((int) currchar)));
					};
				}
			}

			arquivo.close();
			
		return (new Token(lexema.append(currchar).toString(),TokenType.EOF));
		
	}
}
