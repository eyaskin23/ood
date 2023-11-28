package cs3500.lab2.skills;

import java.util.Objects;

abstract class AbstractSkill implements Skill {

  protected boolean isSatisfiedBy(Years other) {
    return false;
  }

  protected boolean isSatisfiedBy(Ability other) {
    return false;
  }

  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    AbstractSkill that = (AbstractSkill) other;
    return this.sameAbility((Ability) that);
  }

  public int hashCode() {
    return Objects.hash(getClass());
  }

  protected boolean sameAbility(Ability other) {
    return false;
  }
}
