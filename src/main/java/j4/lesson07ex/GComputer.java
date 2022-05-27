package j4.lesson07ex;

import java.util.Random;

public class GComputer {
	public static int decidesComputerHand() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		Random random = new Random();
		int rnd = random.nextInt(3) + 1;
		if (rnd == 1) GPanel.contentsLabel.setIcon(GPlayer.ROCK);
		else if (rnd == 2) GPanel.contentsLabel.setIcon(GPlayer.SCISSORS);
		else GPanel.contentsLabel.setIcon(GPlayer.PAPER);
		return (rnd);
	}
}
