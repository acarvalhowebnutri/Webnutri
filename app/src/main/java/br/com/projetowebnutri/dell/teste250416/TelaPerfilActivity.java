package br.com.projetowebnutri.dell.teste250416;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TelaPerfilActivity extends AppCompatActivity {

    LoginDataBaseAdapter loginDataBaseAdapter;
    TextView nome, peso, idade;
    EditText ednome;
    Button alterar;

    Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil);

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);



        Intent intent = getIntent();
        Bundle params = intent.getExtras();
        //  TextView textView = new TextView(this);
        //TextView textView2 = new TextView(this);

        //ednome = (EditText) findViewById(R.id.editText6);
        nome = (TextView) findViewById(R.id.textViewExibeNome);
        idade =		(TextView) findViewById(R.id.textViewExibeIdade);
        peso=		(TextView) findViewById(R.id.textViewExibePeso);


        alterar = (Button) findViewById(R.id.btnAlterar);

        if (params != null) {
            String recebe = params.getString("mensagem");
            loginDataBaseAdapter = loginDataBaseAdapter.open();
            usuario = loginDataBaseAdapter.read(recebe);
            nome.setText(usuario.getNome());
            idade.setText(usuario.getIdade());
            peso.setText(usuario.getPeso());
//setContentView(nome);
            // setContentView(textView2);

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String userName=nome.getText().toString();


                if (userName!="") {
                    Intent alterar = new Intent(TelaPerfilActivity.this, AlterarActivity.class);
                    Bundle params = new Bundle();

                    params.putString("mensagem", userName);
                    alterar.putExtras(params);
                    startActivity(alterar);
                }else{
                    Toast.makeText(TelaPerfilActivity.this, "\n" +
                            "Nome de usuário ou senha não corresponde", Toast.LENGTH_LONG).show();
                }
            }
        });



        }


    }


}


/*
        Bundle params = intent.getExtras();


        if (params != null) {
            String userName = params.getString("mensagem");
            String storedPassword = loginDataBaseAdapter.getSinlgeEntry(userName);

            textView.setText(storedPassword);
            setContentView(textView);
        }

        String userName = params.getString("mensagem");
*/