package com.company;

import java.util.*;

class N_Back {
    //private int parameter;
    private int size = 5;
    private final String answerPositive = "Y";
    private final String answerNegative = "N";
    private final String quit = "Q";
    private final String optionsToChoose = "'Y' or 'N'?";
    private final String whiteSpace= String.join("", Collections.nCopies(40, "\n"));;
    private static final int delay = 2000;
    private int numberOfCorrectAnswers = 0;
    private double probability = 0.3;
    private int result = size - 2;
    private String instruction =
            whiteSpace+"This is the learning session of the 'n-back' test, where you will see sequence of "+size+" random letters." + " " +
                    "After FIRST 3 letters, your task will be to press 'Y' " +
                    "if current letter is the same as the second letter before.\n" +
                    "In other case you have to press 'N'. In the end You will see your score.\n" +
                    "Don't mind the result of this session. The same task with different letter will be repeated.\n" +
                    "\n" +
                    "When your will be ready, click ENTER.\n" +
                    "\n" +
                    "GOOD LUCK :)\"";

    public void showInstruction(){
        System.out.println(instruction);
        Scanner startTest = new Scanner(System.in);
        startTest.nextLine();
        System.out.println(whiteSpace);
    }

    public void showListWithRandomLetter(int parameter) throws InterruptedException {
        String participantAnswer;
        Scanner scanner = new Scanner(System.in);
        List<String> listOfLetters =  prepareListOfRandomLetters(size, probability, parameter);

        for(int numberOfLetter = 0; numberOfLetter < listOfLetters.size() - 1; numberOfLetter++){
            System.out.println("                 " + listOfLetters.get(numberOfLetter));
            Thread.sleep(delay);
            System.out.println(whiteSpace);
            if(numberOfLetter < parameter){
                continue;
            }
            System.out.println(optionsToChoose);
            participantAnswer = scanner.nextLine();
            Boolean lettersEqual = listOfLetters.get(numberOfLetter).equals(listOfLetters.get(numberOfLetter - parameter));
            String expectedAnswer = lettersEqual ? answerPositive : answerNegative;
            if(expectedAnswer.equalsIgnoreCase(participantAnswer)){
                numberOfCorrectAnswers++;
            }
            else if (participantAnswer.equalsIgnoreCase(quit)){
                break;
            }
            System.out.println(whiteSpace);
        }

    }

    public String getRandomLetter(){
        Random random = new Random();
        int letter = random.nextInt(26) + (byte)'a';
        return String.valueOf((char)letter);
    }

    public List<String> prepareListOfRandomLetters(int size, double probability, int parameter) {
        List<String> listOfRandomLetter = new ArrayList<>();
        for (int i = 0; i <= size; i++){
            double randomNumber = generateRandomNumber();
            if(randomNumber <= probability && i >= parameter){
                listOfRandomLetter.add(listOfRandomLetter.get(i - parameter));
            }else{
                listOfRandomLetter.add(getRandomLetter());
            }
        }
        return listOfRandomLetter;
    }

    private double generateRandomNumber() {
        Random random = new Random();
        return random.nextDouble();
    }

    public void showTheResult() throws InterruptedException {
        String theEnding = "Well done! You have " + numberOfCorrectAnswers + " correct answers from "+result+".";
        System.out.println(theEnding);
        Thread.sleep(delay);
    }
}

public class Main{
    public  static void main(String[] args) throws InterruptedException {
        N_Back session1 = new N_Back();
        session1.showInstruction();
        session1.showListWithRandomLetter(2);
        session1.showTheResult();
        N_Back session2 = new N_Back();
        session2.showInstruction();
        session2.showListWithRandomLetter(3);
        session2.showTheResult();
        N_Back session3 = new N_Back();
        session3.showInstruction();
        session3.showListWithRandomLetter(4);
        session3.showTheResult();
    }
}
