package gov.pacts.beans.lookups;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

/**
 * A very simple Bean for containing a lookup type value, such as a code and a description.
 * @author Tim Collins
 */

@XmlType(name = "CodeLookupBean")
public class CodeLookupBean implements Serializable{

	protected String _code;
	protected String _description;

	public CodeLookupBean() {

	}
	public CodeLookupBean(final String code, final String description) {
		_code = code;
		_description = description;
	}

	public String getCode() {
		return _code;
	}

	public void setCode(String code) {
		_code = code;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getOptionText()
	{
		return getDescription();
	}

	public String getOptionValue()
	{
		return getCode();
	}
}
