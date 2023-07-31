package com.b07finalproject_group9.login;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.b07finalproject_group9.LoginModel;
import com.b07finalproject_group9.R;
import com.tempfragments.ShopperDashboard;

public class ShopperLoginFragment extends Fragment {

    private void performShopperLogin(String username, String password){
        LoginModel lm = new LoginModel();
        lm.loginShopper(username, password)
                .thenAccept(success -> {
                    if (success) {
                        Fragment f = new ShopperDashboard();
                        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                        ft.replace(R.id.main_login_redirect, f).commit();
                    } else {
                        Toast.makeText(getActivity(), "Login Unsuccessful",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopperlogin, container, false);
        EditText username_editText = view.findViewById(R.id.shopper_username_editText);
        EditText password_editText = view.findViewById(R.id.owner_password_editText);
        Button btnLogin = view.findViewById(R.id.shopper_login_button);
        Button btnRedirectSignUp = view.findViewById(R.id.shopper_redirect_signup);


        //Do Login On Clicking Login
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String username = username_editText.getText().toString();
                String password = password_editText.getText().toString();
                performShopperLogin(username, password);
            }
        });

        btnRedirectSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Fragment f = new ShopperSignUpFragment();
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.main_login_redirect, f).commit();
            }
        });

        return view;
    }


}

