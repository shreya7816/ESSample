package com.java.elasticsearch.filter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.java.elasticsearch.entity.filters.BrandNameFilter;
import com.java.elasticsearch.entity.filters.ConditionFilter;
import com.java.elasticsearch.entity.filters.FeatureFilter;
import com.java.elasticsearch.entity.filters.PriceRangeFilter;
import com.java.elasticsearch.entity.filters.ScreenSizeRangeFilter;
import com.java.elasticsearch.entity.filters.ScreenTypeFilter;

public class CommonFilterToPropertyMap {
	private final Map<Class<? extends BaseFilter>, String> filterToPropertyMap;

	public CommonFilterToPropertyMap() {
		this.filterToPropertyMap = new HashMap<Class<? extends BaseFilter>, String>();
		
		filterToPropertyMap.put(ScreenSizeRangeFilter.class, "screenSize");
		filterToPropertyMap.put(ConditionFilter.class, "condition");
		filterToPropertyMap.put(ScreenTypeFilter.class, "screenType");
		filterToPropertyMap.put(BrandNameFilter.class, "brandName");
		filterToPropertyMap.put(PriceRangeFilter.class, "price");
		filterToPropertyMap.put(FeatureFilter.class, "feature");

	}

	public Optional<String> getAttributeNameForFilter(final BaseFilter filter) {
		return Optional.ofNullable(this.filterToPropertyMap.get(filter.getClass()));
	}

}
