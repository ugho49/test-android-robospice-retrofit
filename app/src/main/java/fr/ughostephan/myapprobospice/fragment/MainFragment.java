package fr.ughostephan.myapprobospice.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.PendingRequestListener;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fr.ughostephan.myapprobospice.R;
import fr.ughostephan.myapprobospice.adapter.OnItemClickListener;
import fr.ughostephan.myapprobospice.adapter.PostAdapter;
import fr.ughostephan.myapprobospice.model.Post;
import fr.ughostephan.myapprobospice.network.request.GetAllPostRequest;
import fr.ughostephan.myapprobospice.network.request.GetPostCommentRequest;

public class MainFragment extends BaseSpiceFragment implements OnItemClickListener {

    @BindView(R.id.recyclerview_products)
    RecyclerView recyclerViewProducts;

    private PostAdapter postAdapter;
    private Unbinder unbinder;

    private GetAllPostRequest getAllPostRequest;
    private GetPostCommentRequest getPostCommentRequest;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerViewProducts.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewProducts.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        postAdapter = new PostAdapter(getContext());
        postAdapter.setOnItemClickListener(this);
        recyclerViewProducts.setAdapter(postAdapter);

        // Get post request
        getAllPostRequest = new GetAllPostRequest();

        // Get all posts
        getPosts();

        return view;
    }

    @Override
    public void onItemClick(Object item) {
        Post postClicked = (Post) item;
        Toast.makeText(getContext(), "Post " + postClicked.getId() + " Clicked", Toast.LENGTH_LONG).show();
        getCommentsForPost(postClicked);
    }

    private void getPosts() {
        getSpiceManager().execute(getAllPostRequest, "posts", DurationInMillis.ONE_MINUTE, new RequestListener<List>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                Toast.makeText(getContext(), "Fail to get posts", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRequestSuccess(List list) {
                List<Post> posts = list;
                postAdapter.setPosts(posts);
                postAdapter.notifyDataSetChanged();
            }
        });
    }

    private void getCommentsForPost(Post post) {
        // TODO
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
