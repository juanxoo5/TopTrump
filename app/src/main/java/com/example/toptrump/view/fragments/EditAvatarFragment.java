package com.example.toptrump.view.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.toptrump.R;
import com.example.toptrump.model.room.pojo.Usuario;
import com.example.toptrump.view.MainActivity;
import com.example.toptrump.view.adapter.Avatar.CrearAvatarAdapter;
import com.example.toptrump.view.adapter.Avatar.EditarAvatarAdapter;
import com.example.toptrump.viewmodel.ViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class EditAvatarFragment extends Fragment {

    private ViewModel viewModelActivity;
    private RecyclerView recyclerView;
    private EditarAvatarAdapter adapter;
    private static List<Usuario> ImagenLista;
    private RecyclerView.LayoutManager layoutManager;
    private NavController navController;
    private int[] sliderImagesId = new int[]{
            R.drawable.avatar, R.drawable.chicobasura, R.drawable.chicocalvo,
            R.drawable.chicoelegante, R.drawable.chicahipster, R.drawable.chicainteligente,
            R.drawable.chicarubia
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_avatar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        MainActivity mainActivity = (MainActivity) view.getContext();
        Toolbar toolbar = view.findViewById(R.id.tbEditAvatarFrgm);
        mainActivity.setSupportActionBar(toolbar);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);

        init(view);

    }

    private void init(View view) {

        recyclerView = view.findViewById(R.id.rvEditAvatares);
        recyclerView.setHasFixedSize(true);
        adapter = new EditarAvatarAdapter(sliderImagesId);
        recyclerView.setAdapter(adapter);
        layoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);
    }
}