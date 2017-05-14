package com.github.firefliesforj.function;

public class RotatedRastriginFunction extends Function
{
    public RotatedRastriginFunction(double bottomDomainLimit, double topDomainLimit)
    {
	super(bottomDomainLimit, topDomainLimit);
	this.setMinValue(0);
	this.setMaxValue(Double.MAX_VALUE / 2);
    }

    @Override
    public double fitness(double[] position)
    {
	double fitness = 0;

	for (int i = 0; i < position.length; i++)
	{
	    fitness += Math.pow(position[i], 2) - 10 * Math.cos(2 * Math.PI * position[i]) + 10;
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
