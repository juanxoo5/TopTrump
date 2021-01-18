package com.example.toptrump.view.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
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

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.toptrump.R;
import com.example.toptrump.model.room.pojo.Usuario;
import com.example.toptrump.view.MainActivity;
import com.example.toptrump.viewmodel.ViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class UsuaFragment extends Fragment {

    private ViewModel viewModelActivity;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_usua, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModelActivity = new ViewModelProvider(this).get(ViewModel.class);

        navigation(view);
        //mostrarUsuarios(view);
        //init(view);

    }

    /*private void mostrarUsuarios(View view) {
        recyclerView = view.findViewById(R.id.rvamigos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AmigoRecyclerAdapter(amigo);
        recyclerView.setAdapter(adapter);

        viewModelActivity.getListaUsuarios().observe(getViewLifecycleOwner(), new Observer<List<Usuario>>() {
            @Override
            public void onChanged(List<Usuario> usuarios) {
                amigo.clear();
                amigo.addAll(amigos);
                adapter.notifyDataSetChanged();

                lista.clear();
                lista.addAll(amigos);
            }
        });
    }*/

    /*private void init(View view) {
        RecyclerView mi_recycler = view.findViewById(R.id.rvamigos);
        mi_recycler.addOnItemTouchListener(new RecyclerItemClickListener(this,mi_recycler, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Intent intent = new Intent(MainActivity.this, VerAmigo.class);
                //intent.putExtra("objeto2",amigo.get(position).toString());
                //startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
        mi_recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AmigoRecyclerAdapter(amigo);
        mi_recycler.setAdapter(adapter);
    }*/

    public void navigation(View view){

        MainActivity mainActivity = (MainActivity) view.getContext();
        Toolbar toolbar = view.findViewById(R.id.tbUsuariosFrgm);
        mainActivity.setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = view.findViewById(R.id.drawerLayoutUsua);
        NavigationView navigationView = view.findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph()).setOpenableLayout(drawerLayout).build();
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
        NavigationUI.setupActionBarWithNavController(mainActivity, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        NavController navController = Navigation.findNavController(this.getActivity(), R.id.nav_host_fragment);
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
    }

}