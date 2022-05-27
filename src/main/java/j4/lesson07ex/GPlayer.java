package j4.lesson07ex;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GPlayer implements ActionListener {
	public static final ImageIcon ROCK = new ImageIcon("imgGame/GameRock.png");
	public static final ImageIcon SCISSORS = new ImageIcon("imgGame/GameScissor.png");
	public static final ImageIcon PAPER = new ImageIcon("imgGame/GamePaper.png");

	public static void createButton(JPanel footerPanel) {
		System.out.println(ROCK);
		//ボタンを表示
		JButton btnGu = new JButton("グー");
		setButton(btnGu);
		btnGu.setIcon(createScaledImageIcon(ROCK, 50));
		footerPanel.add(btnGu, BorderLayout.WEST);

		JButton btnChoki = new JButton("チョキ");
		setButton(btnChoki);
		btnChoki.setIcon(createScaledImageIcon(SCISSORS, 50));
		footerPanel.add(btnChoki, BorderLayout.CENTER);

		JButton btnPa = new JButton("パー");
		setButton(btnPa);
		btnPa.setIcon(createScaledImageIcon(PAPER, 50));
		footerPanel.add(btnPa, BorderLayout.EAST);
	}

	private static ImageIcon createScaledImageIcon(ImageIcon icon, int height) {
		Image iconScale = icon.getImage().getScaledInstance(-1, height, Image.SCALE_SMOOTH);
		return new ImageIcon(iconScale);
	}

	public static JButton setButton(JButton button) {
		int buttonSizeX = (640 - 20)/3;
		Dimension buttonDimesion = new Dimension(buttonSizeX, 50);
		button.setPreferredSize(buttonDimesion);
		Font buttonFont = new Font("ＭＳ ゴシック",Font.PLAIN,24);
		button.setFont(buttonFont);
		button.setBackground(Color.CYAN);

		GPlayer player = new GPlayer();
		button.addActionListener(player);

		return(button);
	}

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		int playerHand = 0;
		if (command.equals("グー")) playerHand = 1;
		else if (command.equals("チョキ")) playerHand = 2;
		else if (command.equals("パー"))  playerHand = 3;

		int computerHand = GComputer.decidesComputerHand();
		GVictoryOrDefeat.decisionVictoryOrDefeat(computerHand, playerHand);
	}
}
