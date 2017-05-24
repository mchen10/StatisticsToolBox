

class PolyXYDEfunction
  implements RegressionFunction3
{
  private double[][] xErrors = (double[][])null;
  private double[] yErrors = null;
  private int deg = 0;
  
  public double[] function(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, int paramInt)
  {
    double[] arrayOfDouble = new double[2];
    arrayOfDouble[0] = paramArrayOfDouble1[0];
    arrayOfDouble[1] = (this.yErrors[paramInt] * this.yErrors[paramInt]);
    double d1 = 0.0D;
    double d2 = this.xErrors[0][paramInt] * this.xErrors[0][paramInt];
    for (int i = 1; i <= this.deg; i++)
    {
      arrayOfDouble[0] += paramArrayOfDouble1[i] * Math.pow(paramArrayOfDouble2[0], i);
      d1 += i * paramArrayOfDouble1[i] * Math.pow(paramArrayOfDouble2[0], i - 1);
    }
    arrayOfDouble[1] += d1 * d1 * d2;
    return arrayOfDouble;
  }
  
  public void setDeg(int paramInt)
  {
    this.deg = paramInt;
  }
  
  public void setXerrors(double[][] paramArrayOfDouble)
  {
    this.xErrors = paramArrayOfDouble;
  }
  
  public void setYerrors(double[] paramArrayOfDouble)
  {
    this.yErrors = paramArrayOfDouble;
  }
}
