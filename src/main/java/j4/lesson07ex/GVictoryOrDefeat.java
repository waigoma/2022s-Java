package j4.lesson07ex;

public class GVictoryOrDefeat {
	private static int point = 0;
	private static int count = 0;

	public static final void decisionVictoryOrDefeat(int computerHand, int playerHand) {
		int playerHandTemp = playerHand;
		playerHandTemp++;
		count++;

		if (playerHand == computerHand) {
			GPanel.headerLabel.setText("あいこかよ - Tie！（" + point + "/ " + count + "）");
		}
		else if ((playerHand == 3 && computerHand == 1)||(playerHandTemp == computerHand)) {
			point++;
			GPanel.headerLabel.setText("お前の勝ちかよ - Win！（" + point + "/ " + count + "）");
		}
		else {
			point=0;
			GPanel.headerLabel.setText("お前の負けかよ - Loss！（" + point + "/ " + count + "）");
		}
	}
}
