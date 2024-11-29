package Q1bankquestions;

public class truefalsequestion implements Question {
	private String question;
	private boolean correctoption;

	
	

	public truefalsequestion(String question, boolean correctoption) {
		super();
		this.question = question;
		this.correctoption = correctoption;
	
	}

	@Override
	public String getquestion() {
		
		return question;
	}

	@Override
	public int getcorrectanswer() {
		
		return correctoption?0:1;
	}

	@Override
	public String[] getoptions() {
		
		return new String[]{"True", "False"} ;
	}



}
