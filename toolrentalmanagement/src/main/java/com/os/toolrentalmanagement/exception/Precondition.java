package com.os.toolrentalmanagement.exception;

import java.util.Objects;

import org.springframework.lang.Nullable;

public final class Precondition {

	private Precondition() {}

	public static void check(boolean expression, @Nullable BusinessErrorCodes businessErrorCodes,  @Nullable String errorMessageTemplate) {
		if (Boolean.FALSE.equals(expression)) {
			if(Objects.nonNull(errorMessageTemplate)) {
				throw new ClassifiedException(businessErrorCodes, errorMessageTemplate);
			} else {
				throw new ClassifiedException(businessErrorCodes);
			}
		}
	}
}
