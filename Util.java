import java.util.ArrayList;
import java.util.Iterator;

public class Util {

	public static int toB(int rgb) {
		int mask = 255;
		int b = rgb & mask;
		return b;
	}

	public static int toG(int rgb) {
		int mask = 255;
		int g = rgb >> 8;
		g = g & mask;
		return g;
	}
	
	public static void nullPointers(Node nO) {
		((NodePai)nO).setNE(null);
		((NodePai)nO).setSE(null);
		((NodePai)nO).setSO(null);
		((NodePai)nO).setNO(null);
	}
	
	public static ArrayList<NodeFolha> descompressList(ArrayList<Node> list){
		Iterator<Node> it = list.iterator();
		ArrayList<NodeFolha> pixels = new ArrayList<NodeFolha>();
		while(it.hasNext()){
			Node n = it.next();
			if(n instanceof NodePai){
			int rgb = n.getRgb();
			int x = ((NodePai)n).getxMin();
			int y = ((NodePai)n).getyMin();
			for (int i = x+1; i <= ((NodePai)n).getxMax(); i++) {
				for (int j = y+1; j <= ((NodePai)n).getyMax(); j++) {
					pixels.add(new NodeFolha(rgb, i , j));
				}
				
			}
			}
			else{
				pixels.add((NodeFolha) n);
			}
			
		}
		ComparaFolha cf = new ComparaFolha();
		pixels.sort(cf);
		return pixels;
	}
	
	public static boolean taxaDeCompressão(double dist1,double dist2,double dist3, double dist4, int taxa){
		if((dist1 > taxa || dist2 > taxa || dist3 > taxa || dist4 > taxa)){
			return true;
		}
		return false;
	}
}
