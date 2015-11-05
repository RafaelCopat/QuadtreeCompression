import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

//N�s (Gian Ribolli, Rafael Eyng e Rafael Copat), garantimos que:
//
//- N�o utilizamos c�digo fonte obtidos de outros estudantes,
//ou fonte n�o autorizada, seja modificado ou c�pia literal.
//- Todo c�digo usado em nosso trabalho � resultado do nosso
//trabalho original, ou foi derivado de um
//c�digo publicado nos livros texto desta disciplina.
//- Temos total ci�ncia das consequ�ncias em caso de violarmos estes termos.

public class Run {

	public static void main(String[] args) {
		try {
			//Deve-se mudar o diret�rio abaixo antes de o programa ser executado
			File f = new File(
					"C:\\Users\\RafaelCopat\\Desktop\\images\\tim_maia" + ".ppm");
			Ppm ppm = new Ppm(f);
			int matrix[] = ppm.getMatrix();
			int horiz = ppm.getHoriz();
			int vert = ppm.getVert();
			Quadtree qt = new Quadtree(horiz, vert);
			int cont = 0;
			for (int i = 1; i <= horiz; i++) {
				for (int j = 1; j <= vert; j++) {
					NodeFolha n = new NodeFolha(matrix[cont], i, j);
					qt.insert(n);
					cont++;
				}
				}
			//Taxa de compress�o, quanto maior mais deformado
			qt.compress(30);
			qt.printTree();
			ArrayList<Node> list = qt.descompress();
			ArrayList<NodeFolha> pixels = Util.descompressList(list);
			Iterator<NodeFolha> it = pixels.iterator();
			int i = -1;
			matrix = new int[pixels.size()*3];
			while(it.hasNext()){
				int rgb = it.next().getRgb();
				matrix[++i] = rgb;
			}
			ImagePanel ip = new ImagePanel(); ip.update(matrix, horiz,vert);
			new ImageTestFrame(ip).setVisible(true);
			 
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
