package com.myapp.cars2see;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class mainAdapter extends RecyclerView.Adapter{

    private Context context;
    private List<List<Model>> modelLists;
    private List<Brands> brandsList;
    private String[] names;

    public mainAdapter(Context context, List<List<Model>> modelLists, List<Brands> brandsList, String[] names) {
        this.context = context;
        this.modelLists = modelLists;
        this.brandsList = brandsList;
        this.names = names;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0)
            return 0;
        else
            return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==0)
            return new Holder1(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recyclerview2,parent,false));
        else
            return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recyclerview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(position==0) {
            Holder1 holder1= (Holder1) holder;
            showBrands(holder1,position);
        }
        else{
            Holder h= (Holder) holder;
            h.textView.setText(names[position]);
            showModels(h,position);
        }
    }

    private void showBrands(Holder1 holder1,int position) {
        holder1.textView1.setText(names[position]);
        holder1.recyclerView1.setAdapter(new brandsAdapter(context,brandsList, (brandsAdapter.ma1) context));
        holder1.recyclerView1.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
    }

    @Override
    public int getItemCount() {
        return modelLists.size()+1;
    }

    public class Holder1 extends RecyclerView.ViewHolder {
        private TextView textView1;
        private RecyclerView recyclerView1;
        public Holder1(@NonNull View itemView) {
            super(itemView);
            textView1=itemView.findViewById(R.id.title_textview2);
            recyclerView1=itemView.findViewById(R.id.nestedRecyclerView2);
        }
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView textView;
        private RecyclerView recyclerView;
        public Holder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.title_textview);
            recyclerView=itemView.findViewById(R.id.nestedRecyclerView);
        }
    }


    private void showModels(Holder h,int position){

        int time;

        switch(position){
            case 1: time=2500;
                break;
            case 2:time=2600;
                break;
            case 3:time=2700;
                break;
            case 4:time=2800;
            break;
            default:time=2500;
            break;
        }
        RecyclerView r1;
        LinearLayoutManager linearLayoutManager1;
        r1=h.recyclerView;
        r1.setAdapter(new modelsAdapter(context,modelLists.get(position-1),(modelsAdapter.ma2) context,names[position]));
        linearLayoutManager1=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        h.recyclerView.setLayoutManager(linearLayoutManager1);

        LinearSnapHelper linearSnapHelper=new LinearSnapHelper();
        linearSnapHelper.attachToRecyclerView(h.recyclerView);

        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(linearLayoutManager1.findLastCompletelyVisibleItemPosition()<(h.recyclerView.getAdapter().getItemCount()-1)){
                    linearLayoutManager1.smoothScrollToPosition(h.recyclerView,new RecyclerView.State(),linearLayoutManager1.findLastCompletelyVisibleItemPosition()+1);
                }
                else{
                    linearLayoutManager1.smoothScrollToPosition(h.recyclerView,new RecyclerView.State(),0);
                }
            }
        },2000,time);
    }
}
