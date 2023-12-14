package src.models;

/**
 * InnerAlgoProfile
 */
public class TimeComplexity {

  public final String worstCase;
  public final String bestCase;
  public final String averageCase;

  public TimeComplexity(String worstCase, String bestCase, String averageCase) {
    this.worstCase = worstCase;
    this.bestCase = bestCase;
    this.averageCase = averageCase;
  }
}
