package cmecf.fts;

public class BkCaddDocument   extends BkFtsDocument
{

    public final static String DATA_NAME = "bkcadd";

    /** Creates a new instance of CaddDocument */
    public BkCaddDocument ()
    {
//        super ( DATA_NAME );
    }

    public boolean isBkNdd ()
    {
        return false;
    }

    public boolean isBkCadd ()
    {
        return true;
    }

    public boolean isBkLudd ()
    {
        return false;
    }

	/*
	 * public Object clone () { BkCaddDocument _CD = new BkCaddDocument ();
	 * 
	 * return clone ( _CD ); }
	 */
}
