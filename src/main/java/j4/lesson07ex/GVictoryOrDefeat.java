package j4.lesson07ex;

public class GVictoryOrDefeat {
	private static int point = 0;
	public static final void decisionVictoryOrDefeat(int computerHand,int playerHand) {
		int playerHandTemp = playerHand;
		playerHandTemp++;

		if (playerHand == computerHand) {
			GPanel.headerLabel.setText("あいこかよ！（" + point + "）");
		}
		else if ((playerHand == 3 && computerHand == 1)||(playerHandTemp == computerHand)) {
			point+=1;
			GPanel.headerLabel.setText("お前の勝ちかよ！（" + point + "）");
		}
		else {
			point=0;
			GPanel.headerLabel.setText("お前の負けかよ！（" + point + "）");
		}
	}
}
