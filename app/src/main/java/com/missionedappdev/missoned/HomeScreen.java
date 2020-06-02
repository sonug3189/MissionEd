package com.missionedappdev.missoned;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.missionedappdev.missoned.ui.gallery.quizoesFragment;

public class HomeScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView btnAct1,btnAct2,btnAct3,btnAct4;

    private AppBarConfiguration mAppBarConfiguration;
    public Student student;
    public String uname,email,id,uname1;
    public FirebaseAuth firebaseAuth;
    public TextView tvUsername,tvEmail,displayUsername;

    public DrawerLayout drawer;
    public Toolbar toolbar;
    public NavController navController;
    public NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        /*if(savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashboardFrag()).commit();
            navigationView.setCheckedItem(R.id.nav_dashboard);
        }*/

        // Passing each menu ID as a set of Ids because each
        // nothing
        // menu should be considered as top level destinations.

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_dashboard, R.id.nav_quizoes, R.id.nav_premium, R.id.nav_settings, R.id.nav_terms)
                .setDrawerLayout(drawer)
                .build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        View headerView=navigationView.getHeaderView(0);

         tvUsername=(TextView) headerView.findViewById(R.id.tvUsername);
         tvEmail=(TextView) headerView.findViewById(R.id.tvEmail);
         displayUsername=findViewById(R.id.displayUsername);
        email=getIntent().getStringExtra("Email");
        firebaseAuth = FirebaseAuth.getInstance();

        id=getIntent().getStringExtra("id");
        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("student").child(id);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                student = dataSnapshot.getValue(Student.class);

                uname = student.getName();
                if(uname!=null) {
                    tvUsername.setText(uname);
                    displayUsername.setText(uname);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(HomeScreen.this, " data base error " , Toast.LENGTH_SHORT).show();
            }
        });


        tvEmail.setText(email);

        if(uname!=null) {
            tvUsername.setText(uname);
            displayUsername.setText(uname);
        }


        /*
        * Buttons for navigation to various activities : change them as needed in content_main.xml
        * */

        btnAct1=findViewById(R.id.physics); btnAct2=findViewById(R.id.chemistry);
        btnAct3=findViewById(R.id.maths); btnAct4=findViewById(R.id.biology);

        btnAct1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Step","Activity 1 opened");
                startActivity(new Intent(HomeScreen.this, Activity1.class));
            }
        });
        btnAct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Step","Activity 2 opened");

                startActivity(new Intent(HomeScreen.this,Activity2.class));
            }
        });
        btnAct3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Step","Activity 3 opened");

                startActivity(new Intent(HomeScreen.this,Activity3.class));
            }
        });
        btnAct4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Step","Activity 4 opened");

                startActivity(new Intent(HomeScreen.this,Activity4.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        if(id==R.id.action_settings)
        {
            firebaseAuth.signOut();
            Intent intent = new Intent(HomeScreen.this,LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return  true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        //int id=menuItem.getItemId();

        menuItem.setChecked(true);
        drawer.closeDrawers();

        switch (menuItem.getItemId()){

            case R.id.nav_dashboard:
                Log.d("Selected:","Dashboard");
               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashboardFrag()).commit();
                Toast.makeText(this, "Profile Selected", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_quizoes:
                Log.d("Selected:","Quizzoes");
                Toast.makeText(this, "Quizzos Selected", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new quizoesFragment()).commit();
                break;

            case R.id.nav_premium:
                Log.d("Selected:","Premium");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new premium_fragment()).commit();
                Toast.makeText(this, "Purchase Selected", Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.nav_premium);
                break;

            case R.id.nav_settings:
                Log.d("Selected:","Settings");
                Toast.makeText(this, "Settings Selected", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new SettingsActivity.SettingsFragment()).commit();
                break;

            case R.id.nav_terms:
                Log.d("Selected:","Terms");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragment_terms_new()).commit();
                //Intent j = new Intent(HomeScreen.this,terms_fragment.class);
                Toast.makeText(this, "T&C Selected", Toast.LENGTH_SHORT).show();
                //startActivity(j);
                // navController.navigate(R.id.nav_terms);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
