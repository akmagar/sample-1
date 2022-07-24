package com.example.demo.services;

import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private HttpServletRequest req;

    @Autowired
    private UserRepository userDAO;

    public List<User> findAll() {
        return userDAO.findAll();
    }

    public User findById(int id) {
        return userDAO.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("No user with id = %d", id)));
    }

    public User registerUser(User u) {
        if(u.getId() != 0) {
            // This should be a custom exception class instead
            throw new RuntimeException("User ID must be zero to create a new User");
        }

        userDAO.save(u); // Modify the user with the new ID

        return u;
    }

    public User update(User u) {
        if(!userDAO.existsById(u.getId())) {
            throw new RuntimeException("User must already exist to update");
        }

        userDAO.save(u);

        HttpSession session = req.getSession(false); // They must have already been logged in, because we had our guard method

        User sessionUser = (User) session.getAttribute("currentUser");

        // If a User updated themselves, update the information in the session
        if(sessionUser.getId() == u.getId()) {
            session.setAttribute("currentUser", u);
        }

        return u;
    }

    public boolean delete(int id) {
        if(!userDAO.existsById(id)) {
            return false;
        }

        userDAO.deleteById(id);

        return true;
    }

    public User login(String username, String password) {
        User exists = userDAO.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new UserNotFoundException(String.format("No User with username = %s", username)));

        HttpSession session = req.getSession();
        session.setAttribute("currentUser", exists);

        return exists;
    }

    public void logout() {

        HttpSession session = req.getSession(false);

        if(session == null) {
            // No one was logged in

            return;
        }

        session.invalidate();
    }
}
