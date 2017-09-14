package com.fomo.rest.hateos.web.util;

public final class LinkUtil {
	public static final String REL_COLLECTIONS = "collections";
	public static final String REL_PREV = "prev";
	public static final String REL_NEXT = "next";
	public static final String REL_FIRST = "first";
	public static final String REL_LAST = "last";

	private LinkUtil() {
		throw new AssertionError();
	}

	public static String createLinkHeader(final String uri, final String rel) {
		String url = "<" + uri + ">;rel=\"" + rel + "\"";
		return url;
	}
}
