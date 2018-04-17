package com.ubs.opsit.interviews;

public class BerlinClock {


    public String processTime(String time){
        StringBuffer berlinTime = new StringBuffer();
        if(time != null){
            String[] arrTime = time.split(":");
            int hours, minutes, seconds = 0;
            if(arrTime.length == 3){
                try{
                    hours = Integer.parseInt(arrTime[0]);
                    minutes = Integer.parseInt(arrTime[1]);
                    seconds = Integer.parseInt(arrTime[2]);

                    berlinTime.append(getSeconds(seconds)).append(".");
                    berlinTime.append(getTopHours(hours)).append(".");
                    berlinTime.append(getBottomHours(hours)).append(".");
                    berlinTime.append(getTopMinutes(minutes)).append(".");
                    berlinTime.append(getBottomMinutes(minutes)).append(".");

                }catch(NumberFormatException e){
                    throw new IllegalArgumentException("Invalid Input Time!");
                }

            }else{
                throw new IllegalArgumentException("Invalid Input Time!");
            }

        }

        return berlinTime.toString();
    }

    public String getSeconds(int seconds){
        return (seconds % 2 == 0) ? "Y" : "0";
    }

    public String getTopHours(int hours){
        int noOfOnLights = hours / 5;
        return getOnOffLights(noOfOnLights,4,"R").toString();
    }

    public String getBottomHours(int hours){
        int noOfOnLights = hours % 5;
        return getOnOffLights(noOfOnLights,4,"R").toString();
    }

    public String getTopMinutes(int minutes){
        int noOfOnLights = minutes / 5;
        StringBuffer topMinutes = new StringBuffer();
        for(int i=0; i<noOfOnLights; i++){
            if((i+1)%3 == 0) {
                topMinutes.append("R");
            }else {
                topMinutes.append("Y");
            }
        }

        int noOfOffLights = 11 - noOfOnLights;

        for(int i=0; i<noOfOffLights; i++){
            topMinutes.append("0");
        }
         return topMinutes.toString();
    }

    public String getBottomMinutes(int minutes){
        int noOfOnLights = minutes % 5;
        return getOnOffLights(noOfOnLights,4,"Y").toString();
    }

    public StringBuffer getOnOffLights(int noOfOnLights, int lightsInRow, String onSign){
        StringBuffer onOffLights = new StringBuffer();
        for(int i=0; i<noOfOnLights; i++){
            onOffLights.append(onSign);
        }

        int noOfOffLights = lightsInRow - noOfOnLights;

        for(int i=0; i<noOfOffLights; i++){
            onOffLights.append("0");
        }

        return onOffLights;
    }
}
