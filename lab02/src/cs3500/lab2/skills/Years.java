package cs3500.lab2.skills;

/**
 * Represents the number of years of experience.
 * As a requirement, it is satisfied if another Year
 * meets or exceeds the number of years in this skill.
 */
public class Years extends AbstractSkill {

  public int numYears;

  /**
   * Creates a year based on a given integer, and checks to make
   * sure it's not negative.
   */
  public Years(int y) {
    if (y < 0) {
      throw new IllegalArgumentException("Years cannot be negative");
    }
    this.numYears = y;
  }

  public boolean satisfiesReq(Skill requirement) {
    return (requirement instanceof AbstractSkill)
            && ((AbstractSkill) requirement).isSatisfiedBy(this);
  }

  protected boolean isSatisfiedBy(Years that) {
    return that.numYears >= this.numYears;
  }

  protected boolean sameYear(Years that) {
    return this.numYears == that.numYears;
  }
}
