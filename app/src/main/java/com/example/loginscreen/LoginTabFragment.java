package com.example.loginscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatCallback;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginTabFragment extends Fragment {

    EditText edit_email, edit_pass;
    TextView edit_forgetPass;
    ProgressBar progressBar;
    Button buttonLogin;
    float v = 0;
    Message message = new Message();


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        edit_email = root.findViewById(R.id.email);
        edit_pass = root.findViewById(R.id.password);
        edit_forgetPass = root.findViewById(R.id.forget_pass);
        buttonLogin = root.findViewById(R.id.button);
        progressBar = root.findViewById(R.id.progressbar);

        //animation
        edit_email.setTranslationX(800);
        edit_pass.setTranslationX(800);
        edit_forgetPass.setTranslationX(800);
        buttonLogin.setTranslationX(800);

        edit_email.setAlpha(v);
        edit_pass.setAlpha(v);
        edit_forgetPass.setAlpha(v);
        buttonLogin.setAlpha(v);

        edit_email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        edit_pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        edit_forgetPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(600).start();
        buttonLogin.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edit_email.getText().toString();
                String senha = edit_pass.getText().toString();

                if (email.isEmpty() || senha.isEmpty()) {
                    message.Print(v, false, "Preencha todos os campos");

                } else {
                    AutenticarUsuario(v);
                }
            }
        });

        return root;
    }

    private void AutenticarUsuario(View view) {
        String email = edit_email.getText().toString();
        String senha = edit_pass.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.VISIBLE);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mainActivity();
                        }
                    }, 3000);
                } else {
                    String erro;

                    try {
                        throw task.getException();
                    } catch (Exception e) {
                        erro = "Erro ao logar usuário";
                    }
                    message.Print(view, false, erro);
                }
            }
        });
    }

    private void mainActivity() {
        Intent intent = new Intent(LoginTabFragment.this.getActivity(), MainActivity2.class);
        startActivity(intent);
        //finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser();

        if (usuarioAtual != null) {
            mainActivity();
        }
    }
}

