package com.github.firefliesforj.function;

public abstract class Function
{
    private double bottomDomainLimit;
    private double topDomainLimit;
    private double minValue;
    private double maxValue;

    public Function(double bottomDomainLimit, double topDomainLimit)
    {
	this.bottomDomainLimit = bottomDomainLimit;
	this.topDomainLimit = topDomainLimit;
    }

    /**
     * Função avaliadora da aptidão de uma determinada função para uma dada
     * posição no espaço de busca.
     * 
     * @param position
     *            - Posição a ser avaliada pela função de "fitness"
     * @return Aptidão da posição passada como parâmetro para essa função
     * 
     * @author Airton Soares
     */
    public abstract double fitness(double[] position);

    public abstract boolean compareFitness(double fitness1, double fitness2);

    public double getBottomDomainLimit()
    {
	return bottomDomainLimit;
    }

    public void setBottomDomainLimit(double bottomDomainLimit)
    {
	this.bottomDomainLimit = bottomDomainLimit;
    }

    public double getTopDomainLimit()
    {
	return topDomainLimit;
    }

    public void setTopDomainLimit(double topDomainLimit)
    {
	this.topDomainLimit = topDomainLimit;
    }

    public double getMinValue()
    {
	return minValue;
    }

    public void setMinValue(double minValue)
    {
	this.minValue = minValue;
    }

    public double getMaxValue()
    {
	return maxValue;
    }

    public void setMaxValue(double maxValue)
    {
	this.maxValue = maxValue;
    }
}
