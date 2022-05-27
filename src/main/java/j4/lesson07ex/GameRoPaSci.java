package j4.lesson07ex;

import javax.swing.JFrame;

public class GameRoPaSci {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ウィンドウ設定
		JFrame frame = new JFrame("じゃんけんゲーム");
		frame.setSize(640, 480);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		GPanel.createPanel(frame);

		//ウィンドウ表示
		frame.setVisible(true);
	}
}
