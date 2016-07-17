package com.cor.luis.administrator.pruebaandroid.vista.fragments;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cor.luis.administrator.pruebaandroid.R;
import com.cor.luis.administrator.pruebaandroid.controlador.controladorJson.loginJSON.JsonServicioLogin;
import com.cor.luis.administrator.pruebaandroid.controlador.controladorJson.prospectoJSON.JsonServicioProspecto;
import com.cor.luis.administrator.pruebaandroid.controlador.dao.CrudLogin;
import com.cor.luis.administrator.pruebaandroid.modelo.Login;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLogin extends Fragment {

    boolean esValido;
    public FragmentLogin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_login, container, false);


        //obtener los datos de los controles
        Button loginbtn= (Button) view.findViewById(R.id.loginstartbutton);
        final EditText email = (EditText) view.findViewById(R.id.emailtxt);
        final EditText password = (EditText)view.findViewById(R.id.passwordtxt);

        //verificar validez del email
        validarEmail(email);

        //eventar el ingresar
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(esValido){

                    try {

                        URL url = new URL("http://directotesting.igapps.co/application/login?email="+email.getText().toString().trim()+"&amp;password="+password.getText().toString().trim());

                        JsonServicioLogin servicioLogin = new JsonServicioLogin();
                        servicioLogin.execute(url);
                        Login login = servicioLogin.get();
                        login.setPassword(password.getText().toString().trim());

                        if(login.getToken().contains("error")){
                            Toast.makeText(getActivity().getApplicationContext(),"Autenticacion no valida",Toast.LENGTH_SHORT).show();
                        }else
                        {
                            CrudLogin crudLogin = new CrudLogin(getActivity().getApplicationContext());
                            crudLogin.insertar(login);

                            //enviar el token a la actividad de prospectos
                            Fragment fragment = new FragmentProspecto();
                            Bundle bundle = new Bundle();
                            bundle.putString("TOKEN",login.getToken());
                            fragment.setArguments(bundle);

                            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                            getActivity().getFragmentManager().beginTransaction().replace(R.id.contentMag, fragment).commit();
                        }
                    }
                    catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                }else{
                    email.setError("El correo electronico es invalido");
                }
            }
        });
        return view;
    }

    private void validarEmail(final EditText email) {
        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        final String emailtxt = email.getText().toString().trim();
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (email.getText().toString().trim().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+") && s.length() > 0)
                {
                    esValido=true;
                }
                else
                {
                    esValido=false;
                }
            }
        });
    }

}
