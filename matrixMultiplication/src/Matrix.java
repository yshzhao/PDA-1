import java.util.Vector;

class ComputeElement extends Thread {
	int row;
	int column;
	int rv[], cv[];

	public ComputeElement(int row, int column, int rv[], int cv[]) {
		this.row = row;
		this.column = column;
		this.rv = rv;
		this.cv = cv;
		this.start();
	}

	public void run() {
		synchronized (this) {
			Matrix.c[row][column] = 0;
			for (int i = 0; i < rv.length; i++) {
				Matrix.c[row][column] += rv[i] * cv[i];
			}
		}
	}
}

public class Matrix {
	static int[][] c = new int[10000][10000];

	public static void main(String args[]) throws InterruptedException {
		int a[][] = new int[100][100], b[][] = new int[100][100];

		// lines for first matrix
		int m = 4;
		// columns for first matrix and lines for second matrix
		int n = 4;
		// columns for second matrix
		int p = 4;
		System.out.println("First Matrix:");

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				a[i][j] = (int) (Math.random() * 10);
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("Second Matrix:");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < p; j++) {
				b[i][j] = (int) (Math.random() * 10);
				System.out.print(b[i][j] + " ");
			}
			System.out.println();
		}


		Vector<ComputeElement> CEVector = new Vector<ComputeElement>();
		for (int i = 0; i < m; i++)
			for (int j = 0; j < p; j++) {
				int rv[] = new int[100];
				int cv[] = new int[100];
				for (int k = 0; k < n; k++) {
					rv[k] = a[i][k];
					cv[k] = b[k][j];
				}

				CEVector.add(new ComputeElement(i, j, rv, cv));

			}
		for (int i = 0; i < CEVector.size(); i++)
			CEVector.get(i).join();
		System.out.println("multiplication result");
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < p; j++)
				System.out.print(c[i][j] + " ");
			System.out.println();
		}

	}
}