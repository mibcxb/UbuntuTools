package org.mibcxb;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.mibcxb.ubuntu.AbsTool;
import org.mibcxb.ubuntu.BackgroundConfigurator;

/**
 * 
 * @author mibcxb
 *
 */
public class UbuntuTools {

    public static void main(String[] args) {
        if (args == null || args.length < 1) {
            System.out.println("Hello world!");
            return;
        }

        AbsTool tool = chooseTool(args[0]);
        if (args.length > 1) {
            tool.execute(Arrays.copyOfRange(args, 1, args.length));
        } else {
            tool.execute();
        }
    }

    private static AbsTool chooseTool(String name) {
        if (StringUtils.equals(name, "-B")) {
            return new BackgroundConfigurator();
        }
        return null;
    }
}
