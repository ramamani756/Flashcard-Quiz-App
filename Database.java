package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    private Map<String, User> users;
//    private List<Folder> folders;

    public Database() {
        users = new HashMap<>();
//        folders = new ArrayList<>();
    }

    public void addUser(User user) {
        users.put(user.getUserName(), user);
    }

    public User getUser(String username,String password) {
        if(users.containsKey(username)){
            User user = users.get(username);
            if(user != null && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
    public Map<String, User> getUsers(){
    	return users;
    }
    public boolean isUserPresent(String username) {
    	if(users.containsKey(username)) {
    		return true;
    	}else {
    		return false;
    	}
    }

//    public void addFolder(Folder folder) {
//        folders.add(folder);
//    }

//    public Folder getFolder(String folderName) {
//        for (Folder folder : folders) {
//            if (folder.getFolderName().equals(folderName)) {
//                return folder;
//            }
//        }
//        return null;
//    }

    // Add methods for managing topics, flashcards, etc. as needed
}


