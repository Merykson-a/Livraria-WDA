package br.com.devmedia.introspringbootweb.controller;

import br.com.devmedia.introspringbootweb.repository.AluguelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/teste")
public class HomeController {

    @Autowired
    private AluguelRepository aluguelRepository;

    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model) {

        model.addAttribute("variavelteste", aluguelRepository.atrasoE());
        return new ModelAndView("teste/teste", model);
    }

}
