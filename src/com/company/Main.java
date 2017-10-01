package com.company;

import java.util.*;


class N_Back {
    private int n;
    private int size;
    private int result;
    private List<String> listOfLetters;
    private final String answerPositive = "Y";
    private final String answerNegative = "N";
    private final String quit = "Q";
    private final String optionsToChoose = "'Y' or 'N'?";
    private final String whiteSpace= String.join("", Collections.nCopies(40, "\n"));;
    private static final int delay = 2000;
    private int numberOfCorrectAnswers = 0;
    private String instruction =
            whiteSpace+"This is the learning session of the 'n-back' test, where you will see sequence of %s random letters." + " " +
                    "After FIRST  %s letters, your task will be to press 'Y' " +
                    "if current letter is the same as a letter %s questions before.\n" +
                    "In other case you have to press 'N'. In the end You will see your score.\n" +
                    "Don't mind the result of this session. The same task with different letter will be repeated.\n" +
                    "\n" +
                    "When your will be ready, click ENTER.\n" +
                    "\n" +
                    "GOOD LUCK :)\"";

    public N_Back(DataSource data){
        this.n = data.getN();
        this.listOfLetters = data.getListOfLetters();
        this.size = listOfLetters.size();
        this.result = this.size - n;

    }

    public void showInstruction(){
        System.out.println(String.format(instruction, size, n + 1, n));
        Scanner startTest = new Scanner(System.in);
        startTest.nextLine();
        System.out.println(whiteSpace);
    }

    public void runTest() throws InterruptedException {
        String participantAnswer;
        Scanner scanner = new Scanner(System.in);
        for(int numberOfLetter = 0; numberOfLetter < listOfLetters.size() - 1; numberOfLetter++){
            System.out.println("                 " + listOfLetters.get(numberOfLetter));
            Thread.sleep(delay);
            System.out.println(whiteSpace);
            if(numberOfLetter < n){
                continue;
            }
            System.out.println(optionsToChoose);
            participantAnswer = scanner.nextLine();
            Boolean lettersEqual = listOfLetters.get(numberOfLetter).equals(listOfLetters.get(numberOfLetter - n));
            String expectedAnswer = lettersEqual ? answerPositive : answerNegative;
            if(expectedAnswer.equalsIgnoreCase(participantAnswer)){
                numberOfCorrectAnswers++;
            } else if (participantAnswer.equalsIgnoreCase(quit)){
                break;
            }
            System.out.println(whiteSpace);
        }
    }

    public void showTheResult() throws InterruptedException {
        String theEnding = "Well done! You have %s correct answers from %s.";
        System.out.println(String.format(theEnding, numberOfCorrectAnswers, result));
        Thread.sleep(delay);
    }
}


interface DataSource {
    public int getN();
    public List<String> getListOfLetters();
}

class FixedList implements DataSource{
    private int n;
    private List<String> list;

    public FixedList(int n, List<String> list){
        this.n = n;
        this.list = list;
    }
    @Override
    public int getN() {
        return n;
    }

    @Override
    public List<String> getListOfLetters() {
        return Arrays.asList("v", "z", "n", "z", "k", "n", "p", "w", "k", "p", "k");
    }
}


class RandomListGenerator implements DataSource {
    private int n;
    private int size;
    private double probability;

    public RandomListGenerator(int n, int size, double probability){
        this.n = n;
        this.size = size;
        this.probability = probability;

    }

    public String getRandomLetter(){
        Random random = new Random();
        int letter = random.nextInt(26) + (byte)'a';
        return String.valueOf((char)letter);
    }

    public List<String> getListOfLetters() {
        List<String> listOfRandomLetter = new ArrayList<>();
        for (int i = 0; i <= size; i++){
            double randomNumber = generateRandomNumber();
            if(randomNumber <= probability && i >= n){
                listOfRandomLetter.add(listOfRandomLetter.get(i - n));
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

    @Override
    public int getN() {
        return n;
    }
}


public class Main{
    public  static void main(String[] args) throws InterruptedException {
        DataSource dataSource = new FixedList(2, Arrays.asList("v", "z", "n", "z", "k", "n", "p", "w", "k", "p", "k"));
        N_Back session1 = new N_Back(dataSource);
        session1.showInstruction();
        session1.runTest();
        session1.showTheResult();

        RandomListGenerator generator = new RandomListGenerator(3, 13, 0.3);
        N_Back session2 = new N_Back(generator);
        session2.showInstruction();
        session2.runTest();
        session2.showTheResult();
        RandomListGenerator generator1 = new RandomListGenerator(4, 13, 0.3);
        N_Back session3 = new N_Back(generator1);
        session3.showInstruction();
        session3.runTest();
        session3.showTheResult();
    }
}
