package com.ourownjava.endeca;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.endeca.navigation.DimVal;
import com.endeca.navigation.DimValList;

/**
 *
 * @author Sanju Thomas
 *
 */

public class DimensionTree {

  private final Map<Long, Dimension> idRootDimensionMap = new LinkedHashMap<Long, Dimension>();

  public void addNodes(final DimVal rootNode, final DimValList ancestors, final DimVal leaf) {
    Dimension root = this.idRootDimensionMap.get(rootNode.getId());
    if (null == root) {
      root = new Dimension(rootNode.getId());
      root.setName(rootNode.getName());
      this.idRootDimensionMap.put(rootNode.getId(), root);
    }
    for (final Object dimValElement : ancestors) {
      final DimVal dimVal = (DimVal) dimValElement;
      root = this.createNode(root, dimVal);
    }
    this.createNode(root, leaf);
  }

  private Dimension createNode(final Dimension parentDimension, final DimVal dimVal) {
    Dimension node = this.idRootDimensionMap.get(dimVal.getId());
    if (null == node) {
      node = new Dimension(dimVal.getId());
      node.setName(dimVal.getName());
      parentDimension.addRefinement(node);
      this.idRootDimensionMap.put(dimVal.getId(), node);
    }
    return node;
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    for (final Dimension dimension : this.getRootDimension()) {
      builder.append(dimension);
      builder.append("\n");
    }
    return builder.toString();
  }

  public List<Dimension> getRootDimension() {
    final List<Dimension> dimensions = new ArrayList<Dimension>();
    for (final Dimension dimensionBean : this.idRootDimensionMap.values()) {
      if (dimensionBean.hasRefinements()) {
        dimensions.add(dimensionBean);
      }
    }
    return dimensions;
  }

}
