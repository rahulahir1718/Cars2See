package com.myapp.cars2see;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity implements modelsAdapter.ma2 {

    private RecyclerView recyclerView;
    private FirebaseFirestore firestore;
    private Model model;
    private modelsAdapter modelsAdapter;
    private String brand;
    private List<Model> modelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initialize();
        process();
    }

    private void process() {
        brand=getIntent().getStringExtra("brand");
        if(brand.equals("Audi"))
            fetch("Model");
        else
            fetch(brand);
    }

    private void fetch(String collection){
        firestore.collection(collection).orderBy("name").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document:task.getResult()){
                        model=document.toObject(Model.class);
                        modelList.add(model);
                    }
                    modelsAdapter=new modelsAdapter(MainActivity2.this,modelList,MainActivity2.this,"");
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity2.this));
                    recyclerView.setAdapter(modelsAdapter);
                    recyclerView.getLayoutManager().smoothScrollToPosition(recyclerView,new RecyclerView.State(),recyclerView.getAdapter().getItemCount());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity2.this,e.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("Failure",e.getMessage());
            }
        });
    }
    public void initialize(){
        recyclerView=findViewById(R.id.recycleView2);
        firestore=FirebaseFirestore.getInstance();
        model=Model.getInstance();
        modelList=new ArrayList<>();
        brand=getIntent().getStringExtra("brand");
    }

    @Override
    public void touchModel(Model m,String c) {
        Intent intent=new Intent(MainActivity2.this,MainActivity3.class);
        intent.putExtra("Model",m);
        startActivity(intent);
    }
}

