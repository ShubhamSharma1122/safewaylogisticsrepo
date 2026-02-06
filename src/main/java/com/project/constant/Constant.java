package com.project.constant;

import java.util.Date;

public final class Constant {

	private Constant() {
		super();
	}

	public static final String CAN_VIEW = "CAN_VIEW";
	public static final String CAN_EDIT = "CAN_EDIT";
	public static final String CAN_ADD = "CAN_ADD";
	public static final String CAN_DELETE = "CAN_DELETE";

	public static final String GRANT_TYPE_PASSWORD = "custom_password";
	public static final String CLIENT_ID = "kody-client";
	public static final String SECRET_ID = "kody-secret";
	public static final String PAGE_NUMBER = "1";
	public static final String PAGE_SIZE = "5";

	/**
	 * Messages
	 */
	public static final long OTP_VALIDITY_TIME_IN_MIN = 1L;
	public static final String LOGIN_SUCCESS = "login.success";

	public static final boolean EMAIL_USING_GMAIL_OAUTH = true;
	public static final String ACCESS_TOKEN = "X-Auth-Token";

	/**
	 * How many top institute needs to be fetched
	 */
	public static final String INSTITUTE_COUNT = "5";

	/**
	 * How many top Students needs to be fetched from for the institute if not specified if not specified
	 */
	public static final String STUDENT_COUNT = "10";

	/**
	 * Default Question Number to display to the student if not specified
	 */
	public static final String SEQUENCE_NO = "1";

	public static final Date TODAY = new Date();

	public static final long MEDIA_FILE_SIZE = 100000000L;

	/**
	 * Maximum options that can be selected by student
	 */
	public static final int MAXIMUM_OPTION_COUNT = 5;

	/**
	 * Minimum options that can be selected by student
	 */
	public static final int MINIMUM_OPTION_COUNT = 2;

}
