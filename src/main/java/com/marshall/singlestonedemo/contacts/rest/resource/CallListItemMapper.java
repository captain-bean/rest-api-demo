package com.marshall.singlestonedemo.contacts.rest.resource;

import java.util.ArrayList;
import java.util.List;

import com.marshall.singlestonedemo.contacts.modeling.Contact;
import com.marshall.singlestonedemo.contacts.modeling.Phone;
import com.marshall.singlestonedemo.contacts.modeling.PhoneNumberType;
import com.marshall.singlestonedemo.contacts.rest.dto.CallListItem;

/**
 * Transforms a full contact into a slimmer call list item
 *
 */
public class CallListItemMapper {

	public static CallListItem contactToCallListItem(Contact contact) {
		if(contact == null) {
			return null;
		}

		CallListItem item = new CallListItem();
		item.setName(contact.getName());

		Phone homePhone = contact.getPhone().stream()
				.filter(number -> number.getType() == PhoneNumberType.HOME)
				.findFirst()
				.orElse(null);

		if(homePhone != null) {
			item.setPhone(homePhone.getNumber());
		}

		return item;

	}

	public static List<CallListItem> contactListToCallList(List<Contact> contacts) {
		if (contacts == null) {
			return null;
		}

		List<CallListItem> callList = new ArrayList<>(contacts.size());
		for (Contact contact : contacts ) {
			callList.add( contactToCallListItem(contact) );
		}

		return callList;
	}
}
