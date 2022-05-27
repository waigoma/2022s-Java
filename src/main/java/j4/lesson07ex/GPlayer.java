package j4.lesson07ex;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GPlayer implements ActionListener {
	public static void createButton(JPanel footerPanel) {
		//ボタンを表示
		JButton btnGu = new JButton("グー");
		btnGu = setButton(btnGu);
		footerPanel.add(btnGu, BorderLayout.WEST);

		JButton btnChoki = new JButton("チョキ");
		btnChoki = setButton(btnChoki);
		footerPanel.add(btnChoki, BorderLayout.CENTER);

		JButton btnPa = new JButton("パー");
		btnPa = setButton(btnPa);
		footerPanel.add(btnPa, BorderLayout.EAST);
	}
	public static JButton setButton(JButton button) {
		int buttonSizeX = (640 - 20)/3;
		Dimension buttonDimesion = new Dimension(buttonSizeX, 50);
		button.setPreferredSize(buttonDimesion);
		Font buttonFont = new Font("ＭＳ ゴシック",Font.PLAIN,24);
		button.setFont(buttonFont);

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
