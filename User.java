package application;


import java.util.ArrayList;

public class User {
    private String userName;
    private String email;
    private String password;
    private ArrayList<Subject> subjects;
    private int usSid=0;    //*incrementer for user specific id for folders */

    public User() {
    	userName="";
    	email="";
    	password="";
    }
    
    
    public User(String userName,String password){
        this.subjects=new ArrayList<Subject>();
        this.userName=userName;
        this.password=password;
    }
    
    public String getUserName(){
        return userName;
    } 
    public String getEamil(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public void setUserName(String userName){
        this.userName=userName;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public Subject getSubject(int id){
    	Subject subject=subjects.stream()
        .filter(fol->fol.getId()==id)
        .findFirst()
        .orElse(null);

        return subject;
    }
    public Subject getSubjectByName(String subjectName) {
    	Subject subject=subjects.stream()
    			.filter(sub->sub.getSubjectName().equals(subjectName))
    			.findFirst()
    			.orElse(null);
    	
    	return subject;
    }
    public ArrayList<Subject> getSubjects(){
        return subjects;
    }
    public void addSubject(String subjectName){
        if(subjects.stream()
        .anyMatch(sub->sub.getSubjectName().equals(subjectName))){
            System.out.println("folder name already exists");
        }else{
            Subject subject=new Subject(subjectName, ++usSid);
            subjects.add(subject);
            System.out.printf("%s is added %d",subject.getSubjectName(),subject.getId());
            System.out.println("\n");
        }
    }
//    public void removeFolder(Folder folder){
//        if(folders.contains(folder)){
//            folders.remove(folder);
//            System.out.printf("\" %s \" is removed",folder.getFolderName());
//            System.out.println("\n");
//        }else{
//            System.out.println("nt found");
//        }
//    }

//    public void printFolders(){
//        System.out.println("======================================");
//        System.out.printf("|%-3s|%-24s|%-6s|","ID","FOLDER NAME" ,"TOPICS");
//        System.out.println("\n======================================");
//
//        if(folders.size()==0){
//            System.out.println("THEIR IS NO FOLDES");
//        }else{
//            for(Folder folder : folders){
//               folder.printInfo();
//            }
//        }
//    }

}
