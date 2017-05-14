package com.github.firefliesforj.swarm;

import java.util.ArrayList;
import java.util.List;

import com.github.firefliesforj.firefly.Firefly;
import com.github.firefliesforj.function.Function;

public class Swarm
{
    private List<Firefly> firefliesList;
    private double absorptionCoefficient;
    private double stepSize;
    private double[] bestPosition;

    public Swarm(int swarmSize, int dimension, double absorptionCoefficient, double stepSize, double minGlowValue,
	    double maxGlowValue, Function function)
    {
	this.bestPosition = new double[dimension];
	this.absorptionCoefficient = absorptionCoefficient;
	this.stepSize = stepSize;
	this.firefliesList = initializeSwarm(swarmSize, dimension, minGlowValue, maxGlowValue, function);
    }

    private List<Firefly> initializeSwarm(int swarmSize, int dimension, double minGlowValue, double maxGlowValue,
	    Function function)
    {
	List<Firefly> firefliesList = new ArrayList<Firefly>();

	for (int i = 0; i < swarmSize; i++)
	{
	    firefliesList.add(new Firefly(dimension, minGlowValue, maxGlowValue, function));
	}

	return firefliesList;
    }

    public void performFlight(Function function)
    {
	int numerOfFireflies = this.firefliesList.size();

	for (int i = 0; i < numerOfFireflies; i++)
	{
	    Firefly currentFirefly = this.firefliesList.get(i);
	    double[] currentFireflyPosition = currentFirefly.getCurrentPosition();
	    int dimension = currentFireflyPosition.length;
	    boolean isTheBest = true;

	    for (int j = 0; j < numerOfFireflies; j++)
	    {
		if (j != i)
		{
		    Firefly neighborFirefly = this.firefliesList.get(j);

		    if (neighborFirefly.getGlow() > currentFirefly.getGlow())
		    {
			double[] neighborFireflyPosition = neighborFirefly.getCurrentPosition();

			double squareSum = 0;

			for (int k = 0; k < dimension; k++)
			{
			    squareSum += Math.pow((currentFireflyPosition[k] - neighborFireflyPosition[k]), 2);
			}

			double distance = Math.sqrt(squareSum);

			double newGlow = currentFirefly.getGlow()
				* Math.exp(-(this.absorptionCoefficient * Math.pow(distance, 2)));
			currentFirefly.setGlow(newGlow);

			for (int k = 0; k < dimension; k++)
			{
			    double glowFactor = currentFirefly.getGlow()
				    * (neighborFireflyPosition[k] - currentFireflyPosition[k]);
			    currentFireflyPosition[k] += glowFactor + this.stepSize * (Math.random() - 0.5);
			}

			currentFirefly.updateBestPosition(function);

			isTheBest = false;
		    }
		}
	    }

	    if (isTheBest)
	    {
		for (int k = 0; k < dimension; k++)
		{
		    currentFireflyPosition[k] += this.stepSize * (Math.random() - 0.5);
		}

		currentFirefly.updateBestPosition(function);
		currentFirefly.glowValueBasedOnFunction(function);
	    }
	}

	this.updateBestPosition(function);
    }

    private void updateBestPosition(Function function)
    {
	int numerOfFireflies = this.firefliesList.size();
	double bestPositionFitness = function.fitness(this.bestPosition);

	for (int i = 0; i < numerOfFireflies; i++)
	{
	    double[] currentFireflyPosition = this.firefliesList.get(i).getCurrentPosition();
	    double currentFireflyFitness = function.fitness(currentFireflyPosition);

	    if (function.compareFitness(currentFireflyFitness, bestPositionFitness))
	    {
		this.bestPosition = currentFireflyPosition.clone();
	    }
	}

    }

    public List<Firefly> getFirefliesList()
    {
	return firefliesList;
    }

    public void setFirefliesList(List<Firefly> firefliesList)
    {
	this.firefliesList = firefliesList;
    }

    public double getAbsorptionCoefficient()
    {
	return absorptionCoefficient;
    }

    public void setAbsorptionCoefficient(double absorptionCoefficient)
    {
	this.absorptionCoefficient = absorptionCoefficient;
    }

    public double getStepSize()
    {
	return stepSize;
    }

    public void setStepSize(double stepSize)
    {
	this.stepSize = stepSize;
    }

    public double[] getBestPosition()
    {
	return bestPosition;
    }

    public void setBestPosition(double[] bestPosition)
    {
	this.bestPosition = bestPosition;
    }
}
