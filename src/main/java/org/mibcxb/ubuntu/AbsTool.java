/**
 * 
 */
package org.mibcxb.ubuntu;

/**
 * @author mibcxb
 *
 */
public abstract class AbsTool {

    public abstract String getName();

    public abstract String getShortName();

    public abstract void execute(String... args);
}
