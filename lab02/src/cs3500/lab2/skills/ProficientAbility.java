package cs3500.lab2.skills;

/**
 * Sets up a Proficient Ability
 * Skills can either be beginner, intermediate, or expert.
 */
public class ProficientAbility extends AbstractSkill {
  Skill beginner;
  Skill intermediate;
  Skill expert;

  /**
   * Skills for a proficient ability
   * can either be at a beginner level, intermediate, or expert.
   */
  public ProficientAbility(Skill beginner, Skill intermediate, Skill expert) {
    this.beginner = beginner;
    this.intermediate = intermediate;
    this.expert = expert;
  }

  public boolean satisfiesReq(Skill requirement) {
    return (requirement instanceof AbstractSkill)
            && ((AbstractSkill) requirement).isSatisfiedBy((Years) requirement);
  }
}
