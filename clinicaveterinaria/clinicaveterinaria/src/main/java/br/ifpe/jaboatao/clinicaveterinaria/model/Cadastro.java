package br.ifpe.jaboatao.clinicaveterinaria.model;
import java.util.ArrayList;
import java.util.List;

public class Cadastro {

    private List<Cliente> clientes;
    private List<Veterinario> veterinarios;
    private List<Animal> animais;
    private List<Consulta> consultas;

    public Cadastro() {
        this.clientes = new ArrayList<>();
        this.veterinarios = new ArrayList<>();
        this.animais = new ArrayList<>();
        this.consultas = new ArrayList<>();
    }
    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso: " + cliente.nome);
    }

    public void cadastrarVeterinario(Veterinario veterinario) {
        veterinarios.add(veterinario);
        System.out.println("Veterinário cadastrado com sucesso: " + veterinario.nome);
    }

    public void cadastrarAnimal(Animal animal) {
        animais.add(animal);
        System.out.println("Animal cadastrado com sucesso: " + animal.getNome());
    }

    public void cadastrarConsulta(Consulta consulta) {
        consultas.add(consulta);
        System.out.println("Consulta cadastrada com sucesso: " + consulta);
    }
    public List<Cliente> getClientes() {
        return this.clientes;
    }
    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    public List<Veterinario> getVeterinarios() {
        return veterinarios;
    }
    public void setVeterinarios(List<Veterinario> veterinarios) {
        this.veterinarios = veterinarios;
    }
    public List<Animal> getAnimais() {
        return animais;
    }
    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }
    public List<Consulta> getConsultas() {
        return consultas;
    }
    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }
}