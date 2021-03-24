package br.com.devmedia.introspringbootweb.controller;

import br.com.devmedia.introspringbootweb.domain.Aluguel;
import br.com.devmedia.introspringbootweb.service.*;
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

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("usuarios/{usuarioId}/alugueis")

public class AluguelController {

    @Autowired
    private AluguelService aluguelService;

    @GetMapping("/listar")
    public String listar(@PathVariable("usuarioId") long usuarioId, Model model) {
        /*model.addAttribute("alugueis", aluguelService.recuperarPorUsuario(usuarioId));
        model.addAttribute("usuarioId", usuarioId);
        return new ModelAndView("/aluguel/list", model);*/
        return findPaginated(usuarioId, 1, 5, "id", "asc", "", model);
    }

    @GetMapping("/page/{pageNo}/{pageSize}")
    public String findPaginated(@PathVariable("usuarioId") long usuarioId,
                                @PathVariable(value = "pageNo") int pageNo,
                                @PathVariable(value = "pageSize") int pageSize,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                @RequestParam("pesquisa") String pesquisa,
                                Model model) {

        Page<Aluguel> page = aluguelService.findPaginated(usuarioId, pesquisa, pageNo, pageSize, sortField, sortDir);
        List<Aluguel> listAlugueis = page.getContent();

        Date dataAgora = new Date();

        int status = 0;

        if (pesquisa != "") {
            status = 1;
        }
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("alugueis", listAlugueis);
        model.addAttribute("pesquisa", pesquisa);
        model.addAttribute("status", status);
        model.addAttribute("dataAtual", dataAgora);
        return "aluguel/list";
    }

    @GetMapping("/cadastro")
    public ModelAndView preSalvar(@ModelAttribute("aluguel") Aluguel aluguel, @PathVariable("usuarioId") long usuarioId
            , ModelMap model) {
        model.addAttribute("livros", aluguelService.recuperarLivro());
        model.addAttribute("teste", usuarioId);
        return new ModelAndView("aluguel/add", model);
    }
   /* @GetMapping("/cadastro")
    public String preSalvar(@ModelAttribute("aluguel") Aluguel aluguel, @PathVariable("usuarioId") long usuarioId) {
        return "/aluguel/add";
    }*/

    @PostMapping("/salvar")
    public String salvar(@PathVariable("usuarioId") long usuarioId, @Validated @ModelAttribute("aluguel")
            Aluguel aluguel, BindingResult result, RedirectAttributes attr) {
        if (aluguel.getLivro().getId() == 0) {
            attr.addFlashAttribute("mensagemerro", "Por favor selecione algum livro");
            return "redirect:/usuarios/" + usuarioId + "/alugueis/cadastro";
        } else {

            if (aluguel.getPrevDataDevolucao().before(aluguel.getDataAluguel())) {
                attr.addFlashAttribute("mensagemerro", "A data de previsão da devolução não pode ser antes da data de aluguel!");
                return "redirect:/usuarios/" + usuarioId + "/alugueis/cadastro";
            } else {
                if (result.hasErrors()) {
                    return "aluguel/add";
                }
                aluguelService.salvar(aluguel, usuarioId);
                attr.addFlashAttribute("mensagem", "Aluguel salvo com sucesso.");
                return "redirect:/usuarios/" + usuarioId + "/alugueis/listar";
            }
        }
    }

    @GetMapping("/{aluguelId}/atualizar")
    public ModelAndView preAtualizar(@PathVariable("usuarioId") long usuarioId, @PathVariable("aluguelId")
            long aluguelId, ModelMap model) {
        Aluguel aluguel = aluguelService.recuperarPorUsuarioIdEAluguelId(usuarioId, aluguelId);
        model.addAttribute("livros", aluguelService.recuperarLivroList());
        model.addAttribute("aluguel", aluguel);
        model.addAttribute("usuarioId", usuarioId);
        return new ModelAndView("aluguel/add", model);
    }

    @PutMapping("/salvar")
    public Object atualizar(@PathVariable("usuarioId") long usuarioId, @Validated @ModelAttribute("aluguel")
            Aluguel aluguel, BindingResult result, RedirectAttributes attr) {

        if (aluguel.getPrevDataDevolucao().before(aluguel.getDataAluguel())) {
            attr.addFlashAttribute("mensagemerro", "A data de previsão da devolução não pode ser antes da data de aluguel!");
            return new ModelAndView("aluguel/add");
        } else {
            if (result.hasErrors()) {
                return new ModelAndView("aluguel/add");
            }
            aluguelService.atualizar(aluguel, usuarioId);
            attr.addFlashAttribute("mensagem", "Aluguel atualizado com sucesso.");
            return new ModelAndView("redirect:/usuarios/" + usuarioId + "/alugueis/listar");
        }
    }


    @GetMapping("/{aluguelId}/remover/{livroId}")
    public String remover(@PathVariable("usuarioId") long usuarioId, @PathVariable("aluguelId") long aluguelId,
                          @PathVariable("livroId") long livroId, RedirectAttributes attr) {
        aluguelService.excluir(usuarioId, aluguelId, livroId);
        attr.addFlashAttribute("mensagem", "Aluguel excluído com sucesso.");
        return "redirect:/usuarios/" + usuarioId + "/alugueis/listar";
    }

    @GetMapping("/{aluguelId}/devolver")
    public ModelAndView preDevolver(@PathVariable("usuarioId") long usuarioId, @PathVariable("aluguelId")
            long aluguelId, ModelMap model) {
        Aluguel aluguel = aluguelService.recuperarPorUsuarioIdEAluguelId(usuarioId, aluguelId);
        model.addAttribute("livros", aluguelService.recuperarLivroList());
        model.addAttribute("aluguel", aluguel);
        model.addAttribute("usuarioId", usuarioId);
        model.addAttribute("status", 1);
        return new ModelAndView("aluguel/add", model);
    }
}
