package cs3500.lab2.skills;

import java.util.Objects;

/**
 * Represents an ability for a character.
 * This class provides the basis for defining abilities
 * that a person can possess.
 * Abilities can vary in different positions.
 */
public class Ability extends AbstractSkill {
  final String ability;

  // constructor for an ability using a string
  public Ability(String ability) {
    Objects.requireNonNull(ability);
    this.ability = ability;
  }

  // checks if the requirement satisfies the needed requirement for the position
  @Override
  public boolean satisfiesReq(Skill requirement) {
    if (!(requirement instanceof AbstractSkill)) {
      return false;
    }
    AbstractSkill that = (AbstractSkill) requirement;
    return that.isSatisfiedBy(this);
  }

  // checks if the ability is satisfied by the given
  @Override
  protected boolean isSatisfiedBy(Ability other) {
    return other.ability.equals(this.ability);
  }

  // checks if the ability is the same as the given
  @Override
  protected boolean sameAbility(Ability that) {
    return that.ability.equals(this.ability);
  }
}
