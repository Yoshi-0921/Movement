package simulation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import agent.Agent;
import core.Sfmt;
import environment.Field;

public class Simulation {
	private static int[][] log = new int[500][4];
	private static int[][] event;
	private static ArrayList<ArrayList<Integer>> t = new ArrayList<ArrayList<Integer>>();
	
	public static void start(String f_clock) {
		int sim_max = 500;
		int action = 4;
		int action2 = 4;
		int pos[] = {0, 0};
		int pos2[] = {49, 49};
		int score = 0;
		int score2 = 0;
		
		Sfmt rnd = new Sfmt(7);
		Sfmt rnd2 = new Sfmt(13);
		Field f = new Field(rnd);
		Agent player = new Agent(f,pos,rnd);
		Agent player2 = new Agent(f,pos2,rnd2);
		event = f.getEventCoord();
		t = f.getEventTime();

		for(int time=0;time<sim_max;time++){
			if(time%(sim_max/10) == 0){
				System.out.println(f_clock + ": " + time + "step------------------------------------------------------------------------");
			}

			action = player.action();
			action2 = player2.action();

			player.move(action);
			score += f.acquire_event(pos[0], pos[1], time);
			player2.move(action2);
			score2 += f.acquire_event(pos2[0], pos2[1], time);

			pos = player.getPos();
			pos2 = player2.getPos();
			
			System.out.println("action player1: " + action);
			System.out.println("position player1: (" + player.getPos()[0] + "," + player.getPos()[1] + ")");
			System.out.println("Score: "+score);
			log[time][0] = player.getPos()[0];
			log[time][1] = player.getPos()[1];
			
			System.out.println("action player2: " + action2);
			System.out.println("position player2: (" + player2.getPos()[0] + "," + player2.getPos()[1] + ")");
			System.out.println("Score2: "+score2);
			//player.print_coveragemap();
			log[time][2] = player2.getPos()[0];
			log[time][3] = player2.getPos()[1];
			System.out.println();
		}
	}
	
	public static void print_t(){
		for(int i=0; i<t.size(); i++) {
			System.out.println(t.get(i));
		}
	}
	
	public static void print_file() {
		try {
			FileWriter f = new FileWriter("C:\\Users\\Yoshinari\\Python Practices\\Research Project A\\event.csv", false);
			PrintWriter p = new PrintWriter(new BufferedWriter(f));
			
			for(int i=0; i<event.length; i++) {
				p.print(event[i][0]);
				p.print(",");
				p.print(event[i][1]);
				p.println();
			}
			
			/*for(int i=0; i<t.size();i++) {
				p.print(t.get(i));
				p.println();
			}*/
			System.out.println("");

			p.close();

			System.out.println("イベントファイル出力完了！");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		try {
			FileWriter f = new FileWriter("C:\\Users\\Yoshinari\\Python Practices\\Research Project A\\combinations.csv", false);
			PrintWriter p = new PrintWriter(new BufferedWriter(f));
			
			for(int i=0; i<t.size(); i++) {
				for(int j=0; j<t.get(i).size(); j++) {
					p.print(t.get(i).get(j));
					if(j!=t.get(i).size()-1) p.print(",");
				}
				p.println();
			}
			
			System.out.println("");

			p.close();

			System.out.println("コンビネーションファイル出力完了！");
			System.out.println();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		try {
			FileWriter f = new FileWriter("C:\\Users\\Yoshinari\\Python Practices\\Research Project A\\result.csv", false);
			PrintWriter p = new PrintWriter(new BufferedWriter(f));
			
			p.print(0);
			p.print(",");
			p.print(0);
			p.print(",");
			p.print(Field.NUM_COL);
			p.print(",");
			p.println(Field.NUM_COL);
			for(int i=0; i<log.length; i++) {
				p.print(log[i][0]);
				p.print(",");
				p.print(log[i][1]);
				p.print(",");
				p.print(log[i][2]);
				p.print(",");
				p.println(log[i][3]);
			}

			p.close();

			System.out.println("ログファイル出力完了！");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
