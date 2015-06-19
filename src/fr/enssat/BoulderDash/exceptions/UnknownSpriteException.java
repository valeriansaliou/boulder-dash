package fr.enssat.BoulderDash.exceptions;


/**
 * UnknownSpriteException
 *
 * Raises an 'UnknownSpriteException' exception.
 * Given the exception message.
 *
 * @author      Valerian Saliou <valerian@valeriansaliou.name>
 * @since       2015-06-19
 */
public class UnknownSpriteException extends Exception {
	public UnknownSpriteException(String message) {
		super(message);
	}
}
