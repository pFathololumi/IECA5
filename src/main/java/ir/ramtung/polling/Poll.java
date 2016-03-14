package ir.ramtung.polling;

import java.util.Arrays;

public class Poll {
	private String code;
	private String subject;
	private String[] choices;
	private int[] voteCounts;
	
	public Poll(String subject, String... choices) {
		if (subject == null || subject.equals("") ||
			choices == null || choices.length == 0)
			throw new IllegalArgumentException();
		for (String choice: choices)
			if (choice == null || choice.equals(""))
				throw new IllegalArgumentException();
		this.subject = subject;
		this.choices = choices;
		this.code = null;
		this.voteCounts = new int[choices.length];
	}

	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append(subject + "\n");
		for (String choice: choices)
			output.append("\t[] " + choice + "\n");
		return output.toString();
	}

	public String getCode() {
		return code;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public String[] getChoices() {
		return choices;
	}
	
	public void setCode(String code) {
		if (code == null || code.equals(""))
			throw new IllegalArgumentException();
		this.code = code;
	}
	
	public void voteFor(int choiceIdx) {
		voteCounts[choiceIdx]++;
	}
	
	public double[] getVotePercents() {
		int totalVotes = 0;
		for (int vc : voteCounts)
			totalVotes += vc;
		double[] votePercents = new double[voteCounts.length];
		if (totalVotes == 0)
			return votePercents;
		for (int i = 0; i < votePercents.length; i++)
			votePercents[i] = 100.0 * voteCounts[i] / totalVotes;
		return votePercents;
	}
	
	
}
