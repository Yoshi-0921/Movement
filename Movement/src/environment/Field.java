package environment;

import core.Sfmt;

public class Field {
	private int field[][][]= {
			{{1,1,0,0},{1,1,1,0},{1,1,1,0},{1,1,1,0},{1,1,1,0},{1,1,1,0},{1,1,1,0},{1,1,1,0},{0,1,1,0}},
			{{1,1,0,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{0,1,1,1}},
			{{1,1,0,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{0,1,1,1}},
			{{1,1,0,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{0,1,1,1}},
			{{1,1,0,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{0,1,1,1}},
			{{1,1,0,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{0,1,1,1}},
			{{1,1,0,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{0,1,1,1}},
			{{1,1,0,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{0,1,1,1}},
			{{1,0,0,1},{1,0,1,1},{1,0,1,1},{1,0,1,1},{1,0,1,1},{1,0,1,1},{1,0,1,1},{1,0,1,1},{0,0,1,1}}
			};
	private int event[][];

	public Field(Sfmt rnd){
		event = new int[9][9];

		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(rnd.NextUnif() < 0.1){
					event[i][j] = 1;
					System.out.println("Event on ("+i+","+j+")");
				}
			}
		}
	}

	public int getPosStatus(int x, int y, int act){
		return field[x][y][act];
	}

	public int[] getPosStatus(int x, int y){
		return field[x][y];
	}

	public int[][][] getField(){
		return field;
	}
	
	public int acquire_event(int x, int y){
		int result = event[x][y];
		event[x][y] = 0;
		if(result == 1) System.out.println("Encountered");
		return result;
	}

	public int getEvent(int x, int y){
		return event[x][y];
	}

}
