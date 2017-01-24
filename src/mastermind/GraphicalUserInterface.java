package mastermind;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GraphicalUserInterface {

	private final JFrame frame;
	private final JTextArea hintTextArea;
	private final JScrollPane hintScroll;
	private final JTextArea historyTextArea;
	private final JScrollPane historyScroll;
	private final Font font;
	private static GraphicalUserInterface instance;
	private StringBuilder historyText;
	private final JPanel mainPanel;

	private GraphicalUserInterface() {

		// instantiate
		historyText = new StringBuilder();
		frame = new JFrame("Mastermind");
		font = new Font(Font.MONOSPACED, Font.PLAIN, 14);
		hintTextArea = new JTextArea();
		hintScroll = new JScrollPane();
		historyTextArea = new JTextArea();
		historyScroll = new JScrollPane();
		mainPanel = new JPanel();

		// attributes
		historyTextArea.setFont(font);
		hintTextArea.setFont(font);
		hintTextArea.setColumns(10);
		hintScroll.setVisible(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel.setLayout(new BorderLayout());

		// add
		frame.add(BorderLayout.CENTER, historyScroll);
		frame.add(BorderLayout.SOUTH, hintScroll);
		hintScroll.setViewportView(hintTextArea);
		historyScroll.setViewportView(historyTextArea);

		// make visible
		frame.setSize(500, 500);
		frame.setVisible(true);

	}

	public static GraphicalUserInterface getInstance() {
		if (instance == null) {
			instance = new GraphicalUserInterface();
		}
		return instance;
	}

	public void write(List<Code> x) {
		hintTextArea.setText("");
		for (Code i : x) {
			hintTextArea.append(i.toString() + "\n");
		}
	}

	public void clear() {
		historyText = new StringBuilder();
		historyTextArea.setText("");
	}

	public void unhidePossible() {
		hintScroll.setVisible(true);
		frame.revalidate();
	}

	public void appendSummary(String guess, Response response) {
		historyText.append(guess).append("\t").append(response).append("\n");
		historyTextArea.setText(historyText.toString());
	}

}
