/*
 * Copyright (C) 2011 Thomas Akehurst
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.tomakehurst.wiremock.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.System.err;
import static java.lang.System.out;

public class ConsoleNotifier implements Notifier {

    private class ConsoleColors {
        public static final String ANSI_RESET = "\u001B[0m";
        public static final String ANSI_BLACK = "\u001B[30m";
        public static final String ANSI_RED = "\u001B[31m";
        public static final String ANSI_GREEN = "\u001B[32m";
        public static final String ANSI_YELLOW = "\u001B[33m";
        public static final String ANSI_BLUE = "\u001B[34m";
        public static final String ANSI_PURPLE = "\u001B[35m";
        public static final String ANSI_CYAN = "\u001B[36m";
        public static final String ANSI_WHITE = "\u001B[37m";
    }

    private final boolean verbose;

    public ConsoleNotifier(boolean verbose) {
        this.verbose = verbose;
        if (verbose) {
            info("Verbose logging enabled");
        }
    }

    @Override
    public void info(String message) {
        if (verbose) {
            out.println(formatMessage(message));
        }
    }

    @Override
    public void warn(String message) {
        if (verbose) {
            err.println(ConsoleColors.ANSI_BLUE + formatMessage(message) + ConsoleColors.ANSI_RESET);
        }
    }

    @Override
    public void debug(String message) {
        if (verbose) {
            err.println(ConsoleColors.ANSI_GREEN + formatMessage(message) + ConsoleColors.ANSI_RESET);
        }
    }

    @Override
    public void error(String message) {
        err.println(ConsoleColors.ANSI_RED + formatMessage(message) + ConsoleColors.ANSI_RESET);
    }

    @Override
    public void error(String message, Throwable t) {
        err.println(formatMessage(message));
        t.printStackTrace(err);
    }

    private static String formatMessage(String message) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        String date = df.format(new Date());
        return String.format("%s %s", date, message);
    }
}
