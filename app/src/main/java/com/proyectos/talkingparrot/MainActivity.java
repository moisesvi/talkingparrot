package com.proyectos.talkingparrot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.proyectos.talkingparrot.Data.Create;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    Fragment frgInicio, frgTemas, frgChat, frgResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //drawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        BottomNavigationView navigation = findViewById(R.id.bttNavigationView);
        frgChat = new ChatFragment();
        frgInicio = new InicioFragment();
        frgResultado = new ResultadoFragment();
        frgTemas = new TemasFragment();

        replaceFragment(frgInicio);

        //estamos creando el tema
        Create.createTema();


        //partes del drawer
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                        .build();


        //opciones de drawer
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_home){
                    Toast.makeText(MainActivity.this, "Opción Home seleccionada", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.nav_gallery) {
                    Toast.makeText(MainActivity.this, "Opción Gallery seleccionada", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.nav_slideshow) {
                    Toast.makeText(MainActivity.this, "Opción Slideshow seleccionada", Toast.LENGTH_SHORT).show();
                }

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });


        //no tocar Partes del navegable
        navigation.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.inicio){
                replaceFragment(frgInicio);
            } else if (item.getItemId() == R.id.temas) {
                replaceFragment(frgTemas);
                //navigation.setVisibility(View.GONE);
                Intent intent = new Intent(this, Tematica.class);
                startActivity(intent);
            } else if (item.getItemId() == R.id.chat) {
                replaceFragment(frgChat);
            } else if (item.getItemId() == R.id.resultado) {
                replaceFragment(frgResultado);
            }

            return true;
        });
    }


    //no tocar
    @Override
    public void onBackPressed() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frmLayoutMain);
        if (currentFragment instanceof TemasFragment || currentFragment instanceof ChatFragment || currentFragment instanceof ResultadoFragment){
            replaceFragment(frgInicio);
            BottomNavigationView navigation = findViewById(R.id.bttNavigationView);
            navigation.setVisibility(View.VISIBLE);
            navigation.getMenu().findItem(R.id.inicio).setChecked(true);
        } else {
            // Si estamos en el fragmento de inicio, dejar que el botón "Back" funcione como lo hace normalmente
            super.onBackPressed();
        }
    }

    //no tocar
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frmLayoutMain, fragment);
        fragmentTransaction.commit();
    }

    //para la actividad temática
    private void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);

        //aquí hacer el llamada a los elementos dialog.findBysjsj

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }
}