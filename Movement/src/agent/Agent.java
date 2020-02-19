package agent;

import environment.Field;

public class Agent {
	private Field field;
	private int pos[];
	private Action action;

	public Agent(Field f, int p[]){
		field = f;
		pos = p;
		action = new Action(field, pos);
	}

	public int action(){
		return action.act(pos);
	}

	public void move(int act){
		switch(act){
		case 0:
			if(field.getPosStatus(pos[0],pos[1],act) == 1) pos[1]++;
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
	}

	public int[] getPos(){
		return pos;
	}
}
