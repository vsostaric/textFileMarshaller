package tfm.marshaller;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import tfm.file.FieldName;

public class TextFileMarshaller {

	// ************************* FIELDS

	private String delimitor;

	private String lineEnd;

	private String header_row_mark;

	public TextFileMarshaller(String delimitor, String lineEnd, String header_row_mark) {
		this.delimitor = delimitor;
		this.header_row_mark = header_row_mark;
		this.lineEnd = lineEnd;
	}

	// ******************** PUBLIC METHODS

	public Set<?> marshall(String fileContent, Class<?> targetClass) {

		Map<String, Integer> fields = null;
		Set<Object> dataRead = new HashSet<>();

		String[] rows = fileContent.split(lineEnd);

		for (String row : rows) {

			String[] data = row.split(delimitor);

			if (getHeader_row_mark().equals(data[0])) {

				Class<?> obj = targetClass;
				Field[] fieldsInClass = obj.getDeclaredFields();
				fields = new HashMap<String, Integer>();

				for (Field field : fieldsInClass) {
					String fieldName = field.getAnnotation(FieldName.class).name();
					for (int i = 1; i < data.length; i++) {
						if(data[i].trim().equals(fieldName)) {
							fields.put(fieldName, i);
							continue;
						}
					}
				}

			} else {

				Class<?> obj = targetClass;
				Field[] fieldsInClass = obj.getDeclaredFields();

				Object dataFromFile = null;

				try {
					dataFromFile = targetClass.newInstance();
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (dataFromFile == null) {
					continue;
				}

				for (Field field : fieldsInClass) {
					String fieldName = field.getAnnotation(FieldName.class).name();
					String setterName = "set" + capitalize(field.getName());
					String valueToSet = data[fields.get(fieldName)];
					if (valueToSet != null && valueToSet.equals("")) {
						valueToSet = valueToSet.trim();
					}
					try {
						dataFromFile.getClass().getMethod(setterName, String.class).invoke(dataFromFile, valueToSet);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				dataRead.add(dataFromFile);

			}

		}

		return dataRead;

	}

	// ******************** Utility methods

	private String capitalize(String name) {

		if (name == null || name.length() == 0) {
			return "";
		}

		String first = name.substring(0, 1).toUpperCase();

		return (name.length() == 1) ? first : first + name.substring(1, name.length());

	}

	// ******************** GETTERS AND SETTERS

	public void setDelimitor(String delimitor) {
		this.delimitor = delimitor;
	}

	public String getHeader_row_mark() {
		return header_row_mark;
	}

	public void setHeader_row_mark(String header_row_mark) {
		this.header_row_mark = header_row_mark;
	}

}
