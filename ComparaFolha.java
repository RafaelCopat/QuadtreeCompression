// N�s (Gian Ribolli, Rafael Eyng e Rafael Copat), garantimos que:
//
// - N�o utilizamos c�digo fonte obtidos de outros estudantes,
// ou fonte n�o autorizada, seja modificado ou c�pia literal.
// - Todo c�digo usado em nosso trabalho � resultado do nosso
// trabalho original, ou foi derivado de um
// c�digo publicado nos livros texto desta disciplina.
// - Temos total ci�ncia das consequ�ncias em caso de violarmos estes termos.

import java.util.Comparator;



public class ComparaFolha implements Comparator<NodeFolha> {

	@Override
	public int compare(NodeFolha arg0, NodeFolha arg1) {
		return arg0.
                compareTo(arg1);
	}

}
