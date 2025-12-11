package br.ifpe.jaboatao.clinicaveterinaria.model;
public class Veterinario extends Pessoa {
    private int idVeterinario;
    private String especialidade;

    public Veterinario(int idVeterinario, String nome, String telefone, String especialidade) {
        super(nome, telefone);
        this.idVeterinario = idVeterinario;
        this.especialidade = especialidade;
    }

    @Override
    public void exibirDados() {
        System.out.println("Veterinário: " + nome + " - Especialidade: " + especialidade);
    }

    public int getIdVeterinario() {
        return this.idVeterinario;
    }

    public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public String getEspecialidade() {
        return this.especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

}
