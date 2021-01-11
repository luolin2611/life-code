package cn.lifecode.backmusic.comm;

public class UUID {
	/**
	 * @desc Generate UUID
	 * @author rollin
	 * @time 2018-10-10 11:48:01
	 * @param str
	 *            str[0] Y Express remove '-' N Express not remove '-', str[1]
	 *            number express get Num digits str[1] value <
	 */
	public static String getUUID(String... str) {
		String uuid = java.util.UUID.randomUUID().toString();
		if (str.length < 1) {
			return uuid;
		}
		if (str[0] != null && str[0].equals("Y")) {
			// remove '-'
			uuid = uuid.replace("-", "");
			if (str.length > 1 && str[1] != null) {
				int dig = Integer.parseInt(str[1]);
				uuid = generateUUID(dig, true);
			}
		}

		if (str[0] != null && str[0].equals("N")) {
			if (str.length > 1 && str[1] != null) {
				int dig = Integer.parseInt(str[1]);
				uuid = generateUUID(dig, false);
			}
		}
		return uuid;
	}

	private static String generateUUID(int digs,boolean removeSymbol){
		String tempUUID = removeSymbol ? java.util.UUID.randomUUID().toString().replace("-", "") : java.util.UUID.randomUUID().toString();
		String retStr = "";
		if(tempUUID.length() < digs){
			int full = digs/tempUUID.length();
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<full;i++){
				sb.append(removeSymbol ? java.util.UUID.randomUUID().toString().replace("-", "") : java.util.UUID.randomUUID().toString());
			}
			int remainder = digs%tempUUID.length();
			sb.append((removeSymbol ? java.util.UUID.randomUUID().toString().replace("-", "") : java.util.UUID.randomUUID().toString()).substring(0,remainder));
			retStr = sb.toString();
		} else {
			retStr = java.util.UUID.randomUUID().toString().substring(0,digs);
		}
		return retStr;
	}

	public static void main(String[] args) {
		System.out.println(getUUID());
		System.out.println(getUUID("Y"));
		System.out.println(getUUID("Y", "40"));
		System.out.println(getUUID("N", "5"));
		System.out.println("");
		System.out.println("");
		System.out.println(generateUUID(50,true));
		System.out.println(generateUUID(49,true));
		System.out.println(generateUUID(50,false));
		System.out.println(generateUUID(49,false));
		
	}
}
