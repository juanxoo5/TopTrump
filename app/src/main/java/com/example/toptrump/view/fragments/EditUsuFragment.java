package com.example.toptrump.view.fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.toptrump.R;
import com.example.toptrump.model.room.pojo.Usuario;
import com.example.toptrump.view.MainActivity;
import com.example.toptrump.viewmodel.ViewModel;
import com.google.android.material.navigation.NavigationView;

public class EditUsuFragment extends Fragment {

    ViewModel viewmodel;
    Usuario usuario;
    EditText etnombre;
    int avatar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_usu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewmodel = new ViewModelProvider(this).get(ViewModel.class);


        Button btEditar = view.findViewById(R.id.btEditar);
        Button btEditarAvatar = view.findViewById(R.id.btEditarAvatar);
        ImageView imageView = view.findViewById(R.id.imgAvatar2);
        etnombre = view.findViewById(R.id.etEditNombreUsuario);

        Bundle bundle = new Bundle();

        Long id = getArguments().getLong("Id");
        String nombre = getArguments().getString("Nombre");
        etnombre.setText(nombre);

        try {
            avatar = getArguments().getInt("Imagen");
        } catch( NullPointerException e){ }
        int numResp = getArguments().getInt("NumResp");
        int respCorrecta = getArguments().getInt("RespCor");


        navigation(view);

        NavController navController = new NavController(view.getContext());

        imageView.setImageResource(avatar);
        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreGuardado = etnombre.getText().toString();
                if(nombreGuardado.isEmpty()){
                    etnombre.setError("Este campo no puede estar vacío");
                } else {
                    usuario = new Usuario(nombreGuardado, avatar, numResp, respCorrecta);
                    usuario.setId(id);
                    Log.v("XYZ", usuario.toString());
                    viewmodel.updateUsuario(usuario);
                    NavHostFragment.findNavController(EditUsuFragment.this).navigate(R.id.action_editUsuaFragment_to_admUsuaFragment);
                }
            }
        });

        btEditarAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(EditUsuFragment.this).navigate(R.id.action_editUsuaFragment_to_editAvatarFragment);
            }
        });

    }

    public void navigation(View view){

        MainActivity mainActivity = (MainActivity) view.getContext();
        Toolbar toolbar = view.findViewById(R.id.tbEditUsuFrgm);
        mainActivity.setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = view.findViewById(R.id.drawerLayoutEditUsu);
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