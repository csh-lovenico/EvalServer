package live.bokurano.evalserver.util;

import java.util.HashMap;
import java.util.Map;

public class GlobalProps {
	private static Map<String, Object> props = new HashMap<>();

	public static void put(String key, Object value) {
		props.put(key, value);
	}

	public static Object get(String key) {
		return props.get(key);
	}
}
