package br.com.devmedia.introspringbootweb.controller;

import br.com.devmedia.introspringbootweb.domain.Usuario;
import br.com.devmedia.introspringbootweb.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("usuarios", usuarioService.recuperar());
        /*      model.addAttribute("livros", usuarioService.recuperarLivro());*/
        return new ModelAndView("usuario/list", model);
    }

    @GetMapping("/cadastro")
    public String preSalvar(@ModelAttribute("usuario") Usuario usuario) {
        return "usuario/add";
    }

    @PostMapping("/salvar")
    public String salvar(@Validated @ModelAttribute("usuario") Usuario usuario, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "usuario/add";
        }
        usuarioService.salvar(usuario);
        attr.addFlashAttribute("mensagem", "Usuario criado com sucesso.");
        return "redirect:/usuarios/listar";
    }

    @GetMapping("/{id}/atualizar")
    public ModelAndView preAtualizar(@PathVariable("id") long id, ModelMap model) {
        Usuario usuario = usuarioService.recuperarPorId(id);
        model.addAttribute("usuario", usuario);
        return new ModelAndView("usuario/add", model);
    }

    @PutMapping("/salvar")
    public ModelAndView atualizar(@Validated @ModelAttribute("usuario") Usuario usuario, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return new ModelAndView("usuario/add");
        }

        usuarioService.atualizar(usuario);
        attr.addFlashAttribute("mensagem", "Usuario atualizado com sucesso.");
        return new ModelAndView("redirect:/usuarios/listar");
    }

    @GetMapping("/{id}/remover")
    public String remover(@PathVariable("id") long id, RedirectAttributes attr) {

        List alugueis = usuarioService.recuperarAluguel(id);
        if (alugueis != null && !alugueis.isEmpty()) {
            attr.addFlashAttribute("mensagemerro", "Não foi possível excluir esse usuário, verifique se não há alugueis desse usuário :).");
            return "redirect:/usuarios/listar";
        } else {
            usuarioService.excluir(id);
            attr.addFlashAttribute("mensagem", "Usuário excluído com sucesso.");
            return "redirect:/usuarios/listar";
        }

    }


}
