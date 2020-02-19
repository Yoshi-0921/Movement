package simulation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import agent.Agent;
import core.Sfmt;
import environment.Field;

public class Simulation {
	private static int[][] log = new int[100][2];
	
	public static void start(String f_clock) {
		int sim_max = 100;
		int action = 4;
		int pos[] = {0, 0};
		int score = 0;
		
		Sfmt rnd = new Sfmt(7);
		Field f = new Field(rnd);
		Agent player = new Agent(f,pos);

		for(int time=0;time<sim_max;time++){
			if(time%(sim_max/10) == 0){
				System.out.println(f_clock + ": " + time + "step------------------------------------------------------------------------");
			}

			action = player.action();

			if(action < 4) player.move(action);
			score += f.acquire_event(pos[0], pos[1]);

			pos = player.getPos();
			System.out.println("action player1: " + action);
			//それぞれのエージェントのこのターンの座標を出力
			System.out.println("position player1: (" + player.getPos()[0] + "," + player.getPos()[1] + ")");
			log[time][0] = player.getPos()[0];
			log[time][1] = player.getPos()[1];
			System.out.println("Score: "+score);
			System.out.println();
		}
	}
	
	public static int[][] get_log(){
		return log;
	}
	
	public static void print_log() {
		try {
			FileWriter f = new FileWriter("C:\\Users\\Yoshinari\\Python Practices\\Research Project A\\result.csv", false);
			PrintWriter p = new PrintWriter(new BufferedWriter(f));
			
			for(int i=0; i<log.length; i++) {
				p.print(log[i][0]);
				p.print(",");
				p.print(log[i][1]);
				p.println();
			}

			p.close();

			System.out.println("ファイル出力完了！");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
