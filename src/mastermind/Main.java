package mastermind;

public class Main {

	public static int COLOR_NUM = 6;
	public static int POS_NUM = 4;
	public static int game_num = 1;
	public static boolean viewPossible = false;
	public static boolean player1 = false;
	public static boolean player2 = true;
	public static boolean guessRater = false;

	public static void main(String[] args) {
		GraphicalUserInterface gui = GraphicalUserInterface.getInstance();
		CommandLineInterface cli = CommandLineInterface.getInstance();
		if (args.length == 1 && "menu".equals(args[0])) {
			cli.menu();
		}
		Sage sage = new Sage(COLOR_NUM, POS_NUM);
		Statistics stats = new Statistics();
		while (stats.getGames() < game_num) {
			Wizard wiz = new Wizard(sage);
			Player1 p1 = player1 ? new HumanPlayer1(cli) : new ComputerPlayer1();
			Player2 p2 = player2 ? new HumanPlayer2(cli) : new ComputerPlayer2(new DonaldKnuth(wiz));
			cli.directions();
			Code guess;
			Response response = null;
			int turn = 0;
			gui.clear();
			while (!win(response)) {
				cli.turn(++turn);
				if (viewPossible) {
					gui.unhidePossible();
					gui.write(wiz.getPossible());
				}
				guess = p2.guess();
//				cli.guess(guess);
				response = p1.respond(guess);
//				cli.response(response);
				gui.appendSummary(guess.toString(), response);
				if (guessRater || !player2 || viewPossible) {
					wiz.handleResponse(guess, response);
				}
				if (guessRater) {
					cli.guessRater(wiz.getRemovedCount(turn), wiz.getNumPossible(turn - 1), wiz.getNumPossible(turn));
				}
			}
			cli.answer(p1.getAnswer());
			cli.win();
			stats.addStats(turn);
			cli.stats(stats.getMax(), stats.getMin(), stats.getAvg());
			if (stats.getGames() == game_num) {
				cli.tryAgain();
			}
		}
		System.exit(0);
	}

	private static boolean win(Response response) {
		if (response == null) {
			return false;
		}
//		if (response.length() == POS_NUM) {
//			for (int i = 0; i < response.length(); i++) {
//				if (response.charAt(i) != 'B') {
//					return false;
//				}
//			}
//			return true;
//		}
		return response.win();
	}

}
