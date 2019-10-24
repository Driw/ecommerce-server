package org.diverproject.ecommerce.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import static org.diverproject.scarlet.util.ScarletUtils.nameOf;

public class ServiceUtils
{
	protected static final Logger LOGGER = LoggerFactory.getLogger(ServiceUtils.class);

	public static <D extends CopyOf<M>, M> Iterable<D> castIterableToDto(Iterable<M> iterableModel, Class<D> dtoClass)
	{
		List<D> dataTransactionObjects = new LinkedList<>();

		for (M model : iterableModel)
		{
			try {

				D dataTransactionObject = dtoClass.getConstructor().newInstance();
				dataTransactionObject.copyOf(model);
				dataTransactionObjects.add(dataTransactionObject);

			} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			    LOGGER.warn("failure on instance a new DTO ({}: {})", nameOf(e), e.getMessage());
			}
		}

		return dataTransactionObjects;
	}
}
