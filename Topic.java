package application;

import java.util.ArrayList;
import java.util.Scanner;

public class Topic {
    private String topicName;
    private int id;
    // private int highestScore=0;
    private int lastFlashId=0; //*last assigned id for flash card in order to uniquly identify them */
    private ArrayList<FlashCard> flashCards;

    static Scanner input=new Scanner(System.in);
    public Topic(String topicName,int id){
        flashCards=new ArrayList<FlashCard>();
        this.topicName=topicName;
        this.id=id;
    }
    public String getTopicName(){
        return topicName;
    }
    public int getId(){
        return id;
    }
    public void setTopicName(String topicName){
        this.topicName=topicName;
    }
    public ArrayList<FlashCard> getFlashCards(){
        return flashCards;
    }
    public void addFlashCard(FlashCard flashCard){
        lastFlashId++;
        flashCard.setId(lastFlashId);
        flashCards.add(flashCard);
    }
    public void printInfo(){
        System.out.printf("|%-4d|%-24s|%-5d|",id,topicName, flashCards.size());
        System.out.println("\n=====================================");
    }
    public void printFlashes(){
        System.out.println("==============================================================");
        System.out.printf("|%-4s|%-55s|","ID","FLASH CARDS");
        System.out.println("\n==============================================================");
        if(flashCards.size()>0){
            for(FlashCard card : flashCards){
                String trancatedQuestion=card.getQuestion().length()>40?card.getQuestion().substring(0,40)+"...":card.getQuestion();
                System.out.printf("|%-4d|%-55s|",card.getId(),"Question: "+trancatedQuestion);
                System.out.println("\n==============================================================");
            }

        }else{
            System.out.println("Empty Topic :)");
        }

    }
//    public void practice(){
//        if(flashCards.size()==0){
//            System.out.println("Empty Topic");
//            return;
//        }
//        Utility.clearConsole();
//        System.out.println("practicing ____"+topicName+"____");
//        System.out.println("1.Observe the answer upon completing each question.");
//        System.out.println("2.Observe the answers upon completing all questions.");
//        System.out.print("enter your choice: ");
//        int choice=input.nextInt();
//        input.nextLine();
//        if(choice!=1 && choice!=2){
//            System.out.println("your choice must be 1 or 2.");
//            System.out.println("press enter to continue...");
//            input.nextLine();
//            return;
//        }
//        int queNum=0;
//        int score=0;
//        String userAnswer;
//        boolean correct=false;
//        for(FlashCard card : flashCards){
//            Utility.clearConsole();
//            queNum++;
//            System.out.print(queNum+", "+card.getQuestion()+"\nYOUR ANSWER: ");
//            userAnswer=input.nextLine();
//            card.steUserAnswer(userAnswer);
//            correct=Utility.checkAnswer(userAnswer, card.getAnswer(), 0.7);
//            if(correct){
//                score++;
//                if(choice==1){
//                    System.out.println(Utility.GREEN+"CORRECT ANSWER "+Utility.GREEN);
//                    System.out.println(Utility.RESET+"CURRENT SCORE: " + score+Utility.RESET);
//                    System.out.println("ANSWER: " + card.getAnswer());
//                    System.out.println("EXPLANATION: "+card.getExplanation());
//                    
//                }else{
//                    card.setState(Utility.GREEN+"CORRECT ANSWER "+Utility.GREEN);
//
//                }
//            }else{
//                if(choice == 1){
//                    System.out.println(Utility.RED+"INCORRECT ANSWER"+Utility.RED);
//                    System.out.println(Utility.RESET+"CURRENT SCORE: " + score+Utility.RESET);
//                    System.out.println("CORRECT ANSWER IS: " + card.getAnswer());
//                    System.out.println("EXPLANATION: "+card.getExplanation());
//                }else{
//                    card.setState(Utility.RED+"INCORRECT ANSWER"+Utility.RED);
//
//                }
//            }
//            
//            System.out.println("press enter to continue...");
//            input.nextLine();
//        }
//        Utility.clearConsole();
//        if(choice ==2){
//            
//            System.out.println("======================== answers with explanations=============================");
//            for(FlashCard card : flashCards){
//                System.out.println("QUESTION: "+card.getQuestion());
//                System.out.println("\tYOUR ANSWER: "+card.getUserAnswer());
//                System.out.println(card.getState());
//                System.out.println(Utility.RESET+"\tTHE CORRECT ANSWER IS: " + card.getAnswer()+Utility.RESET);
//                System.out.println("\tEXPLANATION: "+card.getExplanation());
//                card.setState("NOT ANSWERED");
//                card.steUserAnswer(" ");
//            }
//
//        }
//        System.out.println("\n\nyou are already practice "+queNum+" questions");
//        System.out.println("your total score is: " +score+"/"+queNum);
//
//        System.out.println("press enter to continue...");
//        input.nextLine();
//    }
}

