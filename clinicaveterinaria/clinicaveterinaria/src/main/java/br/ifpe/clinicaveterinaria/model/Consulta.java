package br.ifpe.clinicaveterinaria.model;

import java.time.LocalDate;

public class Consulta {
    private int idConsulta;
    private LocalDate data;
    private String diagnostico;
    private String tratamento;
    private Animal animal;
    private Veterinario veterinario;

    public Consulta(int idConsulta, LocalDate data, String diagnostico, String tratamento,
                    Animal animal, Veterinario veterinario) {
        this.idConsulta = idConsulta;
        this.data = data;
        this.diagnostico = diagnostico;
        this.tratamento = tratamento;
        this.animal = animal;
        this.veterinario = veterinario;
    }

    public int getIdConsulta() {
        return this.idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public LocalDate getData() {
        return this.data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDiagnostico() {
        return this.diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamento() {
        return this.tratamento;
    }

    public void setTratamento(String tratamento) {
        this.tratamento = tratamento;
    }

    public Animal getAnimal() {
        return this.animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Veterinario getVeterinario() {
        return this.veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

}
