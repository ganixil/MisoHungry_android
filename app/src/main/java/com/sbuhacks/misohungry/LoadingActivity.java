package com.sbuhacks.misohungry;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoadingActivity extends AppCompatActivity {

    public static final String MATERIALS = "materials";
    public ArrayList<Recipe> recipes = new ArrayList<Recipe>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        //Receive the previous intent called
        Intent preIntent = getIntent();
        //Receive the info passed from the intent
        ArrayList<String> materials = preIntent.getStringArrayListExtra(MATERIALS);

        //Collect the material list
        String materialCollection = "";
        for (String s: materials){
            materialCollection+=s;
            materialCollection+="\n";
        }

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("recipes");
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            String title = snapshot.child("title").getValue(String.class);
                            ArrayList ingredients = new ArrayList();
                            for(DataSnapshot i : snapshot.child("ingredients").getChildren()){
                                ingredients.add(i.getValue().toString());
                            }
                            String instructions = snapshot.child("instructions").getValue(String.class);
                            String yield = snapshot.child("yields").getValue().toString();
                            int total_time = snapshot.child("total_time").getValue(int.class);
                            Recipe r = new Recipe(title,ingredients,instructions,yield,total_time);
                            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.recipesChoice);
                            Button myButton = new Button(getBaseContext());
                            myButton.setText(title);
                            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                            relativeLayout.addView(myButton, lp);
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

        Log.d("length",Integer.toString(recipes.size()));
        Intent intent = new Intent(this, RecipesActivity.class);
        //intent.putStringArrayListExtra(RecipesActivity, materials);

    }



}
