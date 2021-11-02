package utils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import org.apache.commons.text.StringSubstitutor;

public class TemplateBuilder {

	public static String resolveTemplate(String path, Map<String, Object> valuesMap) throws IOException, FileNotFoundException {
		String template = readTextFromFile(path, Charset.defaultCharset());
		StringSubstitutor substitution = new StringSubstitutor(valuesMap, "{[", "]}");
		String resolvedTemplate = substitution.replace(template);
		return resolvedTemplate;
	}

	public static String readTextFromFile(String path, Charset encoding) throws IOException, FileNotFoundException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
}
