package br.com.projetowebnutri.dell.teste250416;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {

    EditText editTextUserName,editTextPassword,editTextConfirmPassword, editPeso, editIdade;
    Button btnCreateAccount;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrar);

        // get Instance  of Database Adapter
        loginDataBaseAdapter    =  new  LoginDataBaseAdapter(this);
        loginDataBaseAdapter    =       loginDataBaseAdapter.open();

        // Get Refferences of Views
        editTextUserName        =(EditText)findViewById(R.id.editTextUserName);
        editTextPassword        =(EditText)findViewById(R.id.editTextPassword);
        editTextConfirmPassword =(EditText)findViewById(R.id.editTextConfirmPassword);
        editIdade               =(EditText)findViewById(R.id.editIdade);
        editPeso                =(EditText)findViewById(R.id.editPeso);


        btnCreateAccount        =(Button)findViewById(R.id.buttonCreateAccount);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

                String userName         =editTextUserName.getText().toString();
                String password         =editTextPassword.getText().toString();
                String confirmPassword  =editTextConfirmPassword.getText().toString();
                String idade            = editIdade.getText().toString();
                String peso             = editPeso.getText().toString();

                // Valida campo senha
                if(userName.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Preencha o campo usuario", Toast.LENGTH_LONG).show();
                    return;
                }

                // Valida campo senha
                if(password.equals("")||confirmPassword.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Campo senha ou confirmação de senha em branco", Toast.LENGTH_LONG).show();
                    return;
                }

                // Valida campo idade
                if(userName.equals("")||idade.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Preencha o campo idade", Toast.LENGTH_LONG).show();
                    return;
                }

                // Valida campo peso
                if(userName.equals("")||peso.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Preencha o campo peso", Toast.LENGTH_LONG).show();
                    return;
                }




                // Valida se a senha são diferentes
                if(!password.equals(confirmPassword))
                {
                    Toast.makeText(getApplicationContext(), "Senhas diferentes", Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
                    // Save the Data in Database
                    loginDataBaseAdapter.insertEntry(userName, password, idade, peso);
                    Toast.makeText(getApplicationContext(), "Conta criada com sucesso", Toast.LENGTH_LONG).show();
                    Intent voltar=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(voltar);
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        loginDataBaseAdapter.close();
    }
}
