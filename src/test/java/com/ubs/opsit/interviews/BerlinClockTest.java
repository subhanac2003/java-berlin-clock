package com.ubs.opsit.interviews;

import org.junit.Test;
import static org.junit.Assert.*;

public class BerlinClockTest {

    BerlinClock berlinClock = new BerlinClock();

    @Test
    public void testSecodsRowLampBlinksForEveryAlternateSeconds(){
        assertEquals("0", berlinClock.getSeconds(1));
        assertEquals("Y", berlinClock.getSeconds(2));
        assertNotEquals("Y", berlinClock.getSeconds(45));
    }

    @Test
    public void testTopHoursLightsShouldBeOnForHoursValueMultiplesOf5(){
        assertEquals("R000", berlinClock.getTopHours(5));
        assertEquals("RRRR", berlinClock.getTopHours(23));
        assertNotEquals("R000", berlinClock.getTopHours(3));
    }

    @Test
    public void testTopHoursLightsShouldBeOffForHoursValuesLessThan5(){
        assertEquals("0000", berlinClock.getTopHours(4));
        assertEquals("0000", berlinClock.getTopHours(2));
    }

    @Test
    public void testBottomHoursLightsShouldBeOnForHoursValuesLessThan5(){
        assertEquals("R000", berlinClock.getBottomHours(1));
        assertEquals("RRRR", berlinClock.getBottomHours(4));
    }

    @Test
    public void testBottomHoursLightsShouldBeOffForHoursValuesMoreThan4(){
        assertEquals("0000", berlinClock.getBottomHours(10));
    }

    @Test
     public void testTopMinutesAllLightsShouldBeOffForMinutesValueLessThan5(){
        assertEquals("00000000000", berlinClock.getTopMinutes(4));
     }

    @Test
    public void testTopMinutesTwoYellowLightsShouldBeOnForEvery5MinutesLessThan15Minutes(){
        assertEquals("Y0000000000", berlinClock.getTopMinutes(5));
        assertEquals("YY000000000", berlinClock.getTopMinutes(10));
    }

    @Test
    public void testTopMinutesCheckRedLightsShouldBeOnForEvery15thMinute(){
        assertEquals("YYR00000000", berlinClock.getTopMinutes(15));
        assertEquals("YYRYYR00000", berlinClock.getTopMinutes(30));
        assertEquals("YYRYYRYY000", berlinClock.getTopMinutes(40));
    }

    @Test
    public void testBottomMinutesLightsShouldBeOnForMinutesValuesLessThan5(){
        assertEquals("Y000", berlinClock.getBottomMinutes(1));
        assertEquals("YYYY", berlinClock.getBottomMinutes(4));
        assertNotEquals("Y000", berlinClock.getBottomMinutes(10));

    }

    @Test
    public void testBottomMinutesLightsShouldBeOffForMinutesvaluesMoreThan4(){
        assertEquals("0000", berlinClock.getBottomMinutes(15));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testProcessShouldReturnErrorForInvalidTimeInput(){
        berlinClock.processTime("22:59");
        berlinClock.processTime("22:45:SS");
    }

    public void testProcessTimeShouldReturnBerlinTimeForNormalTime(){
        assertEquals("0.RRRR.RR00.YYRYYRYYRYY.YYYY", berlinClock.processTime("22:59:15"));
        assertEquals("Y.R000.0000.YYRYYRYY000.YYY0", berlinClock.processTime("05:43:16"));
    }
}
