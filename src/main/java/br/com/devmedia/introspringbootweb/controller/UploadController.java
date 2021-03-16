package br.com.devmedia.introspringbootweb.controller;

import br.com.devmedia.introspringbootweb.domain.Imagem;
import br.com.devmedia.introspringbootweb.repository.ImagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("imagem")
public class UploadController {

    @Autowired
    private ImagemRepository imagemRepository;

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "intro-spring-boot-web\\src\\main\\resources\\static\\imagem\\";

    @GetMapping("/edit")
    public ModelAndView index(ModelMap model) {
        model.addAttribute("imagens", imagemRepository.findAll());
        return new ModelAndView("/imagem/add", model);
    }
    /*@GetMapping("/edit")
    public String index() {
        return ("/imagem/add");
    }*/

    @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes, Imagem imagem) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/home";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            System.out.println();

            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            imagem.setNome(file.getOriginalFilename());
            imagemRepository.deleteAll();
            imagemRepository.save(imagem);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/home";
    }
}