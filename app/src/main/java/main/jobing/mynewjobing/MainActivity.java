package main.jobing.mynewjobing;

import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import main.jobing.mynewjobing.ui.employee.EmployeeFragment;
import main.jobing.mynewjobing.ui.mainfragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //BottomNavigationView navView = findViewById(R.id.nav_view);

       /* AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();*/
  /*      getSupportFragmentManager().beginTransaction()
                .add(R.id.idMainFragment, new EmployeeFragment(), null)
                .addToBackStack(null)
                .commit();*/

        getSupportFragmentManager().beginTransaction().add(R.id.container, new MainFragment())
                .commit();
       /* getSupportFragmentManager().beginTransaction().replace(R.id.container, new EmployeeFragment())
                .commit();*/
        /*NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);*/
    }
}