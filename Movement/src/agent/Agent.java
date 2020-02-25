package agent;

import core.Sfmt;
import environment.Field;

public class Agent {
	private Field field;
	private int pos[];
	private Sfmt rnd;
	private Action action;
	private int[][] coverage_map = new int[Field.NUM_COL+1][Field.NUM_COL+1];

	public Agent(Field f, int p[], Sfmt rnd){
		field = f;
		pos = p;
		this.rnd = rnd;
		action = new Action(field, pos);
	}

	public int action(){
		//return action.act(rnd);
		return action.act(pos,coverage_map);
	}

	public void move(int act){
		switch(act){
		case 0:
			if(field.getPosStatus(pos[0],pos[1],act) == 1) {
				pos[1]++;
				coverage_map[pos[0]][pos[1]]++;
			}
			break;
		case 1:
			if(field.getPosStatus(pos[0],pos[1],act) == 1) {
				pos[0]++;
				coverage_map[pos[0]][pos[1]]++;
			}
			break;
		case 2:
			if(field.getPosStatus(pos[0],pos[1],act) == 1) {
				pos[1]--;
				coverage_map[pos[0]][pos[1]]++;
			}
			break;
		case 3:
			if(field.getPosStatus(pos[0],pos[1],act) == 1) {
				pos[0]--;
				coverage_map[pos[0]][pos[1]]++;
			}
			break;
		default:
		}
	}
	
	/*public void move(int act){
		switch(act){
		case 0:
			if(field.getPosStatus(pos[0],pos[1],act) == 1)pos[1]++;
			break;
		case 1:
			if(field.getPosStatus(pos[0],pos[1],act) == 1) pos[0]++;
			break;
		case 2:
			if(field.getPosStatus(pos[0],pos[1],act) == 1) pos[1]--;
			break;
		case 3:
			if(field.getPosStatus(pos[0],pos[1],act) == 1) pos[0]--;
			break;
		default:
		}
		coverage_map[pos[0]][pos[1]]++;
	}*/
	
	/*public void print_coveragemap() {
		System.out.println(coverage_map[0][5]);
	}*/

	public int[] getPos(){
		return pos;
	}
}
