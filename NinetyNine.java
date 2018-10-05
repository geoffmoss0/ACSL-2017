/*
 * Geoffrey Moss
 * Grade 12
 * Wheeler School
 * Senior Division
 * Contest 1
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;
public class NinetyNine {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new FileReader("C:\\Users\\gb_mo\\Desktop\\ACSLinput.txt")); //reads input from file, can be changed to read from typed input
		
		//Setup--------------------------------------------------
		String[] hand1 = new String[5];
		String[] hand2 = new String[5];
		String start = in.nextLine();
		start = start.replaceAll(",",""); //remove any commas if in comma-separated list format
		start = start.replaceAll(" ", ""); //remove any spaces
		for (int i = 0; i < 5; i++) {hand1[i] = start.substring(i, i+1);} //assign the first five cards to player 1
		for (int i = 0; i < 5; i++) {hand2[i] = start.substring(i+5, i+6);} //assign the last five cards to player 2
		Player player1 = new Player(hand1);
		Player player2 = new Player(hand2);
		int won; 
		//-------------------------------------------------------
		
		while (in.hasNextLine()) { //runs for all test cases
			String turn = in.nextLine(); //list of cards that will be drawn
			won = 0;                                                                 //reset the winner
			turn += "0";                                                             //padding the end of the list of cards drawn in case the last card must be drawn
			player1.setHand(hand1);                                                  //set or reset players 1 and 2's hands
			player2.setHand(hand2);
			int scoreStart = Integer.parseInt(turn.substring(0, turn.indexOf(","))); //find the starting score (works even when starting score is not two digits
			turn = turn.substring(turn.indexOf(",") + 1);                            //trim the starting score off the string
			turn = turn.replaceAll(",", "");                                         //remove all commas
			turn = turn.replaceAll(" ", "");                                         //remove all spaces
			Player.setScore(scoreStart);                                             //set score to the starting score
			
			
			while (Player.getScore() < 100) { //game loop, runs until at least one player is above 100 points
				
				player1.play();                                 //removes the middle card from the hand, calls the score method
				if (Player.getScore() >= 100) {won = 2; break;} //check to see if game is won
				player1.draw(turn.substring(0, 1));             //draw the next card for player 1
				turn = turn.substring(1);                       //trim the card drawn by player 1 off the list of drawn cards
				
				//repeat for player 2
				player2.play();
				if (Player.getScore() >= 100) {won = 1; break;}
				player2.draw(turn.substring(0, 1));
				turn = turn.substring(1);
			}
			
			if (won == 1) {
				System.out.println(Player.getScore() + ", Player #1");
			}
			else {
				System.out.println(Player.getScore() + ", Player #2");
			}
		}
	}
}

class Player{
	public static int score;
	private String[] hand;
	private int[] intHand;
	
	public Player(String[] hand_) { //Player constructor
		hand = hand_;
		intHand = new int[5];
	}
	
	public static int getScore() {
		return score;
	}
	
	public static void setScore(int score_) {
		score = score_;
	}
	
	public void setHand(String[] hand_) {
		hand = hand_;
		fixHand();
	}
	
	public void fixHand() { //create intHand based on the contents of hand (intHand can be sorted, which makes playing the median card much easier)
		for (int i = 0; i < hand.length; i++) {
			if (hand[i].equals("T")) {intHand[i] = 10;} 
			else if (hand[i].equals("J")) {intHand[i] = 11;} 
			else if (hand[i].equals("Q")) {intHand[i] = 12;}
			else if (hand[i].equals("K")) {intHand[i] = 13;}
			else if (hand[i].equals("A")) {intHand[i] = 14;}
			else {intHand[i] = Integer.parseInt(hand[i]);}
		}
		Arrays.sort(intHand);
	}
	
	public void play() { //remove the median card from the player's hand and replace it with a zero, call the score method
		score(intHand[2]);
		intHand[2] = 0;
	}
	
	public void score(int card) { //Calculate the score when playing a card
		if (!(card == 9)) {
			if (card == 10) { //10 loop runs subtracts 10 from score, check for lower values because value of score is decreasing
				for (int i = -10; i < 0; i++) {
					score--;
					if (score == 33 || score == 55 || score == 77) score += 5;
				}
			}
			else if (card == 7) {
				if (score < 93) { //7 cannot put total over 99
					for (int i = 0; i < card; i++) {
						score++;
						if (score == 34 || score == 56 || score == 78) score += 5; //check for higher values because score is increasing
					}
				}
				else { //no need to check for going over point borders because score is at least 93
					score++;
				}
			}
			else {
				for (int i = 0; i < card; i++) {
					score++;
					if (score == 34 || score == 56 || score == 78) score += 5;
				}
			}
		}
	}
	
	public void draw(String card) { // add a card to the player's hand where the zero was (always index 2) and sort intHand
		if (card.equals("A")) {intHand[2] = 14;}
		else if(card.equals("K")) {intHand[2] = 13;}
		else if(card.equals("Q")) {intHand[2] = 12;}
		else if(card.equals("J")) {intHand[2] = 11;}
		else if(card.equals("T")) {intHand[2] = 10;}
		else {intHand[2] = Integer.parseInt(card);}
		Arrays.sort(intHand);
	}
}
