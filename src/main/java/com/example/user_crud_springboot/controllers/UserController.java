package com.example.user_crud_springboot.controllers;
import com.example.user_crud_springboot.dto.PostInputDTO;
import com.example.user_crud_springboot.dto.PostOutputDTO;
import com.example.user_crud_springboot.dto.UserInputDTO;
import com.example.user_crud_springboot.dto.UserOutputDTO;
import com.example.user_crud_springboot.entities.Post;
import com.example.user_crud_springboot.entities.User;
import com.example.user_crud_springboot.exceptions.UserAlreadyExistsException;
import com.example.user_crud_springboot.exceptions.UserNotFoundException;
import com.example.user_crud_springboot.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    //inject user service
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/users")
    public List<UserOutputDTO> getAllUsers(){
        List<User> usersList= userService.getAllUsers();
        List<UserOutputDTO> userOutputDTOList = new ArrayList<>();
        for(User user : usersList){
            userOutputDTOList.add(modelMapper.map(user,UserOutputDTO.class));
        }
        return userOutputDTOList;
    }

    @PostMapping("/users")
    public UserOutputDTO addUser(@Valid @RequestBody UserInputDTO userInputDTO) throws UserAlreadyExistsException {
        User user = modelMapper.map(userInputDTO,User.class);

        User returnedUser= userService.addUser(user);
        UserOutputDTO userOutputDTO = modelMapper.map(returnedUser,UserOutputDTO.class);
        return userOutputDTO;
    }

    @GetMapping("/users/{id}")
    public UserOutputDTO getUserById(@PathVariable("id") int id) throws UserNotFoundException {
        User user= userService.getUserById(id);
        UserOutputDTO userOutputDTO = modelMapper.map(user,UserOutputDTO.class);
        return userOutputDTO;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable("id") int id) throws UserNotFoundException {
        userService.deleteUserById(id);
    }

    @GetMapping("/users/{id}/posts")
    public List<PostOutputDTO> getUserPosts(@PathVariable("id") int id) throws UserNotFoundException {
        List<Post> postList= userService.getUserPostsById(id);
        List<PostOutputDTO> postOutputDTOList = new ArrayList<>();
        for(Post post:postList){
            postOutputDTOList.add(modelMapper.map(post,PostOutputDTO.class));
        }
        return  postOutputDTOList;
    }

    @PostMapping("/users/{id}/posts")
    public PostOutputDTO createUserPost(@PathVariable("id") int id, @RequestBody PostInputDTO postInputDTO) throws UserNotFoundException {
        Post post = modelMapper.map(postInputDTO,Post.class);
        Post returnedPost= userService.createUserPost(id,post);
        PostOutputDTO postOutputDTO = modelMapper.map(returnedPost,PostOutputDTO.class);
        return postOutputDTO;
    }



}
