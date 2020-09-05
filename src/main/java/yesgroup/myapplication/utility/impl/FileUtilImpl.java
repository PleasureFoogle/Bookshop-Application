package yesgroup.myapplication.utility.impl;

import yesgroup.myapplication.utility.FileUtil;

import java.io.*;
import java.util.Objects;

public class FileUtilImpl implements FileUtil {
    @Override
    public String readFile(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();

        File file = new File(Objects.requireNonNull(FileUtilImpl.class.getClassLoader().getResource(filePath))
                .getPath());
        BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        String line;

        while ((line = bf.readLine()) != null){
            sb.append(line).append(System.lineSeparator());
        }

        return sb.toString();
    }

}
