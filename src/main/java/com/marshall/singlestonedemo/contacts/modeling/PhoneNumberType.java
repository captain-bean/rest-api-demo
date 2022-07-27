package com.marshall.singlestonedemo.contacts.modeling;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * A descriptor to categorize a phone number based on its usage
 *
 */
public enum PhoneNumberType {

	HOME, WORK, MOBILE;

	@JsonCreator
	public static PhoneNumberType setValue(String key) {
		return Arrays.stream(PhoneNumberType.values())
				.filter(exampleEnum -> exampleEnum.toString().equals(key.toUpperCase()))
				.findAny()
				.orElse(null);
	}
}
