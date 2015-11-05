// Nós (Gian Ribolli, Rafael Eyng e Rafael Copat), garantimos que:
//
// - Não utilizamos código fonte obtidos de outros estudantes,
// ou fonte não autorizada, seja modificado ou cópia literal.
// - Todo código usado em nosso trabalho é resultado do nosso
// trabalho original, ou foi derivado de um
// código publicado nos livros texto desta disciplina.
// - Temos total ciência das consequências em caso de violarmos estes termos.

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class NodePai extends Node {

	protected PosCart pos;
	protected Node NO;
	protected Node NE;
	protected Node SO;
	protected Node SE;
	protected int xMin;
	protected int xMax;
	protected int yMin;
	protected int yMax;
	protected boolean corParecida;
	protected boolean compressed;

	public NodePai(PosCart pos, int xMin, int xMax, int yMin, int yMax,
			int pai, int nivel) {

		this.pos = pos;
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
		this.pai = pai;
		this.nivel = nivel;

	}

	public Node getNO() {
		return NO;
	}

	public void setNO(Node nO) {
		NO = nO;
	}

	public Node getNE() {
		return NE;
	}

	public void setNE(Node nE) {
		NE = nE;
	}

	public Node getSO() {
		return SO;
	}

	public void setSO(Node sO) {
		SO = sO;
	}

	public Node getSE() {
		return SE;
	}

	public void setSE(Node sE) {
		SE = sE;
	}

	public void insert(Node n) throws InvalidXYException {

		int y;
		int x;
		PosCart pos;
		y = ((NodeFolha) n).getY();
		x = ((NodeFolha) n).getX();
		if (validateXY(x, y)) {
			pos = whereToPut(x, y);
			Node here = nodeAtPos(pos);
			// fim da recursão
			if (here == null) {
				realInsert(pos, n);
				return;
			} else {
				// se é pai, chama insert novamente
				if (here instanceof NodePai) {
					((NodePai) here).insert(n);
				} else {

					int[] range = minsAndMaxs(pos);
					NodePai np = new NodePai(pos, range[0], range[1], range[2],
							range[3], indice, nivel + 1);
					NodeFolha anterior = (NodeFolha) nodeAtPos(pos);
					PosCart newPos = np.whereToPut(anterior.getX(),
							anterior.getY());
					realInsert(pos, np);
					np.realInsert(newPos, anterior);
					np.insert(n);

				}
			}
		} else {
			throw new InvalidXYException();
		}
	}

	private boolean validateXY(int x, int y) {
		if (x >= xMin && x <= xMax) {
			if (y >= yMin && y <= yMax) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private PosCart whereToPut(int x, int y) {
		if (x > (xMin + xMax) / 2) {
			if (y > (yMin + yMax) / 2) {
				return PosCart.NE;
			} else {
				return PosCart.SE;
			}
		} else {
			if (y > (yMin + yMax) / 2) {
				return PosCart.NO;
			} else {
				return PosCart.SO;
			}
		}
	}

	private Node nodeAtPos(PosCart pos) {
		if (pos == PosCart.NO) {
			return this.NO;
		} else if (pos == PosCart.NE) {
			return this.NE;
		} else if (pos == PosCart.SE) {
			return this.SE;
		} else {
			return this.SO;
		}
	}

	private void realInsert(PosCart pos, Node n) {

		if (pos == PosCart.NO) {
			this.NO = n;
		} else if (pos == PosCart.NE) {
			this.NE = n;
		} else if (pos == PosCart.SE) {
			this.SE = n;
		} else {
			this.SO = n;
		}
		n.setNivel(nivel + 1);
		n.setPai(indice);

	}

	private int[] minsAndMaxs(PosCart pos) {
		int[] n = new int[4];

		if (pos == PosCart.NO) {
			n[0] = xMin;
			n[1] = (xMin + xMax) / 2;
			n[2] = (yMin + yMax) / 2;
			n[3] = yMax;
		} else if (pos == PosCart.NE) {
			n[0] = (xMin + xMax) / 2;
			n[1] = xMax;
			n[2] = (yMin + yMax) / 2;
			n[3] = yMax;
		} else if (pos == PosCart.SO) {
			n[0] = xMin;
			n[1] = (xMin + xMax) / 2;
			n[2] = yMin;
			n[3] = (yMin + yMax) / 2;
		} else {
			n[0] = (xMin + xMax) / 2;
			n[1] = xMax;
			n[2] = yMin;
			n[3] = (yMin + yMax) / 2;
		}
		return n;
	}

	public void printSubTree(PrintWriter pw) throws IOException {

		if (NO instanceof NodePai) {
			((NodePai) NO).printSubTree(pw);
			((NodePai) NE).printSubTree(pw);
			((NodePai) SO).printSubTree(pw);
			((NodePai) SE).printSubTree(pw);
			this.rgb = (NO.getRgb() + NE.getRgb() + SE.getRgb() + SO.getRgb()) / 4;
			pw.println(NO);
			pw.println(NE);
			pw.println(SO);
			pw.println(SE);
			System.out.println(NO);
			System.out.println(NE);
			System.out.println(SO);
			System.out.println(SE);
		} else {
			if(NO != null){
			this.rgb = (NO.getRgb() + NE.getRgb() + SE.getRgb() + SO.getRgb()) / 4;
			pw.println(NO + "NO, " + "(" + ((NodeFolha) NO).getX() + ","
					+ ((NodeFolha) NO).getY() + "), " + NO.getRgb());
			pw.println(NE + "NE, " + "(" + ((NodeFolha) NE).getX() + ","
					+ ((NodeFolha) NE).getY() + "), " + NE.getRgb());
			pw.println(SO + "SO, " + "(" + ((NodeFolha) SO).getX() + ","
					+ ((NodeFolha) SO).getY() + "), " + SO.getRgb());
			pw.println(SE + "SE, " + "(" + ((NodeFolha) SE).getX() + ","
					+ ((NodeFolha) SE).getY() + "), " + SE.getRgb());

			System.out.println(NO + "NO, " + "(" + ((NodeFolha) NO).getX()
					+ "," + ((NodeFolha) NO).getY() + "), " + NO.getRgb());

			System.out.println(NE + "NE, " + "(" + ((NodeFolha) NE).getX()
					+ "," + ((NodeFolha) NE).getY() + "), " + NE.getRgb());

			System.out.println(SO + "SO, " + "(" + ((NodeFolha) SO).getX()
					+ "," + ((NodeFolha) SO).getY() + "), " + SO.getRgb());

			System.out.println(SE + "SE, " + "(" + ((NodeFolha) SE).getX()
					+ "," + ((NodeFolha) SE).getY() + "), " + SE.getRgb());
			}
		}

	}

	public void compress(int taxa) {
		
		if (NO instanceof NodePai) {
			((NodePai) NO).compress(taxa);
			((NodePai) NE).compress(taxa);
			((NodePai) SO).compress(taxa);
			((NodePai) SE).compress(taxa);
			if (((NodePai) NO).isCorParecida()
					&& ((NodePai) SO).isCorParecida()
					&& ((NodePai) SE).isCorParecida()
					&& ((NodePai) NE).isCorParecida()) {
				compressed = true;
				CompressIfOk(taxa);
				Util.nullPointers(NO);
				Util.nullPointers(NE);
				Util.nullPointers(SE);
				Util.nullPointers(SO);

			}

		} else {
			CompressIfOk(taxa);
		}

	}

	public boolean isCorParecida() {
		return corParecida;
	}

	public boolean isCompressed() {
		return compressed;
	}

	public String toString() {
		return super.toString() + "(" + xMin + "," + yMin + ")" + " X " + "("
				+ xMax + "," + yMax + ")" + ", " + rgb;
	}

	public void CompressIfOk(int taxa) {
		int rgb1 = NO.getRgb();
		int rgb2 = NE.getRgb();
		int rgb3 = SO.getRgb();
		int rgb4 = SE.getRgb();
		int r1, r2, r3, r4, g1, g2, g3, g4, b1, b2, b3, b4;
		r1 = rgb1 >> 16;
		r2 = rgb2 >> 16;
		r3 = rgb3 >> 16;
		r4 = rgb4 >> 16;
		b1 = Util.toB(rgb1);
		b2 = Util.toB(rgb2);
		b3 = Util.toB(rgb3);
		b4 = Util.toB(rgb4);
		g1 = Util.toG(rgb1);
		g2 = Util.toG(rgb2);
		g3 = Util.toG(rgb3);
		g4 = Util.toG(rgb4);
		double dist1 = Math.sqrt(Math.pow(r1 - r2, 2) + Math.pow(g1 - g2, 2)
				+ Math.pow(b1 - b2, 2));
		double dist2 = Math.sqrt(Math.pow(r3 - r4, 2) + Math.pow(g3 - g4, 2)
				+ Math.pow(b3 - b4, 2));
		double dist3 = Math.sqrt(Math.pow(r3 - r1, 2) + Math.pow(g3 - g1, 2)
				+ Math.pow(b3 - b1, 2));
		double dist4 = Math.sqrt(Math.pow(r2 - r4, 2) + Math.pow(g2 - g4, 2)
				+ Math.pow(b2 - b4, 2));
		if (Util.taxaDeCompressão(dist1, dist2, dist3, dist4, taxa)) {
			return;
		} else {
			corParecida = true;
			int r = (r1 + r2 + r3 + r4) / 4;
			r = r << 16;
			int g = (g1 + g2 + g3 + g4) / 4;
			g = g << 8;
			int b = (b1 + b2 + b3 + b4) / 4;
			rgb = r + g + b;
			return;
		}
	}

	public void decompress(ArrayList<Node> list) {
		if (NO != null) {
			if (isCompressed()) {
				list.add(NO);
				list.add(NE);
				list.add(SO);
				list.add(SE);
			} else {
				if (NO instanceof NodePai)
					((NodePai) NO).decompress(list);
				else
					list.add(NO);
				if (NE instanceof NodePai)
					((NodePai) NE).decompress(list);
				else
					list.add(NE);
				if (SO instanceof NodePai)
					((NodePai) SO).decompress(list);
				else
					list.add(SO);
				if (SE instanceof NodePai)
					((NodePai) SE).decompress(list);
				else
					list.add(SE);
			}
		}

	}

	public int getxMin() {
		return xMin;
	}

	public int getxMax() {
		return xMax;
	}

	public int getyMin() {
		return yMin;
	}

	public int getyMax() {
		return yMax;
	}

}
