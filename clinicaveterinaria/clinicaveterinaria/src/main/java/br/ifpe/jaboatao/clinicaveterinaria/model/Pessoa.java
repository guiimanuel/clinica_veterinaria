package br.ifpe.jaboatao.clinicaveterinaria.model;
public abstract class Pessoa {
  protected String nome;
  protected String telefone;

  public Pessoa(String nome, String telefone){
    this.nome = nome;
    this.telefone = telefone;
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