package com.example.instagram20.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagram20.Post;
import com.example.instagram20.PostsAdapter;
import com.example.instagram20.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PostsFragment} factory method to
 * create an instance of this fragment.
 */
public class PostsFragment extends Fragment {
    public static final String TAG = "PostsFragment";
    private RecyclerView rvPosts;
    protected PostsAdapter adapter;
    protected List<Post> allPosts;

    public PostsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvPosts = view.findViewById(R.id.rvPosts);

        //2. Create the data source
        allPosts = new ArrayList<>();
        //1. Create the adapter
        adapter = new PostsAdapter(getContext(), allPosts);
        //Steps to use the recycler view:
        //0.Create a layout for one row in the list

        //3. Set the adapter on the recycler view
        rvPosts.setAdapter(adapter);
        //4. Set the layout manager on the recycler view
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        queryPosts();
    }

    protected void queryPosts(){
        // Specify which class to query
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null){
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }

                for (Post post:posts){
                    Log.i(TAG, "Post " + post.getDescription() + ", username: " + post.getUser().getUsername()) ;
                }
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }

        });

    }

}