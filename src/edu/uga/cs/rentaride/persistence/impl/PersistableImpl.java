package edu.uga.cs.rentaride.persistence.impl;

import edu.uga.cs.rentaride.persistence.Persistable;
import edu.uga.cs.rentaride.persistence.PersistenceLayer;


public abstract class PersistableImpl
    implements Persistable
{
    private long id;
    private static PersistenceLayer persistencaLayer;
    
    public PersistableImpl()
    {
        this.id = -1;
    }
    
    public PersistableImpl( long id )
    {
        this.id = id;
    }
    
    public long getId()
    {
        return id;
    }

    public void setId( long id )
    {
        this.id = id;
    }

    public boolean isPersistent()
    {
        return id >= 0;
    }

    public static PersistenceLayer getPersistencaLayer()
    {
        return persistencaLayer;
    }

    public static void setPersistenceLayer(PersistenceLayer pLayer)
    {
        persistencaLayer = pLayer;
    }
}
