package j4.lesson07ex;

import java.util.Random;

public class GComputer {
	public static int decidesComputerHand() {
		Random random = new Random();
		int rnd = random.nextInt(3)+1;
		if (rnd == 1) GPanel.contentsLabel.setText("グー");
		else if (rnd == 2) GPanel.contentsLabel.setText("チョキ");
		else if (rnd == 3) GPanel.contentsLabel.setText("パー");
		return (rnd);
	}
}
