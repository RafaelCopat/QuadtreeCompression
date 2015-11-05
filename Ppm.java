// Nós (Gian Ribolli, Rafael Eyng e Rafael Copat), garantimos que:
//
// - Não utilizamos código fonte obtidos de outros estudantes,
// ou fonte não autorizada, seja modificado ou cópia literal.
// - Todo código usado em nosso trabalho é resultado do nosso
// trabalho original, ou foi derivado de um
// código publicado nos livros texto desta disciplina.
// - Temos total ciência das consequências em caso de violarmos estes termos.

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
public class Ppm {
	
	protected int horiz;
	protected int vert;
	protected int matrix[];
	
	public Ppm(File f) {
		int[] matrix = null;
		BufferedReader br;
		try {
			FileReader fr = new FileReader(f);
			br = new BufferedReader(fr);
			String line = br.readLine();
			if (!line.equals("P3")) {
				System.out.println("Não é arquivo .ppm");
				return;
			}
			line = br.readLine();
			line = br.readLine();
			String[] numbers = line.split(" ");
			this.vert = Integer.parseInt(numbers[0]);
			this.horiz = Integer.parseInt(numbers[1]);
			int i = 0;
			int k = 0;
			matrix = new int[vert * horiz];
			int[] aux = new int[3];
			line = br.readLine();
			line = br.readLine();
			aux[i] = Integer.parseInt(line);
			int rgb = aux[0];
			i++;
			while (line != null) {
				line = br.readLine();
				if (line != null) {
					aux[i] = Integer.parseInt(line);
					i++;
				}

				if (i == 3) {
					int r = aux[0];
					r = r << 16;
					int g = aux[1];
					g = g << 8;
					int b = aux[2];
					rgb = r + g + b;
					matrix[k] = rgb;
					k++;
					line = br.readLine();
					i = 1;
					if (line != null)
						aux[0] = Integer.parseInt(line);
				}

			}
			this.matrix = matrix;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getHoriz() {
		return horiz;
	}

	public int getVert() {
		return vert;
	}

	public int[] getMatrix() {
		return matrix;
	}
	
	
}
