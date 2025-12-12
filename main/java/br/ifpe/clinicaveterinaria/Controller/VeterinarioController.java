package br.ifpe.clinicaveterinaria.Controller;

import java.util.List;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ifpe.clinicaveterinaria.DAO.VeterinarioDAO;
import br.ifpe.clinicaveterinaria.model.Veterinario;

@Controller 
@RequestMapping("/veterinarios")
public class VeterinarioController {

    private final VeterinarioDAO veterinarioDAO;

    public VeterinarioController(VeterinarioDAO veterinarioDAO) {
        this.veterinarioDAO = veterinarioDAO;
    }


    @GetMapping
    public String listarVeterinarios(Model model) {
        List<Veterinario> veterinarios = veterinarioDAO.findAll();
        model.addAttribute("veterinarios", veterinarios);
        return "veterinarios"; 
    }


    @GetMapping("/novoveterinario")
    public String novoVeterinario(Model model) {
        model.addAttribute("veterinario", new Veterinario(1, "Gabriely", "8198363467", "Cachorro"));
        return "novoveterinario"; 
    }

    @PostMapping("/novoveterinario")
    public String criarVeterinario(@ModelAttribute Veterinario veterinario) {
        veterinarioDAO.create(veterinario);
        return "redirect:/veterinarios";
    }

    @GetMapping("/editarveterinario/{idPessoa}")
    public String editarVeterinarioString(@PathVariable Long idPessoa, Model model) {
        Veterinario v = veterinarioDAO.findById(idPessoa).orElse(null);
        model.addAttribute("veterinario", v);
        return "novoveterinario";
    }


    @PostMapping("/editarveterinario/{idPessoa}")
    public String atualizarVeterinario(@PathVariable Long idPessoa, @ModelAttribute Veterinario veterinario) {
        veterinarioDAO.update(idPessoa, veterinario);
        return "redirect:/veterinarios";
    }

    @GetMapping("/deletarveterinario/{idPessoa}")
    public String deletarVeterinario(@PathVariable Long idPessoa) {
        veterinarioDAO.delete(idPessoa);
        return "redirect:/veterinarios";
    }

    @GetMapping("/buscarveterinario")
    public String buscarVeterinario(@RequestParam Long idPessoa, Model model) {
        Veterinario v = veterinarioDAO.findById(idPessoa).orElse(null);
        model.addAttribute("veterinario", v);
        return "idPessoa";
    }


    @GetMapping("/filtrarveterinario")
    public String filtrarVeterinarios(
            @RequestParam(required = false) String especialidade,
            Model model) {

        List<Veterinario> filtrados = veterinarioDAO.findByFilters(especialidade);
        model.addAttribute("veterinarios", filtrados);
        return "veterinarios"; 
    }

}