package com.myapp.cars2see;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class MainActivity3 extends AppCompatActivity {

    private SliderView sliderView;
    private String[] images;
    private sliderAdapter sliderAdapter;
    private TextView modelname_textview;
    private TextView fuel_type_textview;
    private TextView fuel_tank_capacity_textview;
    private TextView mileage_textview;
    private TextView bold_type_textview;
    private TextView seating_capacity_textview;
    private TextView price_textview;
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        initialize();
        process();
        setValues();
    }

    public void initialize(){
        modelname_textview=findViewById(R.id.modelname_textview);
        fuel_type_textview=findViewById(R.id.fuel_type_textview);
        fuel_tank_capacity_textview=findViewById(R.id.fuel_tank_capacity_textview);
        mileage_textview=findViewById(R.id.mileage_textview);
        bold_type_textview=findViewById(R.id.bold_type_textview);
        seating_capacity_textview=findViewById(R.id.seating_capacity_textview);
        price_textview=findViewById(R.id.price_textview);
    }

    public void process(){
        model=(Model)getIntent().getSerializableExtra("Model");
        images=new String[4];
        images[0]=model.getImage1();
        images[1]=model.getImage2();
        images[2]=model.getImage3();
        images[3]=model.getImage4();
        sliderView=findViewById(R.id.image_slider);
        sliderAdapter=new sliderAdapter(images,MainActivity3.this);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
    }

    public void setValues(){
        modelname_textview.setText(model.getName());
        fuel_type_textview.setText(model.getFuel_type());
        fuel_tank_capacity_textview.setText(model.getFuel_tank_capacity()+" Liters");
        mileage_textview.setText(model.getMileage()+" kmpl");
        bold_type_textview.setText(model.getBold_type());
        seating_capacity_textview.setText(model.getSeating_capacity());
        price_textview.setText(model.getPrice()+" Lakhs");
    }
}