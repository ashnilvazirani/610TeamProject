package myPLS.services;

import java.util.Map;
import java.util.List;
import myPLS.DAO.RITUserDAO;
import myPLS.DAO.UserDAO;
import myPLS.beans.User;

public class UserService {
    RITUserDAO ritUserDAO;
    private static UserDAO userDAO;
    public UserService(){
        ritUserDAO = new RITUserDAO();
        userDAO = new UserDAO();
    }
    public List<User> getAllUsers(){
        return userDAO.getUsers();
    }
    public User getUserByID(int userID){
        return userDAO.getUserByID(userID);
    }
}
