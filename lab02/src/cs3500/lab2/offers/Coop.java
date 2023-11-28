package cs3500.lab2.offers;

import java.util.List;
import java.util.Objects;

import cs3500.lab2.skills.Skill;

/**
 * Represents a Coop position.
 * Sets up a coop position for a student.
 */
public class Coop extends JobOffer implements Offer {
  private final int hourlyRate;
  private final int maxHours;
  private final List<Skill> requirements;

  /**
   * A Coop position includes a job description, an hourly rate,
   * maximum number of hours, and a list of requirements.
   */
  public Coop(String description, int rate, int hours, List<Skill> reqs) {
    super(description, reqs);
    this.requirements = Objects.requireNonNull(reqs);
    this.jobDescription = Objects.requireNonNull(description);
    if (rate < 0 || hours < 0) {
      throw new IllegalArgumentException("Hourly rate cannot be negative.");
    }
    this.hourlyRate = rate;
    this.maxHours = hours;
  }

  // calculates a salary for a coop based on the hourly rate
  // multiplied by the max hours * 52
  @Override
  public int calculateSalary() {
    return this.hourlyRate * this.maxHours * 52;
  }

  // checks if the list from the application
  // meets the requirements for the coop position
  @Override
  public boolean satisfiesRequirements(List<Skill> app) {
    return requirements.stream()
            .allMatch(req -> app.stream().anyMatch(appSkill -> appSkill.satisfiesReq(req)));
  }
}
