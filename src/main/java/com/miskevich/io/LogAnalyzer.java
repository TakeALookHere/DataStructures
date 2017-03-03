package com.miskevich.io;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;

public class LogAnalyzer {

    private String path;
    private LocalDateTime timeFrom;
    private LocalDateTime timeTo;

    public LogAnalyzer(String path, LocalDateTime timeFrom, LocalDateTime timeTo) {
        this.path = path;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }

    public Collection<LogToken> scanLog(String path, LocalDateTime timeFrom, LocalDateTime timeTo) {
        Collection<LogToken> tokenCollection = new ArrayList<>();

        File file = new File(path);
        try(Reader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader)){

        }catch (IOException e){
                throw new RuntimeException(e);
        }



        return tokenCollection;
    }
}
