package application;
import java.util.ArrayList;

public class Subject {
    private String subjectName;
    private  int lastTopicId=0;  //*lastly assigned topic id inorder to identify topics uniquely */
    private int id;    //* user specific id for folders; their can be the same id for different users folder*/
    private ArrayList<Topic> topics =new ArrayList<Topic>();

    public Subject(String subjectName,int id){
        this.subjectName=subjectName;
        this.id=id;
    }

    public int getId(){
        return this.id;
    }

    public String getSubjectName(){
        return subjectName;
    }
    public ArrayList<Topic> getTopics(){
        return topics;

    }
    public Topic getTopicByName(String topicName) {
    	Topic topic = topics.stream()
    			.filter(top->top.getTopicName().equals(topicName))
    			.findFirst()
    			.orElse(null);
    	
    	return topic;
    }
    public void setSubjectNmae(String subjectName){
        this.subjectName=subjectName;
    }
    public int getTopicSize(){
        return topics.size();
    }
    public void addTopic(String topicName){
        lastTopicId++;
        Topic topic = new Topic(topicName,lastTopicId);
        topics.add(topic);
    }
    public void printInfo(){
        System.out.printf("|%-3d|%-24s|%-6d|",id, subjectName,getTopicSize());
        System.out.println("\n=====================================");

    }
//    public void printTopics(){
//        System.out.println("=====================================");
//        System.out.printf("|%-4s|%-24s|%-5s|","id","TOPIC NAME" ,"cards");
//        System.out.println("\n=====================================");
//
//        if(topics.size()==0){
//            System.out.println("EMPTY FOLDER");
//            return;
//        }
//        for(Topic topic : topics){
//            topic.printInfo();
//        }
//        
//    }
}
