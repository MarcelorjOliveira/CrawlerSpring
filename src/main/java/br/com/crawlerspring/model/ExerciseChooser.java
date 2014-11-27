/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crawlerspring.model;

import java.util.ArrayList;

/**
 *
 * @author marcelo
 */
public class ExerciseChooser {
    ArrayList<Exercise> exercisesToDo = new ArrayList<Exercise>();
    ArrayList<Exercise> exercisesDone = new ArrayList<Exercise>();
    boolean canDoNextExercise;
    
    public ExerciseChooser()
    {
        addExercise();
    }
    
    public void addExercise() {
        exercisesToDo.add(new FactorialExercise());
        exercisesToDo.add(new FibonnaciExercise());
        exercisesToDo.add(new SpotExercise());
        exercisesToDo.add(new MultipleExercise());
        exercisesToDo.add(new SumExercise());
        exercisesToDo.add(new MultipleSumExercise());
        exercisesToDo.add(new SummationExercise());
        exercisesToDo.add(new MultiplicandExercise());
    }
    
    public Exercise chooseExercise()
    {
        double random = Math.random() * exercisesToDo.size();
        
        Exercise exercise = exercisesToDo.get((int)random);
        exercisesToDo.remove(exercise);
        exercisesDone.add(exercise);
        
        return exercise;
    } 
    
    private double media() {
        double totalMark=0,media;
        for(Exercise exercise : exercisesDone)
        {
            totalMark += exercise.exerciseMark();
        }
        
        media = totalMark/exercisesDone.size();
        javax.swing.JOptionPane.showMessageDialog(null, "Média : " + media);
        return media;
    }
    
    public boolean canDoNextExercise() {
         if(exercisesDone.size() >= 3)
            {
            if(exercisesDone.size() <= 7)
            {
                if(media() >= 8.0)
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
            else
            {
                return false;
            }
        }
        else
        {
            return true;
        }
    } 
}
