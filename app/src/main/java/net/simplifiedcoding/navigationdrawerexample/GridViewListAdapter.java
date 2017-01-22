package net.simplifiedcoding.navigationdrawerexample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by owner on 12/18/2016.
 */
public class GridViewListAdapter extends RecyclerView.Adapter<GridViewListAdapter.ViewHolder> {
    private Context context;
    ArrayList<DataPojo> item = new ArrayList<>();

    public GridViewListAdapter(Context context, ArrayList<DataPojo> item) {
        this.context = context;
        this.item = item;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_category_list_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Picasso.with(context)
                .load(item.get(position).getpreviewURL())
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading)
                .into(holder.imageItem);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, DetailImage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("image",position);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageItem;

        public ViewHolder(View itemView) {
            super(itemView);
            imageItem = (ImageView) itemView.findViewById(R.id.imgView);
            imageItem.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }
}