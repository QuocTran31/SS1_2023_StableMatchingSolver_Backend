package com.example.SS2_Backend.model.StableMatching;
import com.example.SS2_Backend.dto.request.IndividualDeserializer;
import com.example.SS2_Backend.model.StableMatching.Requirement.Requirement;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

import static com.example.SS2_Backend.util.Utils.*;

@Getter
@JsonDeserialize(using = IndividualDeserializer.class)
public class Individual {
    @Setter
    private String IndividualName;
    @Setter
    private int IndividualSet;
    private int Capacity;
    private final List<Property> Properties = new ArrayList<>();


    public Individual(){

    }

    @JsonProperty("Properties")
    public void setProperty(Double propertyValue, int propertyWeight, String inputRequirement) {
        String[] decodedRequirement = decodeInputRequirement(inputRequirement);
        Property property = new Property(propertyValue, propertyWeight, decodedRequirement);
        this.Properties.add(property);
    }
    public void setProperty(Double propertyValue, int propertyWeight, String[] inputRequirement) {
        Property property = new Property(propertyValue, propertyWeight, inputRequirement);
        this.Properties.add(property);
    }
    public static String[] decodeInputRequirement(String item){
        item = item.trim();
        String[] result = new String[2];
        int index = findFirstNonNumericIndex(item);
        if(index == -1){
            if(isInteger(item)) {
                try {
                    int a = Integer.parseInt(item);
                    result[0] = item;
                    if(a >= 0 && a <= 10){
                        result[1] = null;
                    }else{
                        result[1] = "++";
                    }
                } catch (NumberFormatException e){
                    System.out.println("error index - 1");
                    result[0] = "-1";
                    result[1] = "++";
                }
            }else if(isDouble(item)) {
                    result[0] = "-2";
                    result[1] = null;
            }else{
                result[0] = "-3";
                result[1] = null;
            }
        }else{
            if(item.contains(":")){
                String[] parts = item.split(":");
                result[0] = parts[0].trim();
                result[1] = parts[1].trim();
            }else if(item.contains("++")){
                String[] parts = item.split("\\+\\+");
                result[0] = parts[0].trim();
                result[1] = "++";
            } else if (item.contains("--")) {
                String[] parts = item.split("--");
                result[0] = parts[0].trim();
                result[1] = "--";
            } else {
                result[0] = "-2";
                result[1] = "++";
            }
        }
        return result;
    }

    public static void main(String[] args){
        String inputReq = "200.011--";
        String[] requirement = decodeInputRequirement(inputReq);
        System.out.println(Arrays.toString(requirement));
    }
    private static int findFirstNonNumericIndex(String s) {
        s = s.trim();
        int index = 0;
        while (index < s.length() && (Character.isDigit(s.charAt(index)) || s.charAt(index) == '.')) {
            index++;
        }
        if(index < s.length()){
            return index;
        }else{
            return -1;
        }
    }
    @JsonProperty("IndividualName")
    public void setIndividualName(String individualName) {
        IndividualName = individualName;
    }

    @JsonProperty("IndividualSet")
    public void setIndividualSet(int individualSet) {
        IndividualSet = individualSet;
    }

    public void setCapacity(int capacity){
        this.Capacity = capacity;
    }

    public int getNumberOfProperties(){
        return Properties.size();
    }

//    public String getPropertyName(int index){
//        if(index >= 0 && index < this.Properties.size()){
//            return Properties.get(index).getName();
//        }else{
//            return null;
//        }
//    }

    public Double getPropertyValue(int index){
        if(index >= 0 && index < this.Properties.size()){
            return Properties.get(index).getValue();
        }else{
            return null;
        }
    }

    public int getPropertyWeight(int index) {
        if(index >= 0 && index < this.Properties.size()){
            return Properties.get(index).getWeight();
        }else{
            return 0;
        }
    }

    public Requirement getRequirement(int index){
        return Properties.get(index).getRequirement();
    }

    public String toString(){
        System.out.println("Name: " + IndividualName);
        System.out.println("Belong to set: " + IndividualSet);
        System.out.println("Properties:");
        System.out.println("---------------------------------");
        for (Property property : Properties) {
            System.out.println(property.toString());
        }
        return "\n";
    }

}
