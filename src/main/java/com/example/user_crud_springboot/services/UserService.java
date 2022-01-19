package com.example.user_crud_springboot.services;

import com.example.user_crud_springboot.entities.Post;
import com.example.user_crud_springboot.entities.User;
import com.example.user_crud_springboot.exceptions.UserAlreadyExistsException;
import com.example.user_crud_springboot.exceptions.UserNotFoundException;
import com.example.user_crud_springboot.repositories.PostRepository;
import com.example.user_crud_springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    //inject repository
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    //function to return all users in the database
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //function to return a user with id
    public User getUserById(int id) throws UserNotFoundException{
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("User Not Found with id:"+id);
        }
        return user.get();
    }

    //function to add a user in the database
    public User addUser(User user) throws UserAlreadyExistsException {
        User existingUser = findByUsername(user.getUsername());
        if (existingUser!=null)
            throw new UserAlreadyExistsException("User with username "+"\'"+user.getUsername()+"\' already exists.");
        return userRepository.save(user);
    }

    //function to delete user by id
    public void deleteUserById(int id) throws UserNotFoundException {
        User user = getUserById(id);
        if(user==null)
            throw new UserNotFoundException("User Not Found with id:"+id);
        deleteAllUserPostsById(id);
        userRepository.delete(user);
    }

    // function to get all posts of a user
    public List<Post> getUserPostsById(int id) throws UserNotFoundException {
        User user = getUserById(id);
        if(user==null)
            throw new UserNotFoundException("User Not Found with id:"+id);
        return user.getPosts();
    }

    //function to create a user post
    public Post createUserPost(int id,Post post) throws UserNotFoundException {
        User user = getUserById(id);
        if(user==null)
            throw new UserNotFoundException("User Not Found with id:"+id);
        post.setUser(user);
        return postRepository.save(post);
    }

    //function to delete a user's posts with a user id
    public void deleteAllUserPostsById(int id) throws UserNotFoundException {
        User user = getUserById(id);
        if(user==null)
            throw new UserNotFoundException("User Not Found with id:"+id);
        List<Post> posts = user.getPosts();
        for (Post post: posts){
            postRepository.delete(post);
        }
    }

    // function to find user by username
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

}
