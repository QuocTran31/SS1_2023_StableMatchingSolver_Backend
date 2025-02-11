package com.example.SS2_Backend.model.StableMatching;

import com.example.SS2_Backend.model.StableMatching.Requirement.OneBoundRequirement;
import com.example.SS2_Backend.model.StableMatching.Requirement.Requirement;
import com.example.SS2_Backend.model.StableMatching.Requirement.ScaleTargetRequirement;
import com.example.SS2_Backend.model.StableMatching.Requirement.TwoBoundRequirement;
import lombok.Getter;

import java.util.Objects;
import static com.example.SS2_Backend.util.Utils.*;

public class Property {
    @Getter
    private final Double value;
    @Getter
    private final int weight;
    private final Requirement requirement;

    public Property(double value, int weight, String[] inputRequirement) {
        this.value = value;
        this.weight = weight;
        this.requirement = setRequirement(inputRequirement);
    }

    public Requirement setRequirement(String[] array) {
        try {
            if (Objects.equals(array[1], "++")) {
                return new OneBoundRequirement(Double.parseDouble(array[0]), "++");
            } else if (Objects.equals(array[1], "--")) {
                return new OneBoundRequirement(Double.parseDouble(array[0]), "--");
            } else if (Objects.equals(array[1], null)) {
                if(isInteger(array[0])){
                    return new ScaleTargetRequirement(Integer.parseInt(array[0]));
                }else if(isDouble(array[0])){
                    return new OneBoundRequirement(Double.parseDouble(array[0]), "++");
                }else{
                    return new OneBoundRequirement(0.0, "++");
                }
            } else {
                double value1 = Double.parseDouble(array[0]);
                double value2 = Double.parseDouble(array[1]);
                return new TwoBoundRequirement(value1, value2);
            }
        } catch (NumberFormatException e) {
            return new OneBoundRequirement(0.0, "++");
        }
    }

    public Requirement getRequirement(){
        return this.requirement;
    }
    @Override
    public String toString() {
        return "Value: " + value + " , Requirement " + requirement + " , Weight: " + weight;
    }
    public static void main(String[] args){
        String[] strings = {"103.44", "++"};
        Property p = new Property(16.5,6, strings);
        System.out.println(p);
    }

}
