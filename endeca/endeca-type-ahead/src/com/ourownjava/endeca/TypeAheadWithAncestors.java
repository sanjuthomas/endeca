package com.ourownjava.endeca;
 
import java.util.ArrayList;
import java.util.List;

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
 * Oracle Endeca - Type-ahead using dimension search.
 * 
 * @author ourownjava.com 
 */
public class TypeAheadWithAncestors {
     
    @SuppressWarnings({ "static-method" })
    private ENEQueryResults search(final ENEQuery eneQuery) throws ENEQueryException {
        return new HttpENEConnection("localhost", "15000").query(eneQuery);
    }

    /**
     * This method shall return suggestion list with ancestors from the dimension tree.
     * 
     * @param dimensionName
     * @return DimensionWrapper
     * @throws ENEQueryException
     */
    public List<String> findRelatedTokens(final String dimensionName) throws ENEQueryException {

        final List<String> tokens = new ArrayList<String>();
        final ENEQuery query = new ENEQuery();
        query.setDimSearchTerms(dimensionName);
        query.setNavERecSearchComputeAlternativePhrasings(true);
        final ENEQueryResults results = search(query);
        final DimensionSearchResult dimensionSearchResult = results.getDimensionSearch();
        final DimensionSearchResultGroupList dimensionSearchResultGroupList = dimensionSearchResult.getResults();
        for (Object dimensionSearchResultGroupElement : dimensionSearchResultGroupList) {
            final DimensionSearchResultGroup dimensionSearchResultGroup = (DimensionSearchResultGroup) dimensionSearchResultGroupElement;
            final DimValList roots = dimensionSearchResultGroup.getRoots();
            if (roots.size() != 0) {
                StringBuilder token = new StringBuilder();
                final DimVal root = (DimVal) roots.get(0);
                if(!"product.category".equalsIgnoreCase(root.getDimensionName())){
                	token.append(root.getDimensionName());
                	token.append("> ");
                }
                for (int i = 0; i < dimensionSearchResultGroup.size(); i++) {
                    final DimLocationList dimLocationList = (DimLocationList) dimensionSearchResultGroup.get(i);
                    for (Object dimLocationElement : dimLocationList) {
                        final DimLocation dimLocation = (DimLocation) dimLocationElement;
                        final DimValList ancestors = dimLocation.getAncestors();
                        for (Object dimValElement : ancestors) {
                            final DimVal dimVal = (DimVal) dimValElement;
                            token.append(dimVal.getName());
                            token.append("> ");
                        }
                        final DimVal dimVal = dimLocation.getDimValue();
                        token.append(dimVal.getName());
                        token.append("\n");
                    }
                }
                tokens.add(token.toString());
            }
        }
        return tokens;
    }
}