package com.dilum.myprojectapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dilum.myprojectapplication.Adapter.PopularListAdapter;
import com.dilum.myprojectapplication.Domain.PopularDomain;
import com.dilum.myprojectapplication.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterPopular;
    private RecyclerView recyclerViewPopular;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initRecyclerview();
        bottomNavigation();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void bottomNavigation() {
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout cartBtn = findViewById(R.id.cartBtn);
        LinearLayout locationBtn = findViewById(R.id.locationBtn);
        LinearLayout profileBtn = findViewById(R.id.profileBtn);

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ProfileActivity.class));
            }
        });

        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LocationActivity.class));
            }
        });



        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainActivity.class));
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CartActivity.class));
            }
        });
    }

    private void initRecyclerview() {
        ArrayList<PopularDomain> items = new ArrayList<>();
        items.add(new PopularDomain("Pedigree puppy", "PEDIGREE Puppy Growth & Protection Dry Dog Food Grilled Steak and Vegetable Flavor, 3.5 lb. Bag\n" +
                "\n" +
                "\\n Brand\tPedigree\n" +
                "\\n Flavor\tSteak\n" +
                "\\nAge Range (Description)\tPuppy\n" +
                "\\nItem Form\tKibble, Crunchy\n" +
                "\\nSpecific Uses For Product\tBone Health, Muscle Care, Brain Health, Skin and Coat Health, Dental Care \\n\\n\\nINGREDIENTS: Ground Whole Grain Corn, Chicken By-Product Meal, Ground Whole Grain Wheat, Soybean Meal, Meat and Bone Meal, Animal Fat (Source of Omega 6 Fatty Acids [Preserved with BHA and Citric Acid]), Brewers Rice, Natural Flavor, Corn Gluten Meal, Salt, Monocalcium Phosphate, Fish Oil ([Source of DHA] Preserved with Mixed Tocopherols), Brewers Dried Yeast, Potassium Chloride, Calcium Carbonate, Choline Chloride, DL-Methionine, Dried Peas, Zinc Sulfate, Natural Grilled Steak Flavor, L-Tryptophan, Vitamin E Supplement, Red 40, Yellow 6, Dried Carrots, Blue 2, Yellow 5, Copper Sulfate, Niacin Supplement, Sodium Selenite, Potassium Iodide, D-Calcium Pantothenate, Vitamin A Supplement, Riboflavin Supplement (Vitamin B2), Vitamin B12 Supplement, Thiamine Mononitrate (Vitamin B1), Vitamin D3 Supplement, Pyridoxine Hydrochloride (Vitamin B6), Folic Acid..", "small1", 10, 2,4,3000));
        items.add(new PopularDomain("Pedigree Adult ","Pedigree High Protein Adult Dry Dog Food Beef and Lamb Flavor Dog Kibble, 18 lb. Bag \n +\nBrand\tPedigree\n" +
                "Flavor\tBeef & Lamb\n" +
                "AgeRange \tAdult\n" +
                "ItemForm\tPellet\n" +
                "Specific Uses For Product\tNutrition\n \n\nINGREDIENTS:Contains one (1) PEDIGREE High Protein Adult Dry Dog Food Beef and Lamb Flavor Dog Kibble, 18 lb. Bonus Bag\n" +
                "Made with real red meat and 25% more protein than PEDIGREE Adult Complete Nutrition\n" +
                "Provides whole grains and helps support healthy digestion\n" +
                "Delivers complete and balanced nutrition enriched with omega-6 fatty acids to help nourish your dog’s skin and coat\n" +
                "Made in the USA with the World’s Finest Ingredients\n","adult1",10,3.5,7,4500));
        items.add(new PopularDomain("Nutrish Rachael","Nutrish Rachael Ray Wet Dog Food, Natural Food for Adult Dogs with Added Vitamins, Minerals & Nutrients, Beef, Chicken, and Gentle Digestion Variety Pack, 13 Ounce Can (Pack of 12).\nIngredients:\n" +
                "Beef And Pumpkin Recipe: Beef, Beef Broth, Pumpkin, Liver, Apples, Barley, Peas, Canola Oil (Preserved With Mixed Tocopherols), Carrots, Vitamins (L-Ascorbyl-2-Polyphosphate (Source Of Vitamin C), Vitamin E Supplement, Thiamine Mononitrate, Niacin, D-Calcium Pantothenate, Vitamin A Supplement, Riboflavin Supplement, Vitamin D3 Supplement, Vitamin B12 Supplement, Pyridoxine Hydrochloride, Folic Acid), Guar Gum, Carrageenan, Magnesium Sulfate, Minerals (Zinc Proteinate, Zinc Sulfate, Ferrous Sulfate, Iron Proteinate, Copper Sulfate, Copper Proteinate, Sodium Selenite, Manganese Sulfate, Manganese Proteinate, Calcium Iodate), Choline Chloride Chicken, Salmon, And Pumpkin Recipe: Chicken, Chicken Broth, Liver, Pumpkin, Salmon, Brown Rice, Apples, Peas, Carrots, Guar Gum, Canola Oil, Carrageenan, Vitamins (L-Ascorbyl-2-Polyphosphate (Source Of Vitamin C), Vitamin E Supplement, Thiamine Mononitrate, Niacin, D-Calcium Pantothenate, Vitamin A Supplement, Riboflavin Supplement, Vitamin D3 Supplement, Vitamin B12 Supplement, Pyridoxine Hydrochloride, Folic Acid), Minerals (Zinc Proteinate, Zinc Sulfate, Ferrous Sulfate, Iron Proteinate, Copper Sulfate, Copper Proteinate, Sodium Selenite, Manganese Sulfate, Manganese Proteinate, Calcium Iodate), Choline Chloride Chicken And Apple Recipe: Chicken, Chicken Broth, Liver, Apples, Brown Rice, Peas, Carrots, Guar Gum, Canola Oil, Carrageenan, Vitamins (L-Ascorbyl-2-Polyphosphate (Source Of Vitamin C), Vitamin E Supplement, Thiamine Mononitrate, Niacin, D-Calcium Pantothenate, Vitamin A Supplement, Riboflavin Supplement, Vitamin D3 Supplement, Vitamin B12 Supplement, Pyridoxine Hydrochloride, Folic Acid), Minerals (Zinc Proteinate, Zinc Sulfate, Ferrous Sulfate, Iron Proteinate, Copper Sulfate, Copper Proteinate, Sodium Selenite, Manganese Sulfate, Manganese Proteinate, Calcium Iodate)\n","nut11",15,5.2,6,1500));


        recyclerViewPopular=findViewById(R.id.view1);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        adapterPopular = new PopularListAdapter(items);
        recyclerViewPopular.setAdapter(adapterPopular);
    }
}