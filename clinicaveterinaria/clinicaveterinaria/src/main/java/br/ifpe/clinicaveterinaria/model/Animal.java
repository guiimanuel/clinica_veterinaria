package br.ifpe.clinicaveterinaria.model;

public class Animal {
    private int idAnimal;
    private String nome;
    private String especie;
    private int idade;
    private Cliente dono;

    public Animal(int idAnimal, String nome, String especie, int idade, Cliente dono) {
        this.idAnimal = idAnimal;
        this.nome = nome;
        this.especie = especie;
        this.idade = idade;
        this.dono = dono;
    }

    public int getIdAnimal() {
        return this.idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return this.especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getIdade() {
        return this.idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Cliente getDono() {
        return this.dono;
    }

    public void setDono(Cliente dono) {
        this.dono = dono;
    }

}
