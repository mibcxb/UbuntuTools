package org.mibcxb.ubuntu;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class BackgroundConfigurator extends AbsTool {
    private static final String TAG = BackgroundConfigurator.class
            .getSimpleName();

    @Override
    public String getName() {
        return TAG;
    }

    @Override
    public String getShortName() {
        return "-B";
    }

    @Override
    public void execute(String... args) {
        if (args == null || args.length < 2) {
            System.out.println("Arguments Error.");
            return;
        }

        File input = new File(args[0]);
        File output = new File(args[1]);

        File[] files = input.listFiles(mPictureFilter);

        Document document = DocumentHelper.createDocument();
        Element elRoot = document.addElement("background");
        Element elStartTime = elRoot.addElement("starttime");
        Element elYear = elStartTime.addElement("year");
        elYear.setText("2009");
        Element elMonth = elStartTime.addElement("month");
        elMonth.setText("01");
        Element elDay = elStartTime.addElement("day");
        elDay.setText("01");
        Element elHour = elStartTime.addElement("hour");
        elHour.setText("00");
        Element elMinute = elStartTime.addElement("minute");
        elMinute.setText("00");
        Element elSecond = elStartTime.addElement("second");
        elSecond.setText("00");

        for (int i = 0; i < files.length; i++) {
            String path = files[i].getAbsolutePath();
            Element elStatic = elRoot.addElement("static");
            Element elDuration = elStatic.addElement("duration");
            elDuration.setText("1795.0");
            Element elFile = elStatic.addElement("file");
            elFile.setText(path);

            Element elTransition = elRoot.addElement("transition");
            elDuration = elTransition.addElement("duration");
            elDuration.setText("5.0");
            Element elFrom = elTransition.addElement("from");
            elFrom.setText(path);
            Element elTo = elTransition.addElement("to");
            String next = files[(i + 1) % files.length].getAbsolutePath();
            elTo.setText(next);
        }

        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer = null;
        try {
            writer = new XMLWriter(new FileWriter(output), format);
            writer.write(document);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private FilenameFilter mPictureFilter = new FilenameFilter() {

        @Override
        public boolean accept(File dir, String name) {
            File file = new File(dir, name);
            if (file.isDirectory()) {
                return false;
            }
            return StringUtils.endsWithAny(name, ".jpg", ".png");
        }

    };
}
