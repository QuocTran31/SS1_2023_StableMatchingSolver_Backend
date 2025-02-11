package com.example.SS2_Backend.dto.request;

import com.example.SS2_Backend.model.StableMatching.Individual;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StableMatchingProblemDTO {
    private String problemName;
    private int numberOfSets;
    private int numberOfIndividuals;
    @JsonDeserialize(contentUsing = IndividualDeserializer.class)
    private ArrayList<Individual> Individuals;
    private String[] allPropertyNames;
    private String fitnessFunction;
    private int populationSize;
    private int evolutionRate;
    private int maximumExecutionTime;

    @JsonProperty("Individuals")
    public void setIndividuals(ArrayList<Individual> individuals) {
        this.Individuals = individuals;
    }

    @JsonProperty("populationSize")
    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    @JsonProperty("evolutionRate")
    public void setEvolutionRate(int evolutionRate) {
        this.evolutionRate = evolutionRate;
    }

    @JsonProperty("maximumExecutionTime")
    public void setMaximumExecutionTime(int maximumExecutionTime) {
        this.maximumExecutionTime = maximumExecutionTime;
    }

    public Individual getIndividual(int index) {
        return Individuals.get(index);
    }

    public int getNumberOfIndividuals(){
        return Individuals.size();
    }


    public String toString() {
        return "Matching_Theory_Problem {" +
                ", ProblemName= " + problemName + '\'' +
                ", NumberOfSets= " + numberOfSets + "\n" +
                ", NumberOfIndividuals= " + numberOfIndividuals +
                ", Individuals= " + Individuals.toString() +
                ", AllPropertyName= " + java.util.Arrays.toString(allPropertyNames) +
                ", fitnessFunction= '" + fitnessFunction + '\'' +
                ", PopulationSize= " + populationSize +
                ", EvolutionRate= " +evolutionRate +
                ", MaximumExecutionTime" + maximumExecutionTime +
                "}";
    }
}
