package dk.netarkivet.warclib;

import java.util.HashMap;
import java.util.Map;

/**
 * Class containing all relevant WARC constants and structures. 
 * Including but not limited to field names and mime-types.
 * Also includes not statically initialized structures form validation.  
 * @author nicl
 *
 */
public class WarcConstants {

	/** Warc header start with this string including trailing version information. */
	public static final String WARC_MAGIC_HEADER = "WARC/";

	/** Warc mime type. */
	public static final String WARC_MIME_TYPE = "application/warc";

	public static final int FN_MAX_NUMBER = 19+1;

	public static final int RT_MAX_NUMBER = 8+1;

	/*
	 * Warc field names.
	 */

	public static final String FN_WARC_TYPE = "WARC-Type";
	public static final String FN_WARC_RECORD_ID = "WARC-Record-ID";
	public static final String FN_WARC_DATE = "WARC-Date";
	public static final String FN_CONTENT_LENGTH = "Content-Length";
	public static final String FN_CONTENT_TYPE = "Content-Type";
	public static final String FN_WARC_CONCURRENT_TO = "WARC-Concurrent-To";
	public static final String FN_WARC_BLOCK_DIGEST = "WARC-Block-Digest";
	public static final String FN_WARC_PAYLOAD_DIGEST = "WARC-Payload-Digest";
	public static final String FN_WARC_IP_ADDRESS = "WARC-IP-Address";
	public static final String FN_WARC_REFERS_TO = "WARC-Refers-To";
	public static final String FN_WARC_TARGET_URI = "WARC-Target-URI";
	public static final String FN_WARC_TRUNCATED = "WARC-Truncated";
	public static final String FN_WARC_WARCINFO_ID = "WARC-Warcinfo-ID";
	public static final String FN_WARC_FILENAME = "WARC-Filename";
	public static final String FN_WARC_PROFILE = "WARC-Profile";
	public static final String FN_WARC_IDENTIFIED_PAYLOAD_TYPE = "WARC-Identified-Payload-Type";
	public static final String FN_WARC_SEGMENT_ORIGIN_ID = "WARC-Segment-Origin-ID";
	public static final String FN_WARC_SEGMENT_NUMBER = "WARC-Segment-Number";
	public static final String FN_WARC_SEGMENT_TOTAL_LENGTH = "WARC-Segment-Total-Length";

	public static final String[] FN_IDX_STRINGS = {
		null,
		FN_WARC_TYPE,
		FN_WARC_RECORD_ID,
		FN_WARC_DATE,
		FN_CONTENT_LENGTH,
		FN_CONTENT_TYPE,
		FN_WARC_CONCURRENT_TO,
		FN_WARC_BLOCK_DIGEST,
		FN_WARC_PAYLOAD_DIGEST,
		FN_WARC_IP_ADDRESS,
		FN_WARC_REFERS_TO,
		FN_WARC_TARGET_URI,
		FN_WARC_TRUNCATED,
		FN_WARC_WARCINFO_ID,
		FN_WARC_FILENAME,
		FN_WARC_PROFILE,
		FN_WARC_IDENTIFIED_PAYLOAD_TYPE,
		FN_WARC_SEGMENT_ORIGIN_ID,
		FN_WARC_SEGMENT_NUMBER,
		FN_WARC_SEGMENT_TOTAL_LENGTH
	};

	public static final int FN_IDX_WARC_TYPE = 1;
	public static final int FN_IDX_WARC_RECORD_ID = 2;
	public static final int FN_IDX_WARC_DATE = 3;
	public static final int FN_IDX_CONTENT_LENGTH = 4;
	public static final int FN_IDX_CONTENT_TYPE = 5;
	public static final int FN_IDX_WARC_CONCURRENT_TO = 6;
	public static final int FN_IDX_WARC_BLOCK_DIGEST = 7;
	public static final int FN_IDX_WARC_PAYLOAD_DIGEST = 8;
	public static final int FN_IDX_WARC_IP_ADDRESS = 9;
	public static final int FN_IDX_WARC_REFERS_TO = 10;
	public static final int FN_IDX_WARC_TARGET_URI = 11;
	public static final int FN_IDX_WARC_TRUNCATED = 12;
	public static final int FN_IDX_WARC_WARCINFO_ID = 13;
	public static final int FN_IDX_WARC_FILENAME = 14;					// warcinfo only
	public static final int FN_IDX_WARC_PROFILE = 15;					// revisit only
	public static final int FN_IDX_WARC_IDENTIFIED_PAYLOAD_TYPE = 16;
	public static final int FN_IDX_WARC_SEGMENT_ORIGIN_ID = 17;			// continuation only
	public static final int FN_IDX_WARC_SEGMENT_NUMBER = 18;
	public static final int FN_IDX_WARC_SEGMENT_TOTAL_LENGTH = 19;		//continuation only

	public static final Map<String, Integer> fieldNameIdxMap = new HashMap<String, Integer>();

	static {
		fieldNameIdxMap.put(FN_WARC_TYPE.toLowerCase(), FN_IDX_WARC_TYPE);
		fieldNameIdxMap.put(FN_WARC_RECORD_ID.toLowerCase(), FN_IDX_WARC_RECORD_ID);
		fieldNameIdxMap.put(FN_WARC_DATE.toLowerCase(), FN_IDX_WARC_DATE);
		fieldNameIdxMap.put(FN_CONTENT_LENGTH.toLowerCase(), FN_IDX_CONTENT_LENGTH);
		fieldNameIdxMap.put(FN_CONTENT_TYPE.toLowerCase(), FN_IDX_CONTENT_TYPE);
		fieldNameIdxMap.put(FN_WARC_CONCURRENT_TO.toLowerCase(), FN_IDX_WARC_CONCURRENT_TO);
		fieldNameIdxMap.put(FN_WARC_BLOCK_DIGEST.toLowerCase(), FN_IDX_WARC_BLOCK_DIGEST);
		fieldNameIdxMap.put(FN_WARC_PAYLOAD_DIGEST.toLowerCase(), FN_IDX_WARC_PAYLOAD_DIGEST);
		fieldNameIdxMap.put(FN_WARC_IP_ADDRESS.toLowerCase(), FN_IDX_WARC_IP_ADDRESS);
		fieldNameIdxMap.put(FN_WARC_REFERS_TO.toLowerCase(), FN_IDX_WARC_REFERS_TO);
		fieldNameIdxMap.put(FN_WARC_TARGET_URI.toLowerCase(), FN_IDX_WARC_TARGET_URI);
		fieldNameIdxMap.put(FN_WARC_TRUNCATED.toLowerCase(), FN_IDX_WARC_TRUNCATED);
		fieldNameIdxMap.put(FN_WARC_WARCINFO_ID.toLowerCase(), FN_IDX_WARC_WARCINFO_ID);
		fieldNameIdxMap.put(FN_WARC_FILENAME.toLowerCase(), FN_IDX_WARC_FILENAME);
		fieldNameIdxMap.put(FN_WARC_PROFILE.toLowerCase(), FN_IDX_WARC_PROFILE);
		fieldNameIdxMap.put(FN_WARC_IDENTIFIED_PAYLOAD_TYPE.toLowerCase(), FN_IDX_WARC_IDENTIFIED_PAYLOAD_TYPE);
		fieldNameIdxMap.put(FN_WARC_SEGMENT_ORIGIN_ID.toLowerCase(), FN_IDX_WARC_SEGMENT_ORIGIN_ID);
		fieldNameIdxMap.put(FN_WARC_SEGMENT_NUMBER.toLowerCase(), FN_IDX_WARC_SEGMENT_NUMBER);
		fieldNameIdxMap.put(FN_WARC_SEGMENT_TOTAL_LENGTH.toLowerCase(), FN_IDX_WARC_SEGMENT_TOTAL_LENGTH);
	}

	public static final boolean[] fieldNamesRepeatableLookup = new boolean[FN_MAX_NUMBER];

	static {
		fieldNamesRepeatableLookup[FN_IDX_WARC_CONCURRENT_TO] = true;
	}

	/*
	 * Warc record types.
	 */

	public static final String RT_WARCINFO = "warcinfo";
	public static final String RT_RESPONSE = "response";
	public static final String RT_RESOURCE = "resource";
	public static final String RT_REQUEST = "request";
	public static final String RT_METADATA = "metadata";
	public static final String RT_REVISIT = "revisit";
	public static final String RT_CONVERSION = "conversion";
	public static final String RT_CONTINUATION = "continuation";

	public static final int RT_IDX_UNKNOWN = 0;
	public static final int RT_IDX_WARCINFO = 1;
	public static final int RT_IDX_RESPONSE = 2;
	public static final int RT_IDX_RESOURCE = 3;
	public static final int RT_IDX_REQUEST = 4;
	public static final int RT_IDX_METADATA = 5;
	public static final int RT_IDX_REVISIT = 6;
	public static final int RT_IDX_CONVERSION = 7;
	public static final int RT_IDX_CONTINUATION = 8;

	public static final Map<String, Integer> recordTypeIdxMap = new HashMap<String, Integer>();

	static {
		recordTypeIdxMap.put(RT_WARCINFO.toLowerCase(), RT_IDX_WARCINFO);
		recordTypeIdxMap.put(RT_RESPONSE.toLowerCase(), RT_IDX_RESPONSE);
		recordTypeIdxMap.put(RT_RESOURCE.toLowerCase(), RT_IDX_RESOURCE);
		recordTypeIdxMap.put(RT_REQUEST.toLowerCase(), RT_IDX_REQUEST);
		recordTypeIdxMap.put(RT_METADATA.toLowerCase(), RT_IDX_METADATA);
		recordTypeIdxMap.put(RT_REVISIT.toLowerCase(), RT_IDX_REVISIT);
		recordTypeIdxMap.put(RT_CONVERSION.toLowerCase(), RT_IDX_CONVERSION);
		recordTypeIdxMap.put(RT_CONTINUATION.toLowerCase(), RT_IDX_CONTINUATION);
	}

	public static final String TT_LENGTH = "length";
	public static final String TT_TIME = "time";
	public static final String TT_DISCONNECT = "disconnect";
	public static final String TT_UNSPECIFIED = "unspecified";

	public static final int TT_IDX_FUTURE_REASON = 0;
	public static final int TT_IDX_LENGTH = 1;
	public static final int TT_IDX_TIME = 2;
	public static final int TT_IDX_DISCONNECT = 3;
	public static final int TT_IDX_UNSPECIFIED = 4;

	public static final Map<String, Integer> truncatedTypeIdxMap = new HashMap<String, Integer>();

	static {
		truncatedTypeIdxMap.put(TT_LENGTH.toLowerCase(), TT_IDX_LENGTH);
		truncatedTypeIdxMap.put(TT_TIME.toLowerCase(), TT_IDX_TIME);
		truncatedTypeIdxMap.put(TT_DISCONNECT.toLowerCase(), TT_IDX_DISCONNECT);
		truncatedTypeIdxMap.put(TT_UNSPECIFIED.toLowerCase(), TT_IDX_UNSPECIFIED);
	}

	/*
	 * Warc revisit profiles.
	 */

	public static final String PROFILE_IDENTICAL_PAYLOAD_DIGEST =
			"http://netpreserve.org/warc/1.0/revisit/identical-payload-digest";

	public static final String PROFILE_SERVER_NOT_MODIFIED =
			"http://netpreserve.org/warc/1.0/revisit/server-not-modified";

	public static final int PROFILE_IDX_UNKNOWN = 0;
	public static final int PROFILE_IDX_IDENTICAL_PAYLOAD_DIGEST = 1;
	public static final int PROFILE_IDX_SERVER_NOT_MODIFIED = 2;

	public static final Map<String, Integer> profileIdxMap = new HashMap<String, Integer>();

	static {
		profileIdxMap.put(PROFILE_IDENTICAL_PAYLOAD_DIGEST.toLowerCase(),
				PROFILE_IDX_IDENTICAL_PAYLOAD_DIGEST);
		profileIdxMap.put(PROFILE_SERVER_NOT_MODIFIED.toLowerCase(),
				PROFILE_IDX_SERVER_NOT_MODIFIED);
	}

	/*
	 * Warc content types.
	 */

	public static final String CT_APP_WARC_FIELDS = "application/warc-fields";

	/*
	 * Field validation.
	 */

	public static final int POLICY_IGNORE = 0;
	public static final int POLICY_MANDATORY = 1;
	public static final int POLICY_SHALL = 2;
	public static final int POLICY_SHALL_NOT = 3;
	public static final int POLICY_MAY = 4;
	public static final int POLICY_MAY_NOT = 5;

	public static final int[][] field_policy;

	static {
		field_policy = new int[RT_MAX_NUMBER][FN_MAX_NUMBER];

		// Warc-Record-id
		// Warc-Type
		// Warc-Date
		// Content-Length
		// Also required for unknown warc-types.
		for (int i=0; i<=8; ++i) {
			field_policy[i][FN_IDX_WARC_RECORD_ID] = POLICY_MANDATORY;
			field_policy[i][FN_IDX_WARC_TYPE] = POLICY_MANDATORY;
			field_policy[i][FN_IDX_WARC_DATE] = POLICY_MANDATORY;
			field_policy[i][FN_IDX_CONTENT_LENGTH] = POLICY_MANDATORY;
		}

		// Content-Type
		field_policy[RT_IDX_CONTINUATION][FN_IDX_CONTENT_TYPE] = POLICY_SHALL_NOT;

		// Warc-Ip-Address
		field_policy[RT_IDX_REQUEST][FN_IDX_WARC_IP_ADDRESS] = POLICY_MAY;
		field_policy[RT_IDX_RESPONSE][FN_IDX_WARC_IP_ADDRESS] = POLICY_MAY;
		field_policy[RT_IDX_RESOURCE][FN_IDX_WARC_IP_ADDRESS] = POLICY_MAY;
		field_policy[RT_IDX_METADATA][FN_IDX_WARC_IP_ADDRESS] = POLICY_MAY;
		field_policy[RT_IDX_REVISIT][FN_IDX_WARC_IP_ADDRESS] = POLICY_MAY;
		field_policy[RT_IDX_WARCINFO][FN_IDX_WARC_IP_ADDRESS] = POLICY_SHALL_NOT;
		field_policy[RT_IDX_CONVERSION][FN_IDX_WARC_IP_ADDRESS] = POLICY_SHALL_NOT;
		field_policy[RT_IDX_CONTINUATION][FN_IDX_WARC_IP_ADDRESS] = POLICY_SHALL_NOT;

		// Warc-Concurrent-To
		field_policy[RT_IDX_REQUEST][FN_IDX_WARC_CONCURRENT_TO] = POLICY_MAY;
		field_policy[RT_IDX_RESPONSE][FN_IDX_WARC_CONCURRENT_TO] = POLICY_MAY;
		field_policy[RT_IDX_RESOURCE][FN_IDX_WARC_CONCURRENT_TO] = POLICY_MAY;
		field_policy[RT_IDX_METADATA][FN_IDX_WARC_CONCURRENT_TO] = POLICY_MAY;
		field_policy[RT_IDX_REVISIT][FN_IDX_WARC_CONCURRENT_TO] = POLICY_MAY;
		field_policy[RT_IDX_WARCINFO][FN_IDX_WARC_CONCURRENT_TO] = POLICY_SHALL_NOT;
		field_policy[RT_IDX_CONVERSION][FN_IDX_WARC_CONCURRENT_TO] = POLICY_SHALL_NOT;
		field_policy[RT_IDX_CONTINUATION][FN_IDX_WARC_CONCURRENT_TO] = POLICY_SHALL_NOT;

		// Warc-Refers-To
		field_policy[RT_IDX_METADATA][FN_IDX_WARC_REFERS_TO] = POLICY_MAY;
		field_policy[RT_IDX_CONVERSION][FN_IDX_WARC_REFERS_TO] = POLICY_MAY;
		field_policy[RT_IDX_REVISIT][FN_IDX_WARC_REFERS_TO] = POLICY_MAY;
		field_policy[RT_IDX_WARCINFO][FN_IDX_WARC_REFERS_TO] = POLICY_SHALL_NOT;
		field_policy[RT_IDX_REQUEST][FN_IDX_WARC_REFERS_TO] = POLICY_SHALL_NOT;
		field_policy[RT_IDX_RESPONSE][FN_IDX_WARC_REFERS_TO] = POLICY_SHALL_NOT;
		field_policy[RT_IDX_RESOURCE][FN_IDX_WARC_REFERS_TO] = POLICY_SHALL_NOT;
		field_policy[RT_IDX_CONTINUATION][FN_IDX_WARC_REFERS_TO] = POLICY_SHALL_NOT;

		// Warc-Target-Uri
		field_policy[RT_IDX_REQUEST][FN_IDX_WARC_TARGET_URI] = POLICY_SHALL;
		field_policy[RT_IDX_RESPONSE][FN_IDX_WARC_TARGET_URI] = POLICY_SHALL;
		field_policy[RT_IDX_RESOURCE][FN_IDX_WARC_TARGET_URI] = POLICY_SHALL;
		field_policy[RT_IDX_CONVERSION][FN_IDX_WARC_TARGET_URI] = POLICY_SHALL;
		field_policy[RT_IDX_CONTINUATION][FN_IDX_WARC_TARGET_URI] = POLICY_SHALL;
		field_policy[RT_IDX_REVISIT][FN_IDX_WARC_TARGET_URI] = POLICY_SHALL;
		field_policy[RT_IDX_METADATA][FN_IDX_WARC_TARGET_URI] = POLICY_MAY;
		field_policy[RT_IDX_WARCINFO][FN_IDX_WARC_TARGET_URI] = POLICY_SHALL_NOT;

		// Warc-Warcinfo-Id
		// Warc-Filename
		// Warc-Profile
		// Warc-Segment-Origin-Id
		// Warc-Segment-Total-Length
		for (int i=1; i<=8; ++i) {
			field_policy[i][FN_IDX_WARC_WARCINFO_ID] = POLICY_MAY;
			field_policy[i][FN_IDX_WARC_FILENAME] = POLICY_SHALL_NOT;
			field_policy[i][FN_IDX_WARC_PROFILE] = POLICY_IGNORE;
			field_policy[i][FN_IDX_WARC_SEGMENT_ORIGIN_ID] = POLICY_SHALL_NOT;
			field_policy[i][FN_IDX_WARC_SEGMENT_ORIGIN_ID] = POLICY_SHALL_NOT;
		}
		field_policy[RT_IDX_WARCINFO][FN_IDX_WARC_WARCINFO_ID] = POLICY_MAY_NOT;
		field_policy[RT_IDX_WARCINFO][FN_IDX_WARC_FILENAME] = POLICY_MAY;
		field_policy[RT_IDX_REVISIT][FN_IDX_WARC_PROFILE] = POLICY_MANDATORY;
		field_policy[RT_IDX_CONTINUATION][FN_IDX_WARC_SEGMENT_ORIGIN_ID] = POLICY_MANDATORY;

		// Warc-Segment-Number
		field_policy[RT_IDX_CONTINUATION][FN_IDX_WARC_SEGMENT_NUMBER] = POLICY_MANDATORY;
	}

	//"text/dns"
	//"application/http;msgtype=request"
	//"application/http;msgtype=response"

	/*
	WARC-Type
	WARC-Record-ID
	WARC-Date
	Content-Length
	Content-Type
	WARC-Concurrent-To
	WARC-Block-Digest
	WARC-Payload-Digest
	WARC-IP-Address
	WARC-Refers-To
	WARC-Target-URI
	WARC-Truncated
	WARC-Warcinfo-ID
	WARC-Filename
	WARC-Profile
	WARC-Identified-Payload-Type
	WARC-Segment-Origin-ID
	WARC-Segment-Number
	WARC-Segment-Total-Length
	*/

	//'warcinfo', 'response', 'resource', 'request', 'metadata', 'revisit', 'conversion', 'continuation'

	/**
	 * This utility class does not require instantiation.
	 */
	private WarcConstants() {
	}

}