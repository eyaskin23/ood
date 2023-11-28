package cs3500.lab2.offers;

import java.util.List;
import java.util.Objects;

import cs3500.lab2.skills.Skill;

/**
 * Represents a Volunteer position.
 * Sets up a volunteer position for a student.
 */
public class Volunteer extends JobOffer implements Offer {
  public final String jobDescription;
  private final List<Skill> requirements;

  /**
   * Creates a volunteer job offer based on a description and a list of requirements.
   */
  public Volunteer(String description, List<Skill> reqs) {
    super(description, reqs);
    this.jobDescription = Objects.requireNonNull(description);
    this.requirements = Objects.requireNonNull(reqs);
  }

  // calculates the salary of a volunteer
  public int calculateSalary() {
    return 0;
  }

  // sees if the application from the student meets the list
  // of requirements for a volunteer position
  public boolean satisfiesRequirements(List<Skill> app) {
    return requirements.stream()
            .allMatch(req -> app.stream().anyMatch(appSkill -> appSkill.satisfiesReq(req)));
  }
}
