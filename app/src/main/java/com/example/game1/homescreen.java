package com.example.game1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.Arrays;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
public class homescreen extends AppCompatActivity {
    private SignInButton sign;
    private GoogleSignInClient googleSignInClient;
    private String TAG = "MainActivity";
    private FirebaseAuth auth;
    private TextView sign_out;
    private int RC_SIGN_IN = 1;
    LoginButton loginButton_fb;
    CallbackManager callbackManager;
    ImageView dp,next_pass;
    Database mydb;
    TextView texthint;
    TextView textCoin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        //FacebookSdk.sdkInitialize(getApplicationContext());
        //AppEventsLogger.activateApp(this);
        next_pass=(ImageView)findViewById(R.id.next_pass);
        sign = (findViewById(R.id.signInButton));
        texthint=(findViewById(R.id.hinthere));
        textCoin=(findViewById(R.id.coin));
        mydb =new Database(this) ;
        dp = (ImageView) findViewById(R.id.dp);
        auth = FirebaseAuth.getInstance();
        loginButton_fb = findViewById(R.id.login_button_fb);
        callbackManager = CallbackManager.Factory.create();
        loginButton_fb.setReadPermissions(Arrays.asList("emails"));
        sign_out = findViewById(R.id.show_id);
        next_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(homescreen.this,invite.class);
                startActivity(intent);
            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleSignInClient.signOut();
                Toast.makeText(homescreen.this, "Log out", Toast.LENGTH_LONG).show();
                sign_out.setVisibility(View.INVISIBLE);
                Picasso.get().load("dp.jpg").into(dp);
                sign.setVisibility(View.VISIBLE);
                loginButton_fb.setVisibility(View.VISIBLE);

                AddData();

            }
        });
    }
    public void AddData(){
        loginButton_fb.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = mydb.insertdata(texthint.getText().toString(),
                                textCoin.getText().toString());
                        if(isInserted=true)
                            Toast.makeText(homescreen.this, "Coins $ Hints Added", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    private void signIn() {
        Intent signinintent = googleSignInClient.getSignInIntent();
        startActivityForResult(signinintent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestcode, int resultcode, @Nullable Intent data) {
        super.onActivityResult(requestcode, resultcode, data);
        if (requestcode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInresult(task);
        }

    }
    private void handleSignInresult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount acc = completedTask.getResult(ApiException.class);
            FirebaseGoogleAuth(acc);
        } catch (ApiException e) {
            Toast.makeText(homescreen.this, "Sign Unsuccessful", Toast.LENGTH_LONG).show();
            FirebaseGoogleAuth(null);
        }
    }
    private void FirebaseGoogleAuth(GoogleSignInAccount acc) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(acc.getIdToken(), null);
        auth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(homescreen.this, "successful", Toast.LENGTH_LONG).show();
                    FirebaseUser user = auth.getCurrentUser();
                    UpdateUI(user);
                    loginButton_fb.setVisibility(View.INVISIBLE);
                } else {
                    Toast.makeText(homescreen.this, "Sign Failed", Toast.LENGTH_LONG).show();
                    UpdateUI(null);
                }
            }
        });
    }

    private void UpdateUI(FirebaseUser fuser) {
        sign_out.setVisibility(View.VISIBLE);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (account != null) {
            String personName = account.getDisplayName();
            String persongivenname = account.getGivenName();
            String personFamilyName = account.getFamilyName();
            String personEmail = account.getEmail();
            String peronID = account.getId();
            Uri personphoto = account.getPhotoUrl();
            dp.setImageIcon(null);
            sign_out.setText(personName);
            Picasso.get().load(personphoto).into(dp);
        }
    }
    private void buttonloginfb(View view) {
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookToken(loginResult.getAccessToken());
                sign.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onCancel() {
                Toast.makeText(homescreen.this, "User cancelled the process ", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onError(FacebookException error) {
                Toast.makeText(homescreen.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void handleFacebookToken(AccessToken accessToken) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser obj = auth.getCurrentUser();
             //       UpdateUI(obj);
                    Toast.makeText(homescreen.this, "FaceBook Login Successfull", Toast.LENGTH_SHORT).show();
                    sign.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
