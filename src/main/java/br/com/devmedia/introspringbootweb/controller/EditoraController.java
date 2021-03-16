package br.com.devmedia.introspringbootweb.controller;

import br.com.devmedia.introspringbootweb.domain.Editora;
import br.com.devmedia.introspringbootweb.domain.Usuario;
import br.com.devmedia.introspringbootweb.repository.EditoraRepository;
import br.com.devmedia.introspringbootweb.service.EditoraService;
import br.com.devmedia.introspringbootweb.service.LivroService;
import br.com.devmedia.introspringbootweb.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityManager;
import java.util.List;

@Controller
@RequestMapping("editoras")
public class EditoraController {

    private EditoraRepository editoraRepository;

    @Autowired
    private EditoraService editoraService;
    private LivroService livroService;


    @GetMapping("/listar")
    public String listar(Model model) {
        /*model.addAttribute("editoras", editoraService.recuperar());
        return new ModelAndView("/editora/list", model);*/
        return findPaginated(1, 5, "id", "asc", model);
    }

    @GetMapping("/cadastro")
    public String preSalvar(@ModelAttribute("editora") Editora editora) {
        return "editora/add";
    }

    @PostMapping("/salvar")
    public String salvar(@Validated @ModelAttribute("editora") Editora editora, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "editora/add";
        }
        editoraService.salvar(editora);
        attr.addFlashAttribute("mensagem", "Editora cadastrada com sucesso.");
        return "redirect:/editoras/listar";
    }

    @GetMapping("/{id}/atualizar")
    public ModelAndView preAtualizar(@PathVariable("id") long id, ModelMap model) {
        Editora editora = editoraService.recuperarPorId(id);
        model.addAttribute("editora", editora);
        return new ModelAndView("editora/add", model);
    }

    @PutMapping("/salvar")
    public ModelAndView atualizar(@Validated @ModelAttribute("editora") Editora editora, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return new ModelAndView("editora/add");
        }

        editoraService.atualizar(editora);
        attr.addFlashAttribute("mensagem", "Editora atualizada com sucesso.");
        return new ModelAndView("redirect:/editoras/listar");
    }

    @GetMapping("/{id}/remover")
    public String remover(@PathVariable("id") long id , RedirectAttributes attr) {
        List livros = editoraService.recuperarLivro(id);
        if(livros != null && !livros.isEmpty()) {
            attr.addFlashAttribute("mensagemerro", "Não foi possível excluir essa editora, verifique se não há livros cadastrados :).");
            return "redirect:/editoras/listar";
        }
        else{
            editoraService.excluir(id);
            attr.addFlashAttribute("mensagem", "Editora excluída com sucesso.");
            return "redirect:/editoras/listar";
        }
    }

    @GetMapping("/page/{pageNo}/{pageSize}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @PathVariable(value = "pageSize") int pageSize,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {

        Page<Editora> page = editoraService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Editora> listEditoras = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("ASC") ? "desc" : "asc");
        model.addAttribute("editoras", listEditoras);
        model.addAttribute("status", 0);

        return "editora/list";
    }

    @PostMapping("/listar/search")
    public String listarSearch(@RequestParam("pesquisa") String pesquisa,
                               @RequestParam("sortField") String sortField,
                               @RequestParam("sortDir") String sortDir,
                               Model model) {
        return findPaginatedSearch(1, 5, sortField, sortDir, pesquisa, model);
    }

    @GetMapping("/page/{pageNo}/{pageSize}/search")
    public String findPaginatedSearch(@PathVariable("pageNo") int pageNo,
                                      @PathVariable("pageSize") int pageSize,
                                      @RequestParam("sortField") String sortField,
                                      @RequestParam("sortDir") String sortDir,
                                      @RequestParam("pesquisa") String pesquisa,
                                Model model) {

        Page<Editora> page = editoraService.pesquisarEditora(pageNo, pageSize, sortField, sortDir, pesquisa);
        List<Editora> listEditoras =  page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("ASC") ? "desc" : "asc");
        model.addAttribute("editoras", listEditoras);
        model.addAttribute("status", 1);
        model.addAttribute("pesquisa", pesquisa);

        return "editora/list";
    }


}
