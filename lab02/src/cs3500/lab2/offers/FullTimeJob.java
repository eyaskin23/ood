package cs3500.lab2.offers;

import java.util.List;
import java.util.Objects;

import cs3500.lab2.skills.Skill;

// A FullTimeJob represents a full time job, including a description, a yearly salary,
// and a list of requirements

/**
 * Represents a FullTime Job including all its characteristics.
 * Sets up a full-time job position.
 */
public class FullTimeJob extends JobOffer implements Offer {
  private int yearlySalary;
  public final List<Skill> requirements;

  /**
   * A Full time job consists of a job description, a
   * yearly salary, and a list of requirements.
   */
  public FullTimeJob(String description, int yearlySalary, List<Skill> reqs) {
    super(description, reqs);
    this.jobDescription = Objects.requireNonNull(description);
    this.requirements = Objects.requireNonNull(reqs);
    if (yearlySalary < 0) {
      throw new IllegalArgumentException("Salary cannot be negative");
    }
    this.yearlySalary = yearlySalary;
  }

  // calculates the yearly salary of a full-time job
  @Override
  public int calculateSalary() {
    return this.yearlySalary;
  }

  // checks if the application from a student
  // meets the list of requirements
  @Override
  public boolean satisfiesRequirements(List<Skill> app) {
    return requirements.stream()
            .allMatch(req -> app.stream().anyMatch(appSkill -> appSkill.satisfiesReq(req)));
  }
}
