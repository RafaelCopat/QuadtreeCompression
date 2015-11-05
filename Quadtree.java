// Nós (Gian Ribolli, Rafael Eyng e Rafael Copat), garantimos que:
//
// - Não utilizamos código fonte obtidos de outros estudantes,
// ou fonte não autorizada, seja modificado ou cópia literal.
// - Todo código usado em nosso trabalho é resultado do nosso
// trabalho original, ou foi derivado de um
// código publicado nos livros texto desta disciplina.
// - Temos total ciência das consequências em caso de violarmos estes termos.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Quadtree {
	protected NodePai root;

	public Quadtree(int largura, int altura) {
		super();
		this.root = new NodePai(PosCart.ROOT, 0, largura, 0, altura, -1, 0);

	}

	public void insert(NodeFolha n) {
		try {
			((NodePai) root).insert(n);
		} catch (InvalidXYException e) {
			System.out.println("X ou Y Inválidos!");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void printTree() {

		try {
			//Deve-se mudar o diretório abaixo antes de o programa ser executado
			File f = new File(
					"C:\\Users\\RafaelCopat\\Desktop\\images\\Quadtree"
							+ ".quad");
			FileWriter fr = new FileWriter(f);
			PrintWriter pw = new PrintWriter(fr);
			root.printSubTree(pw);
			System.out.println(root);
			pw.println(root);
			pw.close();
		} catch (IOException e) {
			System.out.println("Erro ao escrever para arquivo");
			e.printStackTrace();
		}

	}
	
	public void compress(int taxa){
		root.compress(taxa);
		if(root.isCompressed() && root.isCorParecida()){
			root.setNE(null);
			root.setSE(null);
			root.setNO(null);
			root.setSO(null);
		}
	}
	
	public ArrayList<Node> descompress(){
		ArrayList<Node> list = new ArrayList<Node>();
		if(root.getSO() == null){
			list.add(root);
		}
		else{
			root.decompress(list);
		}
		return list;
	}

}
