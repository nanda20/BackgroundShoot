package net.simplifiedcoding.navigationdrawerexample;

import android.app.Activity;
import android.content.Context;
import android.provider.ContactsContract;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by owner on 1/21/2017.
 */

public class DetailImageSwipeAdapter extends PagerAdapter{
    Activity activity;
    ArrayList<DataPojo> item = new ArrayList<>();
    LayoutInflater inflater;

    public  DetailImageSwipeAdapter(Activity activity, ArrayList<DataPojo> item){
        this.activity=activity;
        this.item=item;
    }
    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater=(LayoutInflater)activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.detail_image_swipe_item,container,false);

        ImageView image;
        image=(ImageView)view.findViewById(R.id.imageViewSwipe);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        DisplayMetrics dis = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dis);
        int hight = dis.heightPixels;
        int width = dis.widthPixels;
        image.setMinimumHeight(hight);
        image.setMinimumWidth(width);

        try{
            Picasso.with(activity.getApplicationContext())
                    .load(item.get(position).getWebformatURL())
                    .placeholder(R.drawable.loading)
                    .error(R.mipmap.ic_launcher)
                    .into(image);
        }catch (Exception e){

        }
        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }
}
