package com.github.firefliesforj.utils;

public class MathUtils
{
    public static double measurementConverter(double value, double minValue, double maxValue, double targetMinValue,
	    double targetMaxValue)
    {
	double convertedValue = value;
	double range = maxValue - minValue;
	double targetRange = targetMaxValue - targetMinValue;
	
	convertedValue = (targetRange / range) * (value - minValue) + targetMinValue;

	return convertedValue;
    }
}
