package com.company;

import java.util.Scanner;

class N_Back {
    static String instruction =
            "This is the learning session and our will see sequence of  10 random letters. Your task will be click 'Y' " +
            "if current letter is the same than the second letter before.\n" +
            "In other case you have to click 'N'. On the end You will see your score.\n" +
            "Do not care of the result of this session. The same task with different letter will be repeated.\n" +
            "\n" +
            "When your will be ready, click ENTER.\n" +
            "\n" +
            "GOOD LUCK :)\"";
    private final String[] listOfLetters = {"d","b","d","w","k"};
    private final String answerPositive = "Y";
    private final String answerNegative = "N";
    private final String optionsToChoose = "'Y' or 'N'?";
    private final String whiteSpace = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
    private static final int delay = 1000;
    private int numberOfCorrectAnswers = 0;

    public void showInstruction(){
        System.out.println(instruction);
    }

    public void showListWithRandomLetter() throws InterruptedException {
        String participantAnswer;
        Scanner scanner = new Scanner(System.in);

        for(int numberOfLetter = 0; numberOfLetter < listOfLetters.length - 1; numberOfLetter++){
            System.out.println(listOfLetters[numberOfLetter]);
            Thread.sleep(delay);
            System.out.println(whiteSpace);
            if(numberOfLetter < 2){
                continue;
            }
            System.out.println(optionsToChoose);
            participantAnswer = scanner.nextLine();
            Boolean lettersEqual = listOfLetters[numberOfLetter].equals(listOfLetters[numberOfLetter - 2]);
            String expectedAnswer = lettersEqual ? answerPositive : answerNegative;
            if(expectedAnswer.equals(participantAnswer)){
                numberOfCorrectAnswers++;
            }
            System.out.println(whiteSpace);
        }

    }

    public void showTheResult(){
        String theEnding = "Well done! You have " + numberOfCorrectAnswers + " correct answers from 5.";
        System.out.println(theEnding);
    }
}

public class Main{
    public  static void main(String[] args) throws InterruptedException {
        N_Back session1 = new N_Back();
        session1.showInstruction();
        session1.showListWithRandomLetter();
        session1.showTheResult();
    }
}


//TODO: 1 level - 2n-back
//TODO: Instruction for participants.
//TODO: The task to do: Show the list of letters with 1s. breaks.
/*
1). instruction:
TODO - "This is the learning session and our will see sequence of  10 random letters. Your task will be click 'Y' if current letter is the same than the second letter before.
TODO    In other case you have to click 'N'. On the end You will see your score.
TODO    Do not care of the result of this session. The same task with different letter will be repeated.

TODO    When your will be ready, click ENTER.

TODO    GOOD LUCK :)"

2). prepare list with letters:
    TODO - 10 random letters

3). show each of the letter:
    TODO - find way to collect the correct and wrong reactions

4). wait for reaction: click 'Y' or 'N'
    TODO - wait 1second
    - Type of reactions:
        TODO - correct reaction: if 2 letters ago was the same letter what is now, participant should click Y
        TODO - correct reaction: if participant clicks N when the letter is not the same than 2 letters ago
        - wrong reaction 1: if participant won't click ENTER when 2 letters ago was the same letter what is now
        - wrong reaction 2: if participant click ENTER when the letter is not the same than 2 letters ago

5). sum correct reactions:
    TODO - put correct reaction to the list (correct pairs of letter or only letter when was reaction correct)
    TODO - put wrong reaction (the same thing to do what with correct reaction)

6). show results: how many good and bad reactions:
    TODO - "Your results: corrects(sth) / wrong(sth)."
 */
