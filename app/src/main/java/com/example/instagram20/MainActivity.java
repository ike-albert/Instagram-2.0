package com.example.instagram20;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.instagram20.fragments.ComposeFragment;
import com.example.instagram20.fragments.PostsFragment;
import com.example.instagram20.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment;
            switch (menuItem.getItemId()) {
                case R.id.action_home:
//                    Toast.makeText(MainActivity.this, "Home!", Toast.LENGTH_SHORT).show();
                    fragment = new PostsFragment();
//                        return true;
                    break;

                case R.id.action_compose:
//                    Toast.makeText(MainActivity.this, "Compose!", Toast.LENGTH_SHORT).show();
                    fragment = new ComposeFragment();

//                        return true;
                    break;

                case R.id.action_profile:
//                    Toast.makeText(MainActivity.this, "Profile!", Toast.LENGTH_SHORT).show();
                    fragment = new ProfileFragment();
//                   return true;
                    break;
                default:
                    fragment = new PostsFragment(); //Default fragment is the home page/posts
//                    return true;

            }
            fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
            return true;
        }
    });

    //Set default selction for the Framelayout
        bottomNavigationView.setSelectedItemId(R.id.action_home);

    }

    //Should find another way to log out. Maybe in profile
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //if the user wants to logout
        if (item.getItemId() == R.id.logout){
            //User want to logout
            Intent i = new Intent(this, LoginActivity.class);
            LoginActivity.logOut(true);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }




}