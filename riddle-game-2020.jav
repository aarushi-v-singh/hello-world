package gamesAllTogether;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RiddleGame implements ActionListener ,KeyListener{
	
	//timer 
	private static int sec =01;
	private static int min = 02;
	private static JLabel time;
	private static Timer timer = new Timer();
	private static TimerTask task = new TimerTask() {
		public void run() {

			sec--;
			if(min>=1&&sec==0) {
				min--;
				sec = 59;
			}
			if(sec<10) {//check this part 
				time.setText("Timer: 0"+min+":0"+sec);
			}else {
				time.setText("Timer: 0"+min+":"+sec);
			}
			if(sec<=5&&min==0) {
				panel.setBackground(Color.red);
			}
			if(min==0&&sec==0) {
				timer.cancel();
				time.setText("Timer done");
				submitbutton.setVisible(false);
				questionnolabel.setVisible(false);
				question.setVisible(false);
				scoreLabel.setVisible(false);
				answer.setVisible(false);
				answerlabel.setVisible(false);
				time.setVisible(false);

				success.setText("SORRY, YOU LOST THE GAME");

			}



			//				if(sec>=10) {
			//					time.setText("Timer: 0"+min +":"+sec);
			//				}else {
			//					time.setText("Timer: 0"+min +":0"+sec);
			//				}
			//				sec++;
			//				if(sec%60==0) {
			//					sec = 0;
			//					min++;
			//				}
			//				

		}
	};

	//game 

	private static String[] Riddles;
	private static String[] answers;
	private static int score=0;
	private static int quesnumber =0;
	private static JFrame frame;
	private static JPanel panel;
	private static JLabel success;
	private static JLabel scoreLabel;
	private static JLabel question;
	private static JLabel questionnolabel;
	private static JLabel answerlabel;
	private static JTextField answer;
	private static JButton submitbutton;



	public static void main(String[] args) {


		Riddles = new String[7];
		Riddles[0] = "What has many keys but can’t open a single lock?";
		Riddles[1] = "What has to be broken before you can use it?";
		Riddles[2] = "I’m tall when I’m young, and I’m short when I’m old. What am I?";
		Riddles[3] = "What is always in front of you but can’t be seen?";
		Riddles[4] = "What can you break, even if you never pick it up or touch it?";
		Riddles[5] = "What goes up but never comes down?";
		Riddles[6] = "I shave every day, but my beard stays the same. What am I?";	

		answers = new String[7];
		answers[0] = "keyboard";
		answers[1] = "egg";
		answers[2] = "candle";
		answers[3] = "future";
		answers[4] = "promise";
		answers[5] = "age";
		answers[6] = "barber";

		frame = new JFrame();
		panel = new JPanel();
		success = new JLabel("");
		success.setBounds(125, 30, 500, 200);
		panel.add(success);
		Rectangle rect = new Rectangle(500,175);
		frame.setBounds(rect);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		panel.setLayout(null);

		time = new JLabel("hey");
		time.setBounds(250, 55, 100, 175);
		panel.add(time);

		questionnolabel= new JLabel("Question number: "+(quesnumber+1));
		questionnolabel.setBounds(200, 10, 200, 25);
		panel.add(questionnolabel);

		scoreLabel = new JLabel("Score: "+score);
		scoreLabel.setBounds(10, 10, 80, 25);
		panel.add(scoreLabel);

		question = new JLabel("Question: "+Riddles[0]);
		question.setBounds(10, 40, 500, 25);
		panel.add(question);

		answerlabel = new JLabel("Answer: ");
		answerlabel.setBounds(10,70,80,25);
		panel.add(answerlabel);

		answer = new JTextField(20);
		answer.setBounds(100,70,165,25);
		panel.add(answer);

		//		answer.addActionListener(new java.awt.event.ActionListener() {
		//		    public void actionPerformed(java.awt.event.ActionEvent e) {
		//		 checksubmit();
		//		    }
		//		});

		submitbutton = new JButton("Submit");
		submitbutton.setBounds(250, 100, 80, 25);
		submitbutton.addActionListener(new RiddleGame());
		answer.addKeyListener(new RiddleGame());
		panel.add(submitbutton);

		timer.schedule(task, 1000, 1000);

		frame.setVisible(true);	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		checksubmit();

	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}



	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			checksubmit();
		}

	}
	@Override
	public void keyReleased(KeyEvent e) {


	}

	public static void checksubmit() {
		String text = answer.getText();

		if(text.toLowerCase().trim().equals(answers[quesnumber])) {
			score++;
			scoreLabel.setText("Score: "+score);
			success.setText("CORRECT!!");

		}else {
			success.setText("INCORRECT!!");
		}
		if(quesnumber==6) {
			submitbutton.setVisible(false);
			questionnolabel.setVisible(false);
			question.setVisible(false);
			scoreLabel.setVisible(false);
			answer.setVisible(false);
			answerlabel.setVisible(false);

			if(score>=5) {
				success.setText("YEAH, YOU WON THE GAME!!");
				timer.cancel();
				time.setVisible(false);
			}else {
				success.setText("SORRY, YOU LOST THE GAME");
			}

		}else {
			quesnumber++;	
		}
		questionnolabel.setText("Question number: "+(quesnumber+1));
		question.setText("Question: "+Riddles[quesnumber]);
		answer.setText("");
	}


}

