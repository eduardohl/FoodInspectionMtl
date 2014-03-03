package com.sakura.fim;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ConverterUtil {
    /* Constants */
    public static final SimpleDateFormat FIM_FILE_FORMAT = new SimpleDateFormat("dd MMMM yyyy", Locale.CANADA_FRENCH);
    public static final SimpleDateFormat VIEW_FORM_FORMAT = new SimpleDateFormat("yyyy-mm-dd");
}
