
public class Board {
	int a[][] = new int[3][];
	int g;
	int h;
Board(){
	a[0] = new int[3];
	a[1] = new int[3];
	a[2] = new int[3];
}
Board(int b[][],int g,int h){
	a[0] = new int[3];
	a[1] = new int[3];
	a[2] = new int[3];
	for(int i=0;i<3;i++) {
		for(int j=0;j<3;j++) {
			a[i][j]=b[i][j];
		}
	}
	this.g = g;
	this.h = h;
}
@Override
public String toString() {
	String s = new String();
	s = "\n";
	for(int i=0;i<3;i++) {
		for(int j=0;j<3;j++) {
			s = s+ a[i][j]+"\t";
		}
		s=s+"\n";
	}
	s=s + "Heurtic value\t" + h + "Step value\t" + g;
	return s;
}
}
