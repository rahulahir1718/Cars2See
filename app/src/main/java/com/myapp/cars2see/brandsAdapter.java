package com.myapp.cars2see;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class brandsAdapter extends RecyclerView.Adapter<brandsAdapter.Holder>{


    private Context context;
    private List<Brands> brandsList;
    private brandsAdapter.ma1 ma1;

    public brandsAdapter(Context context,List<Brands> brandsList,brandsAdapter.ma1 ma1){
        this.ma1=ma1;
        this.context=context;
        this.brandsList=brandsList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.brands_card_view,viewGroup,false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        Picasso.with(context).load(brandsList.get(i).getImage()).fit().into(holder.imageView, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {

            }
        });
        holder.textView.setText(brandsList.get(i).getName());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ma1.brandTouch(brandsList.get(i).getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return brandsList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView imageView;
        private ProgressBar progressBar;
        public Holder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.brandsName);
            imageView=itemView.findViewById(R.id.brandsLogo);
            progressBar=itemView.findViewById(R.id.progressBrand);
        }
    }

    public interface ma1{
        void brandTouch(String name);
    }
}