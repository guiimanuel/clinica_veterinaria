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

import br.ifpe.clinicaveterinaria.DAO.ClienteDAO;
import br.ifpe.clinicaveterinaria.model.Animal;
import br.ifpe.clinicaveterinaria.model.Cliente;

@Controller 
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteDAO clienteDAO;

    public ClienteController(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }


    @GetMapping
    public String listarClientes(Model model) {
        List<Cliente> clientes = clienteDAO.findAll();
        model.addAttribute("clientes", clientes);
        return "clientes"; 
    }


    @GetMapping("/novocliente")
    public String novoCliente(Model model) {
        model.addAttribute("cliente", new Cliente(1, "Guilherme", "81996167276"));
        model.addAttribute("animal", new Animal(1, "Latex", "Husky", 5, new Cliente(1, "Guilherme", "81996167276")));
        
        return "novocliente"; 
    }

    @PostMapping("/novocliente")
    public String criarCliente(@ModelAttribute Cliente cliente) {
        clienteDAO.create(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editarcliente/{idPessoa}")
    public String editarClienteString(@PathVariable Long idPessoa, Model model) {
        Cliente c = clienteDAO.findById(idPessoa).orElse(null);
        model.addAttribute("cliente", c);
        return "novocliente";
    }


    @PostMapping("/editarcliente/{idPessoa}")
    public String atualizarCliente(@PathVariable Long idPessoa, @ModelAttribute Cliente cliente) {
        clienteDAO.update(idPessoa, cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/deletarcliente/{idPessoa}")
    public String deletarCliente(@PathVariable Long idPessoa) {
        clienteDAO.delete(idPessoa);
        return "redirect:/clientes";
    }

    @GetMapping("/buscarcliente")
    public String buscarCliente(@RequestParam Long idPessoa, Model model) {
        Cliente c = clienteDAO.findById(idPessoa).orElse(null);
        model.addAttribute("cliente", c);
        return "idPessoa";
    }


    @GetMapping("/filtrarcliente")
    public String filtrarClientes(
            @RequestParam(required = false) String nome,
            Model model) {

        List<Cliente> filtrados = clienteDAO.findByFilters(nome);
        model.addAttribute("clientes", filtrados);
        return "clientes"; 
    }

}