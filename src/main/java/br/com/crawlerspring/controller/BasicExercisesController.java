/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crawlerspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.crawlerspring.model.Exercise;
import br.com.crawlerspring.model.ExerciseChooser;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;

/**
 *
 * @author marcelo
 */

@Controller
public class BasicExercisesController {
    
    private String resolution;
    
    private ExerciseChooser chooser;
    private Exercise exercise;
    
    @RequestMapping(Routes.basicExercises)
    public String exercise() {
        chooser = new ExerciseChooser();
        return "redirect:"+Routes.basicExercisesNew;
   }
    
    @RequestMapping(Routes.basicExercisesNew)
    public String newExercise(Model model){
        exercise = chooser.chooseExercise();
        model.addAttribute("title", exercise.title() );
        return Routes.basicExercisesNew;
    }
    
    @RequestMapping(Routes.basicExercisesUpdate)
    public String updateExercise(HttpServletRequest request, Model model){
        model.addAttribute("title", exercise.title() );
        model.addAttribute("resolutionParam", resolution);
        return Routes.basicExercisesNew;
    }
    
    @RequestMapping(Routes.basicExercisesAct)
    public String runExercise(HttpServletRequest request, Model model){
        resolution = request.getParameter("resolution");
        //javax.swing.JOptionPane.showMessageDialog(null, resolution);
        exercise.buildGrading(resolution);
        if (exercise.hasCompileErrors != true) {
            //exercicio.salvarBancoDeDados(codigoUsuario, conexao);
            if (chooser.canDoNextExercise() == true) {
                return "redirect:"+Routes.basicExercisesNew;
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "Parabéns. Você passou no teste!");
                return "redirect:"+Routes.main;
            }
        } else {
            //exercicio.salvarBancoErroDeCompilacao(codigoUsuario, conexao);
            if (exercise.endOfAttempts == true) {
                if (chooser.canDoNextExercise() == true) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Estouro de "
                            + "quantidade de tentativas atingido. "
                            + "Por favor, fazer o próximo exercício");
                    return "redirect:"+Routes.basicExercisesNew;
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, "Você foi reprovado no teste");
                    return "redirect:"+Routes.main;
                }
            }
            else {
                return "redirect:"+Routes.basicExercisesUpdate;
            }
        }
        
    }
}
