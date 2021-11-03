package com.example.loginscreen;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignupTabFragment extends Fragment {

    EditText edit_nome, edit_email, edit_password, edit_confirm_password;
    Button button;
    String usuarioID;
    float v = 0;
    Message message = new Message();


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment, container, false);

        edit_nome = root.findViewById(R.id.nome);
        edit_email = root.findViewById(R.id.email);
        edit_password = root.findViewById(R.id.password);
        edit_confirm_password = root.findViewById(R.id.confirm_password);
        button = root.findViewById(R.id.button);

        edit_nome.setTranslationX(800);
        edit_email.setTranslationX(800);
        edit_password.setTranslationX(800);
        edit_confirm_password.setTranslationX(800);
        button.setTranslationX(800);

        edit_nome.setAlpha(v);
        edit_email.setAlpha(v);
        edit_password.setAlpha(v);
        edit_confirm_password.setAlpha(v);
        button.setAlpha(v);

        edit_nome.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        edit_email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        edit_password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(600).start();
        edit_confirm_password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        button.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(800).start();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edit_nome.getText().toString();
                String email = edit_email.getText().toString();
                String pass = edit_password.getText().toString();
                if (nome.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                    message.Print(v, false, "Preencha todos os campos");
                } else {
                    CadastrarUsuario(v);

                }
            }
        });

        return root;
    }

    private void CadastrarUsuario(View v) {
        String email = edit_email.getText().toString();
        String pass = edit_password.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    SalvarDadosUsuario();

                    message.Print(v, true, "Cadastrado com sucesso!");
                } else {
                    String erro;
                    try {
                        throw task.getException();

                    } catch (FirebaseAuthWeakPasswordException e) {
                        erro = "Digite uma senha com mais de 6 caracteres";
                    } catch (FirebaseAuthUserCollisionException e) {
                        erro = "Esta conta já foi cadastrada";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erro = "Email inválido";
                    } catch (Exception e) {
                        erro = "Erro ao cadastrar usuário";
                    }

                    message.Print(v, false, erro);
                }
            }
        });
    }

    private void SalvarDadosUsuario() {
        String nome = edit_nome.getText().toString();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> usuarios = new HashMap<>();
        usuarios.put("nome", nome);
        /*
        outros cadastros
        usuarios.put("telefone", telefone);
        usuarios.put("endereço", endereço);
        */
        //Obter o usuáirio atual e pegar o ID de cada usuário
        //referencia do firebase auth, usuário atual, id do usuário
        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);
        documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("db", "Sucesso ao salvar os dados");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("db_error", "Erro ao salvar os dados" + e.toString());

            }
        });


    }

//    private void Mensagem(View v, Boolean aBoolean, String texto) {
//
//        // Snackbar snackbar = Snackbar.make(v, mensagens[aBoolean == true ? 1 : 0], Snackbar.LENGTH_SHORT);
//        Snackbar snackbar = Snackbar.make(v, texto, Snackbar.LENGTH_SHORT);
//        snackbar.setBackgroundTint(aBoolean == true ? Color.GREEN : Color.RED);
//        snackbar.setTextColor(Color.WHITE);
//        snackbar.show();
//    }

}
