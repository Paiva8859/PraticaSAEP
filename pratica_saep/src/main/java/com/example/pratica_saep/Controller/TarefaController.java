package com.example.pratica_saep.Controller;

import com.example.pratica_saep.Model.Tarefa;
import com.example.pratica_saep.Model.Usuario;
import com.example.pratica_saep.Repository.TarefaRepository;
import com.example.pratica_saep.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TarefaController {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Método GET para exibir o formulário de cadastro de tarefas
    @GetMapping("/tarefas/cadastrar")
    public String exibirFormularioCadastro(Model model) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("tarefa", new Tarefa());
        return "tarefa/cadastrar";
    }

    // Método POST para salvar a tarefa
    @PostMapping("/tarefas/salvar")
    public String salvarTarefa(Tarefa tarefa) {
        tarefaRepository.save(tarefa); 
        return "redirect:/tarefas/cadastrar?sucesso"; 
    }

    // Método GET para exibir a página de gerenciamento de tarefas
    @GetMapping("/tarefas/gerenciar")
    public String gerenciarTarefas(Model model) {
        // Carregar todas as tarefas do banco de dados
        List<Tarefa> tarefas = tarefaRepository.findAll();

        // Separar as tarefas por status (utilizando enums)
        List<Tarefa> tarefasAFazer = tarefas.stream()
                                            .filter(t -> t.getStatus() == Tarefa.Status.A_FAZER)
                                            .toList();
        List<Tarefa> tarefasFazendo = tarefas.stream()
                                             .filter(t -> t.getStatus() == Tarefa.Status.FAZENDO)
                                             .toList();
        List<Tarefa> tarefasPronto = tarefas.stream()
                                            .filter(t -> t.getStatus() == Tarefa.Status.PRONTO)
                                            .toList();

        // Adicionar as tarefas separadas por status no modelo
        model.addAttribute("tarefasAFazer", tarefasAFazer);
        model.addAttribute("tarefasFazendo", tarefasFazendo);
        model.addAttribute("tarefasPronto", tarefasPronto);

        return "tarefa/gerenciar";
    }
    
    // Método para editar o status de uma tarefa
    @PostMapping("/tarefas/atualizarStatus")
    public String atualizarStatus(@RequestParam Long id, @RequestParam Tarefa.Status status) {
        Tarefa tarefa = tarefaRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        tarefa.setStatus(status); // Atualiza o status da tarefa
        tarefaRepository.save(tarefa); // Salva a tarefa com o novo status
        return "redirect:/tarefas/gerenciar"; // Redireciona para a página de gerenciamento
    }

    // Método para excluir uma tarefa
    @PostMapping("/tarefas/excluir")
    public String excluirTarefa(@RequestParam Long id) {
        tarefaRepository.deleteById(id); // Exclui a tarefa pelo ID
        return "redirect:/tarefas/gerenciar"; // Redireciona para a página de gerenciamento
    }
}