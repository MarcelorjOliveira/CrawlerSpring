/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crawlerspring.controller;

/**
 *
 * @author marcelo
 */
public class Routes {
   
    public static final String login = "/login",
                               loginAct = login + "/act",
                               loginNew = login + "/new",
                               loginNewAct = loginNew + "/act",
                               loginShow = login + "/show",
                               main = "/main",
                               mainShow = main + "/show",
                               basicExercises = main + "/basicexercises",
                               basicExercisesNew = basicExercises + "/new",
                               basicExercisesAct = basicExercises + "/act", 
                               basicExercisesUpdate = basicExercises + "/update";
}
