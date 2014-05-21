package br.ufg.inf.vendaingresso;

/**
 *
 * @author Danilo Lopes
 */
public class Funcionario {
    private String nome;
    private String cpf; 
    private String login;
    private String senha;

    public Funcionario() {
    
    }
    
    public Funcionario(String nome, String cpf, String login, String senha) {
        this.nome = nome;
        this.cpf = cpf; 
        this.login = login;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
