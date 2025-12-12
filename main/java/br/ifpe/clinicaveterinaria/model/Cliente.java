package br.ifpe.clinicaveterinaria.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {

    private List<Animal>animais;

    public Cliente(int idPessoa, String nome, String telefone){
        super(idPessoa, nome, telefone);
        this.animais = new ArrayList<>();
    }
    public void adicionarAnimal(Animal animal){
        animais.add(animal);
    }
    @Override
    public void exibirDados(){
        System.out.println("cliente:" + nome +"-Tel:"+ telefone);
        System.out.println("Animais:" + animais.size());

    }

    public List<Animal> getAnimais() {
        return this.animais;
    }

    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }    

}