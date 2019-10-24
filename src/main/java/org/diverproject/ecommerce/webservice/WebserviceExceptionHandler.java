package org.diverproject.ecommerce.webservice;

import static org.diverproject.ecommerce.Constants.WS_MODEL_CONSTRAINT;
import static org.diverproject.ecommerce.Constants.WS_MODEL_CONSTRAINT_EXCEPTION;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.diverproject.ecommerce.annotation.ConstraintErrorCode;
import org.diverproject.ecommerce.language.EcommerceLanguage;
import org.hibernate.validator.internal.constraintvalidators.bv.NotBlankValidator;
import org.hibernate.validator.internal.constraintvalidators.bv.PatternValidator;
import org.hibernate.validator.internal.constraintvalidators.bv.size.SizeValidatorForArray;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import org.diverproject.scarlet.ScarletRuntimeException;

import org.diverproject.ecommerce.EcommerceRuntimeException;

@ControllerAdvice
public class WebserviceExceptionHandler extends ResponseEntityExceptionHandler
{
	@ExceptionHandler(value = { EcommerceRuntimeException.class })
	protected ResponseEntity<WebserviceResponse<String[]>> handleEcoomerce(ScarletRuntimeException exception, WebRequest request)
	{
		String stackTraces[] = ExceptionUtils.getStackFrames(exception);

		WebserviceResponse<String[]> webserviceResponse = new WebserviceResponse<>();
		webserviceResponse.setCode(exception.getCode());
		webserviceResponse.setDuration(0L);
		webserviceResponse.setMessage(exception.getMessage());
		webserviceResponse.setResult(stackTraces);

		return webserviceResponse.reponseEntityOk();
	}

	@ExceptionHandler(value = { ConstraintViolationException.class } )
	protected ResponseEntity<WebserviceResponse<Object>> handleConstraints(ConstraintViolationException exception, WebRequest request)
	{
		Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();

		WebserviceResponse<Object> webserviceResponse = new WebserviceResponse<>();
		webserviceResponse.setCode(WS_MODEL_CONSTRAINT);
		webserviceResponse.setDuration(0L);
		webserviceResponse.setMessage(EcommerceLanguage.HANDLE_CONSTRAINTS);

		List<WebserviceConstraint> webserviceConstraints = new LinkedList<>();
		{
			for (ConstraintViolation<?> constraintViolation : constraintViolations)
			{
				WebserviceConstraint webserviceConstraint = this.parseWebserviceConstraint(constraintViolation);
				webserviceConstraints.add(webserviceConstraint);
			}
		}
		webserviceResponse.setResult(webserviceConstraints.toArray());

		return webserviceResponse.reponseEntityNotOk();
	}

	private WebserviceConstraint parseWebserviceConstraint(ConstraintViolation<?> constraintViolation)
	{
		WebserviceConstraint webserviceConstraint = new WebserviceConstraint();
		webserviceConstraint.setCode(WS_MODEL_CONSTRAINT);
		webserviceConstraint.setField(constraintViolation.getPropertyPath().toString());
		webserviceConstraint.setViolation(constraintViolation.getMessage());

		try {

			String fieldName = constraintViolation.getPropertyPath().toString();
			Field field = constraintViolation.getRootBeanClass().getDeclaredField(fieldName);

			if (!field.isAnnotationPresent(ConstraintErrorCode.class))
				return webserviceConstraint;

			ConstraintErrorCode constraintErrorCode = field.getAnnotationsByType(ConstraintErrorCode.class)[0];

			if (constraintViolation.getConstraintDescriptor().getConstraintValidatorClasses().contains(NotBlankValidator.class))
				webserviceConstraint.setCode(constraintErrorCode.notBlankCode());

			else if (constraintViolation.getConstraintDescriptor().getConstraintValidatorClasses().contains(SizeValidatorForArray.class))
				webserviceConstraint.setCode(constraintErrorCode.sizeCode());

			else if (constraintViolation.getConstraintDescriptor().getConstraintValidatorClasses().contains(PatternValidator.class))
				webserviceConstraint.setCode(constraintErrorCode.patternCode());

		} catch (NoSuchFieldException | SecurityException e) {
			webserviceConstraint.setCode(WS_MODEL_CONSTRAINT_EXCEPTION);
			webserviceConstraint.setViolation(e.getMessage());
		}

		return webserviceConstraint;
	}
}
