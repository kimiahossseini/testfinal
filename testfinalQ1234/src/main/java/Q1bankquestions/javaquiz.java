package Q1bankquestions;



public class javaquiz extends basequiz {

	@Override
	protected Question[] getQuestionS() {
		return new Question[]{
	            new multiplechoice("What is the size of an int in Java?", new String[]{"16 bits", "32 bits", "64 bits"}, 1),
	            new truefalsequestion("Java is a platform-independent language.", true),
	            new multiplechoice("Which of the following is not a Java keyword?", new String[]{"class", "method", "static"}, 1)
	        };
	}

}
