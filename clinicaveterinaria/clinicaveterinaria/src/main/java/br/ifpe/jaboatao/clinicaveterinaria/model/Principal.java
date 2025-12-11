package br.ifpe.jaboatao.clinicaveterinaria.model;
import java.time.LocalDate;

public class Principal {
    public static void main(String[] args) {
        Cadastro cadastro = new Cadastro();

        Cliente cliente1 = new Cliente(1, "Maria Silva", "9999-8888");
        Cliente cliente2 = new Cliente(2, "João Pereira", "9888-7777");

        cadastro.cadastrarCliente(cliente1);
        cadastro.cadastrarCliente(cliente2);

        Veterinario vet1 = new Veterinario(1, "Dr. Carlos", "9777-6666", "Clínica Geral");
        Veterinario vet2 = new Veterinario(2, "Dra. Ana", "9666-5555", "Cirurgia Animal");

        cadastro.cadastrarVeterinario(vet1);
        cadastro.cadastrarVeterinario(vet2);

        Animal animal1 = new Animal(1, "Rex", "Cachorro", 5, cliente1);
        Animal animal2 = new Animal(2, "Mimi", "Gato", 3, cliente2);

        cliente1.adicionarAnimal(animal1);
        cliente2.adicionarAnimal(animal2);

        cadastro.cadastrarAnimal(animal1);
        cadastro.cadastrarAnimal(animal2);

        Consulta cons1 = new Consulta(1,LocalDate.of(2025, 10, 12),"Alergia de pele","Uso de pomada tópica",animal1,vet1);

        cadastro.cadastrarConsulta(cons1);
    }
}
