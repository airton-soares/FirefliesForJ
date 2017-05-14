package com.github.firefliesforj.function;

public class RosenbrockFunction extends Function
{
    public RosenbrockFunction(double bottomDomainLimit, double topDomainLimit)
    {
	super(bottomDomainLimit, topDomainLimit);
	this.setMinValue(0);
	this.setMaxValue(Math.pow(10, 100));
    }

    @Override
    public double fitness(double[] position)
    {
	double fitness = 0;

	for (int i = 0; i < position.length - 1; i++)
	{
	    fitness += 100 * Math.pow((Math.pow(position[i], 2) - position[i + 1]), 2) + Math.pow(position[i] - 1, 2);
	}

	return fitness;
    }

    @Override
    public boolean compareFitness(double fitness1, double fitness2)
    {
	boolean compare = false;

	if (fitness1 < fitness2)
	{
	    compare = true;
	}

	return compare;
    }

}
