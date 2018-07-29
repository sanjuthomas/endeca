package com.ourownjava.endeca;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sanju Thomas
 *
 */

public class Dimension {

  public Dimension(final long id) {
    this.id = id;
  }

  private long id;

  private String name;

  private final List<Dimension> refinements = new ArrayList<Dimension>();

  public long getId() {
    return this.id;
  }

  public void setId(final long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public List<Dimension> getRefinements() {
    return this.refinements;
  }

  public void addRefinement(final Dimension dimension) {
    this.refinements.add(dimension);
  }

  public boolean hasRefinements() {
    return !this.refinements.isEmpty();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + (int) (this.id ^ (this.id >>> 32));
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    final Dimension other = (Dimension) obj;
    if (this.id != other.id) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Dimension [id=" + this.id + ", name=" + this.name + ", refinements=" + this.refinements
        + "]";
  }


}
