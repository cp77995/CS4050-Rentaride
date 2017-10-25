package edu.uga.cs.rentaride;

/**
 * This class represents a basic exception to be thrown and handled by the Rent-A-Ride system.
 */
public class RARException 
    extends Exception
{
    private static final long serialVersionUID = 1L;

    /**
     * Create a new RARException object.
     * @param message the message explaining the exception
     */
    public RARException( String message )
    {
      super( message );
    }

    /**
     * Create a new RARException object.
     * @param cause the cause of the exception
     */
    public RARException( Throwable cause )
    {
      super( cause );
    }
}
