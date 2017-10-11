package org.dreamwalker.newsreader.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.squareup.picasso.Picasso;

import org.dreamwalker.newsreader.Common.ISO8601Parse;
import org.dreamwalker.newsreader.DetailArticle;
import org.dreamwalker.newsreader.Interface.ItemClickListener;
import org.dreamwalker.newsreader.Model.Article;
import org.dreamwalker.newsreader.R;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by JAICHANGPARK on 10/10/17.
 */


class ListNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ItemClickListener itemClickListener;


    TextView article_title;
    RelativeTimeTextView article_time;
    CircleImageView article_image;

    public ListNewsViewHolder(View itemView) {
        super(itemView);

        article_image = (CircleImageView)itemView.findViewById(R.id.articleImage);
        article_time = (RelativeTimeTextView)itemView.findViewById(R.id.articleTime);
        article_title = (TextView)itemView.findViewById(R.id.articleTitle);

        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {

        itemClickListener.onClick(v, getAdapterPosition(), false);

    }
}

public class ListNewsAdapter extends RecyclerView.Adapter<ListNewsViewHolder>{

    private List<Article> articleList;
    private Context context;

    public ListNewsAdapter(List<Article> articleList, Context context) {
        this.articleList = articleList;
        this.context = context;
    }


    @Override
    public ListNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.news_layout, parent,false);

        return new ListNewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListNewsViewHolder holder, int position) {

        Picasso.with(context).load(articleList.get(position).getUrlToImage())
                .into(holder.article_image);

        if (articleList.get(position).getTitle().length() > 65){
            holder.article_title.setText(articleList.get(position).getTitle().substring(0,65)+"...");

        }else {
            holder.article_title.setText(articleList.get(position).getTitle());
        }

        Date date = null;
        try {
            date = ISO8601Parse.parse(articleList.get(position).getPublishedAt());
        }catch (ParseException ex){
            ex.printStackTrace();
        }

        holder.article_time.setReferenceTime(date.getTime());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int posision, boolean isLongClick) {

                Intent intent = new Intent(context, DetailArticle.class);
                intent.putExtra("webURL", articleList.get(posision).getUrl());
                context.startActivity(intent);
            }
        });

    }

    @Override
        public int getItemCount() {
            return articleList.size();
    }
}
