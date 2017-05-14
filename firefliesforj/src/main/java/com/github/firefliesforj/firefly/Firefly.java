package com.github.firefliesforj.firefly;

import java.util.concurrent.ThreadLocalRandom;

import com.github.firefliesforj.function.Function;
import com.github.firefliesforj.utils.MathUtils;

public class Firefly
{
    private double[] currentPosition;
    private double[] bestPosition;
    private double glow;
    private double minGlowValue;
    private double maxGlowValue;

    public Firefly(int dimension, double minGlowValue, double maxGlowValue, Function function)
    {
	this.minGlowValue = minGlowValue;
	this.maxGlowValue = maxGlowValue;
	this.currentPosition = initializeFirefly(dimension, function);
	this.bestPosition = this.currentPosition.clone();
    }

    private double[] initializeFirefly(int dimension, Function function)
    {
	double[] currentPosition = new double[dimension];

	for (int i = 0; i < dimension; i++)
	{
	    currentPosition[i] = ThreadLocalRandom.current().nextDouble(function.getBottomDomainLimit(),
		    function.getTopDomainLimit());
	}

	this.currentPosition = currentPosition;
	this.glowValueBasedOnFunction(function);

	return currentPosition;
    }

    public void glowValueBasedOnFunction(Function function)
    {
	double glow = minGlowValue;
	double fitness = function.fitness(this.currentPosition);

	glow = MathUtils.measurementConverter(fitness, function.getMinValue(), function.getMaxValue(), minGlowValue,
		maxGlowValue);
	glow = maxGlowValue - glow;

	this.glow = glow;
    }

    public void updateBestPosition(Function function)
    {
	double currentPositionFitness = function.fitness(this.currentPosition);
	double bestPositionFitness = function.fitness(this.bestPosition);

	if (function.compareFitness(currentPositionFitness, bestPositionFitness))
	{
	    this.bestPosition = this.currentPosition.clone();
	}
    }

    public double[] getCurrentPosition()
    {
	return currentPosition;
    }

    public void setCurrentPosition(double[] currentPosition)
    {
	this.currentPosition = currentPosition;
    }

    public double[] getBestPosition()
    {
	return bestPosition;
    }

    public void setBestPosition(double[] bestPosition)
    {
	this.bestPosition = bestPosition;
    }

    public double getGlow()
    {
	return glow;
    }

    public void setGlow(double glow)
    {
	this.glow = glow;
    }
}
