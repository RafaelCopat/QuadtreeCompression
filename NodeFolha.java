// Nós (Gian Ribolli, Rafael Eyng e Rafael Copat), garantimos que:
//
// - Não utilizamos código fonte obtidos de outros estudantes,
// ou fonte não autorizada, seja modificado ou cópia literal.
// - Todo código usado em nosso trabalho é resultado do nosso
// trabalho original, ou foi derivado de um
// código publicado nos livros texto desta disciplina.
// - Temos total ciência das consequências em caso de violarmos estes termos.

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
