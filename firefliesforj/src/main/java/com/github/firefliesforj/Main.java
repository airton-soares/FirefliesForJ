package com.github.firefliesforj;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

import com.github.firefliesforj.function.Function;
import com.github.firefliesforj.function.RosenbrockFunction;

public class Main
{
    /**
     * 
     * @param args
     *            [0] - Tamanho da população
     * @param args
     *            [1] - Dimensão do espaço de busca
     * @param args
     *            [2] - Coeficiente de absorção
     * @param args
     *            [3] - Tamanho do passo
     * @param args
     *            [4] - Valor de brilho mínimo
     * @param args
     *            [5] - Valor de brilho máximo
     * @param args
     *            [6] - Número de iterações
     * @param args
     *            [7] - Limite inferior do espaço de busca
     * @param args
     *            [8] - Limite superior do espaço de busca
     *
     */
    public static void main(String[] args)
    {
	if (args.length == 9)
	{
	    int swarmSize = Integer.parseInt(args[0]);
	    int dimension = Integer.parseInt(args[1]);
	    double absorptionCoefficient = Double.parseDouble(args[2]);
	    double stepSize = Double.parseDouble(args[3]);
	    double minGlowValue = Double.parseDouble(args[4]);
	    double maxGlowValue = Double.parseDouble(args[5]);
	    int numberOfIteractions = Integer.parseInt(args[6]);
	    double bottomDomainLimit = Double.parseDouble(args[7]);
	    double topDomainLimit = Double.parseDouble(args[8]);
	    Function function = new RosenbrockFunction(bottomDomainLimit, topDomainLimit);

	    FA fa = new FA(swarmSize, dimension, numberOfIteractions, absorptionCoefficient, stepSize, minGlowValue,
		    maxGlowValue, function);

	    fa.optimize(function);

	    double[] results = fa.getResults();
	    double[] iteractions = new double[numberOfIteractions];

	    for (int i = 0; i < numberOfIteractions; i++)
	    {
		iteractions[i] = i + 1;
	    }

	    Plot2DPanel plot = new Plot2DPanel();
	    plot.addLinePlot("Optimization result", iteractions, results);
	    JFrame frame = new JFrame("Optimization report");
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
	    frame.setContentPane(plot);
	    frame.pack();
	    frame.setVisible(true);
	}
	else
	{
	    System.err.println("INVALID NUMBER OF ARGUMENTS!");
	}

    }
}
