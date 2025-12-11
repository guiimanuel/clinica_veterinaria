package Controller;
import java.util.List;

import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ifpe.clinicaveterinaria.DAO.AnimalDAO;
import br.ifpe.clinicaveterinaria.model.Animal;
import br.ifpe.clinicaveterinaria.model.Cliente;

@Controller 
@RequestMapping("/animais")
public class AnimalController {

    private final AnimalDAO animalDAO;

    public AnimalController(AnimalDAO animalDAO) {
        this.animalDAO = animalDAO;
    }


    @GetMapping
    public String listarAnimais(Model model) {
        List<Animal> animais = animalDAO.findAll();
        model.addAttribute("animais", animais);
        return "animais"; 
    }


    @GetMapping("/novoanimal")
    public String novoAnimal(Model model) {
        model.addAttribute("animal", new Animal(1, "Latex", "Husky", 5, new Cliente(1, "Guilherme", "81996167276")));
        model.addAttribute("cliente", new Cliente(1, "Guilherme", "81996167276"));
        return "novoanimal"; 
    }

    @PostMapping("/novoanimal")
    public String criarAnimal(@ModelAttribute Animal animal) {
        animalDAO.create(animal);
        return "redirect:/animais";
    }

    @GetMapping("/editaranimal/{idAnimal}")
    public String editarAnimalString(@PathVariable Long idAnimal, Model model) {
        Animal a = animalDAO.findById(idAnimal).orElse(null);
        model.addAttribute("animal", a);
        return "novoanimal";
    }


    @PostMapping("/editaranimal/{idAnimal}")
    public String atualizarAnimal(@PathVariable Long idAnimal, @ModelAttribute Animal animal) {
        animalDAO.update(idAnimal, animal);
        return "redirect:/animais";
    }

    @GetMapping("/deletaranimal/{idAnimal}")
    public String deletarAnimal(@PathVariable Long idAnimal) {
        animalDAO.delete(idAnimal);
        return "redirect:/animais";
    }

    @GetMapping("/buscaranimal")
    public String buscarAnimal(@RequestParam Long idAnimal, Model model) {
        Animal a = animalDAO.findById(idAnimal).orElse(null);
        model.addAttribute("animal", a);
        return "idAnimal";
    }


    @GetMapping("/filtraranimal")
    public String filtrarAnimais(
            @RequestParam(required = false) String especie,
            Model model) {

        List<Animal> filtrados = animalDAO.findByFilters(especie);
        model.addAttribute("animais", filtrados);
        return "animais"; 
    }

}