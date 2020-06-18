package com.naaniz.naanizcustomer.ui.main.fragments.recipes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import com.naaniz.naanizcustomer.R;

import java.net.URI;

public class add_recipe extends AppCompatActivity {
    private EditText recipename,description;
    private ImageView dishimage;
    private Button dishimageuploadbtn;
    private Button nextbutton;
    private SwitchCompat foodSwitch;
    private static  final int GALLERY_CODE=1;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        recipename=findViewById(R.id.recipe_name_edit_text);
        description=findViewById(R.id.description_edit_text);
        dishimage=findViewById(R.id.recipe_image);
        dishimageuploadbtn=findViewById(R.id.recipe_upload_btn);
        nextbutton=findViewById(R.id.next_item);
        foodSwitch=findViewById(R.id.food_choice_add);

        dishimageuploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryintent=new Intent(Intent.ACTION_GET_CONTENT);
                galleryintent.setType("image/*");
                startActivityForResult(galleryintent,GALLERY_CODE);
            }
        });



        foodSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    foodSwitch.setText(R.string.non_veg);
                } else {
                    foodSwitch.setText(R.string.veg);
                }
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLERY_CODE && resultCode==RESULT_OK){
            if(data!=null){
                imageUri=data.getData();
                dishimage.setImageURI(imageUri);
            }
        }
    }
}
