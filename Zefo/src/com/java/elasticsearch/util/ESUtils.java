package com.java.elasticsearch.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.java.elasticsearch.entity.SearchPage;
import com.java.elasticsearch.entity.filters.BrandNameFilter;
import com.java.elasticsearch.entity.filters.ConditionFilter;
import com.java.elasticsearch.entity.filters.FeatureFilter;
import com.java.elasticsearch.entity.filters.PriceRangeFilter;
import com.java.elasticsearch.entity.filters.ScreenSizeRangeFilter;
import com.java.elasticsearch.entity.filters.ScreenTypeFilter;
import com.java.elasticsearch.filter.BaseFilter;

/**
 * Utility class containing sample data, static variables and method used for
 * both the main classes.
 * 
 * getSearchPageInstance is a utility method which takes random data from
 * mutliple sample data arrays and fills the searchpage instance.
 * 
 * @author
 *
 */
public class ESUtils {

	public static final Double[] SCREEN_SIZE = { 21D, 29D, 31D, 32D, 33D, 35D, 39D, 40D, 43D, 45D, 49D, 44D };

	public static final String[] CONDITION = { "Unboxed Plus", "Unboxed", "Like New", "Gently Used", "Well Used",
			"Slightly Scratched", "Warehouse Reject", "New", "Heavily Used" };

	public static final String[] SCREEN_TYPE = { "LCD", "LED", "Plasma", "LCD", "LED", "Plasma", "LCD", "LED", "Plasma",
			"LCD", "LED", "Plasma" };

	public static final String[] BRAND = { "VU", "Sanyo", "Micromax", "LeEco", "LG", "TCL", "Infocus", "Samsung",
			"Panasonic", "Sony" };

	public static final Double[] PRICE = { 5000D, 10000D, 12000D, 15000D, 20000D, 25000D, 27000D, 30000D, 40000D,
			45000D };

	public static final String[] FEATURE = { "Smart TV", "3D TV", "HD Ready", "Full HD" };

	public static final Double[] POPULARITY = { 80.5D, 40.5D, 60D, 25D, 92D, 15D, 31D, 44D, 71D, 5D, 59D };

	public static final Double[] DISCOUNT = { 0D, 15D, 25D, 5D, 10D, 7.5D, 11.5D, 35D, 50D, 45D, 30D, 35D };

	private static final String CLUSTER_NAME = "cluster.name";
	private static final String CLIENT_TRANSPORT_SNIFF = "client.transport.sniff";
	private static final String CLUSTER = "elasticsearch";

	public static final String TYPE = "newpage";
	public static final String INDEX = "newindex";
	private static final String LOCALHOST = "localhost";
	private static final Integer PORT = 9300;

	private static Client client = null;

	public static Client getClient() throws UnknownHostException {

		if (client != null) {
			return client;
		}
		Settings settings = Settings.builder().put(CLUSTER_NAME, CLUSTER).put(CLIENT_TRANSPORT_SNIFF, true).build();
		client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new TransportAddress(InetAddress.getByName(LOCALHOST), PORT));
		return client;
	}

	public static SearchPage getSearchPageInstance(Double screenSize, String condition, String screenType,
			String brandName, Double price, String feature, Double popularity, Date arrivalDate, Double discount) {
		SearchPage page = new SearchPage();
		page.setScreenSize(screenSize);
		page.setCondition(condition);
		page.setScreenType(screenType);
		page.setArrivalDate(arrivalDate.getTime());
		page.setBrandName(brandName);
		page.setDiscount(discount);
		page.setFeature(feature);
		page.setPopularity(popularity);
		page.setPrice(price);

		return page;
	}

	public static SearchPage getSearchPageInstance(int num) {

		// Random random = new Random();
		// int num = random.nextInt();

		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, num);

		SearchPage page = new SearchPage();
		page.setScreenSize(SCREEN_SIZE[(SCREEN_SIZE.length - 1) % num]);
		page.setCondition(CONDITION[(CONDITION.length - 1) % num]);
		page.setScreenType(SCREEN_TYPE[(SCREEN_TYPE.length - 1) % num]);
		page.setArrivalDate(cal.getTime().getTime());
		page.setBrandName(BRAND[(BRAND.length - 1) % num]);
		page.setDiscount(DISCOUNT[(DISCOUNT.length - 1) % num]);
		page.setFeature(FEATURE[(FEATURE.length - 1) % num]);
		page.setPopularity(POPULARITY[(POPULARITY.length - 1) % num]);
		page.setPrice(PRICE[(PRICE.length - 1) % num]);

		return page;
	}

	public static BaseFilter getPriceRangeFilter(Double before, Double after) {
		PriceRangeFilter filter = new PriceRangeFilter();
		filter.setAfter(after);
		filter.setBefore(before);
		return filter;
	}

	public static BaseFilter getScreenSizeRangeFilter(Double before, Double after) {
		ScreenSizeRangeFilter filter = new ScreenSizeRangeFilter();
		filter.setAfter(after);
		filter.setBefore(before);
		return filter;
	}

	public static BaseFilter getBrandNameFilter(String brand) {
		BrandNameFilter filter = new BrandNameFilter();
		filter.setValue(brand);
		return filter;
	}

	public static BaseFilter getConditionFilter(String value) {
		ConditionFilter filter = new ConditionFilter();
		filter.setValue(value);
		return filter;
	}

	public static BaseFilter getFeatureFilter(String value) {
		FeatureFilter filter = new FeatureFilter();
		filter.setValue(value);
		return filter;
	}

	public static BaseFilter getScreenTypeFilter(String value) {
		ScreenTypeFilter filter = new ScreenTypeFilter();
		filter.setValue(value);
		return filter;
	}
}
