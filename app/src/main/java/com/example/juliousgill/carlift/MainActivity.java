package com.example.juliousgill.carlift;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
//import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.juliousgill.carlift.Model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import android.support.v7.app.AlertDialog;
import dmax.dialog.SpotsDialog;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Julious Gulshan - 15969
 */

public class MainActivity extends AppCompatActivity
{

    // variable
    Button btnLogin,btnSignUp;
    RelativeLayout rootLayout;

   // FirebaseAuth auth;
   private FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference users;

    @Override
    protected void attachBaseContext(Context newBase)
    {
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //before set content view
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/fontOne.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build());
        setContentView(R.layout.activity_main);


        //initial Fire-base
        //auth = FirebaseAuth.getInstance();
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");


        // Initial view
        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        rootLayout = (RelativeLayout)findViewById(R.id.rootLayout);


        // Event handling
        btnSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showSignUpDialog();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showLoginDailog();
            }
        });
    }


    //method for login
    private void showLoginDailog()
    {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("LOGIN");
        dialog.setMessage("Please use e-mail to login");

        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.layout_login,null);

        final MaterialEditText editEmail = login_layout.findViewById(R.id.editMail);
        final MaterialEditText editPassword = login_layout.findViewById(R.id.editPassword);



        dialog.setView(login_layout);

        //set button
        dialog.setPositiveButton("LOGIN", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.dismiss();

                //set disable button login if it is processing
                btnLogin.setEnabled(false);

                //check validation
                if (TextUtils.isEmpty(editEmail.getText().toString()))
                {

                    Snackbar.make(rootLayout,"Please enter your email address",Snackbar.LENGTH_SHORT)
                            .show();

                    return;
                }


                if (TextUtils.isEmpty(editPassword.getText().toString()))
                {

                    Snackbar.make(rootLayout,"Please enter your password",Snackbar.LENGTH_SHORT)
                            .show();

                    return;
                }

                if (editPassword.getText().toString().length() < 6)
                {

                    Snackbar.make(rootLayout,"Password too short !",Snackbar.LENGTH_SHORT)
                            .show();

                    return;
                }

                //show progress bar on main screen after login
                final SpotsDialog waitingDialog = new SpotsDialog(MainActivity.this);
                waitingDialog.show();

                //login
                mAuth.signInWithEmailAndPassword(editEmail.getText().toString(),editPassword.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>()
                        {
                            @Override
                            public void onSuccess(AuthResult authResult)
                            {
                                waitingDialog.dismiss(); //close progress bar
                                startActivity(new Intent(MainActivity.this,HomeActivity.class));
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener()
                        {
                            @Override
                            public void onFailure(@NonNull Exception e)
                            {
                                waitingDialog.dismiss(); //close progress bar
                                Snackbar.make(rootLayout,"failed "+e.getMessage(),Snackbar.LENGTH_SHORT)
                                        .show();


                                //Active button
                                btnLogin.setEnabled(true);
                            }
                        });
            }
        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.dismiss();
            }
        });

        dialog.show();

    }



    //method for sign up
    private void showSignUpDialog()
    {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("SIGN UP");
        dialog.setMessage("Please use e-mail to sign up");

        LayoutInflater inflater = LayoutInflater.from(this);
        View sign_up_layout = inflater.inflate(R.layout.layout_sign_up,null);

        final MaterialEditText editEmail = sign_up_layout.findViewById(R.id.editMail);
        final MaterialEditText editPassword = sign_up_layout.findViewById(R.id.editPassword);
        final MaterialEditText editName = sign_up_layout.findViewById(R.id.editName);
        final MaterialEditText editPhone = sign_up_layout.findViewById(R.id.editPhone);


        dialog.setView(sign_up_layout);

        //set button
        dialog.setPositiveButton("SIGN UP", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.dismiss();

                //check validation
                if (TextUtils.isEmpty(editEmail.getText().toString()))
                {

                    Snackbar.make(rootLayout,"Please enter your email address",Snackbar.LENGTH_SHORT)
                            .show();

                    return;
                }

                if (TextUtils.isEmpty(editPhone.getText().toString()))
                {

                    Snackbar.make(rootLayout,"Please enter your phone number",Snackbar.LENGTH_SHORT)
                            .show();

                    return;
                }

                if (TextUtils.isEmpty(editPassword.getText().toString()))
                {

                    Snackbar.make(rootLayout,"Please enter your password",Snackbar.LENGTH_SHORT)
                            .show();

                    return;
                }

                if (editPassword.getText().toString().length() < 6)
                {

                    Snackbar.make(rootLayout,"Password too short !",Snackbar.LENGTH_SHORT)
                            .show();

                    return;
                }

                //register new user or driver
                mAuth.createUserWithEmailAndPassword(editEmail.getText().toString(),editPassword.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>()
                        {
                            @Override
                            public void onSuccess(AuthResult authResult)
                            {
                                //save user to database
                                User user = new User();
                                user.setEmail(editEmail.getText().toString());
                                user.setName(editName.getText().toString());
                                user.setPhone(editPhone.getText().toString());
                                user.setPassword(editPassword.getText().toString());


                                //use email as key
                                users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>()
                                        {
                                            @Override
                                            public void onSuccess(Void aVoid)
                                            {
                                                Snackbar.make(rootLayout,"Sign up successful !",Snackbar.LENGTH_SHORT)
                                                        .show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener()
                                        {
                                            @Override
                                            public void onFailure(@NonNull Exception e)
                                            {
                                                Snackbar.make(rootLayout,"failed "+e.getMessage(),Snackbar.LENGTH_SHORT)
                                                        .show();
                                            }
                                        });

                            }
                        })
                        .addOnFailureListener(new OnFailureListener()
                        {
                            @Override
                            public void onFailure(@NonNull Exception e)
                            {
                                Snackbar.make(rootLayout,"failed "+e.getMessage(),Snackbar.LENGTH_SHORT)
                                        .show();
                            }
                        });

            }
        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {

                dialogInterface.dismiss();

            }
        });

        dialog.show();

    }
}
