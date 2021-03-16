package br.com.devmedia.introspringbootweb.controller;

import br.com.devmedia.introspringbootweb.domain.Livro;
import br.com.devmedia.introspringbootweb.service.EditoraService;
import br.com.devmedia.introspringbootweb.service.LivroService;
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
@RequestMapping("editoras/{editoraId}/livros")

public class LivroController {

    @Autowired
    private LivroService livroService;
    private EditoraService editoraService;

    @GetMapping("/listar")
    public ModelAndView listar(@PathVariable("editoraId") long editoraId, ModelMap model) {
        model.addAttribute("livros", livroService.recuperarPorEditora(editoraId));
        model.addAttribute("editoras", livroService.recuperarPorIdEditora(editoraId));
        model.addAttribute("editoraId", editoraId);
        return new ModelAndView("/livro/list", model);
    }

    @GetMapping("/cadastro")
    public String preSalvar(@ModelAttribute("livro") Livro livro, @PathVariable("editoraId") long editoraId) {
        return "/livro/add";
    }

    @PostMapping("/salvar")
    public String salvar(@PathVariable("editoraId") long editoraId, @Validated @ModelAttribute("livro")
            Livro livro, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/livro/add";
        }

        livroService.salvar(livro, editoraId);
        attr.addFlashAttribute("mensagem", "Livro salvo com sucesso.");
        return "redirect:/editoras/" + editoraId + "/livros/listar";

        /*if(livro.getQuantidade() != 0) {
            livroService.salvar(livro, editoraId);
            attr.addFlashAttribute("mensagem", "Livro salvo com sucesso.");
            return "redirect:/editoras/" + editoraId + "/livros/listar";
        }
        else{
            return "/livro/add";
        }*/
    }

    @GetMapping("/{livroId}/atualizar")
    public ModelAndView preAtualizar(@PathVariable("editoraId") long editoraId, @PathVariable("livroId")
            long livroId, ModelMap model) {
       Livro livro = livroService.recuperarPorEditoraIdELivroId(editoraId, livroId);
        model.addAttribute("livro", livro);
        model.addAttribute("editoraId", editoraId);
        return new ModelAndView("/livro/add", model);
    }

    @PutMapping("/salvar")
    public ModelAndView atualizar(@PathVariable("editoraId") long editoraId, @Validated @ModelAttribute("livro")
            Livro livro, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return new ModelAndView("/livro/add");
        }
        Livro teste = livroService.recuperarPorId(livro.getId());
        if(teste.getAlugados() <= livro.getQuantidade()) {
            livroService.atualizar(livro, editoraId);
            attr.addFlashAttribute("mensagem", "Livro atualizado com sucesso.");
            return new ModelAndView("redirect:/editoras/" + editoraId + "/livros/listar");
        }
        else{
            attr.addFlashAttribute("mensagemerro", "A quantidade não pode ser menor do que a quantidade de livros alugados.");
            return new ModelAndView("redirect:/editoras/" + editoraId + "/livros/" + livro.getId() + "/atualizar");
        }
    }

    @GetMapping("/{livroId}/remover")
    public String remover(@PathVariable("editoraId") long editoraId, @PathVariable("livroId")
            long livroId, RedirectAttributes attr) {
        List alugueis = livroService.recuperarAluguel(livroId);
        if(alugueis != null && !alugueis.isEmpty()) {
            attr.addFlashAttribute("mensagemerro", "Não foi possível excluir esse livro, verifique se não há alugueis cadastrados :).");
            return "redirect:/editoras/" + editoraId + "/livros/listar";
        }
        else {
            livroService.excluir(editoraId, livroId);
            attr.addFlashAttribute("mensagem", "Livro excluído com sucesso.");
            return "redirect:/editoras/" + editoraId + "/livros/listar";
        }
    }
}
