/*
 *  Copyright (c) 1997-2001 Andrew R. Miller.  All rights reserved.
 */
package amiller.util;

import java.util.Enumeration;
import java.util.StringTokenizer;


/**
 *	Contains string utility methods.
 *
 *  @author  Andrew R. Miller
 *  @author  Code reviewed by NOBODY on NEVER
 *  @version $Revision: $  $Modtime: $
 */
public class StringUtil
    extends Object
{
    //
    // Static data.
    //

    /** The source revision. */
    public static final String REVISION = "$Revision: $";


    //
    // Static methods.
    //

	/**
	 *  Returns a copy of the source string where the first letter
	 *  of each work is capitalized.
	 */
	public static String capitalize(String src) {
		StringBuffer buffer = new StringBuffer();

		for (Enumeration tokens = new StringTokenizer(src, " "); tokens.hasMoreElements(); ) {
			String token = (String) tokens.nextElement();
			buffer.append(token.substring(0, 1).toUpperCase());
			buffer.append(token.substring(1).toLowerCase());
			if (tokens.hasMoreElements()) {
				buffer.append(" ");
			}
		}

		return buffer.toString();
	}

	/**
	 *	Return a copy of the specified string in which all occurences
     *	of one substring have been replaced with another.
	 *
	 *	@param		source     	source string.
	 *	@param		oldStr    	old substring to be replaced.
 	 *	@param		newStr 	    new substring that replaces the old substring.
 	 *
 	 *  @return     String      the result string.
	 */
	public static String replaceString(String source, String oldStr, String newStr)
	{
		if (source == null) {
			return source;
		}
		else {
    		String tmp = source;
    		StringBuffer result = new StringBuffer();

        	int lastPos = 0;
    		for (int pos = source.indexOf(oldStr); pos >= 0; pos = source.indexOf(oldStr, lastPos)) {
    			result.append(source.substring(lastPos, pos) + newStr);
    			lastPos = pos + oldStr.length();
    		}

        	result.append(source.substring(lastPos, source.length()));

        	return result.toString();
		}
	}
	
	/**
	 *  Give a instance variable name it returns a suitable
	 *  column heading capitalizing the first letter and placing 
	 *  a space before all caps
	 */
	public static String displayName(String src) {
	    
		StringBuffer buffer = new StringBuffer();
		char c;
		
		buffer.append(src.substring(0, 1).toUpperCase());
		
		if(src.length() > 1)
		{
		    for(int i = 1; i < src.length(); i++)
		    {
		        c = src.charAt(i);
		        if(Character.isUpperCase(c))
		            buffer.append(" " + c);
		        else
		            buffer.append("" + c);
		    }        
		}

		return buffer.toString();
	}

    //
    // Instance data.
    //


    //
    // Constructors.
    //
    
	/**
	 *	Default constructor.
	 */
	private StringUtil() { }


    //
    // Instance methods.
    //

}
