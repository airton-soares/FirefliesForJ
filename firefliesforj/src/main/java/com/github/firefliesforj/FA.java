package com.github.firefliesforj;

import com.github.firefliesforj.function.Function;
import com.github.firefliesforj.swarm.Swarm;

public class FA
{
    private Swarm swarm;
    private int numberOfIteractions;
    private double[] results;

    public FA(int swarmSize, int dimension, int numberOfIteractions, double absorptionCoefficient, double stepSize,
	    double minGlowValue, double maxGlowValue, Function function)
    {
	this.swarm = new Swarm(swarmSize, dimension, absorptionCoefficient, stepSize, minGlowValue, maxGlowValue,
		function);
	this.numberOfIteractions = numberOfIteractions;
	this.results = new double[numberOfIteractions];
    }

    public void optimize(Function function)
    {
	for (int i = 0; i < this.numberOfIteractions; i++)
	{
	    this.swarm.performFlight(function);
	    this.results[i] = function.fitness(this.swarm.getBestPosition());
	}
    }

    public Swarm getSwarm()
    {
	return swarm;
    }

    public void setSwarm(Swarm swarm)
    {
	this.swarm = swarm;
    }

    public int getNumberOfIteractions()
    {
	return numberOfIteractions;
    }

    public void setNumberOfIteractions(int numberOfIteractions)
    {
	this.numberOfIteractions = numberOfIteractions;
    }

    public double[] getResults()
    {
	return results;
    }

    public void setResults(double[] results)
    {
	this.results = results;
    }
}
