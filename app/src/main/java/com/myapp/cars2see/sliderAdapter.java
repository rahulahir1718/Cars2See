package com.myapp.cars2see;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

public class sliderAdapter extends SliderViewAdapter<sliderAdapter.Holder>{

    private String[] images;
    private Context context;

    public sliderAdapter(String[] images, Context context) {
        this.images = images;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_image,parent,false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder viewHolder, int position) {
        Picasso.with(context).load(images[position]).fit().into(viewHolder.imageView);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    public class Holder extends SliderViewAdapter.ViewHolder{

        private ImageView imageView;
        public Holder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_slider_imageview);
        }
    }

}
