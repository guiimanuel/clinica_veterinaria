package Controller;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ifpe.clinicaveterinaria.DAO.ConsultaDAO;
import br.ifpe.clinicaveterinaria.model.Animal;
import br.ifpe.clinicaveterinaria.model.Cliente;
import br.ifpe.clinicaveterinaria.model.Consulta;
import br.ifpe.clinicaveterinaria.model.Veterinario;

@Controller 
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaDAO consultaDAO;

    public ConsultaController(ConsultaDAO consultaDAO) {
        this.consultaDAO = consultaDAO;
    }


    @GetMapping
    public String listarConsultas(Model model) {
        List<Consulta> consultas = consultaDAO.findAll();
        model.addAttribute("consultas", consultas);
        return "consultas"; 
    }


    @GetMapping("/novaconsulta")
    public String novaConsulta(Model model) {
        model.addAttribute("consulta", new Consulta(1, LocalDate.of(2025, 12, 3), "Pressão Alta", "Medicação", new Animal(1, "Latex", "Husky", 5, new Cliente(1, "Guilherme", "81996167276")), new Veterinario(1, "Gabriely", "8198363467", "Cachorro")));
        return "novaconsulta"; 
    }

    @PostMapping("/novaconsulta")
    public String criarConsulta(@ModelAttribute Consulta consulta) {
        consultaDAO.create(consulta);
        return "redirect:/consultas";
    }

    @GetMapping("/editarconsulta/{idConsulta}")
    public String editarConsultaString(@PathVariable Long idConsulta, Model model) {
        Consulta c = consultaDAO.findById(idConsulta).orElse(null);
        model.addAttribute("consulta", c);
        return "novaconsulta";
    }


    @PostMapping("/editarconsulta/{idConsulta}")
    public String atualizarConsulta(@PathVariable Long idConsulta, @ModelAttribute Consulta consulta) {
        consultaDAO.update(idConsulta, consulta);
        return "redirect:/consultas";
    }

    @GetMapping("/deletarconsulta/{idConsulta}")
    public String deletarConsulta(@PathVariable Long idConsulta) {
        consultaDAO.delete(idConsulta);
        return "redirect:/consultas";
    }

    @GetMapping("/buscarconsulta")
    public String buscarConsulta(@RequestParam Long idConsulta, Model model) {
        Consulta c = consultaDAO.findById(idConsulta).orElse(null);
        model.addAttribute("consulta", c);
        return "idConsulta";
    }


    @GetMapping("/filtrarconsulta")
    public String filtrarConsultas(
            @RequestParam(required = false) String animal,
            Model model) {

        List<Consulta> filtrados = consultaDAO.findByFilters(animal);
        model.addAttribute("consultas", filtrados);
        return "consultas"; 
    }

}