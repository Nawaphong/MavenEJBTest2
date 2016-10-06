package th.go.dlt.utils.string;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.IllegalFormatFlagsException;
import java.util.List;

public class MyUtils {
	public static final String EMPTY_STRING = "";
	public static final String SPACE_STRING = " ";
	public static final String COMMA = ",";
	public static final String DASH = "-";
	public static final Character SCORE = '-';
	public static final String EMPTY_STRING_FOR_SET_NULL_TO_ENTITY_BECAUSE_IGNORE_UPDATE_TOTABLE = "";
	private static final Character ZERO_CHAR = '0';
	public static final String EXPRESSION_LANGUAGE = "[^๐-๙ก-ฮA-Za-z0-9 ]";
	public static final String NUMBODY_ALIAS_PATTERN = "[^A-Za-z0-9]";
	public static final String APPLET_ESCAPE_CHARACTER = "[$฿|~]";
	public static final String EXPRESSION_CHECK_THAI_CHARACTOR_COLUMN = "[ัิ-ฺ็-๎]";
	public static final String GET = "get";

	public static final String BETONG_OFF_LOC_CODE = "90501";

	public static boolean isBlankOrNull(String value) {
		return value == null || value.isEmpty();
	}

	public static String getPadZeroLeft(String source, Integer targetLength) {
		// System.out.println(String.format("%05d", 123));
		if (!isBlankOrNull(source)) {
			Integer sourceLength = source.length();
			StringBuilder target = new StringBuilder();
			target.append(source);
			for (int i = sourceLength; i < targetLength; i++) {
				target.insert(0, ZERO_CHAR);
			}
			return target.toString();
		} else {
			return EMPTY_STRING;
		}
	}

	public static String getPadSpaceLeft(String source, Integer targetLength) {
		try {
			StringBuilder sb = new StringBuilder(targetLength);
			for (int i = source.length(); i < targetLength; i++) {
				sb.append(SPACE_STRING);
			}
			sb.append(source);
			return sb.toString();
		} catch (Exception ex) {
			return EMPTY_STRING;
		}
	}

	public static String getPadSpaceRight(String source, Integer targetLength) {
		try {
			StringBuilder sb = new StringBuilder(targetLength);
			sb.append(source);
			for (int i = source.length(); i < targetLength; i++) {
				sb.append(SPACE_STRING);
			}
			return sb.toString();
		} catch (Exception ex) {
			return EMPTY_STRING;
		}
	}

	/**
	 * pad space left by number
	 * 
	 * @param source
	 * @param number
	 * @return
	 */
	public static String getPadSpaceLeftNumber(String source, Integer number) {
		try {
			StringBuilder sb = new StringBuilder(number + source.length());
			for (int i = 0; i < number; i++) {
				sb.append(SPACE_STRING);
			}
			sb.append(source);
			return sb.toString();
		} catch (Exception ex) {
			return EMPTY_STRING;
		}
	}

	/**
	 * pad space right by number
	 * 
	 * @param source
	 * @param number
	 * @return
	 */
	public static String getPadSpaceRightNumber(String source, Integer number) {
		try {
			StringBuilder sb = new StringBuilder(number + source.length());
			sb.append(source);
			for (int i = 0; i < number; i++) {
				sb.append(SPACE_STRING);
			}
			return sb.toString();
		} catch (Exception ex) {
			return EMPTY_STRING;
		}
	}

	/**
	 * ต่อช่องวางตามตัวเลขที่โยนเข้ามา
	 * เช่น getPadSpace(5) จะได้ ช่องว่าง 5 ช่อง ออกไป
	 * 
	 * @param targetLength
	 * @return
	 */
	public static String getPadSpace(Integer targetLength) {
		if (targetLength < 0) {
			return EMPTY_STRING;
		}
		StringBuilder sb = new StringBuilder(targetLength);
		for (int i = 0; i < targetLength; i++) {
			sb.append(SPACE_STRING);
		}
		return sb.toString();
	}

	public static String getPadZeroRight(String source, Integer targetLength) {
		if (!isBlankOrNull(source)) {
			Integer sourceLength = source.length();
			StringBuilder target = new StringBuilder();
			target.append(source);
			for (int i = sourceLength; i < targetLength; i++) {
				target.append(ZERO_CHAR);
			}
			return target.toString();
		} else {
			return EMPTY_STRING;
		}
	}

	/**
	 * ใช้สำหรับ ต่อ คำนำหน้า + ชื่อ + นามสกุล ของบุคคล เข้าด้วยกัน
	 * และคืนค่ากลับไปเป็น ชื่อเต็ม
	 * 
	 * @param title
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	public static String getPersonFullName(String title, String firstName, String lastName) {
		if (title == null)
			title = "";
		if (firstName == null)
			firstName = "";
		if (lastName == null)
			lastName = "";
		String fullName = title + "" + firstName + " " + lastName;
		return fullName;
	}

	public static String addSpace(Integer number) {
		String spaceString = "";
		for (int i = 0; i < number; i++) {
			spaceString += " ";
		}

		return spaceString;
	}

	public static String concatList(List<String> list, String separator) {
		String result = "";
		int index = 1;

		for (String temp : list) {
			result += temp;
			if (index != list.size()) {
				result += separator;
			}
			index++;
		}

		return result;
	}

	public static String concatList(List<String> list) {
		return concatList(list, COMMA);
	}

	public static String grepAlphaNumeric(String text) {
		return text.replaceAll(EXPRESSION_LANGUAGE, "");
	}

	/**
	 * 
	 * @param source
	 * @param targetLength
	 *            must > 0
	 * @return
	 */
	public static String getPadZeroLeft(int source, int targetLength) {
		String oResult = String.format("%0" + targetLength + "d", source);
		return oResult;
	}

	/**
	 * function สำหรับแปลงค่าจาก character เป็น unicode
	 * 
	 * @param ch
	 * @return
	 */
	public static String stringToUnicode(char ch) {
		if (ch < 0x10) {
			return "\\u000" + Integer.toHexString(ch);
		} else if (ch < 0x100) {
			return "\\u00" + Integer.toHexString(ch);
		} else if (ch < 0x1000) {
			return "\\u0" + Integer.toHexString(ch);
		}
		return "\\u" + Integer.toHexString(ch);
	}

	/**
	 * function สำหรับแปลงค่าจากunicode เป็น character
	 * 
	 * @param unicode
	 * @return
	 */
	public static char unicodeToString(String unicode) {

		Integer code = Integer.parseInt(unicode.substring(2), 16); // the integer 65 in base 10
		char ch = Character.toChars(code)[0]; // the letter 'A'
		return ch;
	}

	/**
	 * function สำหรับ เช็ค isBlankOrNull หลายๆค่าพร้อมกัน
	 * ถ้าเจอ Blank หรือ null ตัวเดียวก็จะ return true และหลุดเลย(เหมือน Short-Circuit)
	 * 
	 * @param String
	 *            ... ที่ต้องการตรวจสอบ
	 * @return boolean ผลการตรวจสอบ
	 */
	public static boolean isBlankOrNull(String... params) {
		for (String param : params) {
			if (isBlankOrNull(param)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * function สำหรับ เช็ค String หลายๆค่าพร้อมกัน
	 * ถ้าเจอ String ที่ไม่ Blank หรือไม่ null ตัวเดียวก็จะ return true และหลุดเลย(เหมือน Short-Circuit)
	 * 
	 * @param String
	 *            ... ที่ต้องการตรวจสอบ
	 * @return boolean ผลการตรวจสอบ
	 */
	public static boolean isNotBlankOrNotNull(String... params) {
		for (String param : params) {
			if (!isBlankOrNull(param)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * function สำหรับ เช็ค String หลายๆค่าพร้อมกัน โดยต้อง BlankOrNull ทั้งหมด
	 * ถ้าเจอ String ที่ไม่ Blank หรือไม่ null ตัวเดียวก็จะ return true และหลุดเลย(เหมือน Short-Circuit)
	 * 
	 * @param String
	 *            ... ที่ต้องการตรวจสอบ
	 * @return boolean ผลการตรวจสอบ
	 */
	public static boolean isBlankOrNullAll(String... params) {
		for (String param : params) {
			if (!isBlankOrNull(param)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * function สำหรับ เช็ค String หลายๆค่าพร้อมกัน โดยต้อง NotBlankOrNotNull ทั้งหมด
	 * ถ้าเจอ String ที่ Blank หรือ null ตัวเดียวก็จะ return false และหลุดเลย(เหมือน Short-Circuit)
	 * 
	 * @param String
	 *            ... ที่ต้องการตรวจสอบ
	 * @return boolean ผลการตรวจสอบ
	 */
	public static boolean isNotBlankOrNotNullAll(String... params) {
		for (String param : params) {
			if (isBlankOrNull(param)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * function สำหรับ เช็ค equals String หลายๆค่าพร้อมกัน
	 * ถ้าเจอ String ที่ equals ตัวเดียวก็จะ return true และหลุดเลย(เหมือน Short-Circuit)
	 * 
	 * @param String
	 *            (ตัวแรก) ค่าหลักที่ต้องการตรวจสอบ
	 * @param String
	 *            ...(ตัวสอง) ค่าที่จะนำมาตรวจสอบ
	 * @return boolean ผลการตรวจสอบ
	 */
	public static boolean equalIn(String str, String... params) {
		if (str != null) {
			for (String param : params) {
				if (str.equals(param)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * function สำหรับ เช็ค not equals String หลายๆค่าพร้อมกัน
	 * ถ้าเจอ String ที่ equals ตัวเดียวก็จะ return false และหลุดเลย(เหมือน Short-Circuit)
	 * 
	 * @param String
	 *            (ตัวแรก) ค่าหลักที่ต้องการตรวจสอบ
	 * @param String
	 *            ...(ตัวสอง) ค่าที่จะนำมาตรวจสอบ
	 * @return boolean ผลการตรวจสอบ
	 */
	public static boolean notEqualIn(String str, String... params) {
		return !equalIn(str, params);
	}

	/**
	 * ตรวจสอบว่า String นั้นเป็นตัวเลขหรือเปล่า
	 * 
	 * @param string
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static boolean isNumber(String string) throws IllegalArgumentException {
		if (string != null && !string.equals("")) {
			char chars[] = string.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				if (!Character.isDigit(chars[i])) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * ตรวจสอบว่า String นั้นเป็นตัวเลขหรือเปล่า โดยรวมเครื่องหมาย + - ด้วย
	 * 
	 * @param string
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static boolean isNumberIncludeSignificant(String string) throws IllegalArgumentException {
		if (string != null && !string.equals("")) {
			char chars[] = string.toCharArray();
			// verify first char is a Significant
			if ('+' == chars[0] || '-' == chars[0]) {
				chars[0] = '0'; // right dummy number
			}
			for (int i = 0; i < chars.length; i++) {
				if (!Character.isDigit(chars[i])) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * ค้นหาตัวอักษรตัวสุดท้ายที่อยู่ใน string
	 * 
	 * @param string
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static int lastIndexOfAlphabet(String string) throws IllegalArgumentException {
		if (string != null && !string.equals("")) {
			char chars[] = string.toCharArray();
			for (int i = chars.length - 1; i >= 0; i--) {
				if (!Character.isDigit(chars[i])) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * ค้นหาตัดอักษรด้านซ้าย
	 * 
	 * @param String
	 *            , String
	 * @return String
	 */
	public static String ltrim(String source, String target) {
		if (MyUtils.isBlankOrNull(source) || MyUtils.isBlankOrNull(target)) {
			return source;
		} else {
			source = source.trim();
			char[] c = source.toCharArray();
			int index = 0;
			for (int i = 0; i < c.length; i++) {
				if (c[i] == target.charAt(0)) {
					index += 1;
				} else {
					break;
				}
			}
			return String.valueOf(c).substring(index, c.length);
		}
	}

	/**
	 * ทำการแปลง list<String> ให้เป็น String ตัวอย่างหลังการแปลงจาก List["s1","s2","s3"] , separator : ',' --> "s1,s2,s3"
	 * 
	 * @param List
	 *            <String>,char
	 * @return String
	 */
	public static String listStringToString(List<String> list, char separator) {
		StringBuilder sb = new StringBuilder();
		if (list != null && list.size() > 0) {
			boolean isFirst = true;
			for (String str : list) {
				if (str != null) {
					if (!isFirst) {
						sb.append(separator);
					} else {
						isFirst = false;
					}
				}
				sb.append(str);
			}
		}
		return sb.toString();
	}

	/**
	 * ทำการแปลง BigDecimal เป็น String ตาม format ที่ส่งมา (รูปแบบ format "99,999,999.99" หรือ "99,999,999")
	 * 
	 * @param BigDecimal
	 *            , String
	 * @return String
	 * @throws IllegalFormatFlagsException
	 */
	public static String bigDecimalToStringFormat(BigDecimal number, String format) {
		if (number != null && !isBlankOrNull(format)) {
			// แปลง format ให้อยู่ในรูปแบบที่สามารถใช้งานกับ class DecimalFormat ได้ เช่น "99,999,999.00" เป็น "##,###,###.00"
			if (format.indexOf('.') != -1) {
				// format มี ทศนิยม
				String[] formats = format.split("\\.");
				if (formats.length == 2) {
					StringBuilder formatBuilder = new StringBuilder();
					formatBuilder.append(formats[0].replaceAll("[0-9]", "#")).append(".").append(formats[1].replaceAll("[0-9]", "0"));
					format = formatBuilder.toString();
				} else {
					throw new IllegalFormatFlagsException(format);
				}
			} else {
				// format ไม่มี ทศนิยม
				format = format.replaceAll("[0-9]", "#");
			}
			// จัด format ให้ BigDecimal
			DecimalFormat decimalFormat = new DecimalFormat(format);
			return decimalFormat.format(number);
		} else {
			return "";
		}
	}

	/**
	 * ทำการนับ String โดยไม่นับอักขระพิเศษ ของภาษาไทย
	 * ไม่นับ 'ั', 'ิ','ี','ึ','ื','ุ','ู','็','่','้','๊','๋','์','ํ','ฺ'
	 * 
	 * @param String
	 *            ข้อความที่ต้องการนับ
	 * @return int length ของ String ที่ไม่รวม อักขระพิเศษ
	 */
	public static int countAliasLength(String str) {
		int strLen = str.length();
		int specialChLen = 0;
		for (int i = 0; i < strLen; i++) {
			int inputCh = (int) str.charAt(i);
			if ((inputCh > 3635 && inputCh < 3643) || (inputCh > 3654 && inputCh < 3662) || inputCh == 3633) {
				specialChLen++;
			}
		}
		return strLen - specialChLen;
	}

	public static String bytesToHex(byte[] bytes) {
		final StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b));
		}

		return sb.toString();
	}

	/**
	 * เพิ่ม format ของเลขบัตรประจำตัวประชาชน
	 * 
	 * @param String
	 *            เลขบัตรประชาชน ในรูปแบบไม่มี (-) ขั้น เช่น 1122399477443
	 * @return เลขบัตร ใน format #-####-#####-##-##
	 */
	public static String getFormatThaiNationId(String nationId) {

		if (nationId == null || nationId.length() != 13) {
			return null;
		}

		StringBuilder sb = new StringBuilder(17);
		int j = 0;
		for (int i = 0; i < 17; i++) {
			if (i == 1 || i == 6 || i == 12 || i == 15) {
				sb.append(DASH);
			} else {
				sb.append(nationId.substring(j, j + 1));

				j++;
			}
		}
		return sb.toString();
	}

	/**
	 * แปลง format ของเลขตัวรถ
	 * 
	 * @param String
	 *            เลขตัวรถ nd125m-0092392
	 * @return String เลขตัวรถ ND125M0092392
	 */
	public static String getNumBodyAlias(String source) {
		try {
			if (!MyUtils.isBlankOrNull(source)) {
				return source.replaceAll(NUMBODY_ALIAS_PATTERN, EMPTY_STRING).toUpperCase();
			} else {
				return source;
			}
		} catch (Exception ex) {
			return source;
		}
	}

	/**
	 * แปลง ข้อมูลสำหรับส่งไปให้appletใช้
	 * 
	 * @param String
	 * @return String
	 */
	public static String getEscapeAppletCommand(String source) {
		try {
			if (!MyUtils.isBlankOrNull(source)) {
				return source.replaceAll(APPLET_ESCAPE_CHARACTER, EMPTY_STRING);
			} else {
				return source;
			}
		} catch (Exception ex) {
			return source;
		}
	}

	/*
	 * แปลงตัวพิมพ์ใหญ่เฉพาะตัวแรก
	 */
	public static String firstLetterToUpperCase(String messages) {
		return Character.toUpperCase(messages.charAt(0)) + messages.substring(1);
	}

	/**
	 * แปลง ข้อมูลโดยเอาสระและวรรณยุกค์ที่ไม่อยู่ในแนวตัวอักษรออก เช่น ่ ิ ี
	 * 
	 * @param String
	 *            stringที่มีสระและวรรณยุกค์ไทย
	 * @return String String ที่เอาวรรณยุกค์ออก
	 */
	public static String getThaiCharacterColumnString(String source) {
		try {
			if (!MyUtils.isBlankOrNull(source)) {
				return source.replaceAll(EXPRESSION_CHECK_THAI_CHARACTOR_COLUMN, EMPTY_STRING);
			} else {
				return source;
			}
		} catch (Exception ex) {
			return source;
		}
	}

	/**
	 * format เลขบัตรประชาชน
	 * 
	 * @param String
	 *            เลขบัตรที่ไม่ใส่ format
	 * @return String เลขบัตรที่มี format -
	 */
	public static String formatIdCard(String idCard) {
		if (isBlankOrNull(idCard) || idCard.length() != 13) {
			return idCard;
		}
		StringBuilder result = new StringBuilder(idCard);
		result.insert(1, "-");
		result.insert(6, "-");
		result.insert(12, "-");
		result.insert(15, "-");
		return result.toString();
	}

	/**
	 * เอา format เลขบัตรประชาชนออก
	 * 
	 * @param String
	 *            เลขบัตรที่มี format
	 * @return String เลขบัตรที่ไม่มี format -
	 */
	public static String noFormatIdCard(String idCard) {
		if (isBlankOrNull(idCard) || idCard.length() != 17) {
			return idCard;
		}
		return idCard.replaceAll("[-_]", "");
	}

	/**
	 * ดึงชื่อระบบจากชื่อตาราง
	 * 
	 * @param String
	 *            ชื่อตาราง
	 * @return String ชื่อระบบ
	 */
	public static String getSystemNameFromTable(String tableName) {
		if (MyUtils.isBlankOrNull(tableName)) {
			return null;
		}
		String[] textSplit = tableName.toUpperCase().split("_");
		if (textSplit != null && textSplit.length > 0 && !MyUtils.isBlankOrNull(textSplit[0])) {
			return textSplit[0].substring(0, 3);
		}
		return tableName;
	}

	/**
	 * ดึงชื่อตารางจากชื่อ detailKey
	 * 
	 * @param String
	 *            detailKey
	 * @return String table
	 */
	public static String getTableFromDetailKey(String detailKey) throws Exception {
		if (MyUtils.isBlankOrNull(detailKey)) {
			return null;
		}
		String[] values = detailKey.toUpperCase().split(":");
		if (values != null && values.length != 3) {
			return null;
		}
		return values[0]; // ชื่อ table
	}

	/**
	 * คืนoffLocCode ของสำนักงานระดับจังหวัด
	 * 
	 * @param String
	 *            offLocCode ที่ต้องการหา offLocCode ของสำนักงานระดับจังหวัด
	 * @return String offLocCode ของสำนักงานระดับจังหวัด
	 */
	public static String getHeadOffLocCode(String offLocCode) throws Exception {
		if (MyUtils.isBlankOrNull(offLocCode)) {
			return offLocCode;
		}
		StringBuilder headOffLocCode = new StringBuilder();
		if (BETONG_OFF_LOC_CODE.compareTo(offLocCode) != 0) {
			headOffLocCode.append(offLocCode.substring(0, 3));
			headOffLocCode.append("00");
		} else {
			return offLocCode;
		}
		return headOffLocCode.toString();
	}

	public static String subStringThaiColumn(String str, int length) throws Exception {
		if (isBlankOrNull(str)) {
			return "";
		}
		String chkStr = getThaiCharacterColumnString(str);
		if (chkStr.length() <= length) {
			return str;
		}
		int x = 0;
		int y = 0;
		while (y < length + 1) {
			while (chkStr.charAt(y) != str.charAt(x)) {
				x++;
			}
			if (chkStr.charAt(y) == str.charAt(x)) {
				x++;
				y++;
			}
		}
		return str.substring(0, x - 1);
	}

	/**
	 * จัดการกับ string ที่เป็น ภาษาไทย ใช้ในการตัดstring เอาไปใช้วางใน column ตัวอักษรของ passbook
	 * 
	 * @param source
	 *            string ต้นแบบ
	 * @param start
	 *            column ตัวอักษรเริ่มต้น
	 * @param end
	 *            column ตัวอักษรสิ้นสุด
	 * @return string ผลลัพธ์ ถ้า ช่วงห่างของstart ถึง end มากกว่า source จะเติม ' ' ด้านหน้าString
	 * @throws Exception
	 */
	public static String makeStringForColumnLPad(String source, int start, int end) throws Exception {
		if (end <= start) {
			return "";
		} else {
			int length = end - start;
			if(isBlankOrNull(source)){
				return getPadSpace(length);
			}
			String chkStr = getThaiCharacterColumnString(source);
			if (chkStr.length() == length) {
				return source;
			} else if (chkStr.length() > length) {
				return subStringThaiColumn(source, length);
			} else {
				return getPadSpaceLeftNumber(source, length - chkStr.length());
			}
		}

	}

	/**
	 * จัดการกับ string ที่เป็น ภาษาไทย ใช้ในการตัดstring เอาไปใช้วางใน column ตัวอักษรของ passbook
	 * 
	 * @param source
	 *            string ต้นแบบ
	 * @param start
	 *            column ตัวอักษรเริ่มต้น
	 * @param end
	 *            column ตัวอักษรสิ้นสุด
	 * @return string ผลลัพธ์ ถ้า ช่วงห่างของstart ถึง end มากกว่า source จะเติม ' ' ด้านหลังString
	 * @throws Exception
	 */
	public static String makeStringForColumnRPad(String source, int start, int end) throws Exception {
		
		if (end <= start) {
			return "";
		} else {
			int length = end - start;
			if(isBlankOrNull(source)){
				return getPadSpace(length);
			}
			String chkStr = getThaiCharacterColumnString(source);
			if (chkStr.length() == length) {
				return source;
			} else if (chkStr.length() > length) {
				return subStringThaiColumn(source, length);
			} else {
				return getPadSpaceRightNumber(source, length - chkStr.length());
			}
		}

	}

	/**
	 * คำนวนจำนวนช่องว่างที่ต้องเติมใน string
	 * 
	 * @param source
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public static int calSpaceForColumn(String source, int start, int end) throws Exception {
		if (end <= start) {
			return 0;
		}
		int length = end - start;
		int spaceLenght = length - getThaiCharacterColumnString(source).length();
		if (spaceLenght < 0) {
			spaceLenght = 0;
		}
		return spaceLenght;
	}

	/**
	 * ใช้ pad zero สำหรับจัด format ให้กับ SerlNo
	 * เช่น input : 12-3
	 * output : 12-0000003
	 * 
	 * @param source
	 * @param targetLength
	 * @return String
	 */
	public static String getPadZeroForSerlNo(String source, int targetLength) {
		if (!isBlankOrNull(source)) {
			String font = null;
			String back = null;
			if (source.indexOf(SCORE) > 0) {
				font = source.substring(0, 3);
				back = source.substring(3);
			} else {
				font = source.substring(0, 2);
				back = source.substring(2);
			}
			int loop = targetLength - (font.length() + back.length());

			StringBuilder target = new StringBuilder();
			target.append(back);
			{
				for (int i = 0; i < loop; i++) {
					target.insert(0, ZERO_CHAR);
				}
			}
			target.insert(0, font);
			return target.toString();
		} else {
			return MyUtils.EMPTY_STRING;
		}
	}

}
