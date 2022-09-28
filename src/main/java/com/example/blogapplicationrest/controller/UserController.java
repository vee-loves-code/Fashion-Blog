package com.example.blogapplicationrest.controller;

import com.example.blogapplicationrest.dto.*;
import com.example.blogapplicationrest.model.Post;
import com.example.blogapplicationrest.model.User;
import com.example.blogapplicationrest.response.*;
import com.example.blogapplicationrest.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController   @Slf4j
@RequestMapping( value = "/api")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



    @PostMapping(value = "/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody UserDto userDto){
        log.info("Successfully Registered {} " , userDto.getEmail());
        return new ResponseEntity<>(userService.register(userDto) , CREATED);
    }
    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> Userlogin(@RequestBody LoginDto loginDto) {
        log.info("Welcome! Successfully logged in {} " , loginDto.getEmail());
        return new ResponseEntity<>(userService.login(loginDto), OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<CreatePostResponse> create(@RequestBody PostDto postDto){
        log.info("Successfully Created A post With Title:  {} " , postDto.getTitle());
        return  new ResponseEntity<>(userService.createPost(postDto) , CREATED);
    }

    @PostMapping(value = "/comment/{user_id}/{post_id}")
    public ResponseEntity<CommentResponse> comment(@PathVariable(value = "user_id") Integer user_id, @PathVariable(value = "post_id") Integer post_id, @RequestBody CommentDto commentDto){
        log.info("Successfully commented :  {} " , commentDto.getComment());
        //  URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/comment").toUriString());
        //  return ResponseEntity.created(uri).body();
        return new ResponseEntity<>( userService.comment(user_id , post_id , commentDto) , CREATED);
    }

    @PostMapping(value = "/like/{user_id}/{post_id}")
    public ResponseEntity<LikeResponse> like(@PathVariable(value = "user_id") Integer user_id, @PathVariable(value = "post_id") Integer post_id, @RequestBody LikeDto likeDto){
        log.info("Successfully liked :  {} " , likeDto.isLiked());
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/like").toUriString());
        return ResponseEntity.created(uri).body( userService.like(user_id , post_id , likeDto));
    }

    @GetMapping(value = "/searchPost/{keyword}")
    public ResponseEntity<SearchPostResponse> searchPost(@PathVariable(  value = "keyword") String keyword){
        return new ResponseEntity<>(userService.searchPost(keyword) , OK);
    }

    @GetMapping(value = "/searchComment/{keyword}")
    public ResponseEntity<SearchCommentResponse> searchComment(@PathVariable(  value = "keyword") String keyword){
        return ResponseEntity.ok().body(userService.searchComment(keyword));
    }

    @GetMapping(value = "/post/{id}")
    public ResponseEntity<Post> searchComment(@PathVariable(  value = "id") Integer id){
        return ResponseEntity.ok().body(userService.findPostById(id));
    }

    @GetMapping("/all-user")
    public List<User> getAllUser() {
        List<User> returnUser = userService.getAllUser();
        return returnUser;
    }

//    @DeleteMapping("/delete-user/{userId}")
//    public void deleteComment(@PathVariable long userId) {
//        userService.deleteComment(commentId);
//    }
}
