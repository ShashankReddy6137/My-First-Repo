package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.SwingConstants;

public class Mygame extends JFrame implements ActionListener {
	JLabel heading, clock;
	System.out.println("hello java ");
	Font f = new Font("", Font.BOLD, 40);
	JPanel mypanel;
	JButton[] btns = new JButton[9];
	int[] gamechances = { 2, 2, 2, 2, 2, 2, 2, 2, 2 };
	int activeplayer = 0;
	int wps[][] = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, { 0, 4, 8 },
			{ 2, 4, 6 } };
	int winner = 2;
boolean gameover=false;
	Mygame() {
		setSize(700, 700);
		setTitle("Tic Tac Toe Game");
		ImageIcon i = new ImageIcon("src/img/tic_tac_icon.png");
		setIconImage(i.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		abc();
		setVisible(true);
	}

	private void abc() {// for headinng
		this.getContentPane().setBackground(Color.decode("#03a5fc"));
		this.setLayout(new BorderLayout());

		heading = new JLabel("Tic Tac Toe");
		heading.setFont(f);
		heading.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(heading, BorderLayout.NORTH);
		// for clock
		clock = new JLabel("clock");
		clock.setFont(f);
		clock.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(clock, BorderLayout.SOUTH);
		Thread t = new Thread() {
			public void run() {
				try {
					while (true) {
						String s1 = new Date().toLocaleString();
						clock.setText(s1);
						Thread.sleep(1000);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		};
		t.start();
		mypanel = new JPanel();
		mypanel.setLayout(new GridLayout(3, 3));

		for (int i = 1; i <= 9; i++) {
			JButton btn = new JButton();
			// btn.setIcon(new ImageIcon("src/img/00.png"));
			btn.setFont(f);
			btn.setBackground(Color.decode("#34ebe8"));
			mypanel.add(btn);
			btns[i - 1] = btn;
			btn.setName(String.valueOf(i - 1));
			btn.addActionListener(this);
		}
		this.add(mypanel, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton currentbutton = (JButton) e.getSource();
		String s = currentbutton.getName();
		System.out.println(s);
		int name = Integer.parseInt(s.trim());
		if(gameover==true)
		{
			JOptionPane.showMessageDialog(this,"Game already over");
			return;
		}
		if (gamechances[name] == 2) {
			if (activeplayer == 1) {
				currentbutton.setIcon(new ImageIcon("src/img/1.png"));
				gamechances[name] = activeplayer;
				activeplayer = 0;
			} else {
				currentbutton.setIcon(new ImageIcon("src/img/00.png"));
				gamechances[name] = activeplayer;
				activeplayer = 1;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Position alredy occupied");
		}
		// winning logic
		for (int[] temp : wps) {
			if ((gamechances[temp[0]] == gamechances[temp[1]]) && (gamechances[temp[1]] == gamechances[temp[2]])
					&& (gamechances[temp[2]] != 2)) 
			{
             winner=gamechances[temp[0]];
             gameover=true;
             JOptionPane.showMessageDialog(null,"player"+winner+"has won the game");
            int i= JOptionPane.showConfirmDialog(this,"do you want to play more?");
            if(i==0)
            {
            	this.setVisible(false);
            	new Mygame();
            }
            else if(i==1)
            {
            	System.exit(54868);
            }
            else {
            	
            }
            System.out.println(i);
             break;
			}
			
		}
		
		int c=0;
		
		for(int x:gamechances)
		{
			if(x==2)
			{
				c++;
				break;
			}
		}
		if(c==0&&gameover==false)
		{
			JOptionPane.showMessageDialog(null, "its draw");
			int i=JOptionPane.showConfirmDialog(this,"play more ??");
			if(i==0)
			{
				this.setVisible(false);
				new Mygame();
			}
			else if(i==1)
			{
				System.exit(5484);
			}
			else {
				
			}
		}
	}
}
