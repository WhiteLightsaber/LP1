import java.util.ArrayList;

public class Solver {
	ArrayList<Board> Array;
	Solver(){
		Array = new ArrayList<>();
	}
	public void calculate(int a[][]) {
		int h[] = new int[4];
		int step = 0;
		Array.add(new Board((a),step,Hamming(a)));
		while(Array.get(0).h!=0) {
			step++;
			int g[][] = new int[3][];
			g[0] = new int[3];
			g[1] = new int[3];
			g[2] = new int[3];
			g = tempo(Array.get(0).a);
			h[0] = Hamming(moveright(g));
			
			g = tempo(Array.get(0).a);
			h[1] = Hamming(moveleft(g));
			
			g = tempo(Array.get(0).a);
			h[2] = Hamming(movedown(g));
			
			g = tempo(Array.get(0).a);
			h[3] = Hamming(moveup(g));
			
			int min = 10000;
			int k = 0;
			for(int i=0;i<3;i++) {
				if(min>h[i]) {
					min = h[i];
					k = i;
				}
			}
			
			if(k==0) {
				Array.add(new Board(moveright(Array.get(0).a),step,h[k]));
			}
			else if(k==1) {
				Array.add(new Board(moveleft(Array.get(0).a),step,h[k]));
			}
			else if(k==2) {
				Array.add(new Board(movedown(Array.get(0).a),step,h[k]));
			}
			else if(k==3) {
				Array.add(new Board(moveup(Array.get(0).a),step,h[k]));
			}
			Array.remove(0);
			System.out.println(Array.get(0));
			if(min==0) {
				return;
			}
		}
	}
	int Hamming(int a[][]) {
		int count = 0;
		int k = 1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (a[i][j] != k && a[i][j] != 0) {
					count++;
				}
				k++;
			}
		}
		return count;
	}
	public static void main(String[] args) {
		int a[][] = {{0,1,3},{4,2,5},{7,8,6}};
		Solver ss = new Solver();
		System.out.println("Hello");
		ss.calculate(a);
	}
	int[][] tempo(int a[][]){
		int g[][] = new int[3][];
		g[0] = new int[3];
		g[1] = new int[3];
		g[2] = new int[3];
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				g[i][j]=a[i][j];
			}
		}
		return g;
	}
	int[][] moveright(int a[][]){
		int flag = 0;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(a[i][j]==0&&i+1<3) {
					int temp = a[i][j];
					a[i][j] = a[i+1][j];
					a[i+1][j] = temp;
					flag = 1;
					break;
				}
			}
			if(flag==1) {
				break;
			}
		}
		return a;
	}
	int[][] moveleft(int a[][]){
		int flag = 0;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(a[i][j]==0&&i-1>=0) {
					int temp = a[i][j];
					a[i][j] = a[i-1][j];
					a[i-1][j] = temp;
					flag = 1;
					break;
				}
			}
			if(flag==1) {
				break;
			}
		}
		return a;
	}
	int[][] moveup(int a[][]){
		int flag = 0;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(a[i][j]==0&&j-1>=0) {
					int temp = a[i][j-1];
					a[i][j-1] = a[i][j];
					a[i][j] = temp;
					flag = 1;
					break;
				}
			}
			if(flag==1) {
				break;
			}
		}
		return a;
	}
	int[][] movedown(int a[][]){
		int flag = 0;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(a[i][j]==0&&j+1<3) {
					int temp = a[i][j+1];
					a[i][j+1] = a[i][j];
					a[i][j] = temp;
					flag = 1;
					break;
				}
			}
			if(flag==1) {
				break;
			}
		}
		return a;
	}
}
