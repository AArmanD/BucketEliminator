package edu.kit.informatik;

/**
 * This Class is the class which is thrown by wrong logical inputs
 *
 * @author armand
 * @version 1.0
 */
public class InputException extends Exception {

    /**
     * This is the constructor of the Exception which makes a super call to Exception
     *
     * @param message to submit to the exception
     */
    public InputException(String message) {
        super(message);
    }
}