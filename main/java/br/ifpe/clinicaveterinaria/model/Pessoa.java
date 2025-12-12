package br.ifpe.clinicaveterinaria.model;

public abstract class Pessoa {
    protected int idPessoa;
    protected String nome;
    protected String telefone;

    public Pessoa(int idPessoa, String nome, String telefone){
    this.idPessoa = idPessoa;
    this.nome = nome;
    this.telefone = telefone;
    }

    public int getIdPessoa() {
        return this.idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public abstract void exibirDados();
}