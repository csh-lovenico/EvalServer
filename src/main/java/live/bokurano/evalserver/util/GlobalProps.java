package live.bokurano.evalserver.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GlobalProps {
	private static Map<String, Object> props = new ConcurrentHashMap<>();

	public static void put(String key, Object value) {
		props.put(key, value);
	}

	public static Object get(String key) {
		return props.get(key);
	}
}
