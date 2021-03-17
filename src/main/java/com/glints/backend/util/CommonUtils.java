package com.glints.backend.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.glints.backend.constants.ApplicationConstant;
import com.glints.backend.dao.entity.OpeningHours;
import com.glints.backend.request.OpenHoursDetails;

public class CommonUtils {

	public static boolean isEmpty(String str) {
		return (null == str || ApplicationConstant.EMPTY.equalsIgnoreCase(str.strip()));
	}

	public static List<OpeningHours> createOpeningHoursDetails(String openingHours) {
		List<OpeningHours> openingHoursList = new ArrayList<>();
		if (!CommonUtils.isEmpty(openingHours)) {
			String[] daysAndHours = openingHours.split(ApplicationConstant.SLASH);
			for (String daysAndHour : daysAndHours) {
				String[] days = daysAndHour.split(ApplicationConstant.COMMA_SEPERATOR);
				OpenHoursDetails openHourDetails = new OpenHoursDetails();
				Map<String, OpenHoursDetails> openingHourDetailsMap = new HashMap<>();
				for (String day : days) {
					if (!day.contains(ApplicationConstant.HYPHEN)) {
						openingHourDetailsMap.put(day, openHourDetails);
					} else {
						String timings[] = day.trim().split(ApplicationConstant.SPACE);
						openingHourDetailsMap.put(timings[0], openHourDetails);
						calculateTimings(Arrays.copyOfRange(timings, 1, timings.length), openHourDetails);
					}
				}
				openingHourDetailsMap.entrySet()
						.forEach(entry -> frameOpeningHoursList(entry, openHourDetails, openingHoursList));
			}
		}

		return openingHoursList;
	}

	private static void frameOpeningHoursList(Entry<String, OpenHoursDetails> entry, OpenHoursDetails openHourDetails,
			List<OpeningHours> openingHoursList) {
		try {
			OpeningHours openingHours = new OpeningHours();
			openingHours.setOpenTime(openHourDetails.getOpenTime());
			openingHours.setCloseTime(openHourDetails.getCloseTime());
			openingHours.setDayOfWeek(entry.getKey());
			openingHoursList.add(openingHours);
		} catch (Exception ex) {
			System.out.println("Error in Date Conversion....");
		}
	}

	public static void calculateTimings(String timings[], OpenHoursDetails openHourDetails) {
		if (timings.length == 5) {
			Long openTime = getLongTimingValue(timings[0], timings[1]);
			Long closeTIme = getLongTimingValue(timings[3], timings[4]);
			openHourDetails.setOpenTime(openTime);
			openHourDetails.setCloseTime(closeTIme);

		}
	}

	public static Long getLongTimingValue(String time, String amPm) {
		String[] hoursAndMinutes = time.split(ApplicationConstant.COLON);
		Long timeVal = null;
		if (hoursAndMinutes.length == 1 || hoursAndMinutes.length == 2) {
			timeVal = Long.valueOf(hoursAndMinutes[0]) * 60 * 60 * 1000;
			if ((ApplicationConstant.PM.equalsIgnoreCase(amPm) && !time.startsWith(ApplicationConstant.TWELVE))
					|| (ApplicationConstant.AM.equalsIgnoreCase(amPm) && time.startsWith(ApplicationConstant.TWELVE))){
				timeVal += 12 * 60 * 60 * 1000;
			}  
		}
		if (hoursAndMinutes.length == 2) {
			timeVal += ((Long.valueOf(hoursAndMinutes[1])) * 60 * 60 * 1000) / 60;
		}
		return timeVal;
	}

}
