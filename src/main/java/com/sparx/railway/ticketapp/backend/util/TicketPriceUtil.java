package com.sparx.railway.ticketapp.backend.util;

import com.sparx.railway.ticketapp.backend.constant.DistanceConstant;
import com.sparx.railway.ticketapp.backend.constant.PriceConstant;
import com.sparx.railway.ticketapp.backend.entities.TrainEntity;
import com.sparx.railway.ticketapp.backend.repository.TrainEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TicketPriceUtil {

    private final TrainEntityRepository trainEntityRepository;

    public Double priceCalculator(int trainNo, List<Integer> ageList,int totalDistance, String classType){
       TrainEntity trainEntity = trainEntityRepository.findByTrainNo(trainNo).get();
       double facilityFactor = trainEntity.getFacilityFactor();
       double price = 0;
       for(Integer age : ageList){
           if(age > 0 && age <= 5){
               if(classType.equalsIgnoreCase("General")){
                   price += totalDistance * facilityFactor * PriceConstant.PRICE_FACTOR_FOR_CHILD * DistanceConstant.RATE_FOR_GENRAL_PER_KM;

               }else if(classType.equalsIgnoreCase("Sleeper")){
                   price += totalDistance * facilityFactor * PriceConstant.PRICE_FACTOR_FOR_CHILD * DistanceConstant.RATE_FOR_SLEEPERCLASS_PER_KM;

               }else if(classType.equalsIgnoreCase("First Ac")){
                   price += totalDistance * facilityFactor * PriceConstant.PRICE_FACTOR_FOR_CHILD * DistanceConstant.RATE_FOR_FIRST_CLASS_AC_PER_KM;

               }else if(classType.equalsIgnoreCase("Second Ac")){
                   price += totalDistance * facilityFactor * PriceConstant.PRICE_FACTOR_FOR_CHILD * DistanceConstant.RATE_FOR_SECOND_CLASS_AC_PER_KM;

               }else if(classType.equalsIgnoreCase("Third Ac")){
                   price += totalDistance * facilityFactor * PriceConstant.PRICE_FACTOR_FOR_CHILD * DistanceConstant.RATE_FOR_THIRD_CLASS_SL_PER_KM;
               }else{
                   System.out.println("invalid coach type option has been selected else block of ticket price util is being executed ");
               }

           }else if( age > 5 && age >= 18){
               if(classType.equalsIgnoreCase("General")){
                   price += totalDistance * facilityFactor * PriceConstant.PRICE_FACTOR_FOR_INFANT* DistanceConstant.RATE_FOR_GENRAL_PER_KM;

               }else if(classType.equalsIgnoreCase("Sleeper")){
                   price += totalDistance * facilityFactor * PriceConstant.PRICE_FACTOR_FOR_INFANT* DistanceConstant.RATE_FOR_SLEEPERCLASS_PER_KM;

               }else if(classType.equalsIgnoreCase("First Ac")){
                   price += totalDistance * facilityFactor * PriceConstant.PRICE_FACTOR_FOR_INFANT* DistanceConstant.RATE_FOR_FIRST_CLASS_AC_PER_KM;

               }else if(classType.equalsIgnoreCase("Second Ac")){
                   price += totalDistance * facilityFactor * PriceConstant.PRICE_FACTOR_FOR_INFANT* DistanceConstant.RATE_FOR_SECOND_CLASS_AC_PER_KM;

               }else if(classType.equalsIgnoreCase("Third Ac")){
                   price += totalDistance * facilityFactor * PriceConstant.PRICE_FACTOR_FOR_INFANT* DistanceConstant.RATE_FOR_THIRD_CLASS_SL_PER_KM;
               }else{
                   System.out.println("invalid coach type option has been selected else block of ticket price util is being executed ");
               }


           }else if(age > 18 && age <= 60){
               if(classType.equalsIgnoreCase("General")){
                   price += totalDistance * facilityFactor * PriceConstant.PRICE_FACTOR_FOR_ADULT * DistanceConstant.RATE_FOR_GENRAL_PER_KM;

               }else if(classType.equalsIgnoreCase("Sleeper")){
                   price += totalDistance * facilityFactor * PriceConstant.PRICE_FACTOR_FOR_ADULT * DistanceConstant.RATE_FOR_SLEEPERCLASS_PER_KM;

               }else if(classType.equalsIgnoreCase("First Ac")){
                   price += totalDistance * facilityFactor * PriceConstant.PRICE_FACTOR_FOR_ADULT * DistanceConstant.RATE_FOR_FIRST_CLASS_AC_PER_KM;

               }else if(classType.equalsIgnoreCase("Second Ac")){
                   price += totalDistance * facilityFactor * PriceConstant.PRICE_FACTOR_FOR_ADULT * DistanceConstant.RATE_FOR_SECOND_CLASS_AC_PER_KM;

               }else if(classType.equalsIgnoreCase("Third Ac")){
                   price += totalDistance * facilityFactor * PriceConstant.PRICE_FACTOR_FOR_ADULT * DistanceConstant.RATE_FOR_THIRD_CLASS_SL_PER_KM;
               }else{
                   System.out.println("invalid coach type option has been selected else block of ticket price util is being executed ");
               }
           }
           else if(age > 60 && age < 125){
               if(classType.equalsIgnoreCase("General")){
                   price += totalDistance * facilityFactor * PriceConstant.PRICE_FACTOR_FOR_SENIOR_CITIZEN * DistanceConstant.RATE_FOR_GENRAL_PER_KM;

               }else if(classType.equalsIgnoreCase("Sleeper")){
                   price += totalDistance * facilityFactor * PriceConstant.PRICE_FACTOR_FOR_SENIOR_CITIZEN * DistanceConstant.RATE_FOR_SLEEPERCLASS_PER_KM;

               }else if(classType.equalsIgnoreCase("First Ac")){
                   price += totalDistance * facilityFactor * PriceConstant.PRICE_FACTOR_FOR_SENIOR_CITIZEN * DistanceConstant.RATE_FOR_FIRST_CLASS_AC_PER_KM;

               }else if(classType.equalsIgnoreCase("Second Ac")){
                   price += totalDistance * facilityFactor * PriceConstant.PRICE_FACTOR_FOR_SENIOR_CITIZEN * DistanceConstant.RATE_FOR_SECOND_CLASS_AC_PER_KM;

               }else if(classType.equalsIgnoreCase("Third Ac")){
                   price += totalDistance * facilityFactor * PriceConstant.PRICE_FACTOR_FOR_SENIOR_CITIZEN * DistanceConstant.RATE_FOR_THIRD_CLASS_SL_PER_KM;
               }else{
                   System.out.println("invalid coach type option has been selected else block of ticket price util is being executed ");
               }
           }else{
                System.out.println("the age that is being selected is not valid ");
           }
       }
       System.out.println("the total price of the ticket after the calculation is "+price);
       return price;
    }


}
