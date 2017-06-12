package com.jameriah.pocketnutritionist;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import java.util.List;

/**
 * Created by jameriah on 6/10/17.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {


    private List<Recipe> recipes;
    private Context mContext;

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    public RecipeAdapter(List<Recipe> recipes, Context mContext) {
        this.recipes = recipes;
        this.mContext = mContext;

    }

    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View recipeView = inflater.inflate(R.layout.recipe_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(recipeView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeAdapter.ViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);

        NetworkImageView thumbnail = holder.thumb;
        TextView title = holder.title;
        TextView description = holder.description;


        mRequestQueue = Volley.newRequestQueue(mContext);

        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache(){
            private final LruCache<String, Bitmap> mCache = new LruCache<String,Bitmap>(10);
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url,bitmap);
            }

            public Bitmap getBitmap(String url){
                return mCache.get(url);
            }

        });

        thumbnail.setImageUrl(recipe.getThumb_url(),mImageLoader);

        Log.i("--------",recipe.getThumb_url());
        title.setText(recipe.getTitle());
        description.setText(recipe.getTitle());

    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        NetworkImageView thumb;
        TextView title, description;


        ViewHolder(View v) {
            super(v);

            thumb = (NetworkImageView) v.findViewById(R.id.thumb);
            title = (TextView) v.findViewById(R.id.title);
            description = (TextView) v.findViewById(R.id.description);

        }
    }
}
