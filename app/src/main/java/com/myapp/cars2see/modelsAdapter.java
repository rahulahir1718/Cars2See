package com.myapp.cars2see;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class modelsAdapter extends RecyclerView.Adapter<modelsAdapter.Holder> {

    private Context context;
    private List<Model> modelList;
    private modelsAdapter.ma2 ma2;
    private String category;

    public modelsAdapter(Context context, List<Model> modelList, modelsAdapter.ma2 ma2,String category) {
        this.context = context;
        this.modelList = modelList;
        this.ma2 = ma2;
        this.category=category;
    }

    @NonNull
    @Override
    public modelsAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.models_card_view,parent,false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull modelsAdapter.Holder holder, int position) {
        bind(holder,position);
    }



    @Override
    public int getItemCount() {
        return modelList==null?0:modelList.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        private CircleImageView imageView;
        private TextView textView;
        private LinearLayout linearLayout;
        private ProgressBar progressBar;
        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.modelImage);
            textView=itemView.findViewById(R.id.modelName);
            linearLayout=itemView.findViewById(R.id.cardLayout);
            progressBar=itemView.findViewById(R.id.progressModel);
        }
    }

    private void bind(Holder holder, int position) {
        Picasso.with(context).load(modelList.get(position).getImage1()).fit().into(holder.imageView, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {

            }
        });
        holder.textView.setText(modelList.get(position).getName());
        switch(category){
            case "Popular Cars":holder.linearLayout.setBackgroundColor(Color.parseColor("#1E90FF"));
                break;
            case "New Launched Cars":holder.linearLayout.setBackgroundColor(Color.parseColor("#00BFFF"));
                break;
            case "Upcoming Cars":holder.linearLayout.setBackgroundColor(Color.parseColor("#87CEEB"));
                break;
            case "Electric Cars":holder.linearLayout.setBackgroundColor(Color.parseColor("#6495ED"));
                break;
            default:holder.linearLayout.setBackgroundColor(Color.parseColor("#1E90FF"));
                break;
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ma2.touchModel(modelList.get(position),category);
            }
        });
    }

    interface ma2{
        void touchModel(Model m,String c);
    }
}
