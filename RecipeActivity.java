package com.jameriah.pocketnutritionist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class RecipeActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    Button button;
    CheckBox vegetarian,vegan,glutenfree,dairyfree;
    String extras = "";
    EditText keywords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        button = (Button) findViewById(R.id.submit);

        keywords = (EditText) findViewById(R.id.keyword);

        vegetarian = (CheckBox) findViewById(R.id.vegetarian);
        vegan = (CheckBox) findViewById(R.id.vegan);
        glutenfree = (CheckBox) findViewById(R.id.glutenfree);
        dairyfree = (CheckBox) findViewById(R.id.dairyfree);

        //add other restrictions

        button.setOnClickListener(this);
        vegetarian.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,RecipeList.class);
        if(!extras.equals("")){
            intent.putExtra("options",extras);
        }

            intent.putExtra("keywords","&q=" + keywords.getText().toString());


        startActivity(intent);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch(buttonView.getId()) {
            case (R.id.vegetarian):
                extras += "&allowedDiet[]=390^Vegetarian";
                break;

            case (R.id.vegan):
                extras += "&allowedDiet[]=388^Lacto vegetarian";
                break;

            case (R.id.glutenfree):
                extras += "&allowedAllergy[]=393^Gluten-Free";
                break;

            case (R.id.dairyfree):
                extras += "&allowedAllergy[]=396^Dairy-Free";
                break;

            default:
                extras += "";

                //other swtich cases
        }
    }
}
