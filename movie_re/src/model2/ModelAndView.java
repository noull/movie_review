package model2;

import java.util.Hashtable;
import java.util.Map;

public class ModelAndView {
	private Map<String, Object> map = null;
	private String path = null;

	public ModelAndView(String view) {
		this.path = view;
	}

	public void addObject(String key, Object value) {
		if (map == null) {
			map = new Hashtable<String, Object>();
		}
		map.put(key, value);
	}

	public String getPath() {
		return path;
	}

	public Map<String, Object> getMap() {
		return map;
	}
}
