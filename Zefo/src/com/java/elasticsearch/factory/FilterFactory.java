package com.java.elasticsearch.factory;

import java.util.List;
import java.util.Optional;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import com.java.elasticsearch.exception.InvalidFilterException;
import com.java.elasticsearch.filter.BaseFilter;
import com.java.elasticsearch.filter.BooleanFilter;
import com.java.elasticsearch.filter.CommonFilterToPropertyMap;
import com.java.elasticsearch.filter.DoubleFilter;
import com.java.elasticsearch.filter.RangeFilter;
import com.java.elasticsearch.filter.StringFilter;

public class FilterFactory {
	private final CommonFilterToPropertyMap filterToPropertyMap;

	public FilterFactory(final CommonFilterToPropertyMap filterToPropertyMap) {
		this.filterToPropertyMap = filterToPropertyMap;
	}

	public QueryBuilder buildFilter(final BaseFilter filter) throws InvalidFilterException {
		final Optional<String> filterPropertyName = this.filterToPropertyMap.getAttributeNameForFilter(filter);

		if (!filterPropertyName.isPresent()) {
			throw new InvalidFilterException(String.format("No corresponding property found for filter %s", filter));
		}

		if (filter instanceof StringFilter) {
			return this.buildStringFilter(filterPropertyName.get(), (StringFilter) filter);
		}

		if (filter instanceof RangeFilter) {
			return this.buildRangeFilter(filterPropertyName.get(), (RangeFilter) filter);
		}

		if (filter instanceof BooleanFilter) {
			return this.buildBooleanFilter(filterPropertyName.get(), (BooleanFilter) filter);
		}

		if (filter instanceof DoubleFilter) {
			return this.buildDoubleFilter(filterPropertyName.get(), (DoubleFilter) filter);
		}

		throw new InvalidFilterException(String.format("Filter %s is invalid", filter));
	}

	public QueryBuilder buildQueryFromFilters(final List<BaseFilter> filterList) throws InvalidFilterException {
		if (filterList.isEmpty()) {
			return QueryBuilders.matchAllQuery();
		}

		final BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

		for (final BaseFilter filter : filterList) {
			final QueryBuilder subFilter = this.buildFilter(filter);
			queryBuilder.must(subFilter);
		}

		return queryBuilder;
	}

	private QueryBuilder buildBooleanFilter(final String filterPropertyName, final BooleanFilter booleanFilter) {
		return QueryBuilders.matchQuery(filterPropertyName, booleanFilter.getValue());
	}

	private QueryBuilder buildRangeFilter(final String filterPropertyName, final RangeFilter rangeFilter)
			throws InvalidFilterException {
		if (rangeFilter.getBefore() != null && rangeFilter.getAfter() != null) {
			if (rangeFilter.getBefore() <= rangeFilter.getAfter()) {
				return QueryBuilders.rangeQuery(filterPropertyName).from(rangeFilter.getBefore())
						.to(rangeFilter.getAfter());
			}

		}

		if (rangeFilter.getBefore() == null && rangeFilter.getAfter() != null) {
			return QueryBuilders.rangeQuery(filterPropertyName).to(rangeFilter.getAfter());

		}

		if (rangeFilter.getBefore() != null && rangeFilter.getAfter() == null) {
			return QueryBuilders.rangeQuery(filterPropertyName).from(rangeFilter.getBefore());
		}

		throw new InvalidFilterException(String.format("Malformed range filter: from: %s, to: %s",
				rangeFilter.getBefore(), rangeFilter.getAfter()));
	}

	private QueryBuilder buildDoubleFilter(final String filterPropertyName, final DoubleFilter doubleFilter) {
		return QueryBuilders.matchQuery(filterPropertyName, doubleFilter.getValue());
	}

	private QueryBuilder buildStringFilter(final String filterPropertyName, final StringFilter stringFilter) {
		return QueryBuilders.matchQuery(filterPropertyName, stringFilter);

	}

}
