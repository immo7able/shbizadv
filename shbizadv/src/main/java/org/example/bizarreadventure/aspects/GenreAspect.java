package org.example.bizarreadventure.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.example.bizarreadventure.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Aspect
@Component
public class GenreAspect {
    @Autowired
    private GenreService genreService;

    @Before("execution(* org.example.bizarreadventure.controllers.*.*(..)) && args(.., model)")
    public void addGenresToModel(Model model) {
        model.addAttribute("genres", genreService.getAllGenres());
    }
}
