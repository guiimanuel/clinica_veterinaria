package br.ifpe.clinicaveterinaria.model;

public class Veterinario extends Pessoa {
    private String especialidade;

    public Veterinario(int idPessoa, String nome, String telefone, String especialidade) {
        super(idPessoa, nome, telefone);
        this.especialidade = especialidade;
    }

    @Override
    public void exibirDados() {
        System.out.println("Veterinário: " + nome + " - Especialidade: " + especialidade);
    }
    public String getEspecialidade() {
        return this.especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

}