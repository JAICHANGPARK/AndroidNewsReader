package org.dreamwalker.newsreader.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.dreamwalker.newsreader.Common.Common;
import org.dreamwalker.newsreader.Interface.IconBetterIdeaService;
import org.dreamwalker.newsreader.Interface.ItemClickListener;
import org.dreamwalker.newsreader.ListNews;
import org.dreamwalker.newsreader.Model.IconBetterIdea;
import org.dreamwalker.newsreader.Model.WebSite;
import org.dreamwalker.newsreader.R;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by JAICHANGPARK on 10/10/17.
 */

class ListSourceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ItemClickListener itemClickListener;

    TextView sourceTitle;
    CircleImageView sourceImage;


    public ListSourceViewHolder(View itemView) {
        super(itemView);
        // TODO: 10/10/17 카드 속에 포함된 아이템 인플레이
        sourceImage = (CircleImageView) itemView.findViewById(R.id.source_image);
        sourceTitle = (TextView) itemView.findViewById(R.id.source_name);
        // TODO: 10/10/17 아래의 리스너 코드를 호출해야 정상적인 클릭동작을 함
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

public class ListSourceAdapter extends RecyclerView.Adapter<ListSourceViewHolder> {

    Context context;
    WebSite website;

    private IconBetterIdeaService mService;

    public ListSourceAdapter(Context context, WebSite website) {
        this.context = context;
        this.website = website;
        mService = Common.getIconService();
    }

    @Override
    public ListSourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.source_layout,parent,false);
        ListSourceViewHolder vh = new ListSourceViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ListSourceViewHolder holder, int position) {

        StringBuilder iconBetterAPI = new StringBuilder("https://icons.better-idea.org/allicons.json?url=");
        iconBetterAPI.append(website.getSources().get(position).getUrl());

        mService.getIconUrl(iconBetterAPI.toString()).enqueue(new Callback<IconBetterIdea>() {
            @Override
            public void onResponse(Call<IconBetterIdea> call, Response<IconBetterIdea> response) {
                if (response.body().getIcons().size() > 0){
                    Picasso.with(context).load(response.body().getIcons().get(0).getUrl()).into(holder.sourceImage);

                }
            }

            @Override
            public void onFailure(Call<IconBetterIdea> call, Throwable t) {

            }
        });

        holder.sourceTitle.setText(website.getSources().get(position).getName());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int posision, boolean isLongClick) {

                Intent intent  =  new Intent(context, ListNews.class);
                intent.putExtra("source",website.getSources().get(posision).getId());
                intent.putExtra("sortBy",website.getSources().get(posision).getSortByAvailable().get(0));
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return website.getSources().size();
    }
}
