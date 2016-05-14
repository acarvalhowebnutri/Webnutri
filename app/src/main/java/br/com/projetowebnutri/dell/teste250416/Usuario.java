package br.com.projetowebnutri.dell.teste250416;

/**
 * Created by Dell on 22/04/2016.
 */
public class Usuario {

    private String nome;
    private String senha;
    private String peso;
    private String idade;
    private String username;
    private Integer id;

    public Usuario() {

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }




    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }


    public Usuario(String nome, String senha, String peso, String idade) {

        this.nome = nome;
        this.senha = senha;
        this.idade = idade;
        this.peso = peso;

    }
}
