package cm.aptoide.myapplication.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import cm.aptoide.myapplication.NavFragments.DashboardFragment;
import cm.aptoide.myapplication.NavFragments.ProfileFragment;
import cm.aptoide.myapplication.NavFragments.UserFragment;
import cm.aptoide.myapplication.R;

public class HomeActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //hide status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        bottomNavigationView=findViewById(R.id.bottomnav);
        bottomNavigationView.setOnItemSelectedListener(this);
        loadFragment(new DashboardFragment());


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment=null;
         switch (item.getItemId()){
             case R.id.dashboard:
                 fragment=new DashboardFragment();
                 break;
             case R.id.profile:
                 fragment=new ProfileFragment();
                 break;
             case R.id.users:
                 fragment=new UserFragment();

             }
        if (fragment!=null){
            loadFragment(fragment);
         }
        return true;
    }

    void loadFragment(Fragment fragment){
        //to attach fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.relativelayout,fragment).commit();

    }
}