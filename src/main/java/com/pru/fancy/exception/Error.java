package com.pru.fancy.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.io.Serializable;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;


/**
 * Error
 */
public class Error implements Serializable{
	@JsonProperty("code")
	private String code = null;

	/**
	 * Gets or Sets severity
	 */
	public enum SeverityEnum {
		ERROR("error"),

		WARNING("warning"),

		INFO("Info");

		private String value;

		SeverityEnum(String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static SeverityEnum fromValue(String text) {
			for (SeverityEnum b : SeverityEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("severity")
	private SeverityEnum severity = null;

	@JsonProperty("description")
	private String description = null;

	@JsonProperty("additionalDetails")
	private List<AdditionalDetails> additionalDetails = null;

	public Error code(String code) {
		this.code = code;
		return this;
	}

	/**
	 * Get code
	 * 
	 * @return code
	 **/
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Error severity(SeverityEnum severity) {
		this.severity = severity;
		return this;
	}

	/**
	 * Get severity
	 * 
	 * @return severity
	 **/
	public SeverityEnum getSeverity() {
		return severity;
	}

	public void setSeverity(SeverityEnum severity) {
		this.severity = severity;
	}

	public Error description(String description) {
		this.description = description;
		return this;
	}

	/**
	 * Get description
	 * 
	 * @return description
	 **/
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Error additionalDetails(List<AdditionalDetails> additionalDetails) {
		this.additionalDetails = additionalDetails;
		return this;
	}

	public Error addAdditionalDetailsItem(AdditionalDetails additionalDetailsItem) {
		if (this.additionalDetails == null) {
			this.additionalDetails = new ArrayList<>();
		}
		this.additionalDetails.add(additionalDetailsItem);
		return this;
	}

	/**
	 * Get additionalDetails
	 * 
	 * @return additionalDetails
	 **/
	@Valid

	public List<AdditionalDetails> getAdditionalDetails() {
		return additionalDetails;
	}

	public void setAdditionalDetails(List<AdditionalDetails> additionalDetails) {
		this.additionalDetails = additionalDetails;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Error error = (Error) o;
		return Objects.equals(this.code, error.code) && Objects.equals(this.severity, error.severity)
				&& Objects.equals(this.description, error.description)
				&& Objects.equals(this.additionalDetails, error.additionalDetails);
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, severity, description, additionalDetails);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Error {\n");

		sb.append("    code: ").append(toIndentedString(code)).append("\n");
		sb.append("    severity: ").append(toIndentedString(severity)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    additionalDetails: ").append(toIndentedString(additionalDetails)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
