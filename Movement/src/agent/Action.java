package agent;

import core.Sfmt;
import environment.Field;

public class Action {
	Field field;
	Sfmt rnd;

	public Action(Field f, int p[]){
		field = f;
		rnd = new Sfmt(7);
	}

	public int act(int pos[]){
		return (int)(rnd.NextUnif()*5);
	}
}
