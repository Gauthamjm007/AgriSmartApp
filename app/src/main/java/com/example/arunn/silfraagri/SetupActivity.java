package com.example.arunn.silfraagri;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class SetupActivity extends AppCompatActivity {

    private EditText UserName,FullName,CountryName;
    private Button SaveButton;
    private CircleImageView profileImage;
    private FirebaseAuth mAuth;
    private DatabaseReference UserRef;
    private ProgressDialog loadingBar;
    private StorageReference UserProfileImageReference;

    String currentUserId;
    final static int Gallery_Pick=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        loadingBar=new ProgressDialog(this);

        mAuth=FirebaseAuth.getInstance();
        currentUserId=mAuth.getCurrentUser().getUid();
        UserRef=FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId);
        UserProfileImageReference = FirebaseStorage.getInstance().getReference().child("Profile Images");



        UserName=(EditText) findViewById(R.id.setup_username);
        FullName=(EditText) findViewById(R.id.setup_fullname);
        CountryName=(EditText)findViewById(R.id.setup_country);
        SaveButton=(Button) findViewById(R.id.setup_button_save);
        profileImage=(CircleImageView)findViewById(R.id.setup_profile_image);

        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveAccountSetupInformation();
            }
        });

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent=new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,Gallery_Pick);
            }
        });

        UserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    if(dataSnapshot.hasChild("profile Image")){
                        String image = dataSnapshot.child("profile Image").getValue().toString();
                        Picasso.with(SetupActivity.this).load(image).placeholder(R.drawable.profile).into(profileImage);

                    }
                   else{
                        Toast.makeText(SetupActivity.this, "Please Select Profile Image First", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==Gallery_Pick && resultCode==RESULT_OK && data !=null){
            Uri ImageUri = data.getData();
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .start(this);

        }

        if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if(resultCode==RESULT_OK){

                loadingBar.setTitle("Profile Image");
                loadingBar.setMessage("Please wait while we are updating your profile Image .....");
                loadingBar.show();
                loadingBar.setCanceledOnTouchOutside(true);

                Uri  resultUri = result.getUri();
                StorageReference filePath = UserProfileImageReference.child(currentUserId+".jpg");

                filePath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SetupActivity.this, "Profile Image Stored To firebase", Toast.LENGTH_SHORT).show();
                            final String downloadUrl = task.getResult().getDownloadUrl().toString();
                            UserRef.child("profile Image").setValue(downloadUrl)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Intent  setupIntent= new Intent(SetupActivity.this,SetupActivity.class);
                                                startActivity(setupIntent);
                                                Toast.makeText(SetupActivity.this, "Pic storage Succesful", Toast.LENGTH_SHORT).show();
                                                loadingBar.dismiss();
                                            }
                                            else{
                                                String message = task.getException().getMessage();
                                                Toast.makeText(SetupActivity.this, "Error Occured"+message, Toast.LENGTH_SHORT).show();
                                                loadingBar.dismiss();
                                            }
                                        }
                                    });
                        }
                    }
                });

            }
            else{
                Toast.makeText(this, "Error Occured :Image cant be cropped", Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        }
    }

    private void SaveAccountSetupInformation() {
    String username = UserName.getText().toString();
    String fullname = FullName.getText().toString();
    String country = CountryName.getText().toString();

    if(TextUtils.isEmpty(username)){
        Toast.makeText(this, "Pleas enter user name", Toast.LENGTH_SHORT).show();
    }
        if(TextUtils.isEmpty(fullname)){
            Toast.makeText(this, "Pleas enter Fullname", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(country)){
            Toast.makeText(this, "Pleas enter Countryname", Toast.LENGTH_SHORT).show();
        }

        else{

            loadingBar.setTitle("Saving information");
            loadingBar.setMessage("Please wait while we are creating your account .....");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true);


            HashMap userMap = new HashMap();
            userMap.put("username",username);
            userMap.put("fullname",fullname);
            userMap.put("Country",country);
            userMap.put("DOB","none");
            userMap.put("Profession","none");
            UserRef.updateChildren(userMap).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                           if(task.isSuccessful()){
                               SendUserToMainActivity();
                               Toast.makeText(SetupActivity.this, "Your Account Created Succesfully", Toast.LENGTH_LONG).show();
                               loadingBar.dismiss();

                           }
                           else {


                               String message = task.getException().getMessage();
                               Toast.makeText(SetupActivity.this, "Error Occured"+message, Toast.LENGTH_SHORT).show();
                               loadingBar.dismiss();

                           }
                }
            });


        }

    }

    private void SendUserToMainActivity() {

        Intent mainIntent = new Intent(SetupActivity.this,MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }
}
