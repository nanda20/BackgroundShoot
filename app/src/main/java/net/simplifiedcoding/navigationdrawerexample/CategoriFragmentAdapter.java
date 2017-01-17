package net.simplifiedcoding.navigationdrawerexample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.simplifiedcoding.navigationdrawerexample.dummy.DummyContent.DummyItem;

public class CategoriFragmentAdapter extends RecyclerView.Adapter<CategoriFragmentAdapter.ViewHolder> {
    private Context context;
    private final String[] mValues;


    public CategoriFragmentAdapter(Context contexts, String[]items) {
        this.context= contexts;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        holder.mItem = mValues[position];
        holder.mIdView.setText(mValues[position]);
//        holder.mContentView.setText(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent= new Intent(context,GridViewList.class);
                intent.putExtra("page","1");
                intent.putExtra("category",String.valueOf(mValues[position]));

                context.startActivity(intent);
          }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;

        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.categori);

        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
