package Q1bankquestions;

import java.util.Scanner;

public abstract class basequiz implements Quiz {
	protected abstract Question[] getQuestionS();


	@Override
	public void Startquiz() {
		//*****************************
		Scanner scanner=new Scanner(System.in);
		Question[] questions=getQuestionS();
		int score=0;
		for (Question question : questions) {
			  System.out.println(question.getquestion());
			  String[] options=question.getoptions();
		  for (int i = 0; i < options.length; i++) {
				System.out.println((i+1)+". "+ options[i]);
			}
		    System.out.print("Your answer (1-" + options.length + "): ");
		    int answer=scanner.nextInt() -1;
			//*************checking answer*****************
			if(answer==question.getcorrectanswer()) {
				System.out.println("Correct!");
				score++;
			}else {System.out.println("you enter worong option"+ " correct answer is "+ question.getcorrectanswer());}
			   
			 System.out.println();
		}
		System.err.println("your score is "+ score);
		scanner.close();

	}


}
