package com.timing.experience.os.p3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class OperatingSystem {

    private Floppy floppyDisk = new Floppy();

    private void writeFileToFloppy(String fileName) {
        File file = new File(fileName);
        InputStream in = null;

        try {
            in = new FileInputStream(file);
            byte[] buf = new byte[512];
            buf[510] = 0x55;
            buf[511] = (byte) 0xaa;
            if (in.read(buf) != -1) {
                //将内核读入到磁盘第0面，第0柱面，第1个扇区
                floppyDisk.writeFloppy(Floppy.MAGNETIC_HEAD.MAGNETIC_HEAD_0, 0, 1, buf);
            }
        } catch(IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public OperatingSystem(String s) {
        writeFileToFloppy(s);
    }

    public void makeFllopy()   {

        String s = "This is a text from cylinder 1 and sector 2";
        floppyDisk.writeFloppy(Floppy.MAGNETIC_HEAD.MAGNETIC_HEAD_0, 1, 2, s.getBytes());

        floppyDisk.makeFloppy("src/main/resources/p3/system.img");
    }

    public static void main(String[] args) {
        OperatingSystem op = new OperatingSystem("src/main/resources/p3/boot.bat");
        op.makeFllopy();
    }

}
