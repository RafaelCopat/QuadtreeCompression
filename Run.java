import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

//Nós (Gian Ribolli, Rafael Eyng e Rafael Copat), garantimos que:
//
//- Não utilizamos código fonte obtidos de outros estudantes,
//ou fonte não autorizada, seja modificado ou cópia literal.
//- Todo código usado em nosso trabalho é resultado do nosso
//trabalho original, ou foi derivado de um
//código publicado nos livros texto desta disciplina.
//- Temos total ciência das consequências em caso de violarmos estes termos.

public class Run {

	public static void main(String[] args) {
		try {
			//Deve-se mudar o diretório abaixo antes de o programa ser executado
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
			//Taxa de compressão, quanto maior mais deformado
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
