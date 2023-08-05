package com.b07finalproject_group9.login;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.b07finalproject_group9.MainActivity;
import com.b07finalproject_group9.R;
import com.b07finalproject_group9.objects.User;
import com.b07finalproject_group9.owner.OwnerDashboardFragment;

public class OwnerLoginFragment extends Fragment {

    private void performOwnerLogin(String username, String password){
        LoginModel lm = new LoginModel();
        lm.loginOwner(username, password)
                .thenAccept(success -> {
                    if (success) {
                        MainActivity.currUser = new User(username);
                        Fragment f = new OwnerDashboardFragment();
                        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                        ft.replace(R.id.main_login_redirect, f).commit();
                    } else {
                        Toast.makeText(getActivity(), "Login Unsuccessful",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setSpinner(View view,int i){
        Spinner spinner;
        spinner = (Spinner) view.findViewById(R.id.spinner);
        if(spinner!=null)spinner.setSelection(i);
    }

    private void openShopperSignup(){
        Fragment f = new ShopperSignUpFragment();
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.main_login_redirect, f).commit();
    }

    private void openOwnerLogin(){
        Fragment f = new OwnerLoginFragment();
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.main_login_redirect, f).commit();
    }

    private void openOwnerSignup(){
        Fragment f = new OwnerSignUpFragment();
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.main_login_redirect, f).commit();
    }

    private void  openShopperLogin(){
        Fragment f = new ShopperLoginFragment();
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.main_login_redirect, f).commit();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ownerlogin, container, false);
        setSpinner(view,2);
        EditText username_editText = view.findViewById(R.id.username_editText);
        EditText password_editText = view.findViewById(R.id.password_editText);
        Button btnLogin = view.findViewById(R.id.owner_login_button);
        Spinner spinner = view.findViewById(R.id.spinner);
        Button menu_button = view.findViewById(R.id.menu_button);
        menu_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OwnerLoginFragment.this.getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });


        //Do Login On Clicking Login
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String username = username_editText.getText().toString();
                String password = password_editText.getText().toString();
                performOwnerLogin(username, password);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(spinner.getSelectedItem().toString().contains("shopper signup")){
                    openShopperSignup();
                }

//                if(spinner.getSelectedItem().toString().contains("owner login")){
//                    openOwnerLogin();
//                }

                if(spinner.getSelectedItem().toString().contains("owner signup")){
                    openOwnerSignup();
                }

                if(spinner.getSelectedItem().toString().contains("shopper login")){
                    openShopperLogin();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Nothing
            }
        });



        return view;
    }


}

