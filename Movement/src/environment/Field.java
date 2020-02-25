package environment;

import java.util.ArrayList;

import core.Sfmt;

public class Field {
	/*private int field[][][]= {
			{{1,1,0,0},{1,1,1,0},{1,1,1,0},{1,1,1,0},{1,1,1,0},{1,1,1,0},{1,1,1,0},{1,1,1,0},{0,1,1,0}},
			{{1,1,0,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{0,1,1,1}},
			{{1,1,0,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{0,1,1,1}},
			{{1,1,0,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{0,1,1,1}},
			{{1,1,0,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{0,1,1,1}},
			{{1,1,0,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{0,1,1,1}},
			{{1,1,0,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{0,1,1,1}},
			{{1,1,0,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{0,1,1,1}},
			{{1,0,0,1},{1,0,1,1},{1,0,1,1},{1,0,1,1},{1,0,1,1},{1,0,1,1},{1,0,1,1},{1,0,1,1},{0,0,1,1}}
			};*/
	
	public final static int NUM_COL = 49;
	private static int[][][] field = new int[NUM_COL+1][NUM_COL+1][4];
	
	public int event[][][];
	private int count_event;
	private int[][] event_coord = new int[NUM_COL+1][2];
	private static ArrayList<ArrayList<Integer>> combinations = new ArrayList<ArrayList<Integer>>();

	public Field(Sfmt rnd){
		event = new int[NUM_COL+1][NUM_COL+1][2];
		
		generate_field();
		
		for(int i=0;i<=NUM_COL;i++){
			for(int j=0;j<=NUM_COL;j++){
				if(rnd.NextUnif() < 0.01){
					event[i][j][0] = count_event++;
					event[i][j][1] = 1;
					event_coord[count_event][0] = i;
					event_coord[count_event][1] = j;
					System.out.println("Event on ("+i+","+j+")");
				}
			}
		}
		System.out.println("");
	}
	
	/*{{{1,1,0,0},{1,1,1,0},{1,1,1,0},{1,1,1,0},{0,1,1,0}},
	 * {{1,1,0,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{0,1,1,1}},
	 * {{1,1,0,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{0,1,1,1}},
	 * {{1,1,0,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{0,1,1,1}},
	 * {{1,0,0,1},{1,0,1,1},{1,0,1,1},{1,0,1,1},{0,0,1,1}}}*/
	
	private static void generate_field() {
		for(int i=0; i<=NUM_COL; i++) {
			for(int j=0; j<=NUM_COL; j++) {
				for(int k=0; k<4; k++) {
				field[i][j][k] = 1;	
				}
			}
		}
		for(int i=0; i<=NUM_COL; i++) {
			field[i][0][2] = 0;
			field[i][NUM_COL][0] = 0;
			if(i==0) {
				for(int j=0; j<=NUM_COL; j++) {
					field[i][j][3] = 0;
				}
			}
			else if(i==NUM_COL) {
				for(int j=0; j<=NUM_COL; j++) {
					field[i][j][1] = 0;
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
	
	public int acquire_event(int x, int y, int time){
		int result = event[x][y][1];
		ArrayList<Integer> combination = new ArrayList<Integer>();
		if(result == 1) {
			event[x][y][1] = 0;
			combination.add(time);
			combination.add(event[x][y][0]);
			combinations.add(combination);
		}
		return result;
	}

	public int[][][] getEvent(){
		return event;
	}
	
	public int getCountEvent() {
		return count_event;
	}
	
	public int[][] getEventCoord(){
		return event_coord;
	}
	
	public ArrayList<ArrayList<Integer>> getEventTime(){
		return combinations;
	}

}
