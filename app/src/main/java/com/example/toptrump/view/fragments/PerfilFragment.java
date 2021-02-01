package com.example.toptrump.view.fragments;

import android.accounts.AccountManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toptrump.R;
import com.example.toptrump.view.MainActivity;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.AccountPicker;
import com.google.android.material.navigation.NavigationView;

import static android.app.Activity.RESULT_OK;

public class PerfilFragment extends Fragment {
    private static final int REQUEST_CODE_EMAIL = 1;
    private String accountName;
    private MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainActivity = (MainActivity) view.getContext();

        ImageView imageView = view.findViewById(R.id.UserProfileImage);
        TextView tvNombre = view.findViewById(R.id.UserProfileName);
        TextView tvResp = view.findViewById(R.id.UserQuestions);
        TextView tvRespCorrect = view.findViewById(R.id.UserQuestionsCorrect);
        Button btCorreo = view.findViewById(R.id.btEnvPunctuation);

        imageView.setImageResource(mainActivity.usuarioActivo.get(0).getAvatar());
        tvNombre.setText(mainActivity.usuarioActivo.get(0).getNombre());
        tvResp.setText("Respuestas realizadas: " + mainActivity.usuarioActivo.get(0).getNumRes());
        tvRespCorrect.setText("Respuestas correctas: " + mainActivity.usuarioActivo.get(0).getResCor());

        btCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = AccountPicker.newChooseAccountIntent(null, null,
                            new String[] { GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE }, false, null, null, null, null);
                    startActivityForResult(intent, REQUEST_CODE_EMAIL);
                } catch (ActivityNotFoundException e) { }
            }
        });

        NavController navController = new NavController(view.getContext());
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_EMAIL && resultCode == RESULT_OK) {
            accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",accountName, null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "TopTrump - Puntuaciones");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "¡Hola soy tu usuario " +
                    mainActivity.usuarioActivo.get(0).getNombre() + " tu puntuacion hasta ahora es de "
                    + mainActivity.usuarioActivo.get(0).getResCor() + " respuestas correctas, intenta superarte!!!");
            startActivity(Intent.createChooser(emailIntent,  getActivity().getString(R.string.enviar_mail)));
        }
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