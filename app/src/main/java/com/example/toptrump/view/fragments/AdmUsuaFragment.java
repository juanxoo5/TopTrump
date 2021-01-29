package com.example.toptrump.view.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toptrump.R;
import com.example.toptrump.model.room.pojo.Usuario;
import com.example.toptrump.view.MainActivity;
import com.example.toptrump.view.adapter.AdminUsu.AdminUsuAdapter;
import com.example.toptrump.view.adapter.Usuarios.UsuariosAdapter;
import com.example.toptrump.viewmodel.ViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class AdmUsuaFragment extends Fragment {

    private ViewModel viewModelActivity;
    private RecyclerView recyclerView;
    private AdminUsuAdapter adapter;
    private static List<Usuario> usuarioLista;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_adm_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModelActivity = new ViewModelProvider(this).get(ViewModel.class);
        navigation(view);
        init(view);

        NavController navController = new NavController(view.getContext());

    }

    public void navigation(View view){

        MainActivity mainActivity = (MainActivity) view.getContext();
        Toolbar toolbar = view.findViewById(R.id.tbAdmUserFrgm);
        mainActivity.setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = view.findViewById(R.id.drawerLayoutAdmUser);
        NavigationView navigationView = view.findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph()).setOpenableLayout(drawerLayout).build();
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
        NavigationUI.setupActionBarWithNavController(mainActivity, navController, appBarConfiguration);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.claveFragment:
                        navController.navigate(R.id.claveFragment);
                        return true;
                    case R.id.juegoFragment:
                        if(!mainActivity.usuarioActivo.isEmpty()){
                            navController.navigate(R.id.juegoFragment);
                        }else {
                            navController.navigate(R.id.usuaFragment);
                        }
                        return true;
                    case R.id.perfilFragment:
                        if(mainActivity.usuarioActivo.isEmpty()){
                            Toast toast = Toast.makeText(view.getContext()," Selecciona antes un usuario ", Toast.LENGTH_SHORT);
                            toast.getView().setBackgroundColor(Color.RED);
                            toast.show();
                        } else {
                            navController.navigate(R.id.perfilFragment);
                        }
                        return true;
                }
                return true;
            }

        });

    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.rvAdminUsur);
        recyclerView.setHasFixedSize(true);
        adapter = new AdminUsuAdapter(new AdminUsuAdapter.UsuarioDiff());

        viewModelActivity.getListaUsuarios().observe(getViewLifecycleOwner(), new Observer<List<Usuario>>() {
            @Override
            public void onChanged(List<Usuario> usuarios) {
                adapter.submitList(usuarios);
                usuarioLista = usuarios;
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_admin, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        NavController navController = Navigation.findNavController(this.getActivity(), R.id.nav_host_fragment);
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
    }

}