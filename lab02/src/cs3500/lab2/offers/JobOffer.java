package cs3500.lab2.offers;

import cs3500.lab2.skills.Skill;

import java.util.List;
import java.util.Objects;

/**
 * Represents a generic Job Offer including all types of jobs.
 * Sets up a job offer based on a job description and a list of requirements.
 */
public abstract class JobOffer {
  String jobDescription;
  List<Skill> requirements;

  /**
   * Creates a job offer based on a description and a list of requirements.
   */
  public JobOffer(String description, List<Skill> reqs) {
    this.jobDescription = Objects.requireNonNull(description);
    this.requirements = Objects.requireNonNull(reqs);
  }
}

