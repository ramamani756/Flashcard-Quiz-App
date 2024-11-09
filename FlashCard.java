package application;

public class FlashCard {
    private int id;
    private String question;
    private String answer;
    private String explanation;
    private String state="NOT ANSWERED";
    private String userAnswer="";

    public FlashCard(String question,String answer,String explanation){
        this.question=question;
        this.answer=answer;
        this.explanation=explanation;
    }
    
    public String getQuestion(){
        return question;
    }
    public String getAnswer(){
        return answer;
    }
    public int getId(){
        return id;
    }
    public String getExplanation(){
        return explanation;
    }
    public String getState(){
        return state;
    }
    public String getUserAnswer(){
        return userAnswer;
    }
    public void steUserAnswer(String userAnswer){
        this.userAnswer = userAnswer;
    }
    public void setState(String state){
        this.state = state;
    }
    public void setExplanation(String explanation){
        this.explanation = explanation;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setAnswer(String answer){
        this.answer = answer;
    }
    public void setQuestion(String question){
        this.question = question;
    }


    public void printInfo(){
        
        String trancatedQuestion=question.length()>40?question.substring(0,40)+"...":question;
        String trancatedAnswer=answer.length()>40?answer.substring(0,40)+"...":answer;
        String trancatedExplanation=explanation.length()>40?explanation.substring(0,40)+"...":explanation;

        System.out.printf("|%-4d|%-55s|",id,"Question: "+trancatedQuestion);
        System.out.printf("\n|%-4s|%-55s|"," ","Answer: "+trancatedAnswer);
        System.out.printf("\n|%-4s|%-55s|"," ","Explanation: "+trancatedExplanation);
        System.out.println("\n==============================================================");
    }

}
