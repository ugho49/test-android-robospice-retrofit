package fr.ughostephan.myapprobospice.network.request;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import java.util.List;

import fr.ughostephan.myapprobospice.model.Comment;
import fr.ughostephan.myapprobospice.model.Post;
import fr.ughostephan.myapprobospice.network.service.PostService;

/**
 * Created by ughostephan on 12/01/2018.
 */
public class GetPostCommentRequest extends RetrofitSpiceRequest<List, PostService> {

    private int postId;

    public GetPostCommentRequest(int postId) {
        super(List.class, PostService.class);
        this.postId = postId;
    }

    @Override
    public List<Comment> loadDataFromNetwork() {
        return getService().getAllCommentByPostId(postId);
    }
}