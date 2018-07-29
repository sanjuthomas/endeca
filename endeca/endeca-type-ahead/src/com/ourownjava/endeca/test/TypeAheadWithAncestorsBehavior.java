package com.ourownjava.endeca.test;

import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.endeca.navigation.ENEQueryException;
import com.ourownjava.endeca.TypeAheadWithAncestors;

/**
 *
 * @author Sanju Thomas
 *
 */
public class TypeAheadWithAncestorsBehavior {

  private TypeAheadWithAncestors typeAheadWithAncestors;

  @Before
  public void setUp() {
    this.typeAheadWithAncestors = new TypeAheadWithAncestors();
  }

  @Test
  public void shouldFindTypeAheadEntries() throws ENEQueryException {
    final List<String> tokens = this.typeAheadWithAncestors.findRelatedTokens("Back");
    assertTrue(tokens.size() > 0);
    tokens.contains("camera");
  }

}
