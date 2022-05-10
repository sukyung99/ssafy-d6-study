import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503_�κ�û�ұ� {
	static int N, M; 
	static int ans = 0;
	static int[][] map;
	static int[] dy = {-1, 0, 1, 0}; // �� �� �� ��
	static int[] dx = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = parse(st.nextToken());
		M = parse(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int r = parse(st.nextToken());
		int c = parse(st.nextToken());
		int dir = parse(st.nextToken());
		if(dir == 1) dir=3; // �� -> ��
		else if(dir==3) dir=1; // �� -> ��
		
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = parse(st.nextToken());
			}
		}
		
		dfs(r, c, dir);
		System.out.println(ans);
	}

	private static void dfs(int i, int j, int d) {
		if(map[i][j] ==0) { // �����ڸ� û��
			map[i][j] = -1;
			ans++; 
		}
			
		boolean flag = false; 
		for(int t=0; t<4; t++) {
			int nd = (d+1)%4;
			int ni = i +dy[nd];
			int nj = j +dx[nd];
			
			if(check(ni, nj) && map[ni][nj] == 0) {
				dfs(ni, nj, nd);
				flag = true; // ������� �ϳ��� �ִٸ�
				break;
			}
			d = (d+1)%4;
		}
		
		if(!flag) { // �� ���� ��� ������� ������, �� or û���� ����
			int backD = (d+2)%4;
			int bi = i + dy[backD];
			int bj = j + dx[backD];

			if(map[bi][bj] == 1) {// ����, �ڿ� ���̶�� ����
				return; 
			}else if(map[bi][bj] <= 0) { // ���� �ƴ϶�� ����
				dfs(bi, bj, d);
			}

		}
	}

	private static boolean check(int ni, int nj) {
		if(ni>0 && nj>0 && ni<N && nj<M) return true;
		return false;
	}

	private static int parse(String str) {
		return Integer.parseInt(str);
	}

	
}
