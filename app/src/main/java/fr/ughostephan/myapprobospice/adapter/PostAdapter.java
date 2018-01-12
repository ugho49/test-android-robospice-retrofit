package fr.ughostephan.myapprobospice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.ughostephan.myapprobospice.R;
import fr.ughostephan.myapprobospice.model.Post;
import fr.ughostephan.myapprobospice.util.LoremPicsum;

/**
 * Created by ughostephan on 10/01/2018.
 */
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private List<Post> postsList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private Context context;

    public PostAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Post post = postsList.get(position);
        holder.bind(context, post, onItemClickListener);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setPosts(List<Post> posts) {
        this.postsList = posts;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_image)
        ImageView itemImage;
        @BindView(R.id.item_title)
        TextView itemTitle;
        @BindView(R.id.item_content)
        TextView itemContent;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(final Context context, final Post post, final OnItemClickListener listener) {

            itemTitle.setText(post.getTitle());
            itemContent.setText(post.getBody());

            Picasso.with(context)
                    .load(LoremPicsum.getRandomImageUrl())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(itemImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(post);
                }
            });
        }
    }

}
