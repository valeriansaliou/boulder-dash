package fr.enssat.BoulderDash.exceptions;


/**
 * UnknownModelException
 *
 * Raises an 'UnknownSpriteException' exception.
 * Given the exception message.
 *
 * @author      Valerian Saliou <valerian@valeriansaliou.name>
 * @since       2015-06-19
 */
public class UnknownModelException extends Exception {
	/**
	 * Class constructor
	 *
	 * @param  message  Exception backtrace message
	 */
	public UnknownModelException(String message) {
		super(message);
	}
}
