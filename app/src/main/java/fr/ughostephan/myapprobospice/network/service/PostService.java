package fr.ughostephan.myapprobospice.network.service;


import java.util.List;

import fr.ughostephan.myapprobospice.model.Comment;
import fr.ughostephan.myapprobospice.model.Post;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by ughostephan on 10/01/2018.
 */
public interface PostService {

    @GET("/posts")
    List<Post> getAllPosts();

    @GET("/posts/{postId}/comments")
    List<Comment> getAllCommentByPostId(@Path("postId") int postId);
}
