// N�s (Gian Ribolli, Rafael Eyng e Rafael Copat), garantimos que:
//
// - N�o utilizamos c�digo fonte obtidos de outros estudantes,
// ou fonte n�o autorizada, seja modificado ou c�pia literal.
// - Todo c�digo usado em nosso trabalho � resultado do nosso
// trabalho original, ou foi derivado de um
// c�digo publicado nos livros texto desta disciplina.
// - Temos total ci�ncia das consequ�ncias em caso de violarmos estes termos.

public class NodeFolha extends Node implements Comparable<NodeFolha> {

	protected int x;
	protected int y;

	public NodeFolha(int rgb, int x, int y) {
		this.rgb = rgb;
		this.x = x;
		this.y = y;
	}

	public int getRgb() {
		return rgb;
	}

	public void setRgb(int rgb) {
		this.rgb = rgb;

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public int compareTo(NodeFolha o) {
		if(getX() > o.getX()){
			return 1;
		}
		else if(getX() == o.getX()){
			if(getY() > o.getY()){
				return 1;
			}
			else{
				return -1;
			}
		}
		else{
			return -1;
		}
	}

}
