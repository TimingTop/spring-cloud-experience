package com.timing.experience.os.p8;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class OperatingSystem {

    private int  MAX_SECTOR_NUM = 18;

    private Floppy floppyDisk = new Floppy();

    private void writeFileToFloppy(String fileName, boolean bootable, int cylinder,int beginSec) {
        File file = new File(fileName);
        InputStream in = null;

        try {
            in = new FileInputStream(file);
            byte[] buf = new byte[512];
            if (bootable) {
                buf[510] = 0x55;
                buf[511] = (byte) 0xaa;
            }

            while (in.read(buf) > 0) {
                // 磁盘 0号 磁头
                floppyDisk.writeFloppy(Floppy.MAGNETIC_HEAD.MAGNETIC_HEAD_0, cylinder, beginSec, buf);
                beginSec++;

                if (beginSec > MAX_SECTOR_NUM) {
                    beginSec = 1;
                    cylinder++;
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public OperatingSystem(String s) {
        writeFileToFloppy(s, true, 0, 1);
    }

    public void makeFllopy()   {

        writeFileToFloppy("src/main/resources/p8/kernel.bat", false, 1, 2);

        floppyDisk.makeFloppy("src/main/resources/p8/system.img");
    }

    public static void main(String[] args) {
        OperatingSystem op = new OperatingSystem("src/main/resources/p8/boot.bat");
        op.makeFllopy();
    }

}
