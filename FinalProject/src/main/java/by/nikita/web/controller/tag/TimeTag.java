package by.nikita.web.controller.tag;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTag extends SimpleTagSupport {
    @Override
    public void doTag() throws IOException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("hh-mm");
        getJspContext().getOut().print(formatter.format(date));
    }

}
