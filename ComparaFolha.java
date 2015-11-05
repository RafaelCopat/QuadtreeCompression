// Nós (Gian Ribolli, Rafael Eyng e Rafael Copat), garantimos que:
//
// - Não utilizamos código fonte obtidos de outros estudantes,
// ou fonte não autorizada, seja modificado ou cópia literal.
// - Todo código usado em nosso trabalho é resultado do nosso
// trabalho original, ou foi derivado de um
// código publicado nos livros texto desta disciplina.
// - Temos total ciência das consequências em caso de violarmos estes termos.

import java.util.Comparator;



public class ComparaFolha implements Comparator<NodeFolha> {

	@Override
	public int compare(NodeFolha arg0, NodeFolha arg1) {
		return arg0.
                compareTo(arg1);
	}

}
