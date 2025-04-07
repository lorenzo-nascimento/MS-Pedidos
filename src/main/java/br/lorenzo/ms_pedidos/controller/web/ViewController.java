package br.lorenzo.ms_pedidos.controller.web;

import br.lorenzo.ms_pedidos.model.enums.StatusPedido;
import br.lorenzo.ms_pedidos.service.ClienteService;
import br.lorenzo.ms_pedidos.service.PedidoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class ViewController {

    private final ClienteService clienteService;
    private final PedidoService pedidoService;

    public ViewController(ClienteService clienteService, PedidoService pedidoService) {
        this.clienteService = clienteService;
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/clientes")
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.listarTodos());
        return "clientes";
    }

    @GetMapping("/pedidos")
    public String listarPedidos(Model model) {
        model.addAttribute("pedidos", pedidoService.listarTodosPedidos());
        return "pedidos";
    }

    @GetMapping("/clientes/{id}/pedidos")
    public String listarPedidosPorCliente(@PathVariable Long id, Model model) {
        model.addAttribute("pedidos", pedidoService.listarPedidosPorCliente(id));
        model.addAttribute("cliente", clienteService.buscarPorId(id));
        return "pedidos-cliente"; // Nome do template sem extensão
    }

    @GetMapping("/pedidos/{id}/status")
    public String atualizarStatusPedido(
            @PathVariable Long id,
            @RequestParam String novoStatus,
            RedirectAttributes redirectAttributes) {

        StatusPedido status = StatusPedido.valueOf(novoStatus);
        pedidoService.atualizarStatus(id, status);

        redirectAttributes.addFlashAttribute("mensagem", "Status atualizado para " + novoStatus);
        return "redirect:/pedidos";
    }


}
