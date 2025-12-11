package br.ifpe.jaboatao.clinicaveterinaria.model;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {

    private int idCliente;
    private List<Animal>animais;

    public Cliente(int idCliente, String nome, String telefone){
        super(nome,telefone);
        this.idCliente = idCliente;
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

    public int getIdCliente() {
        return this.idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public List<Animal> getAnimais() {
        return this.animais;
    }

    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }

}
