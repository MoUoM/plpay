package com.javen.util;

import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

public class JsonUtil {
	
	private static ObjectMapper SAFE_MAPPER = new ObjectMapper();
	static {
		SAFE_MAPPER.setVisibility(JsonMethod.FIELD, org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.ANY);
		SAFE_MAPPER.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
		SAFE_MAPPER.configure(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES, false);
		SAFE_MAPPER.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		SAFE_MAPPER.configure(DeserializationConfig.Feature.USE_BIG_DECIMAL_FOR_FLOATS, true);
		SAFE_MAPPER.configure(DeserializationConfig.Feature.USE_BIG_INTEGER_FOR_INTS, true);
		SAFE_MAPPER.setSerializationInclusion(Inclusion.NON_NULL);
	}
	
	public static ObjectMapper createNonNullMapper()
	{
		return SAFE_MAPPER;
	}

	public static ObjectMapper createSafeMapper()
	{
	    return SAFE_MAPPER;
	}
	
	public static <T> T readValueAsObject(String value, Class<T> clazz)
	{
		try
		{
			return SAFE_MAPPER.readValue(value, clazz);
		}
		catch(Exception ex)
		{
			throw new RuntimeException(ex);
		}
	}
	
	public static String writeValueAsString(Object value)
	{
		try
		{
			return SAFE_MAPPER.writeValueAsString(value);
		}
		catch(Exception ex)
		{
			throw new RuntimeException(ex);
		}
	}
}
