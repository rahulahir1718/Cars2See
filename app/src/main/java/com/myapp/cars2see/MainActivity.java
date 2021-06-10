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
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements brandsAdapter.ma1,modelsAdapter.ma2{

    private FirebaseFirestore firestore;
    private Brands brands;
    private RecyclerView recyclerView;
    private List<Brands> brandsList;
    private List<List<Model>> modelLists;
    private Model model;
    private mainAdapter mainAdapter;
    private String[] names={"Top Brands","Popular Cars","New Launched Cars","Upcoming Cars","Electric Cars"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        setBrands();
    }

    public void initialize(){
        brandsList=new ArrayList<>();
        modelLists=new ArrayList<>();
        brands=Brands.getInstance();
        model=Model.getInstance();
        firestore=FirebaseFirestore.getInstance();
        recyclerView=findViewById(R.id.recycleView);
    }

    public void setBrands(){
        firestore.collection("Brands").orderBy("name").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document:task.getResult()){
                        brands=document.toObject(Brands.class);
                        brandsList.add(brands);
                    }
                    setPopulars(brandsList);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Failure",e.getMessage());
            }
        });
    }

    public void setPopulars(List<Brands> brandsList1){
        List<Model> modelList=new ArrayList<>();
        firestore.collection("populars").orderBy("name").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document:task.getResult()){
                        model=document.toObject(Model.class);
                        modelList.add(model);
                    }
                    modelLists.add(modelList);
                    setNewLaunched(brandsList1,modelLists);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("Failure",e.getMessage());
            }
        });
    }

    public void setNewLaunched(List<Brands> brandsList1,List<List<Model>> modelLists){
        List<Model> modelList=new ArrayList<>();
        firestore.collection("new_launched").orderBy("name").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document:task.getResult()){
                        model=document.toObject(Model.class);
                        modelList.add(model);
                    }
                    modelLists.add(modelList);
                    setUpcomings(brandsList1,modelLists);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("Failure",e.getMessage());
            }
        });
    }

    public void setUpcomings(List<Brands> brandsList1,List<List<Model>> modelLists){
        List<Model> modelList=new ArrayList<>();
        firestore.collection("upcoming").orderBy("name").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document:task.getResult()){
                        model=document.toObject(Model.class);
                        modelList.add(model);
                    }
                    modelLists.add(modelList);
                    setElectric(brandsList1,modelLists);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("Failure",e.getMessage());
            }
        });
    }

    public void setElectric(List<Brands> brandsList1,List<List<Model>> modelLists){
        List<Model> modelList=new ArrayList<>();
        firestore.collection("electric").orderBy("name").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document:task.getResult()){
                        model=document.toObject(Model.class);
                        modelList.add(model);
                    }
                    modelLists.add(modelList);
                    mainAdapter=new mainAdapter(MainActivity.this, modelLists, brandsList1,names);
                    recyclerView.setAdapter(mainAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("Failure",e.getMessage());
            }
        });
    }
    @Override
    public void brandTouch(String name) {
        Intent intent=new Intent(MainActivity.this,MainActivity2.class);
        intent.putExtra("brand",name);
        startActivity(intent);
    }

    @Override
    public void touchModel(Model m,String c) {
        if(c.equals("Upcoming Cars")) {
            Snackbar.make(findViewById(android.R.id.content),"Sorry!! for upcoming cars complete information not available",Snackbar.LENGTH_LONG).show();
        } else{
            Intent intent=new Intent(MainActivity.this,MainActivity3.class);
            intent.putExtra("Model",m);
            startActivity(intent);
        }
    }
}

