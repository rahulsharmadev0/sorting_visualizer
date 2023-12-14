package src.models;

/**
 * AlgoProfile
 */
public class AlgoProfile {

  public final String name;
  public final TimeComplexity timeComplexity;
  public final String spaceComplexity;
  public final String about;
  public final String author;

  public AlgoProfile(
    String name,
    TimeComplexity timeComplexity,
    String spaceComplexity,
    String author,
    String about
  ) {
    this.name = name;
    this.timeComplexity = timeComplexity;
    this.spaceComplexity = spaceComplexity;
    this.about = about;
    this.author = author;
  }
}
