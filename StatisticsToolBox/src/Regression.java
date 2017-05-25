import java.util.ArrayList;

import flanagan.analysis.RegressionDerivativeFunction;
import flanagan.analysis.RegressionDerivativeFunction2;
import flanagan.analysis.Stat;
import flanagan.math.Conv;
import flanagan.math.Fmath;
import flanagan.math.Matrix;

public class Regression
{
  protected int nData0 = 0;
  protected int nData = 0;
  protected double nEffective = 0.0D;
  protected int nXarrays = 1;
  protected int nYarrays = 1;
  protected int nParam = 0;
  protected int degreesOfFreedom = 0;
  protected double[][] xData = (double[][])null;
  protected double[][] xErrors = (double[][])null;
  protected boolean xErrorsEntered = false;
  protected double[] yData = null;
  protected double[] yErrors = null;
  protected boolean yErrorsEntered = false;
  protected boolean dualErrorsRequired = false;
  protected boolean trueErrors = true;
  protected double[] weight = null;
  protected double[] yCalc = null;
  protected double[] residual = null;
  protected double[] residualW = null;
  protected boolean weightOpt = false;
  protected int weightFlag = 0;
  protected String[] weightWord = { "", "Weighted " };
  protected double[] best = null;
  protected double[] bestSd = null;
  protected double[] pseudoSd = null;
  protected double[] tValues = null;
  protected double[] pValues = null;
  protected double fixedInterceptL = 0.0D;
  protected double fixedInterceptP = 0.0D;
  protected double yMean = 0;
  protected double yWeightedMean = 0;
  protected double chiSquare = 0;
  protected double reducedChiSquare = 0;
  protected double sumOfSquaresError = 0;
  protected double sumOfSquaresTotal = 0;
  protected double sumOfSquaresRegrn = 0;
  protected double lastSSnoConstraint = 0.0D;
  protected double[][] covar = (double[][])null;
  protected double[][] corrCoeff = (double[][])null;
  protected double xyR = 0;
  protected double yyR = 0;
  protected double multR = 0;
  protected double adjustedR = 0;
  protected double multipleF = 0;
  protected double multipleFprob = 0;
  protected String[] paraName = null;
  protected int prec = 4;
  protected int field = 13;
  protected int simplexFlag = 1;
  protected int derivFlag = 1;
  protected boolean nonLinStatsNeeded = true;
  protected int lastMethod = -1;
  protected int[] dualMethods = { 19, 20, 21, 25, 26, 28, 39, 40, 41, 42, 43, 44, 45, 50, 51, 52 };
  protected int nSpecDual = this.dualMethods.length;
  protected boolean bestPolyFlag = false;
  protected int bestPolynomialDegree = 0;
  protected double fProbSignificance = 0.05D;
  protected ArrayList<Object> bestPolyArray = new ArrayList();
  protected boolean bestPolyTooFewN = false;
  protected boolean userSupplied = true;
  protected double kayValue = 0.0D;
  protected boolean frechetWeibull = true;
  protected boolean linNonLin = true;
  protected boolean trueFreq = false;
  protected String xLegend = "x axis values";
  protected String yLegend = "y axis values";
  protected String graphTitle = " ";
  protected String graphTitle2 = " ";
  protected boolean legendCheck = false;
  protected boolean suppressPrint = false;
  protected boolean suppressYYplot = false;
  protected boolean suppressErrorMessages = false;
  protected boolean nlrStatus = true;
  protected int scaleOpt = 0;
  protected double[] scale = null;
  protected boolean zeroCheck = false;
  protected boolean penalty = false;
  protected boolean sumPenalty = false;
  protected int nConstraints = 0;
  protected int nSumConstraints = 0;
  protected int maxConstraintIndex = -1;
  protected double constraintTolerance = 1.0E-4D;
  protected ArrayList<Object> penalties = new ArrayList();
  protected ArrayList<Object> sumPenalties = new ArrayList();
  protected int[] penaltyCheck = null;
  protected int[] sumPenaltyCheck = null;
  protected double penaltyWeight = 1.0E30D;
  protected int[] penaltyParam = null;
  protected int[][] sumPenaltyParam = (int[][])null;
  protected double[][] sumPlusOrMinus = (double[][])null;
  protected int[] sumPenaltyNumber = null;
  protected double[] constraints = null;
  protected double[] sumConstraints = null;
  protected int constraintMethod = 0;
  protected ArrayList<Object> constrainedSingle = new ArrayList();
  protected ArrayList<Object> constrainedMultiple = new ArrayList();
  String[] constraintString = null;
  protected boolean scaleFlag = true;
  protected double yScaleFactor = 1.0D;
  protected int nMax = 3000;
  protected int minIter = 300;
  protected int nIter = 0;
  protected int konvge = 3;
  protected int kRestart = 0;
  protected double fMin = -1.0D;
  protected double fTol = 1.0E-9D;
  protected double rCoeff = 1.0D;
  protected double eCoeff = 2.0D;
  protected double cCoeff = 0.5D;
  protected double[] startH = null;
  protected double[] stepH = null;
  protected double[] startSH = null;
  protected double[] stepSH = null;
  protected double dStep = 0.1D;
  protected double[][] grad = (double[][])null;
  protected double delta = 1.0E-4D;
  protected boolean invertFlag = true;
  protected boolean posVarFlag = true;
  protected int minTest = 0;
  protected double simplexSd = 0.0D;
  protected boolean statFlag = true;
  protected boolean plotOpt = true;
  protected boolean multipleY = false;
  protected boolean ignoreDofFcheck = false;
  protected double[] values = null;
  protected boolean[] fixed = null;
  protected int nGaussians = 0;
  protected double[] multGaussFract = null;
  protected double[] multGaussFractErrors = null;
  protected double[] multGaussCoeffVar = null;
  protected double[] multGaussTvalue = null;
  protected double[] multGaussPvalue = null;
  protected double multGaussScale = 1.0D;
  protected double multGaussScaleError = 0.0D;
  protected double multGaussScaleCoeffVar = 0.0D;
  protected double multGaussScaleTvalue = 0.0D;
  protected double multGaussScalePvalue = 0.0D;
  protected boolean plotWindowCloseChoice = false;
  protected double minimumY = 0.0D;
  protected double minimumYindex = 0.0D;
  protected double maximumY = 0.0D;
  protected double maximumYindex = 0.0D;
  protected double bottom = 0.0D;
  protected double top = 0.0D;
  protected double bottomS = 0.0D;
  protected double bottomSindex = 0.0D;
  protected double topS = 0.0D;
  protected double topSindex = 0.0D;
  protected int midPointLowerIndex = 0;
  protected int midPointUpperIndex = 0;
  protected double midPointXvalue = 0.0D;
  protected double midPointYvalue = 0.0D;
  protected int directionFlag = 0;
  protected double dDurbinWatson = 0;
  protected boolean dDurbinWatsonDone = false;
  protected double[][] firstDerivs = (double[][])null;
  protected boolean analyticalDerivative = false;
  protected double obsnVariance = 0.0D;
  protected static double histTol = 1.0001D;
  
  public Regression(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
  {
    this.simplexFlag = 1;
    this.nData0 = paramArrayOfDouble2.length;
    int i = paramArrayOfDouble1.length;
    double[][] arrayOfDouble = new double[1][i];
    double[] arrayOfDouble1 = new double[i];
    for (int j = 0; j < i; j++) {
      arrayOfDouble[0][j] = paramArrayOfDouble1[j];
    }
    this.weightOpt = false;
    this.weightFlag = 0;
    for (int j = 0; j < i; j++) {
      arrayOfDouble1[j] = 1.0D;
    }
    setDefaultValues(arrayOfDouble, Conv.copy(paramArrayOfDouble2), arrayOfDouble1);
  }
  
  public Regression(double[][] paramArrayOfDouble, double[] paramArrayOfDouble1)
  {
    this.nData0 = paramArrayOfDouble1.length;
    int i = paramArrayOfDouble1.length;
    double[] arrayOfDouble = new double[i];
    
    this.weightOpt = false;
    this.weightFlag = 0;
    for (int j = 0; j < i; j++) {
      arrayOfDouble[j] = 1.0D;
    }
    setDefaultValues(Conv.copy(paramArrayOfDouble), Conv.copy(paramArrayOfDouble1), arrayOfDouble);
  }
  
  protected void setDefaultValues(double[][] paramArrayOfDouble, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
  {
    setDefaultValues(paramArrayOfDouble, paramArrayOfDouble1, (double[][])null, paramArrayOfDouble2);
  }
  
  protected void setDefaultValues(double[][] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[][] paramArrayOfDouble3, double[] paramArrayOfDouble4)
  {
    int i = 1;
    if (paramArrayOfDouble3 == null) {
      i = 0;
    }
    this.nData = paramArrayOfDouble2.length;
    this.nXarrays = paramArrayOfDouble1.length;
    this.nParam = this.nXarrays;
    this.yData = new double[this.nData];
    this.yCalc = new double[this.nData];
    this.yErrors = new double[this.nData];
    this.weight = new double[this.nData];
    this.residual = new double[this.nData];
    this.residualW = new double[this.nData];
    this.xData = new double[this.nXarrays][this.nData];
    if (i != 0) {
      this.xErrors = new double[this.nXarrays][this.nData];
    }
    int j = paramArrayOfDouble4.length;
    if (j != this.nData) {
      throw new IllegalArgumentException("The y error and the y data lengths do not agree");
    }
    for (int k = 0; k < this.nData; k++)
    {
      this.yData[k] = paramArrayOfDouble2[k];
      this.yErrors[k] = paramArrayOfDouble4[k];
      this.weight[k] = this.yErrors[k];
    }
    for (int k = 0; k < this.nXarrays; k++)
    {
      j = paramArrayOfDouble1[k].length;
      if (j != this.nData) {
        throw new IllegalArgumentException("An x [" + k + "] length " + j + " and the y data length, " + this.nData + ", do not agree");
      }
      if (i != 0)
      {
        int m = paramArrayOfDouble3[k].length;
        if (m != this.nData) {
          throw new IllegalArgumentException("An x error [" + k + "] length " + j + " and the y data length, " + this.nData + ", do not agree");
        }
      }
      for (int m = 0; m < this.nData; m++) {
        this.xData[k][m] = paramArrayOfDouble1[k][m];
      }
      if (i != 0) {
        for (int m = 0; m < this.nData; m++) {
          this.xErrors[k][m] = paramArrayOfDouble3[k][m];
        }
      }
    }
    this.minimumY = this.yData[0];
    this.minimumYindex = 0.0D;
    this.maximumY = this.yData[0];
    this.maximumYindex = 0.0D;
    for (int k = 0; k < this.nData; k++)
    {
      if (this.yData[k] < this.minimumY)
      {
        this.minimumY = this.yData[k];
        this.minimumYindex = k;
      }
      if (this.yData[k] > this.maximumY)
      {
        this.maximumY = this.yData[k];
        this.maximumYindex = k;
      }
    }
    effectiveNumber();
  }
  
  protected void effectiveNumber()
  {
    double d1 = 0.0D;
    double d2 = 0.0D;
    double d3 = 0.0D;
    for (int i = 0; i < this.nData; i++)
    {
      d3 = 1.0D / (this.weight[i] * this.weight[i]);
      d1 += d3;
      d2 += d3 * d3;
    }
    this.nEffective = (d1 * d1 / d2);
  }
  
  public void polynomial(int paramInt)
  {
    if (this.xErrorsEntered) {
      polynomialDual(paramInt);
    } else {
      polynomialMono(paramInt);
    }
  }
  
  protected void polynomialMono(int paramInt)
  {
    if (this.multipleY) {
      throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
    }
    if (this.nXarrays > 1) {
      throw new IllegalArgumentException("This class will only perform a polynomial regression on a single x array");
    }
    if (paramInt < 1) {
      throw new IllegalArgumentException("Polynomial degree must be greater than zero");
    }
    this.lastMethod = 1;
    this.linNonLin = true;
    this.dualErrorsRequired = false;
    this.nParam = (paramInt + 1);
    this.degreesOfFreedom = (this.nData - this.nParam);
    //System.out.println(paramInt + " " + nParam + " " + nData);
    if ((this.degreesOfFreedom < 1) && (!this.ignoreDofFcheck)) {
      throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
    }
    double[][] arrayOfDouble = new double[this.nParam][this.nData];
    for (int i = 0; i < this.nData; i++) {
      arrayOfDouble[0][i] = 1.0D;
    }
    for (int i = 0; i < this.nData; i++) {
      arrayOfDouble[1][i] = this.xData[0][i];
    }
    for (int i = 2; i < this.nParam; i++) {
      for (int j = 0; j < this.nData; j++) {
        arrayOfDouble[i][j] = Math.pow(this.xData[0][j], i);
      }
    }
    this.best = new double[this.nParam];
    this.bestSd = new double[this.nParam];
    this.tValues = new double[this.nParam];
    this.pValues = new double[this.nParam];
    generalLinear(arrayOfDouble);
    generalLinearStats(arrayOfDouble);
  }
  
  protected void polynomialDual(int paramInt)
  {
    if (this.multipleY) {
      throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
    }
    if (this.nXarrays > 1) {
      throw new IllegalArgumentException("This class will only perform a polynomial regression on a single x array");
    }
    if (paramInt < 1) {
      throw new IllegalArgumentException("Polynomial degree must be greater than zero");
    }
    this.lastMethod = 1;
    this.linNonLin = true;
    this.dualErrorsRequired = true;
    this.nonLinStatsNeeded = false;
    this.nParam = (paramInt + 1);
    this.degreesOfFreedom = (this.nData - this.nParam);
    if ((this.degreesOfFreedom < 1) && (!this.ignoreDofFcheck)) {
      throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
    }
    Regression localRegression = new Regression(this.xData, this.yData);
    localRegression.polynomial(paramInt);
    double[] arrayOfDouble1 = localRegression.getBestEstimates();
    
    PolyXYDEfunction localPolyXYDEfunction = new PolyXYDEfunction();
    localPolyXYDEfunction.setDeg(paramInt);
    localPolyXYDEfunction.setYerrors(this.yErrors);
    localPolyXYDEfunction.setXerrors(this.xErrors);
    
    this.linNonLin = false;
    double[] arrayOfDouble2 = new double[this.nParam];
    for (int i = 0; i < this.nParam; i++)
    {
      arrayOfDouble1[i] *= 0.1D;
      if (arrayOfDouble2[i] == 0.0D) {
        arrayOfDouble2[i] = (Stat.mean(arrayOfDouble1) * 0.1D);
      }
    }
    nelderMead(localPolyXYDEfunction, null, arrayOfDouble1, arrayOfDouble2, this.fTol, this.nMax);
    this.linNonLin = true;
    
    double[][] arrayOfDouble = new double[this.nParam][this.nData];
    for (int j = 0; j < this.nData; j++) {
      arrayOfDouble[0][j] = 1.0D;
    }
    for (int j = 0; j < this.nData; j++) {
      arrayOfDouble[1][j] = this.xData[0][j];
    }
    for (int j = 2; j < this.nParam; j++) {
      for (int k = 0; k < this.nData; k++) {
        arrayOfDouble[j][k] = Math.pow(this.xData[0][k], j);
      }
    }
    this.bestSd = new double[this.nParam];
    this.tValues = new double[this.nParam];
    this.pValues = new double[this.nParam];
    generalLinearStats(arrayOfDouble);
    this.dualErrorsRequired = false;
    this.nonLinStatsNeeded = true;
  }
  
  protected void generalLinear(double[][] paramArrayOfDouble)
  {
    if ((this.nData <= this.nParam) && (!this.ignoreDofFcheck)) {
      throw new IllegalArgumentException("Number of unknown parameters is greater than or equal to the number of data points");
    }
    double d1 = 0.0D;double d2 = 0.0D;double d3 = 0.0D;
    double[][] arrayOfDouble1 = new double[this.nParam][this.nParam];
    double[][] arrayOfDouble2 = new double[this.nParam][this.nParam];
    double[] arrayOfDouble3 = new double[this.nParam];
    double[] arrayOfDouble4 = new double[this.nParam];
    if (this.ignoreDofFcheck)
    {
      this.bestSd = new double[this.nParam];
      this.pseudoSd = new double[this.nParam];
      this.tValues = new double[this.nParam];
      this.pValues = new double[this.nParam];
      
      this.covar = new double[this.nParam][this.nParam];
      this.corrCoeff = new double[this.nParam][this.nParam];
      for (int i = 0; i < this.nParam; i++)
      {
        this.bestSd[i] = 0;
        this.pseudoSd[i] = 0;
        for (int j = 0; j < this.nParam; j++)
        {
          this.covar[i][j] = 0;
          this.corrCoeff[i][j] = 0;
        }
      }
    }
    for (int i = 0; i < this.nParam; i++)
    {
      d2 = 0.0D;
      for (int j = 0; j < this.nData; j++) {
        d2 += this.yData[j] * paramArrayOfDouble[i][j] / Fmath.square(this.weight[j]);
      }
      arrayOfDouble3[i] = d2;
    }
    for (int i = 0; i < this.nParam; i++) {
      for (int j = 0; j < this.nParam; j++)
      {
        d2 = 0.0D;
        for (int k = 0; k < this.nData; k++) {
          d2 += paramArrayOfDouble[i][k] * paramArrayOfDouble[j][k] / Fmath.square(this.weight[k]);
        }
        arrayOfDouble1[j][i] = d2;
      }
    }
    Matrix localMatrix = new Matrix(arrayOfDouble1);
    if (this.suppressErrorMessages) {
      localMatrix.suppressErrorMessage();
    }
    arrayOfDouble4 = localMatrix.solveLinearSet(arrayOfDouble3);
    for (int j = 0; j < this.nParam; j++) {
      this.best[j] = arrayOfDouble4[j];
    }
  }
  
  protected void generalLinearStats(double[][] paramArrayOfDouble)
  {
    double d1 = 0.0D;double d2 = 0.0D;double d3 = 0.0D;
    double[][] arrayOfDouble1 = new double[this.nParam][this.nParam];
    double[][] arrayOfDouble2 = new double[this.nParam][this.nParam];
    double[][] arrayOfDouble3 = new double[this.nParam][this.nParam];
    double[][] arrayOfDouble4 = new double[this.nParam][this.nParam];
    this.covar = new double[this.nParam][this.nParam];
    this.corrCoeff = new double[this.nParam][this.nParam];
    double[] arrayOfDouble5 = new double[this.nParam];
    double[] arrayOfDouble6 = new double[this.nParam];
    for (int i = 0; i < this.nParam; i++) {
      arrayOfDouble6[i] = this.best[i];
    }
    this.chiSquare = 0.0D;
    this.sumOfSquaresError = 0.0D;
    for (int i = 0; i < this.nData; i++)
    {
      d3 = 0.0D;
      for (int j = 0; j < this.nParam; j++) {
        d3 += arrayOfDouble6[j] * paramArrayOfDouble[j][i];
      }
      this.yCalc[i] = d3;
      d3 -= this.yData[i];
      this.residual[i] = d3;
      this.sumOfSquaresError += Fmath.square(d3);
    }
    double d4 = this.sumOfSquaresError / this.degreesOfFreedom;
    double d5 = Math.sqrt(d4);
    if ((this.weightOpt) && (!this.trueErrors))
    {
      double d6 = 0.0D;
      for (int i1 = 0; i1 < this.nData; i1++) {
        d6 = this.weight[i1] * this.weight[i1];
      }
      d6 /= this.degreesOfFreedom;
      double d8 = Math.sqrt(d4 / d6);
      for (int i3 = 0; i3 < this.nData; i3++) {
        this.weight[i3] *= d8;
      }
    }
    for (int k = 0; k < this.nData; k++)
    {
      this.residualW[k] = (this.residual[k] / this.weight[k]);
      this.chiSquare += Fmath.square(this.residual[k] / this.weight[k]);
    }
    this.reducedChiSquare = (this.chiSquare / this.degreesOfFreedom);
    int n;
    if (this.sumOfSquaresError == 0.0D)
    {
      for (int k = 0; k < this.nParam; k++)
      {
        arrayOfDouble5[k] = 0.0D;
        for (n = 0; n < this.nParam; n++)
        {
          this.covar[k][n] = 0.0D;
          if (k == n) {
            this.corrCoeff[k][n] = 1.0D;
          } else {
            this.corrCoeff[k][n] = 0.0D;
          }
        }
      }
    }
    else
    {
      int i2;
      for (int k = 0; k < this.nParam; k++) {
        for (n = 0; n < this.nParam; n++)
        {
          d2 = 0.0D;
          for (i2 = 0; i2 < this.nData; i2++)
          {
            if (this.weightOpt) {
              d1 = this.weight[i2];
            } else {
              d1 = d5;
            }
            d2 += paramArrayOfDouble[k][i2] * paramArrayOfDouble[n][i2] / Fmath.square(d1);
          }
          arrayOfDouble2[n][k] = d2;
        }
      }
      Matrix localMatrix = new Matrix(arrayOfDouble2);
      if (this.suppressErrorMessages) {
        localMatrix.suppressErrorMessage();
      }
      localMatrix = localMatrix.inverse();
      arrayOfDouble3 = localMatrix.getArrayCopy();
      for (n = 0; n < this.nParam; n++) {
        arrayOfDouble5[n] = Math.sqrt(arrayOfDouble3[n][n]);
      }
      for (n = 0; n < this.nParam; n++) {
        for (i2 = 0; i2 < this.nParam; i2++) {
          this.covar[n][i2] = arrayOfDouble3[n][i2];
        }
      }
      for (n = 0; n < this.nParam; n++) {
        for (i2 = 0; i2 < this.nParam; i2++) {
          if (n == i2) {
            this.corrCoeff[n][i2] = 1.0D;
          } else {
            this.corrCoeff[n][i2] = (this.covar[n][i2] / (arrayOfDouble5[n] * arrayOfDouble5[i2]));
          }
        }
      }
    }
    for (int m = 0; m < this.nParam; m++)
    {
      this.bestSd[m] = arrayOfDouble5[m];
      this.tValues[m] = (this.best[m] / this.bestSd[m]);
      double d7 = Math.abs(this.tValues[m]);
      if (d7 != d7) {
        this.pValues[m] = 0;
      } else {
        this.pValues[m] = (1.0D - Stat.studentTcdf(-d7, d7, this.degreesOfFreedom));
      }
    }
    if ((this.nXarrays == 1) && (this.nYarrays == 1)) {
      this.xyR = Stat.corrCoeff(this.xData[0], this.yData, this.weight);
    }
    this.yyR = Stat.corrCoeff(this.yCalc, this.yData, this.weight);
    
    this.yMean = Stat.mean(this.yData);
    this.yWeightedMean = Stat.mean(this.yData, this.weight);
    
    this.sumOfSquaresTotal = 0.0D;
    for (int m = 0; m < this.nData; m++) {
      this.sumOfSquaresTotal += Fmath.square((this.yData[m] - this.yWeightedMean) / this.weight[m]);
    }
    this.sumOfSquaresRegrn = (this.sumOfSquaresTotal - this.chiSquare);
    if (this.sumOfSquaresRegrn < 0.0D) {
      this.sumOfSquaresRegrn = 0.0D;
    }
    this.multR = (this.sumOfSquaresRegrn / this.sumOfSquaresTotal);
    
    this.adjustedR = 0;
    this.multipleF = 0;
    if (this.nData - this.nParam - 1 > 0) {
      this.adjustedR = (1.0D - (1.0D - this.multR) * (this.nData - 1) / (this.nData - this.nParam - 1));
    }
    this.multipleF = (this.multR * (this.nData - this.nParam - 1.0D) / ((1.0D - this.multR) * this.nParam));
    if (this.multipleF >= 0.0D) {
      this.multipleFprob = Stat.fTestProb(this.multipleF, this.nXarrays, this.nData - this.nParam - 1);
    }
    calcDurbinWatson();
    
    varianceOfObservations();
  }
  
  protected void calcDurbinWatson()
  {
    double d1 = 0.0D;
    double d2 = 0.0D;
    for (int i = 1; i < this.nData; i++)
    {
      d2 = this.residual[i] - this.residual[(i - 1)];
      d1 += d2 * d2;
    }
    double d3 = 0.0D;
    for (int j = 0; j < this.nData; j++) {
      d3 += this.residual[j] * this.residual[j];
    }
    this.dDurbinWatson = (d1 / d3);
    this.dDurbinWatsonDone = true;
  }
  
  protected void varianceOfObservations()
  {
    double d1 = 0.0D;
    double d2 = 0.0D;
    double d3 = 0.0D;
    double d4 = 0.0D;
    for (int i = 0; i < this.nData; i++)
    {
      d1 = 1.0D / this.weight[i];
      d1 *= d1;
      d2 += this.residual[i] * this.residual[i] * d1;
      d3 += d1;
      d4 += d1 * d1;
    }
    this.obsnVariance = (d2 * d3 / (d3 * d3 - this.nParam * d4));
  }
  
  public double[] getBestEstimatesErrors()
  {
    return Conv.copy(this.bestSd);
  }
  
  public double[] getBestEstimates()
  {
    return Conv.copy(this.best);
  }
  
  protected void nelderMead(Object paramObject1, Object paramObject2, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double paramDouble, int paramInt)
  {
    if ((this.xErrorsEntered) && (!this.dualErrorsRequired)) {
      throw new IllegalArgumentException("The data fitting method called does not support independent variable errors - use a constructor that does not include x errors in its argument list");
    }
    int i = paramArrayOfDouble1.length;
    if (this.maxConstraintIndex >= i) {
      throw new IllegalArgumentException("You have entered more constrained parameters (" + this.maxConstraintIndex + ") than minimisation parameters (" + i + ")");
    }
    this.nlrStatus = true;
    this.nParam = i;
    int j = i + 1;
    this.lastSSnoConstraint = 0.0D;
    if (this.scaleOpt < 2) {
      this.scale = new double[i];
    }
    if ((this.scaleOpt == 2) && (this.scale.length != paramArrayOfDouble1.length)) {
      throw new IllegalArgumentException("scale array and initial estimate array are of different lengths");
    }
    if (paramArrayOfDouble2.length != paramArrayOfDouble1.length) {
      throw new IllegalArgumentException("step array length " + paramArrayOfDouble2.length + " and initial estimate array length " + paramArrayOfDouble1.length + " are of different");
    }
    for (int k = 0; k < i; k++) {
      if (paramArrayOfDouble2[k] == 0.0D) {
        throw new IllegalArgumentException("step " + k + " size is zero");
      }
    }
    if (this.minIter > this.nMax) {
      this.nMax = this.minIter;
    }
    if (this.ignoreDofFcheck)
    {
      this.bestSd = new double[this.nParam];
      this.pseudoSd = new double[this.nParam];
      this.tValues = new double[this.nParam];
      this.pValues = new double[this.nParam];
      
      this.covar = new double[this.nParam][this.nParam];
      this.corrCoeff = new double[this.nParam][this.nParam];
      for (int k = 0; k < this.nParam; k++)
      {
        this.bestSd[k] = 0;
        this.pseudoSd[k] = 0;
        for (int m = 0; m < this.nParam; m++)
        {
          this.covar[k][m] = 0;
          this.corrCoeff[k][m] = 0;
        }
      }
    }
    this.startH = new double[i];
    this.stepH = new double[i];
    this.startSH = new double[i];
    this.stepSH = new double[i];
    double[] arrayOfDouble1 = new double[i];
    this.best = new double[i];
    this.bestSd = new double[i];
    this.tValues = new double[i];
    this.pValues = new double[i];
    
    
    double[][] arrayOfDouble = new double[j][j];
    double[] arrayOfDouble2 = new double[j];
    double[] arrayOfDouble3 = new double[j];
    double[] arrayOfDouble4 = new double[j];
    double[] arrayOfDouble5 = new double[j];
    
    double d1 = 0.0D;
    for (int n = 0; n < this.nData; n++) {
      d1 += Math.abs(this.yData[n]);
    }
    d1 /= this.nData;
    Integer localInteger;
    Object localObject;
    if (this.penalty)
    {
      localInteger = (Integer)this.penalties.get(1);
      this.nConstraints = localInteger.intValue();
      this.penaltyParam = new int[this.nConstraints];
      this.penaltyCheck = new int[this.nConstraints];
      this.constraints = new double[this.nConstraints];
      localObject = null;
      int i3 = 2;
      for (int i5 = 0; i5 < this.nConstraints; i5++)
      {
        localInteger = (Integer)this.penalties.get(i3);
        this.penaltyParam[i5] = localInteger.intValue();
        i3++;
        localInteger = (Integer)this.penalties.get(i3);
        this.penaltyCheck[i5] = localInteger.intValue();
        i3++;
        localObject = (Double)this.penalties.get(i3);
        this.constraints[i5] = ((Double)localObject).doubleValue();
        i3++;
      }
    }
    int i7;
    if (this.sumPenalty)
    {
      localInteger = (Integer)this.sumPenalties.get(1);
      this.nSumConstraints = localInteger.intValue();
      this.sumPenaltyParam = new int[this.nSumConstraints][];
      this.sumPlusOrMinus = new double[this.nSumConstraints][];
      this.sumPenaltyCheck = new int[this.nSumConstraints];
      this.sumPenaltyNumber = new int[this.nSumConstraints];
      this.sumConstraints = new double[this.nSumConstraints];
      localObject = null;
      double[] arrayOfDouble6 = null;
      Double localDouble = null;
      i7 = 2;
      for (int i8 = 0; i8 < this.nSumConstraints; i8++)
      {
        localInteger = (Integer)this.sumPenalties.get(i7);
        this.sumPenaltyNumber[i8] = localInteger.intValue();
        i7++;
        localObject = (int[])this.sumPenalties.get(i7);
        this.sumPenaltyParam[i8] = (int[]) localObject;
        i7++;
        arrayOfDouble6 = (double[])this.sumPenalties.get(i7);
        this.sumPlusOrMinus[i8] = arrayOfDouble6;
        i7++;
        localInteger = (Integer)this.sumPenalties.get(i7);
        this.sumPenaltyCheck[i8] = localInteger.intValue();
        i7++;
        localDouble = (Double)this.sumPenalties.get(i7);
        this.sumConstraints[i8] = localDouble.doubleValue();
        i7++;
      }
    }
    for (int i1 = 0; i1 < i; i1++)
    {
      paramArrayOfDouble2[i1] = Math.abs(paramArrayOfDouble2[i1]);
      this.startH[i1] = paramArrayOfDouble1[i1];
      this.stepH[i1] = paramArrayOfDouble2[i1];
    }
    if (this.scaleOpt > 0)
    {
      int i1 = 0;
      for (int i2 = 0; i2 < i; i2++) {
        if (paramArrayOfDouble1[i2] == 0.0D) {
          i1 = 1;
        }
      }
      if (i1 != 0)
      {
        System.out.println("Neler and Mead Simplex: a start value of zero precludes scaling");
        System.out.println("Regression performed without scaling");
        this.scaleOpt = 0;
      }
    }
    switch (this.scaleOpt)
    {
    case 0: 
      for (int i1 = 0; i1 < i; i1++) {
        this.scale[i1] = 1.0D;
      }
      break;
    case 1: 
      for (int i1 = 0; i1 < i; i1++)
      {
        this.scale[i1] = (1.0D / paramArrayOfDouble1[i1]);
        paramArrayOfDouble2[i1] /= paramArrayOfDouble1[i1];
        paramArrayOfDouble1[i1] = 1.0D;
      }
      break;
    case 2: 
      for (int i1 = 0; i1 < i; i1++)
      {
        paramArrayOfDouble2[i1] *= this.scale[i1];
        paramArrayOfDouble1[i1] *= this.scale[i1];
      }
      break;
    default: 
      throw new IllegalArgumentException("Scaling factor option " + this.scaleOpt + " not recognised");
    }
    this.fTol = paramDouble;
    this.nMax = paramInt;
    this.nIter = 0;
    for (int i1 = 0; i1 < i; i1++)
    {
      this.startSH[i1] = paramArrayOfDouble1[i1];
      this.stepSH[i1] = paramArrayOfDouble2[i1];
      this.scale[i1] = this.scale[i1];
    }
    double d2 = 0.0D;
    for (int i4 = 0; i4 < i; i4++)
    {
      d2 = paramArrayOfDouble1[i4];
      arrayOfDouble4[i4] = d2;
      arrayOfDouble5[i4] = d2;
      arrayOfDouble1[i4] = d2;
    }
    int i4 = this.konvge;
    for (int i6 = 0; i6 < i; i6++) {
      arrayOfDouble[i6][(j - 1)] = paramArrayOfDouble1[i6];
    }
    arrayOfDouble2[(j - 1)] = sumSquares(paramObject1, paramArrayOfDouble1);
    for (int i6 = 0; i6 < i; i6++)
    {
      paramArrayOfDouble1[i6] += paramArrayOfDouble2[i6];
      for (i7 = 0; i7 < i; i7++) {
        arrayOfDouble[i7][i6] = paramArrayOfDouble1[i7];
      }
      arrayOfDouble2[i6] = sumSquares(paramObject1, paramArrayOfDouble1);
      paramArrayOfDouble1[i6] -= paramArrayOfDouble2[i6];
    }
    double d3 = 0.0D;
    double d4 = 0.0D;
    double d5 = 0.0D;
    double d6 = 0.0D;
    
    int i9 = 0;
    int i10 = 0;
    int i11 = 0;
    int i12 = 1;
    
    double d7 = 0.0D;
    double d8 = 0.0D;
    double d9 = 0.0D;
    double d10 = 0.0D;
    while (i12 != 0)
    {
      d6 = arrayOfDouble2[0];
      d3 = d6;
      i9 = 0;
      i10 = 0;
      for (int i13 = 1; i13 < j; i13++)
      {
        if (arrayOfDouble2[i13] < d6)
        {
          d6 = arrayOfDouble2[i13];
          i9 = i13;
        }
        if (arrayOfDouble2[i13] > d3)
        {
          d3 = arrayOfDouble2[i13];
          i10 = i13;
        }
      }
      int i15;
      for (int i13 = 0; i13 < i; i13++)
      {
        d9 = 0.0D;
        for (i15 = 0; i15 < j; i15++) {
          d9 += arrayOfDouble[i13][i15];
        }
        d9 -= arrayOfDouble[i13][i10];
        arrayOfDouble3[i13] = (d9 / i);
      }
      for (int i13 = 0; i13 < i; i13++) {
        arrayOfDouble4[i13] = ((1.0D + this.rCoeff) * arrayOfDouble3[i13] - this.rCoeff * arrayOfDouble[i13][i10]);
      }
      d4 = sumSquares(paramObject1, arrayOfDouble4);
      
      this.nIter += 1;
      if (d4 < d6)
      {
        for (int i13 = 0; i13 < i; i13++) {
          arrayOfDouble5[i13] = (arrayOfDouble4[i13] * (1.0D + this.eCoeff) - this.eCoeff * arrayOfDouble3[i13]);
        }
        d5 = sumSquares(paramObject1, arrayOfDouble5);
        this.nIter += 1;
        if (d5 < d6)
        {
          for (int i13 = 0; i13 < i; i13++) {
            arrayOfDouble[i13][i10] = arrayOfDouble5[i13];
          }
          arrayOfDouble2[i10] = d5;
        }
        else
        {
          for (int i13 = 0; i13 < i; i13++) {
            arrayOfDouble[i13][i10] = arrayOfDouble4[i13];
          }
          arrayOfDouble2[i10] = d4;
        }
      }
      else
      {
        i11 = 0;
        for (int i13 = 0; i13 < j; i13++) {
          if ((i13 != i10) && (d4 > arrayOfDouble2[i13])) {
            i11++;
          }
        }
        if (i11 == i)
        {
          if (d4 <= arrayOfDouble2[i10])
          {
            for (int i13 = 0; i13 < i; i13++) {
              arrayOfDouble[i13][i10] = arrayOfDouble4[i13];
            }
            arrayOfDouble2[i10] = d4;
          }
          for (int i13 = 0; i13 < i; i13++) {
            arrayOfDouble5[i13] = (this.cCoeff * arrayOfDouble[i13][i10] + (1.0D - this.cCoeff) * arrayOfDouble3[i13]);
          }
          d5 = sumSquares(paramObject1, arrayOfDouble5);
          this.nIter += 1;
          if (d5 > arrayOfDouble2[i10])
          {
            for (int i13 = 0; i13 < j; i13++)
            {
              for (i15 = 0; i15 < i; i15++)
              {
                arrayOfDouble[i15][i13] = (0.5D * (arrayOfDouble[i15][i13] + arrayOfDouble[i15][i9]));
                arrayOfDouble1[i15] = arrayOfDouble[i15][i13];
              }
              arrayOfDouble2[i13] = sumSquares(paramObject1, arrayOfDouble1);
            }
            this.nIter += j;
          }
          else
          {
            for (int i13 = 0; i13 < i; i13++) {
              arrayOfDouble[i13][i10] = arrayOfDouble5[i13];
            }
            arrayOfDouble2[i10] = d5;
          }
        }
        else
        {
          for (int i13 = 0; i13 < i; i13++) {
            arrayOfDouble[i13][i10] = arrayOfDouble4[i13];
          }
          arrayOfDouble2[i10] = d4;
        }
      }
      d8 = 0.0D;
      d3 = arrayOfDouble2[0];
      i9 = 0;
      for (int i13 = 0; i13 < j; i13++)
      {
        d8 += arrayOfDouble2[i13];
        if (d3 > arrayOfDouble2[i13])
        {
          d3 = arrayOfDouble2[i13];
          i9 = i13;
        }
      }
      d8 /= j;
      d10 = 0.0D;
      for (int i13 = 0; i13 < j; i13++)
      {
        d9 = arrayOfDouble2[i13] - d8;
        d10 += d9 * d9;
      }
      d7 = Math.sqrt(d10 / i);
      switch (this.minTest)
      {
      case 0: 
        if ((d7 < paramDouble) && (this.nIter > this.minIter)) {
          i12 = 0;
        }
        break;
      case 1: 
        if ((Math.sqrt(d3 / this.degreesOfFreedom) < d1 * paramDouble) && (this.nIter > this.minIter)) {
          i12 = 0;
        }
        break;
      default: 
        throw new IllegalArgumentException("Simplex standard deviation test option " + this.minTest + " not recognised");
      }
      this.sumOfSquaresError = d3;
      if (i12 == 0)
      {
        for (int i13 = 0; i13 < i; i13++) {
          arrayOfDouble1[i13] = arrayOfDouble[i13][i9];
        }
        arrayOfDouble2[(j - 1)] = d3;
        
        this.simplexSd = d7;
        
        i4--;
        if (i4 > 0)
        {
          i12 = 1;
          for (int i13 = 0; i13 < i; i13++)
          {
            arrayOfDouble1[i13] += paramArrayOfDouble2[i13];
            for (i15 = 0; i15 < i; i15++) {
              arrayOfDouble[i15][i13] = arrayOfDouble1[i15];
            }
            arrayOfDouble2[i13] = sumSquares(paramObject1, arrayOfDouble1);
            arrayOfDouble1[i13] -= paramArrayOfDouble2[i13];
          }
        }
      }
      if ((i12 != 0) && (this.nIter > this.nMax))
      {
        if (!this.suppressErrorMessages)
        {
          System.out.println("Maximum iteration number reached, in Regression.simplex(...)");
          System.out.println("without the convergence criterion being satisfied");
          System.out.println("Current parameter estimates and sum of squares values returned");
        }
        this.nlrStatus = false;
        for (int i13 = 0; i13 < i; i13++) {
          arrayOfDouble1[i13] = arrayOfDouble[i13][i9];
        }
        arrayOfDouble2[(j - 1)] = d3;
        i12 = 0;
      }
    }
    for (int i13 = 0; i13 < i; i13++)
    {
      arrayOfDouble1[i13] = arrayOfDouble[i13][i9];
      this.best[i13] = (arrayOfDouble1[i13] / this.scale[i13]);
      this.scale[i13] = 1.0D;
    }
    this.fMin = d3;
    this.kRestart = (this.konvge - i4);
    if (this.xErrorsEntered)
    {
      double[] arrayOfDouble7 = new double[this.nXarrays];
      double[] arrayOfDouble8 = new double[2];
      for (int i16 = 0; i16 < this.nData; i16++)
      {
        for (int i17 = 0; i17 < this.nXarrays; i17++) {
          arrayOfDouble7[i17] = this.xData[i17][i16];
        }
        arrayOfDouble8 = ((RegressionFunction3)paramObject1).function(this.best, arrayOfDouble7, i16);
        this.yCalc[i16] = arrayOfDouble8[0];
        this.weight[i16] = Math.sqrt(arrayOfDouble8[1]);
      }
      effectiveNumber();
    }
    if (this.nonLinStatsNeeded) {
      if (this.statFlag)
      {
        if (!this.ignoreDofFcheck) {
          if (this.analyticalDerivative) {
            pseudoLinearStats(paramObject1, paramObject2);
          } else {
            pseudoLinearStats(paramObject1);
          }
        }
      }
      else {
        for (int i14 = 0; i14 < i; i14++) {
          this.bestSd[i14] = 0;
        }
      }
    }
  }
  
  protected int pseudoLinearStats(Object paramObject1, Object paramObject2)
  {
    double d1 = 0.0D;double d2 = 0.0D;
    int i = 0;
    
    int j = this.nParam;
    
    double[] arrayOfDouble1 = new double[j];
    double[] arrayOfDouble2 = new double[j];
    double[] arrayOfDouble3 = new double[j];
    double[] arrayOfDouble4 = new double[this.nXarrays];
    double[][] arrayOfDouble = new double[j][j];
    this.pseudoSd = new double[j];
    
    Object localObject = null;
    
    this.grad = new double[j][2];
    this.covar = new double[j][j];
    this.corrCoeff = new double[j][j];
    
    arrayOfDouble2 = Conv.copy(this.best);
    
    double d3 = 0.0D;
    for (int k = 0; k < this.nData; k++)
    {
      for (int m = 0; m < this.nXarrays; m++) {
        arrayOfDouble4[m] = this.xData[m][k];
      }
      switch (this.simplexFlag)
      {
      case 1: 
        this.yCalc[k] = ((RegressionFunction)paramObject1).function(this.best, arrayOfDouble4);
        break;
      case 2: 
        this.yCalc[k] = ((RegressionFunction2)paramObject1).function(this.best, arrayOfDouble4, k);
        break;
      case 3: 
      case 4: 
        double[] arrayOfDouble5 = ((RegressionFunction3)paramObject1).function(this.best, arrayOfDouble4, k);
        this.yCalc[k] = arrayOfDouble5[0];
        this.weight[k] = Math.sqrt(arrayOfDouble5[1]);
      }
      this.residual[k] = (this.yCalc[k] - this.yData[k]);
      d3 += Fmath.square(this.residual[k]);
    }
    this.sumOfSquaresError = d3;
    double d4 = d3 / (this.nData - j);
    double d5 = Math.sqrt(d4);
    if ((this.weightOpt) && (!this.trueErrors))
    {
      double d6 = 0.0D;
      for (int n = 0; n < this.nData; n++) {
        d6 = this.weight[n] * this.weight[n];
      }
      d6 /= this.degreesOfFreedom;
      double d7 = Math.sqrt(d4 / d6);
      for (int i2 = 0; i2 < this.nData; i2++) {
        this.weight[i2] *= d7;
      }
    }
    double d6 = 0.0D;
    for (int i1 = 0; i1 < this.nData; i1++)
    {
      this.residualW[i1] = (this.residual[i1] / this.weight[i1]);
      d6 += Fmath.square(this.residualW[i1]);
    }
    this.chiSquare = d6;
    this.reducedChiSquare = (d6 / (this.nData - j));
    
    double d8 = 1.0D;
    if ((!this.weightOpt) && (!this.trueFreq)) {
      d8 = this.sumOfSquaresError / (this.nData - j);
    }
    double d9 = 1.0D;
    int i5;
    for (int i3 = 0; i3 < j; i3++)
    {
      for (i5 = 0; i5 < j; i5++) {
        arrayOfDouble1[i5] = arrayOfDouble2[i5];
      }
      d9 = arrayOfDouble2[i3];
      if (d9 == 0.0D)
      {
        d9 = this.stepH[i3];
        this.zeroCheck = true;
      }
      arrayOfDouble1[i3] = (d9 * (1.0D - this.delta));
      this.lastSSnoConstraint = this.sumOfSquaresError;
      d1 = sumSquares(paramObject1, arrayOfDouble1);
      arrayOfDouble1[i3] = (d9 * (1.0D + this.delta));
      this.lastSSnoConstraint = this.sumOfSquaresError;
      d2 = sumSquares(paramObject1, arrayOfDouble1);
      this.grad[i3][0] = ((this.fMin - d1) / Math.abs(this.delta * d9));
      this.grad[i3][1] = ((d2 - this.fMin) / Math.abs(this.delta * d9));
    }
    this.lastSSnoConstraint = this.sumOfSquaresError;
    for (int i3 = 0; i3 < j; i3++) {
      for (i5 = 0; i5 < j; i5++) {
        arrayOfDouble[i3][i5] = secondDerivative(paramObject1, paramObject2, arrayOfDouble2, i3, i5);
      }
    }
    for (int i3 = 0; i3 < j; i3++)
    {
      this.pseudoSd[i3] = (2.0D * this.delta * d8 * Math.abs(arrayOfDouble2[i3]) / (this.grad[i3][1] - this.grad[i3][0]));
      if (this.pseudoSd[i3] >= 0.0D) {
        this.pseudoSd[i3] = Math.sqrt(this.pseudoSd[i3]);
      } else {
        this.pseudoSd[i3] = 0;
      }
    }
    double d10;
    if (j == 1)
    {
      arrayOfDouble[0][0] = (1.0D / arrayOfDouble[0][0]);
      this.covar[0][0] = (arrayOfDouble[0][0] * d8);
      if (this.covar[0][0] >= 0.0D)
      {
        arrayOfDouble3[0] = Math.sqrt(this.covar[0][0]);
        this.corrCoeff[0][0] = 1.0D;
      }
      else
      {
        arrayOfDouble3[0] = 0;
        this.corrCoeff[0][0] = 0;
        this.posVarFlag = false;
      }
    }
    else
    {
      Matrix localMatrix = new Matrix(arrayOfDouble);
      if (this.suppressErrorMessages) {
        localMatrix.suppressErrorMessage();
      }
      d10 = localMatrix.determinant();
      if (d10 == 0.0D)
      {
        this.invertFlag = false;
      }
      else
      {
        localMatrix = localMatrix.inverse();
        this.invertFlag = localMatrix.getMatrixCheck();
      }
      if (!this.invertFlag) {
        i--;
      }
      arrayOfDouble = localMatrix.getArrayCopy();
      
      this.posVarFlag = true;
      int i6;
      int i7;
      if (this.invertFlag)
      {
        for (i6 = 0; i6 < j; i6++)
        {
          for (i7 = i6; i7 < j; i7++)
          {
            this.covar[i6][i7] = (2.0D * arrayOfDouble[i6][i7] * d8);
            this.covar[i7][i6] = this.covar[i6][i7];
          }
          if (this.covar[i6][i6] >= 0.0D)
          {
            arrayOfDouble3[i6] = Math.sqrt(this.covar[i6][i6]);
          }
          else
          {
            arrayOfDouble3[i6] = 0;
            this.posVarFlag = false;
          }
        }
        for (i6 = 0; i6 < j; i6++) {
          for (i7 = 0; i7 < j; i7++) {
            if ((arrayOfDouble3[i6] != 0) && (arrayOfDouble3[i7] != 0)) {
              this.corrCoeff[i6][i7] = (this.covar[i6][i7] / (arrayOfDouble3[i6] * arrayOfDouble3[i7]));
            } else {
              this.corrCoeff[i6][i7] = 0;
            }
          }
        }
      }
      else
      {
        for (i6 = 0; i6 < j; i6++)
        {
          for (i7 = 0; i7 < j; i7++)
          {
            this.covar[i6][i7] = 0;
            this.corrCoeff[i6][i7] = 0;
          }
          arrayOfDouble3[i6] = 0;
        }
      }
    }
    if (!this.posVarFlag) {
      i--;
    }
    for (int i4 = 0; i4 < this.nParam; i4++)
    {
      this.bestSd[i4] = arrayOfDouble3[i4];
      this.tValues[i4] = (this.best[i4] / this.bestSd[i4]);
      d10 = Math.abs(this.tValues[i4]);
      if (d10 != d10) {
        this.pValues[i4] = 0;
      } else {
        this.pValues[i4] = (1.0D - Stat.studentTcdf(-d10, d10, this.degreesOfFreedom));
      }
    }
    if ((this.nXarrays == 1) && (this.nYarrays == 1)) {
      this.xyR = Stat.corrCoeff(this.xData[0], this.yData, this.weight);
    }
    this.yyR = Stat.corrCoeff(this.yCalc, this.yData, this.weight);
    
    this.yMean = Stat.mean(this.yData);
    this.yWeightedMean = Stat.mean(this.yData, this.weight);
    
    this.sumOfSquaresTotal = 0.0D;
    for (int i4 = 0; i4 < this.nData; i4++) {
      this.sumOfSquaresTotal += Fmath.square((this.yData[i4] - this.yWeightedMean) / this.weight[i4]);
    }
    this.sumOfSquaresRegrn = (this.sumOfSquaresTotal - this.chiSquare);
    if (this.sumOfSquaresRegrn < 0.0D) {
      this.sumOfSquaresRegrn = 0.0D;
    }
    this.multR = (this.sumOfSquaresRegrn / this.sumOfSquaresTotal);
    
    this.adjustedR = 0;
    this.multipleF = 0;
    if (this.nData - this.nXarrays - 1 > 0) {
      this.adjustedR = (1.0D - (1.0D - this.multR) * (this.nData - 1) / (this.nData - this.nXarrays - 1));
    }
    this.multipleF = (this.multR * (this.nData - this.nXarrays - 1.0D) / ((1.0D - this.multR) * this.nXarrays));
    if (this.multipleF >= 0.0D) {
      this.multipleFprob = Stat.fTestProb(this.multipleF, this.nXarrays, this.nData - this.nXarrays - 1);
    }
    calcDurbinWatson();
    
    varianceOfObservations();
    
    return i;
  }
  
  protected int pseudoLinearStats(Object paramObject)
  {
    double d1 = 0.0D;double d2 = 0.0D;double d3 = 0.0D;double d4 = 0.0D;
    int i = 0;
    
    int j = this.nParam;
    
    double[] arrayOfDouble1 = new double[j];
    double[] arrayOfDouble2 = new double[j];
    double[] arrayOfDouble3 = new double[j];
    double[] arrayOfDouble4 = new double[this.nXarrays];
    double[][] arrayOfDouble = new double[j][j];
    this.pseudoSd = new double[j];
    
    Object localObject = null;
    
    this.grad = new double[j][2];
    this.covar = new double[j][j];
    this.corrCoeff = new double[j][j];
    
    arrayOfDouble2 = Conv.copy(this.best);
    
    double d5 = 1.0D;
    double d6 = 1.0D;
    this.firstDerivs = new double[this.nParam][this.nData];
    int m;
    int n;
    for (int k = 0; k < j; k++)
    {
      for (m = 0; m < j; m++) {
        arrayOfDouble1[m] = arrayOfDouble2[m];
      }
      d5 = arrayOfDouble2[k];
      if (d5 == 0.0D)
      {
        d5 = this.stepH[k];
        this.zeroCheck = true;
      }
      arrayOfDouble1[k] = (d5 * (1.0D - this.delta));
      this.lastSSnoConstraint = this.sumOfSquaresError;
      d1 = sumSquares(paramObject, arrayOfDouble1);
      for (m = 0; m < this.nData; m++)
      {
        for (n = 0; n < this.nXarrays; n++) {
          arrayOfDouble4[n] = this.xData[n][m];
        }
        switch (this.simplexFlag)
        {
        case 1: 
          this.firstDerivs[k][m] = (-((RegressionFunction)paramObject).function(arrayOfDouble1, arrayOfDouble4) / Math.abs(this.delta * 2.0D * d5));
          break;
        case 2: 
          this.firstDerivs[k][m] = (-((RegressionFunction2)paramObject).function(arrayOfDouble1, arrayOfDouble4, m) / Math.abs(this.delta * 2.0D * d5));
          break;
        case 3: 
        case 4: 
          this.firstDerivs[k][m] = (-((RegressionFunction3)paramObject).function(arrayOfDouble1, arrayOfDouble4, m)[0] / Math.abs(this.delta * 2.0D * d5));
        }
      }
      arrayOfDouble1[k] = (d5 * (1.0D + this.delta));
      this.lastSSnoConstraint = this.sumOfSquaresError;
      d2 = sumSquares(paramObject, arrayOfDouble1);
      for (m = 0; m < this.nData; m++)
      {
        for (n = 0; n < this.nXarrays; n++) {
          arrayOfDouble4[n] = this.xData[n][m];
        }
        switch (this.simplexFlag)
        {
        case 1: 
          this.firstDerivs[k][m] = (-((RegressionFunction)paramObject).function(arrayOfDouble1, arrayOfDouble4) / Math.abs(this.delta * 2.0D * d5));
          break;
        case 2: 
          this.firstDerivs[k][m] = (-((RegressionFunction2)paramObject).function(arrayOfDouble1, arrayOfDouble4, m) / Math.abs(this.delta * 2.0D * d5));
          break;
        case 3: 
        case 4: 
          this.firstDerivs[k][m] = (-((RegressionFunction3)paramObject).function(arrayOfDouble1, arrayOfDouble4, m)[0] / Math.abs(this.delta * 2.0D * d5));
        }
      }
      this.grad[k][0] = ((this.fMin - d1) / Math.abs(this.delta * d5));
      this.grad[k][1] = ((d2 - this.fMin) / Math.abs(this.delta * d5));
    }
    this.lastSSnoConstraint = this.sumOfSquaresError;
    for (int k = 0; k < j; k++) {
      for (m = 0; m < j; m++)
      {
        for (n = 0; n < j; n++) {
          arrayOfDouble1[n] = arrayOfDouble2[n];
        }
        d5 = arrayOfDouble1[k];
        if (d5 == 0.0D)
        {
          d5 = this.stepH[k];
          this.zeroCheck = true;
        }
        arrayOfDouble1[k] = (d5 * (1.0D + this.delta / 2.0D));
        d5 = arrayOfDouble1[m];
        if (d5 == 0.0D)
        {
          d5 = this.stepH[m];
          this.zeroCheck = true;
        }
        arrayOfDouble1[m] = (d5 * (1.0D + this.delta / 2.0D));
        this.lastSSnoConstraint = this.sumOfSquaresError;
        d1 = sumSquares(paramObject, arrayOfDouble1);
        arrayOfDouble1[k] = arrayOfDouble2[k];
        arrayOfDouble1[m] = arrayOfDouble2[m];
        d5 = arrayOfDouble1[k];
        if (d5 == 0.0D)
        {
          d5 = this.stepH[k];
          this.zeroCheck = true;
        }
        arrayOfDouble1[k] = (d5 * (1.0D - this.delta / 2.0D));
        d5 = arrayOfDouble1[m];
        if (d5 == 0.0D)
        {
          d5 = this.stepH[m];
          this.zeroCheck = true;
        }
        arrayOfDouble1[m] = (d5 * (1.0D + this.delta / 2.0D));
        this.lastSSnoConstraint = this.sumOfSquaresError;
        d2 = sumSquares(paramObject, arrayOfDouble1);
        arrayOfDouble1[k] = arrayOfDouble2[k];
        arrayOfDouble1[m] = arrayOfDouble2[m];
        d5 = arrayOfDouble1[k];
        if (d5 == 0.0D)
        {
          d5 = this.stepH[k];
          this.zeroCheck = true;
        }
        arrayOfDouble1[k] = (d5 * (1.0D + this.delta / 2.0D));
        d5 = arrayOfDouble1[m];
        if (d5 == 0.0D)
        {
          d5 = this.stepH[m];
          this.zeroCheck = true;
        }
        arrayOfDouble1[m] = (d5 * (1.0D - this.delta / 2.0D));
        this.lastSSnoConstraint = this.sumOfSquaresError;
        d3 = sumSquares(paramObject, arrayOfDouble1);
        arrayOfDouble1[k] = arrayOfDouble2[k];
        arrayOfDouble1[m] = arrayOfDouble2[m];
        d5 = arrayOfDouble1[k];
        if (d5 == 0.0D)
        {
          d5 = this.stepH[k];
          this.zeroCheck = true;
        }
        arrayOfDouble1[k] = (d5 * (1.0D - this.delta / 2.0D));
        d5 = arrayOfDouble1[m];
        if (d5 == 0.0D)
        {
          d5 = this.stepH[m];
          this.zeroCheck = true;
        }
        arrayOfDouble1[m] = (d5 * (1.0D - this.delta / 2.0D));
        this.lastSSnoConstraint = this.sumOfSquaresError;
        d4 = sumSquares(paramObject, arrayOfDouble1);
        arrayOfDouble[k][m] = ((d1 - d2 - d3 + d4) / (this.delta * this.delta));
      }
    }
    double d7 = 0.0D;
    double d8 = 0.0D;
    for (int i1 = 0; i1 < this.nData; i1++)
    {
      for (int i2 = 0; i2 < this.nXarrays; i2++) {
        arrayOfDouble4[i2] = this.xData[i2][i1];
      }
      switch (this.simplexFlag)
      {
      case 1: 
        this.yCalc[i1] = ((RegressionFunction)paramObject).function(arrayOfDouble2, arrayOfDouble4);
        break;
      case 2: 
        this.yCalc[i1] = ((RegressionFunction2)paramObject).function(arrayOfDouble2, arrayOfDouble4, i1);
        break;
      case 3: 
      case 4: 
        double[] arrayOfDouble5 = ((RegressionFunction3)paramObject).function(arrayOfDouble2, arrayOfDouble4, i1);
        this.yCalc[i1] = arrayOfDouble5[0];
        this.weight[i1] = Math.sqrt(arrayOfDouble5[1]);
      }
      this.residual[i1] = (this.yCalc[i1] - this.yData[i1]);
      d7 += Fmath.square(this.residual[i1]);
      this.residualW[i1] = (this.residual[i1] / this.weight[i1]);
      d8 += Fmath.square(this.residualW[i1]);
    }
    this.sumOfSquaresError = d7;
    double d9 = d7 / (this.nData - j);
    double d10 = Math.sqrt(d9);
    this.chiSquare = d8;
    this.reducedChiSquare = (d8 / (this.nData - j));
    
    double d11 = 1.0D;
    if ((!this.weightOpt) && (!this.trueFreq)) {
      d11 = this.sumOfSquaresError / (this.nData - j);
    }
    for (int i3 = 0; i3 < j; i3++)
    {
      this.pseudoSd[i3] = (2.0D * this.delta * d11 * Math.abs(arrayOfDouble2[i3]) / (this.grad[i3][1] - this.grad[i3][0]));
      if (this.pseudoSd[i3] >= 0.0D) {
        this.pseudoSd[i3] = Math.sqrt(this.pseudoSd[i3]);
      } else {
        this.pseudoSd[i3] = 0;
      }
    }
    double d12;
    if (j == 1)
    {
      d5 = arrayOfDouble2[0];
      if (d5 == 0.0D) {
        d5 = this.stepH[0];
      }
      arrayOfDouble[0][0] = (1.0D / arrayOfDouble[0][0]);
      this.covar[0][0] = (arrayOfDouble[0][0] * d11 * d5 * d5);
      if (this.covar[0][0] >= 0.0D)
      {
        arrayOfDouble3[0] = Math.sqrt(this.covar[0][0]);
        this.corrCoeff[0][0] = 1.0D;
      }
      else
      {
        arrayOfDouble3[0] = 0;
        this.corrCoeff[0][0] = 0;
        this.posVarFlag = false;
      }
    }
    else
    {
      Matrix localMatrix = new Matrix(arrayOfDouble);
      if (this.suppressErrorMessages) {
        localMatrix.suppressErrorMessage();
      }
      d12 = localMatrix.determinant();
      if (d12 == 0.0D)
      {
        this.invertFlag = false;
      }
      else
      {
        localMatrix = localMatrix.inverse();
        this.invertFlag = localMatrix.getMatrixCheck();
      }
      if (!this.invertFlag) {
        i--;
      }
      arrayOfDouble = localMatrix.getArrayCopy();
      
      this.posVarFlag = true;
      int i5;
      int i6;
      if (this.invertFlag)
      {
        for (i5 = 0; i5 < j; i5++)
        {
          d5 = arrayOfDouble2[i5];
          if (d5 == 0.0D) {
            d5 = this.stepH[i5];
          }
          for (i6 = i5; i6 < j; i6++)
          {
            d6 = arrayOfDouble2[i6];
            if (d6 == 0.0D) {
              d6 = this.stepH[i6];
            }
            this.covar[i5][i6] = (2.0D * arrayOfDouble[i5][i6] * d11 * d5 * d6);
            this.covar[i6][i5] = this.covar[i5][i6];
          }
          if (this.covar[i5][i5] >= 0.0D)
          {
            arrayOfDouble3[i5] = Math.sqrt(this.covar[i5][i5]);
          }
          else
          {
            arrayOfDouble3[i5] = 0;
            this.posVarFlag = false;
          }
        }
        for (i5 = 0; i5 < j; i5++) {
          for (i6 = 0; i6 < j; i6++) {
            if ((arrayOfDouble3[i5] != 0) && (arrayOfDouble3[i6] != 0)) {
              this.corrCoeff[i5][i6] = (this.covar[i5][i6] / (arrayOfDouble3[i5] * arrayOfDouble3[i6]));
            } else {
              this.corrCoeff[i5][i6] = 0;
            }
          }
        }
      }
      else
      {
        for (i5 = 0; i5 < j; i5++)
        {
          for (i6 = 0; i6 < j; i6++)
          {
            this.covar[i5][i6] = 0;
            this.corrCoeff[i5][i6] = 0;
          }
          arrayOfDouble3[i5] = 0;
        }
      }
    }
    if (!this.posVarFlag) {
      i--;
    }
    for (int i4 = 0; i4 < this.nParam; i4++)
    {
      this.bestSd[i4] = arrayOfDouble3[i4];
      this.tValues[i4] = (this.best[i4] / this.bestSd[i4]);
      d12 = Math.abs(this.tValues[i4]);
      if (d12 != d12) {
        this.pValues[i4] = 0;
      } else {
        this.pValues[i4] = (1.0D - Stat.studentTcdf(-d12, d12, this.degreesOfFreedom));
      }
    }
    if ((this.nXarrays == 1) && (this.nYarrays == 1)) {
      this.xyR = Stat.corrCoeff(this.xData[0], this.yData, this.weight);
    }
    this.yyR = Stat.corrCoeff(this.yCalc, this.yData, this.weight);
    
    this.yMean = Stat.mean(this.yData);
    this.yWeightedMean = Stat.mean(this.yData, this.weight);
    
    this.sumOfSquaresTotal = 0.0D;
    for (int i4 = 0; i4 < this.nData; i4++) {
      this.sumOfSquaresTotal += Fmath.square((this.yData[i4] - this.yWeightedMean) / this.weight[i4]);
    }
    this.sumOfSquaresRegrn = (this.sumOfSquaresTotal - this.chiSquare);
    if (this.sumOfSquaresRegrn < 0.0D) {
      this.sumOfSquaresRegrn = 0.0D;
    }
    this.multR = (this.sumOfSquaresRegrn / this.sumOfSquaresTotal);
    
    this.adjustedR = 0;
    this.multipleF = 0;
    if (this.nData - this.nXarrays - 1 > 0) {
      this.adjustedR = (1.0D - (1.0D - this.multR) * (this.nData - 1) / (this.nData - this.nXarrays - 1));
    }
    this.multipleF = (this.multR * (this.nData - this.nXarrays - 1.0D) / ((1.0D - this.multR) * this.nXarrays));
    if (this.multipleF >= 0.0D) {
      this.multipleFprob = Stat.fTestProb(this.multipleF, this.nXarrays, this.nData - this.nXarrays - 1);
    }
    calcDurbinWatson();
    
    varianceOfObservations();
    
    return i;
  }
  
  protected double sumSquares(Object paramObject, double[] paramArrayOfDouble)
  {
    RegressionFunction localRegressionFunction = null;
    RegressionFunction2 localRegressionFunction2 = null;
    RegressionFunction3 localRegressionFunction3 = null;
    switch (this.simplexFlag)
    {
    case 1: 
      localRegressionFunction = (RegressionFunction)paramObject;
      break;
    case 2: 
      localRegressionFunction2 = (RegressionFunction2)paramObject;
      break;
    case 3: 
    case 4: 
      localRegressionFunction3 = (RegressionFunction3)paramObject;
    }
    double d1 = -3.0D;
    double[] arrayOfDouble1 = new double[this.nParam];
    double[] arrayOfDouble2 = new double[this.nXarrays];
    for (int i = 0; i < this.nParam; i++) {
      paramArrayOfDouble[i] /= this.scale[i];
    }
    double d2 = this.lastSSnoConstraint;
    int j = 1;
    int k;
    if (this.penalty)
    {
      k = 0;
      for (int m = 0; m < this.nConstraints; m++)
      {
        k = this.penaltyParam[m];
        switch (this.penaltyCheck[m])
        {
        case -1: 
          if (arrayOfDouble1[k] < this.constraints[m])
          {
            d1 = d2 + this.penaltyWeight * Fmath.square(this.constraints[m] - arrayOfDouble1[k]);
            j = 0;
          }
          break;
        case 0: 
          if (arrayOfDouble1[k] < this.constraints[m] * (1.0D - this.constraintTolerance))
          {
            d1 = d2 + this.penaltyWeight * Fmath.square(this.constraints[m] * (1.0D - this.constraintTolerance) - arrayOfDouble1[k]);
            j = 0;
          }
          if (arrayOfDouble1[k] > this.constraints[m] * (1.0D + this.constraintTolerance))
          {
            d1 = d2 + this.penaltyWeight * Fmath.square(arrayOfDouble1[k] - this.constraints[m] * (1.0D + this.constraintTolerance));
            j = 0;
          }
          break;
        case 1: 
          if (arrayOfDouble1[k] > this.constraints[m])
          {
            d1 = d2 + this.penaltyWeight * Fmath.square(arrayOfDouble1[k] - this.constraints[m]);
            j = 0;
          }
          break;
        default: 
          throw new IllegalArgumentException("The " + m + "th penalty check " + this.penaltyCheck[m] + " not recognised");
        }
      }
    }
    int n;
    if (this.sumPenalty)
    {
      k = 0;
      double d4 = 0.0D;
      for (n = 0; n < this.nSumConstraints; n++)
      {
        double d5 = 0.0D;
        for (int i2 = 0; i2 < this.sumPenaltyNumber[n]; i2++)
        {
          k = this.sumPenaltyParam[n][i2];
          d4 = this.sumPlusOrMinus[n][i2];
          d5 += arrayOfDouble1[k] * d4;
        }
        switch (this.sumPenaltyCheck[n])
        {
        case -1: 
          if (d5 < this.sumConstraints[n])
          {
            d1 = d2 + this.penaltyWeight * Fmath.square(this.sumConstraints[n] - d5);
            j = 0;
          }
          break;
        case 0: 
          if (d5 < this.sumConstraints[n] * (1.0D - this.constraintTolerance))
          {
            d1 = d2 + this.penaltyWeight * Fmath.square(this.sumConstraints[n] * (1.0D - this.constraintTolerance) - d5);
            j = 0;
          }
          if (d5 > this.sumConstraints[n] * (1.0D + this.constraintTolerance))
          {
            d1 = d2 + this.penaltyWeight * Fmath.square(d5 - this.sumConstraints[n] * (1.0D + this.constraintTolerance));
            j = 0;
          }
          break;
        case 1: 
          if (d5 > this.sumConstraints[n])
          {
            d1 = d2 + this.penaltyWeight * Fmath.square(d5 - this.sumConstraints[n]);
            j = 0;
          }
          break;
        default: 
          throw new IllegalArgumentException("The " + n + "th summation penalty check " + this.sumPenaltyCheck[n] + " not recognised");
        }
      }
    }
    if (j != 0)
    {
      d1 = 0.0D;
      double d3 = 0.0D;
      double[] arrayOfDouble3 = null;
      for (n = 0; n < this.nData; n++)
      {
        for (int i1 = 0; i1 < this.nXarrays; i1++) {
          arrayOfDouble2[i1] = this.xData[i1][n];
        }
        switch (this.simplexFlag)
        {
        case 1: 
          d1 += Fmath.square((this.yData[n] - localRegressionFunction.function(arrayOfDouble1, arrayOfDouble2)) / this.weight[n]);
          break;
        case 2: 
          d1 += Fmath.square((this.yData[n] - localRegressionFunction2.function(arrayOfDouble1, arrayOfDouble2, n)) / this.weight[n]);
          break;
        case 3: 
        case 4: 
          arrayOfDouble3 = localRegressionFunction3.function(arrayOfDouble1, arrayOfDouble2, n);
          d3 = Fmath.square(this.yData[n] - arrayOfDouble3[0]);
          this.weight[n] = Math.sqrt(arrayOfDouble3[1]);
          d1 += d3 / arrayOfDouble3[1];
        }
      }
      this.lastSSnoConstraint = d1;
    }
    return d1;
  }
  
  private double secondDerivative(Object paramObject1, Object paramObject2, double[] paramArrayOfDouble, int paramInt1, int paramInt2)
  {
    if ((paramInt1 == 0) && (paramInt2 == 0)) {
      this.firstDerivs = new double[this.nParam][this.nData];
    }
    double[] arrayOfDouble1 = null;
    double[] arrayOfDouble2 = new double[this.nXarrays];
    double d = 0.0D;
    switch (this.simplexFlag)
    {
    case 1: 
      RegressionDerivativeFunction localRegressionDerivativeFunction = (RegressionDerivativeFunction)paramObject2;
      RegressionFunction localRegressionFunction = (RegressionFunction)paramObject1;
      for (int i = 0; i < this.nData; i++)
      {
        for (int j = 0; j < this.nXarrays; j++) {
          arrayOfDouble2[j] = this.xData[j][i];
        }
        arrayOfDouble1 = localRegressionDerivativeFunction.function(paramArrayOfDouble, arrayOfDouble2, paramInt1, paramInt2);
        d += (2.0D * arrayOfDouble1[0] * arrayOfDouble1[1] + 2.0D * arrayOfDouble1[2] * (localRegressionFunction.function(paramArrayOfDouble, arrayOfDouble2) - this.yData[i])) / (this.weight[i] * this.weight[i]);
        if (paramInt1 == 0) {
          this.firstDerivs[paramInt2][i] = arrayOfDouble1[1];
        }
      }
      break;
    case 2: 
      RegressionDerivativeFunction2 localRegressionDerivativeFunction2 = (RegressionDerivativeFunction2)paramObject2;
      RegressionFunction2 localRegressionFunction2 = (RegressionFunction2)paramObject1;
      for (int k = 0; k < this.nData; k++)
      {
        for (int m = 0; m < this.nXarrays; m++) {
          arrayOfDouble2[m] = this.xData[m][k];
        }
        arrayOfDouble1 = localRegressionDerivativeFunction2.function(paramArrayOfDouble, arrayOfDouble2, paramInt1, paramInt2, k);
        d += (2.0D * arrayOfDouble1[0] * arrayOfDouble1[1] + 2.0D * arrayOfDouble1[2] * (localRegressionFunction2.function(paramArrayOfDouble, arrayOfDouble2, k) - this.yData[k])) / (this.weight[k] * this.weight[k]);
        if (paramInt1 == 0) {
          this.firstDerivs[paramInt2][k] = arrayOfDouble1[1];
        }
      }
      break;
    case 3: 
    case 4: 
      Object localObject;
      RegressionFunction3 localRegressionFunction3;
      int n;
      int i1;
      if (this.derivFlag == 1)
      {
        localObject = (RegressionDerivativeFunction)paramObject2;
        localRegressionFunction3 = (RegressionFunction3)paramObject1;
        for (n = 0; n < this.nData; n++)
        {
          for (i1 = 0; i1 < this.nXarrays; i1++) {
            arrayOfDouble2[i1] = this.xData[i1][n];
          }
          arrayOfDouble1 = ((RegressionDerivativeFunction)localObject).function(paramArrayOfDouble, arrayOfDouble2, paramInt1, paramInt2);
          d += (2.0D * arrayOfDouble1[0] * arrayOfDouble1[1] + 2.0D * arrayOfDouble1[2] * (localRegressionFunction3.function(paramArrayOfDouble, arrayOfDouble2, n)[0] - this.yData[n])) / (this.weight[n] * this.weight[n]);
          if (paramInt1 == 0) {
            this.firstDerivs[paramInt2][n] = arrayOfDouble1[1];
          }
        }
      }
      else
      {
        localObject = (RegressionDerivativeFunction2)paramObject2;
        localRegressionFunction3 = (RegressionFunction3)paramObject1;
        for (n = 0; n < this.nData; n++)
        {
          for (i1 = 0; i1 < this.nXarrays; i1++) {
            arrayOfDouble2[i1] = this.xData[i1][n];
          }
          arrayOfDouble1 = ((RegressionDerivativeFunction2)localObject).function(paramArrayOfDouble, arrayOfDouble2, paramInt1, paramInt2, n);
          d += (2.0D * arrayOfDouble1[0] * arrayOfDouble1[1] + 2.0D * arrayOfDouble1[2] * (localRegressionFunction3.function(paramArrayOfDouble, arrayOfDouble2, n)[0] - this.yData[n])) / (this.weight[n] * this.weight[n]);
          if (paramInt1 == 0) {
            this.firstDerivs[paramInt2][n] = arrayOfDouble1[1];
          }
        }
      }
      break;
    }
    return d;
  }
  
}