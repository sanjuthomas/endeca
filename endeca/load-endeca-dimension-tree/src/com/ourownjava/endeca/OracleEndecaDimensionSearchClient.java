package com.ourownjava.endeca;

import com.endeca.navigation.DimLocation;
import com.endeca.navigation.DimLocationList;
import com.endeca.navigation.DimVal;
import com.endeca.navigation.DimValList;
import com.endeca.navigation.DimensionSearchResult;
import com.endeca.navigation.DimensionSearchResultGroup;
import com.endeca.navigation.DimensionSearchResultGroupList;
import com.endeca.navigation.ENEQuery;
import com.endeca.navigation.ENEQueryException;
import com.endeca.navigation.ENEQueryResults;
import com.endeca.navigation.HttpENEConnection;

/**
 * Oracle Endeca Dimension Search Example to load the dimension tree.
 * 
 * @author ourownjava.com
 * 
 */
public class OracleEndecaDimensionSearchClient {

	public DimensionTree loadDimensionTree() throws ENEQueryException {

		final DimensionTree dimensionTree = new DimensionTree();
		final ENEQuery query = new ENEQuery();
		query.setDimSearchTerms("*");
		final ENEQueryResults results = search(query);
		final DimensionSearchResult dimensionSearchResult = results
				.getDimensionSearch();
		final DimensionSearchResultGroupList dimensionSearchResultGroupList = dimensionSearchResult
				.getResults();
		for (final Object dimensionSearchResultGroupElement : dimensionSearchResultGroupList) {
			final DimensionSearchResultGroup dimensionSearchResultGroup = (DimensionSearchResultGroup) dimensionSearchResultGroupElement;
			final DimValList roots = dimensionSearchResultGroup.getRoots();
			if (roots.size() != 0) {
				final DimVal root = (DimVal) roots.get(0);
				for (int i = 0; i < dimensionSearchResultGroup.size(); i++) {
					final DimLocationList dimLocationList = (DimLocationList) dimensionSearchResultGroup
							.get(i);
					for (final Object dimLocationElement : dimLocationList) {
						final DimLocation dimLocation = (DimLocation) dimLocationElement;
						final DimValList ancestors = dimLocation.getAncestors();
						final DimVal dimVal = dimLocation.getDimValue();
						dimensionTree.addNodes(root, ancestors, dimVal);
					}
				}
			}
		}
		return dimensionTree;
	}

	private ENEQueryResults search(final ENEQuery eneQuery)
			throws ENEQueryException {
		return new HttpENEConnection("localhost", "15000").query(eneQuery);
	}
	
	public static void main(String[] args) throws ENEQueryException {
		
		System.out.println(new OracleEndecaDimensionSearchClient().loadDimensionTree());
	}

}
