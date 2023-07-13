import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class Compilador {

	public static void main(String[] args) {
		ArvoreSintatica arv;

		try {

			// frontend
			AnaliseLexica al = new AnaliseLexica(args[0]);
			Parser as = new Parser(al);

			arv = as.parseProg();

			// backend
			System.out.println("Compile code:");
			CodeGen backend = new CodeGen();
			String codigo = backend.geraCodigo(arv);
			System.out.println(codigo);
			toFile(codigo);

			System.out.println("Run code:");
			CodeRun runner = new CodeRun();
			int result = runner.runCodigo(arv);
			System.out.println("Result: " + result);

		} catch (Exception e) {
			System.out.println("Erro de compilação:\n" + e);
		}

	}

	private static void toFile(String data) {
		try {
			File file = new File("output.txt");

			if (file.createNewFile()) {
				System.out.println("File created: " + file.getName());
			} else {
				System.out.println("File already exists.");
			}

			try {
				FileWriter myWriter = new FileWriter("output.txt");
				myWriter.write(data);
				myWriter.close();
				System.out.println("Successfully wrote to the file.");
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}

		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
