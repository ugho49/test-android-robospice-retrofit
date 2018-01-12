package fr.ughostephan.myapprobospice.network.request;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import java.util.List;
import fr.ughostephan.myapprobospice.model.Post;
import fr.ughostephan.myapprobospice.network.service.PostService;

/**
 * Created by ughostephan on 12/01/2018.
 */
public class GetAllPostRequest extends RetrofitSpiceRequest<List, PostService> {

    public GetAllPostRequest() {
        super(List.class, PostService.class);
    }

    @Override
    public List<Post> loadDataFromNetwork() {
        return getService().getAllPosts();
    }
}