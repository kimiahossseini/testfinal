package Q1bankquestions;

public class multiplechoice implements Question {

	private String question;
	private String[] options;
	private int correctoption;
	

	public multiplechoice(String question, String[] options, int correctoption) {
		super();
		this.question = question;
		this.options = options;
		this.correctoption = correctoption;
	}

	@Override
	public String getquestion() {
		return question;
	}

	@Override
	public int getcorrectanswer() {
		return correctoption;
	}

	@Override
	public String[] getoptions() {
		return options;
	}


}
