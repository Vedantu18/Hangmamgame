package com.dang.man;

import java.awt.*;

public class Constants {
    public static final Dimension FRAME_SIZE = new Dimension(540,1000);
    public static final  String DATA_PATH = "/data.txt";
    public static final String IMAGE_PATH = "/1.png";

    public static final Color PRIMARY_COLOR = Color.decode("#142120");
    public static final Color SECONDARY_COLOR = Color.decode("#FCA311");
    public static final Color BG_COLOR = Color.decode("#101820");

    public static final Dimension BUTTON_PANEL_SIZE = new Dimension(FRAME_SIZE.width, (int) (FRAME_SIZE.height*0.42));
    public static final Dimension RESULT_SIZE = new Dimension((FRAME_SIZE.width/2),(FRAME_SIZE.height/6));
}
