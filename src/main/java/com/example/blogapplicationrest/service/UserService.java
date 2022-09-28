package com.example.blogapplicationrest.service;

import com.example.blogapplicationrest.dto.*;
import com.example.blogapplicationrest.model.Post;
import com.example.blogapplicationrest.model.User;
import com.example.blogapplicationrest.response.*;

import java.util.List;

public interface UserService {

    RegisterResponse register(UserDto userDto);

    LoginResponse login(LoginDto loginDto);

    CreatePostResponse createPost(PostDto postDto);

    CommentResponse comment(int user_id , int post_id , CommentDto commentDto);

    LikeResponse like(int user_id , int post_id , LikeDto likeDto);

    SearchCommentResponse searchComment(String keyword);


    SearchPostResponse searchPost(String keyword);

    Post findPostById(int id);

    List<User> getAllUser();

//    void deleteComment(int commentId);

}
