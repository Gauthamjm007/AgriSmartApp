package com.example.arunn.silfraagri;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.TwitterAuthProvider;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class loginactivity extends AppCompatActivity {

    private Button LoginButton1;
    private EditText UserEmail,UserPassword;
    private TextView NeedNewAccountLink;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;
    private static final int RC_SIGN_IN = 1;
    private GoogleApiClient mGoogleSignInClient ;
    private static final String TAG = "loginactivity";
    private TwitterLoginButton mLoginButton;
    private SignInButton googlesigninbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Twitter.initialize(this);
        setContentView(R.layout.activity_loginactivity);
        mAuth=FirebaseAuth.getInstance();

        NeedNewAccountLink=(TextView) findViewById(R.id.register_account_link);
        UserEmail=(EditText)findViewById(R.id.login_email);
        UserPassword=(EditText)findViewById(R.id.login_password);
        LoginButton1=(Button)findViewById(R.id.login_button);
        googlesigninbutton=findViewById(R.id.google_sign_in_button);
        loadingBar=new ProgressDialog(this);

        mLoginButton = findViewById(R.id.button_twitter_login);
        mLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {

                mAuth.getCurrentUser();
                handleTwitterSession(result.data);
            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(loginactivity.this, "Authentication failed!", Toast.LENGTH_LONG).show();

            }
        });





        NeedNewAccountLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             SendUserToRegisterActivity();
            }
        });

        LoginButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllowingUserToLogin();

            }
        });

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(loginactivity.this, "Connection to google firebase Failed", Toast.LENGTH_SHORT).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions)
                .build();

        googlesigninbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    signIn();
            }
        });
    }

    private void handleTwitterSession(TwitterSession session) {
        Log.d(TAG, "handleTwitterSession:" + session);

        AuthCredential credential = TwitterAuthProvider.getCredential(
                session.getAuthToken().token,
                session.getAuthToken().secret);

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            sendUserToMainActivity();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            String message =task.getException().toString();
                            Toast.makeText(loginactivity.this, "Error Authenticating :"+message, Toast.LENGTH_SHORT).show();
                            sendUserToLoginActivity();

                        }

                        // ...
                    }
                });
    }




    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleSignInClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mLoginButton.onActivityResult(requestCode, resultCode, data);



        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            loadingBar.setTitle("Google Sign In");
            loadingBar.setMessage("Please wait while we are allowing you to login.....");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true);

            if (result.isSuccess()){
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
                Toast.makeText(this, "Please wait while we are checking your Auth", Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
            else{
                Toast.makeText(this, "Can't get Auth Result", Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        }
    }




    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Log.d(TAG, "signInWithCredential:success");
                            sendUserToMainActivity();

                        } else {

                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            String message =task.getException().toString();
                            Toast.makeText(loginactivity.this, "Error Authenticating :"+message, Toast.LENGTH_SHORT).show();
                            sendUserToLoginActivity();
                        }


                    }
                });
    }



    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser=mAuth.getCurrentUser();
        if(currentUser != null){
            sendUserToMainActivity();
        }
    }


    private void AllowingUserToLogin() {
        String email=UserEmail.getText().toString();
        String password = UserPassword.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please Enter the Email Address", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Enter the Password", Toast.LENGTH_SHORT).show();
        }

        else{

            loadingBar.setTitle("Login");
            loadingBar.setMessage("Please wait while we are allowing you to login.....");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true);

            mAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){

                           sendUserToMainActivity();
                           Toast.makeText(loginactivity.this,"you are logged in sucessfully",Toast.LENGTH_SHORT).show();
                           loadingBar.dismiss();
                       }
                            else{
                                String message=task.getException().getMessage();
                                Toast.makeText(loginactivity.this,"Error Occuered"+message,Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
        }
    }



    private void sendUserToMainActivity() {
        Intent mainIntent = new Intent(loginactivity.this,MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }

    private void sendUserToLoginActivity() {
        Intent mainIntent = new Intent(loginactivity.this,loginactivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }

    private void SendUserToRegisterActivity() {
        Intent registerIntent= new Intent(loginactivity.this,RegisterActivity.class);
        startActivity(registerIntent);

    }
}
