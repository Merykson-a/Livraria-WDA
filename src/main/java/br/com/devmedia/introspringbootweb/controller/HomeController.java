package br.com.devmedia.introspringbootweb.controller;

import br.com.devmedia.introspringbootweb.repository.AluguelRepository;
import br.com.devmedia.introspringbootweb.repository.EditoraRepository;
import br.com.devmedia.introspringbootweb.repository.LivroRepository;
import br.com.devmedia.introspringbootweb.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Controller
@RequestMapping("/statistic")
public class HomeController {

    @Autowired
    private AluguelRepository aluguelRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private EditoraRepository editoraRepository;

    @GetMapping("/view")
    public ModelAndView listar(ModelMap model) {

        OffsetDateTime dataAgora = OffsetDateTime.now(ZoneOffset.UTC);

        model.addAttribute("noA", aluguelRepository.noA());
        model.addAttribute("noP", aluguelRepository.noP());
        model.addAttribute("emA", aluguelRepository.emA());
        model.addAttribute("emP", aluguelRepository.emP());
        model.addAttribute("contaU", usuarioRepository.count());
        model.addAttribute("contaA", aluguelRepository.count());
        model.addAttribute("contaL", livroRepository.count());
        model.addAttribute("contaE", editoraRepository.count());
        return new ModelAndView("statistic/statistic", model);
    }

}
