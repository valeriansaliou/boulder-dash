package fr.enssat.BoulderDash.exceptions;


/**
 * ModelNotReadyException
 *
 * Raises an 'ModelNotReadyException' exception.
 * Given the exception message.
 *
 * @author      Valerian Saliou <valerian@valeriansaliou.name>
 * @since       2015-06-23
 */
public class ModelNotReadyException extends Exception {
    /**
     * Class constructor
     *
     * @param  message  Exception backtrace message
     */
    public ModelNotReadyException(String message) {
        super(message);
    }
}
