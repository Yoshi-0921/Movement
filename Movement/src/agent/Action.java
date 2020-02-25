package agent;

import core.Sfmt;
import environment.Field;

public class Action {
	Field field;
	Sfmt rnd;
	private int direction[][] = {
			{0,1},{1,0},{0,-1},{-1,0}
	};

	public Action(Field f, int p[]){
		field = f;
		rnd = new Sfmt(7);
	}
	
	public int act(int pos[], int map[][]) {
		//周辺で最小のmapの値を見つけ進む
		int map_value = 0;
		int decision = 0;
		for(int i=0; i<4;i++) {
			if(0<=pos[0]+direction[i][0]&&pos[0]+direction[i][0]<Field.NUM_COL+1&&
					0<=pos[1]+direction[i][1]&&pos[1]+direction[i][1]<Field.NUM_COL+1){
				if(map_value >= map[pos[0]+direction[i][0]][pos[1]+direction[i][1]]){
					map_value = map[pos[0]+direction[i][0]][pos[1]+direction[i][1]];
					decision = i;
				}
			}
		}

		for(int i=0; i<4;i++) {
			if(0<=pos[0]+direction[i][0]&&pos[0]+direction[i][0]<Field.NUM_COL+1&&
					0<=pos[1]+direction[i][1]&&pos[1]+direction[i][1]<Field.NUM_COL+1) {
				if(field.event[pos[0]+direction[i][0]][pos[1]+direction[i][1]][1] == 1){
					decision = i;
				}
			}
		}

		return decision;
	}

	/*0, pos[1]++;
	  1, pos[0]++;
	  2, pos[1]--;
	  3, pos[0]--;*/

	/*public int act(Sfmt rnd){
		return (int)(rnd.NextUnif()*5);
	}*/
}
